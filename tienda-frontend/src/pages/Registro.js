import React, { useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Registro.css";

function Registro() {
  const [formData, setFormData] = useState({
    nombre: "",
    apellidos: "",
    email: "",
    contrasena: "",
    confirmarContrasena: "",
    direccion: "",
    telefono: "",
  });

  const [status, setStatus] = useState(""); // Mensajes de estado

  // 🔹 Actualiza el estado con los inputs
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // 🔹 Envía los datos al backend
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validación de contraseñas
    if (formData.contrasena !== formData.confirmarContrasena) {
      setStatus("Las contraseñas no coinciden.");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/usuarios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre: formData.nombre,
          apellidos: formData.apellidos,
          email: formData.email,
          contrasena: formData.contrasena,
          direccion: formData.direccion,
          telefono: formData.telefono,
        }),
      });

      const success = await response.json();

      if (success) {
        setStatus("Registro exitoso. Ya puedes iniciar sesión.");
        setFormData({
          nombre: "",
          apellidos: "",
          email: "",
          contrasena: "",
          confirmarContrasena: "",
          direccion: "",
          telefono: "",
        });
        localStorage.setItem("email", formData.email);
        window.location.href = "/"; // Redirige al home
      } else {
        setStatus("Error al registrar usuario.");
      }
    } catch (error) {
      console.error("Error en el registro:", error);
      setStatus("Error de conexión con el servidor.");
    }
  };

  const contrasenasNoCoinciden =
    formData.confirmarContrasena && formData.contrasena !== formData.confirmarContrasena;

  return (
    <div className="registro-container">
      <Header />

      <main className="registro-main">
        <h2>Crear Cuenta</h2>
        <form className="registro-form" onSubmit={handleSubmit}>
          <input
            type="text"
            name="nombre"
            placeholder="Nombre"
            value={formData.nombre}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="apellidos"
            placeholder="Apellidos"
            value={formData.apellidos}
            onChange={handleChange}
            required
          />
          <input
            type="email"
            name="email"
            placeholder="Correo electrónico"
            value={formData.email}
            onChange={handleChange}
            required
          />
          <input
            type="password"
            name="contrasena"
            placeholder="Contraseña"
            value={formData.contrasena}
            onChange={handleChange}
            style={{ borderColor: contrasenasNoCoinciden ? "red" : "#b2dfdb" }}
            required
          />
          <input
            type="password"
            name="confirmarContrasena"
            placeholder="Repetir contraseña"
            value={formData.confirmarContrasena}
            onChange={handleChange}
            style={{ borderColor: contrasenasNoCoinciden ? "red" : "#b2dfdb" }}
            required
          />
          {contrasenasNoCoinciden && (
            <p className="status" style={{ color: "red", fontSize: "0.9rem", marginTop: "0.2rem" }}>
              Las contraseñas no coinciden
            </p>
          )}
          <input
            type="text"
            name="direccion"
            placeholder="Dirección"
            value={formData.direccion}
            onChange={handleChange}
          />
          <input
            type="text"
            name="telefono"
            placeholder="Teléfono"
            value={formData.telefono}
            onChange={handleChange}
          />

          <button type="submit" disabled={contrasenasNoCoinciden}>
            Registrarse
          </button>
        </form>

        {status && <p className="status">{status}</p>}

        <p className="register-link">
          ¿Ya tienes cuenta? <a href="/login">Iniciar sesión</a>
        </p>
      </main>

      <Footer />
    </div>
  );
}

export default Registro;
