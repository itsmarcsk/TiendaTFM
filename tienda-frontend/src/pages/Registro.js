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

  const [status, setStatus] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Comprobar contraseñas iguales
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

      if (response.ok) {
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
      } else {
        setStatus("Error al registrar usuario.");
      }
    } catch (error) {
      console.error("Error en el registro:", error);
      setStatus("Error de conexión con el servidor.");
    }
  };

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
            required
          />
          <input
            type="password"
            name="confirmarContrasena"
            placeholder="Repetir contraseña"
            value={formData.confirmarContrasena}
            onChange={handleChange}
            required
          />
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

          <button type="submit">Registrarse</button>
        </form>

        {status && <p className="status">{status}</p>}

        <p className="register-link">
          <a href="/login">Iniciar sesión</a>
        </p>
      </main>

      <Footer />
    </div>
  );
}

export default Registro;
