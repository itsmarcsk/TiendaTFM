import React, { useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/CambiarContrasena.css";

function CambiarContrasena() {
  const [contrasena, setContrasena] = useState("");
  const [status, setStatus] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const email = localStorage.getItem("email");

    fetch(`http://localhost:8080/usuarios/${email}/contrasena`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: contrasena,
    })
      .then((res) => {
        if (!res.ok) throw new Error("Error al cambiar contraseña");
        return res.text();
      })
      .then(() => {
        setStatus("Contraseña actualizada ✅");
        setTimeout(() => {
          window.location.href = "/usuario";
        }, 2000);
      })
      .catch((error) => {
        console.error(error);
        setStatus("Error al cambiar la contraseña.");
      });
  };

  return (
    <div className="usuario-container">
      <Header />
      <main className="usuario-main">
        <h2>Cambiar Contraseña</h2>
        {status && <p className="status">{status}</p>}
        <form onSubmit={handleSubmit}>
          <input
            type="password"
            placeholder="Nueva contraseña"
            value={contrasena}
            onChange={(e) => setContrasena(e.target.value)}
            required
          />
          <button type="submit">Actualizar Contraseña</button>
        </form>
      </main>
      <Footer />
    </div>
  );
}

export default CambiarContrasena;
