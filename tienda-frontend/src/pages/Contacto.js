// src/pages/Contacto.js
import React, { useState } from "react"; // Importa React y el hook useState
import Header from "../components/Header"; // Componente Header
import Footer from "../components/Footer"; // Componente Footer
import { db } from "../firebase"; // Importa la instancia de Firebase Firestore
import { collection, addDoc, Timestamp } from "firebase/firestore"; // Funciones para Firestore
import "../styles/Contacto.css"; // Estilos específicos del contacto

function Contacto() {
  // -------------------------------
  // Estado para datos del formulario
  // -------------------------------
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    message: "",
  });

  // Estado para mensajes de éxito/error
  const [status, setStatus] = useState("");

  // -------------------------------
  // Maneja cambios en los inputs
  // -------------------------------
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value }); // Actualiza el campo correspondiente
  };

  // -------------------------------
  // Maneja el envío del formulario
  // -------------------------------
  const handleSubmit = async (e) => {
    e.preventDefault(); // Evita que la página se recargue

    try {
      // Añade un documento a la colección 'contacto' en Firestore
      await addDoc(collection(db, "contacto"), {
        ...formData,
        createdAt: Timestamp.now(), // Guarda la fecha y hora actual
      });
      setStatus("Mensaje enviado correctamente."); // Mensaje de éxito
      setFormData({ name: "", email: "", message: "" }); // Limpia el formulario
    } catch (error) {
      console.error("Error al enviar el mensaje:", error);
      setStatus("Error al enviar el mensaje. Intenta de nuevo."); // Mensaje de error
    }
  };

  // -------------------------------
  // Renderizado del componente
  // -------------------------------
  return (
    <div className="contact-container">
      <Header />

      <main className="contact-main">
        <h2>Contacto</h2>
        <p>
          Completa el formulario y nos pondremos en contacto contigo lo antes posible.
        </p>

        <form className="contact-form" onSubmit={handleSubmit}>
          <label htmlFor="name">Nombre</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="Tu nombre"
            required
          />

          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            placeholder="Tu correo"
            required
          />

          <label htmlFor="message">Mensaje</label>
          <textarea
            id="message"
            name="message"
            value={formData.message}
            onChange={handleChange}
            rows="5"
            placeholder="Escribe tu mensaje"
            required
          ></textarea>

          <button type="submit">Enviar</button>
        </form>

        {/* Mostrar estado del envío */}
        {status && <p className="status">{status}</p>}
      </main>

      <Footer />
    </div>
  );
}

export default Contacto;
