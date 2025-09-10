import React, { useEffect, useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Usuario.css";

// Firebase Firestore
import { collection, query, where, getDocs, deleteDoc, doc } from "firebase/firestore";
import { db } from "../firebase";

function Usuario() {
  const [usuario, setUsuario] = useState(null);
  const [status, setStatus] = useState("");

  // 🔹 Cargar datos del usuario al montar el componente
  useEffect(() => {
    const email = localStorage.getItem("email");
    if (email) {
      fetch(`http://localhost:8080/usuarios/email/${email}`)
        .then((res) => {
          if (!res.ok) throw new Error("Usuario no encontrado");
          return res.json();
        })
        .then((data) => setUsuario(data))
        .catch((error) => {
          console.error(error);
          setStatus("Error al cargar los datos del usuario.");
        });
    } else {
      setStatus("No has iniciado sesión.");
    }
  }, []);

  // 🔹 Ocultar status automáticamente después de 4 segundos
  useEffect(() => {
    if (status) {
      const timer = setTimeout(() => setStatus(""), 4000);
      return () => clearTimeout(timer);
    }
  }, [status]);

  // 🔹 Cerrar sesión
  const handleLogout = () => {
    localStorage.removeItem("email");
    window.location.href = "/login";
  };

  // 🔹 Eliminar cuenta
  const handleDelete = async () => {
    if (
      window.confirm(
        "¿Seguro que quieres eliminar tu cuenta? Esta acción no se puede deshacer."
      )
    ) {
      const email = localStorage.getItem("email");

      try {
        // Paso 1: Borrar carrito de Firestore
        const carritoQuery = query(collection(db, "carritos"), where("email", "==", email));
        const querySnapshot = await getDocs(carritoQuery);
        for (const carritoDoc of querySnapshot.docs) {
          await deleteDoc(doc(db, "carritos", carritoDoc.id));
        }

        // Paso 2: Borrar usuario en backend
        const res = await fetch(`http://localhost:8080/usuarios/${email}`, { method: "DELETE" });
        if (!res.ok) throw new Error("Error al eliminar la cuenta");

        // Paso 3: Limpiar sesión y redirigir
        localStorage.removeItem("email");
        window.location.href = "/login";
      } catch (error) {
        console.error(error);
        setStatus("Error al eliminar la cuenta.");
      }
    }
  };

  // 🔹 Cambiar contraseña
  const handleChangePassword = () => {
    window.location.href = "/cambiar-contrasena";
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
            <button onClick={handleChangePassword}>Cambiar Contraseña</button>
            <button onClick={handleDelete} className="danger">Eliminar Cuenta</button>
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
