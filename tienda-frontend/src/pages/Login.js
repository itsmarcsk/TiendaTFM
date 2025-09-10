import React, { useState } from "react"; // Importa React y el hook useState
import Header from "../components/Header"; // Componente de cabecera
import Footer from "../components/Footer"; // Componente de pie de página
import "../styles/Login.css"; // Estilos específicos de la página Login

function Login() {
  // Estado para guardar los datos del formulario y mensajes de estado
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [status, setStatus] = useState("");

  // Actualiza el estado cuando el usuario escribe en los inputs
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // Envía los datos del formulario al backend
  const handleSubmit = async (e) => {
    e.preventDefault(); // Evita que el formulario recargue la página

    try {
      // Llamada al endpoint de login
      const res = await fetch(
        `http://localhost:8080/usuarios/login/${encodeURIComponent(formData.email)}/${encodeURIComponent(formData.password)}`
      );

      const data = await res.json();

      if (data.success) {
        // Si el login es correcto, guarda el email en localStorage
        localStorage.setItem("email", formData.email);
        setStatus("Inicio de sesión exitoso.");
        window.location.href = "/"; // Redirige al Home
      } else {
        setStatus("Email o contraseña incorrectos.");
      }
    } catch (error) {
      console.error("Error en login:", error);
      setStatus("Error al iniciar sesión. Intenta de nuevo.");
    }
  };

  return (
    <div className="login-container">
      {/* Header */}
      <Header />

      <main className="login-main">
        <h2>Iniciar Sesión</h2>

        {/* Formulario de login */}
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

        {/* Mensaje de estado */}
        {status && <p className="status">{status}</p>}

        <p className="register-link">
          ¿No tienes cuenta? <a href="/registro">Crear cuenta</a>
        </p>
      </main>

      {/* Footer */}
      <Footer />
    </div>
  );
}

export default Login;
