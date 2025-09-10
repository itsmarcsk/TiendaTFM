import React, { useState } from "react";
import Header from "../components/Header"; // Cabecera de la página
import Footer from "../components/Footer"; // Pie de página
import "../styles/CambiarContrasena.css"; // Estilos específicos del componente

function CambiarContrasena() {
  // -------------------------------
  // Estados locales
  // -------------------------------
  const [contrasena, setContrasena] = useState(""); // Nueva contraseña que ingresa el usuario
  const [status, setStatus] = useState(""); // Mensaje de estado (éxito o error)

  // -------------------------------
  // Función para manejar el envío del formulario
  // -------------------------------
  const handleSubmit = (e) => {
    e.preventDefault(); // Evita que la página se recargue al enviar el formulario
    const email = localStorage.getItem("email"); // Obtiene el email del usuario desde localStorage

    // Llamada a la API para actualizar la contraseña
    fetch(`http://localhost:8080/usuarios/${email}/contrasena`, {
      method: "PUT", // Método HTTP PUT para actualización
      headers: { "Content-Type": "application/json" }, // Indica que se envía JSON
      body: contrasena, // Contraseña nueva (texto plano)
    })
      .then((res) => {
        if (!res.ok) throw new Error("Error al cambiar contraseña"); // Manejo de errores HTTP
        return res.text(); // Lee la respuesta
      })
      .then(() => {
        setStatus("Contraseña actualizada ✅"); // Mensaje de éxito
        // Redirige al usuario a su perfil después de 2 segundos
        setTimeout(() => {
          window.location.href = "/usuario";
        }, 2000);
      })
      .catch((error) => {
        console.error(error);
        setStatus("Error al cambiar la contraseña."); // Mensaje de error
      });
  };

  // -------------------------------
  // Renderizado del componente
  // -------------------------------
  return (
    <div className="usuario-container">
      <Header />
      <main className="usuario-main">
        <h2>Cambiar Contraseña</h2>
        {status && <p className="status">{status}</p>} {/* Muestra el estado si existe */}
        <form onSubmit={handleSubmit}>
          <input
            type="password"
            placeholder="Nueva contraseña"
            value={contrasena} // Controla el valor del input
            onChange={(e) => setContrasena(e.target.value)} // Actualiza el estado al escribir
            required // Campo obligatorio
          />
          <button type="submit">Actualizar Contraseña</button>
        </form>
      </main>
      <Footer />
    </div>
  );
}

export default CambiarContrasena;
