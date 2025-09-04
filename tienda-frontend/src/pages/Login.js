// src/pages/Login.js
import React, { useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Login.css";

function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [status, setStatus] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await fetch(`http://localhost:8080/api/usuarios/email/${formData.email}`);
      if (res.ok) {
        const usuario = await res.json();

        if (usuario && usuario.contrasena === formData.password) {
          // ✅ Login correcto
          localStorage.setItem("email", usuario.email);
          setStatus("Inicio de sesión exitoso.");
          window.location.href = "/"; // redirige al home
        } else {
          setStatus("Contraseña incorrecta.");
        }
      } else {
        setStatus("Usuario no encontrado.");
      }
    } catch (error) {
      console.error("Error en login:", error);
      setStatus("Error al iniciar sesión. Intenta de nuevo.");
    }
  };

  return (
    <div className="login-container">
      <Header />

      <main className="login-main">
        <h2>Iniciar Sesión</h2>
        <form className="login-form" onSubmit={handleSubmit}>
          <label htmlFor="email">Correo electrónico</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Tu correo"
            required
          />

          <label htmlFor="password">Contraseña</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            placeholder="Tu contraseña"
            required
          />

          <button type="submit">Ingresar</button>
        </form>

        {status && <p className="status">{status}</p>}

        <p className="register-link">
          ¿No tienes cuenta? <a href="/registro">Crear cuenta</a>
        </p>
      </main>

      <Footer />
    </div>
  );
}

export default Login;
