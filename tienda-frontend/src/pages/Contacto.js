// src/pages/Contacto.js
import React, { useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { db } from "../firebase";
import { collection, addDoc, Timestamp } from "firebase/firestore";
import "../styles/Contacto.css";

function Contacto() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    message: "",
  });
  const [status, setStatus] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await addDoc(collection(db, "contacto"), {
        ...formData,
        createdAt: Timestamp.now(),
      });
      setStatus("Mensaje enviado correctamente.");
      setFormData({ name: "", email: "", message: "" });
    } catch (error) {
      console.error("Error al enviar el mensaje:", error);
      setStatus("Error al enviar el mensaje. Intenta de nuevo.");
    }
  };

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

        {status && <p className="status">{status}</p>}
      </main>

      <Footer />
    </div>
  );
}

export default Contacto;
