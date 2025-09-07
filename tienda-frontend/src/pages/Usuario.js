// src/pages/Usuario.js
import React, { useEffect, useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Usuario.css";

function Usuario() {
  const [usuario, setUsuario] = useState(null);
  const [status, setStatus] = useState("");

  useEffect(() => {
    const email = localStorage.getItem("email");
    if (email) {
      fetch(`http://localhost:8080/usuarios/email/${email}`)
        .then((res) => {
          if (!res.ok) throw new Error("Usuario no encontrado");
          return res.json();
        })
        .then((data) => {
          setUsuario(data);
        })
        .catch((error) => {
          console.error(error);
          setStatus("Error al cargar los datos del usuario.");
        });
    } else {
      setStatus("No has iniciado sesión.");
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("email");
    window.location.href = "/login";
  };

  return (
    <div className="usuario-container">
      <Header />

      <main className="usuario-main">
        <h2>Perfil de Usuario</h2>

        {status && <p className="status">{status}</p>}

        {usuario ? (
          <div className="usuario-info">
            <p><strong>Nombre:</strong> {usuario.nombre} {usuario.apellidos}</p>
            <p><strong>Email:</strong> {usuario.email}</p>
            <p><strong>Dirección:</strong> {usuario.direccion}</p>
            <p><strong>Teléfono:</strong> {usuario.telefono}</p>

            <button onClick={handleLogout}>Cerrar Sesión</button>
          </div>
        ) : (
          !status && <p>Cargando información...</p>
        )}
      </main>

      <Footer />
    </div>
  );
}

export default Usuario;
