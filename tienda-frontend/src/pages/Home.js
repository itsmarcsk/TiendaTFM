import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Home.css";

function Home() {
  return (
    <div className="home-container">
      <Header />

      <main className="home">
        {/* Hero */}
        <section className="hero">
          <h2>Bienvenido a nuestra tienda de fisioterapia</h2>
          <p>
            Productos especializados para mejorar tu bienestar, recuperarte de lesiones y potenciar tu rendimiento físico.
          </p>
        </section>

        {/* Beneficios */}
        <section className="features">
          <h3>Beneficios de nuestros productos</h3>
          <div className="feature-cards">
            <div className="card">
              <h4>Recuperación rápida</h4>
              <p>Ayuda a tu cuerpo a recuperarse de lesiones de forma efectiva.</p>
            </div>
            <div className="card">
              <h4>Bienestar diario</h4>
              <p>Mejora tu comodidad y movilidad cada día con productos ergonómicos.</p>
            </div>
            <div className="card">
              <h4>Rendimiento físico</h4>
              <p>Optimiza tu entrenamiento y potencia tu fuerza y resistencia.</p>
            </div>
          </div>
        </section>

        {/* Cómo funciona */}
        <section className="how-it-works">
          <h3>Cómo Funciona</h3>
          <p>
            Explora nuestros productos, selecciona el que mejor se adapte a tus necesidades y comienza a mejorar tu bienestar de manera sencilla y efectiva.
          </p>
        </section>

        {/* Testimonios */}
        <section className="testimonials">
          <h3>Lo que dicen nuestros clientes</h3>
          <div className="testimonial-cards">
            <div className="testimonial">
              <p>"Productos de gran calidad, me ayudaron mucho en mi recuperación."</p>
              <span>- Ana P.</span>
            </div>
            <div className="testimonial">
              <p>"Muy recomendables, cómodos y fáciles de usar."</p>
              <span>- Carlos M.</span>
            </div>
            <div className="testimonial">
              <p>"Mi rendimiento físico ha mejorado gracias a estos productos."</p>
              <span>- Laura G.</span>
            </div>
          </div>
        </section>

        {/* Call to Action */}
        <section className="cta">
          <h3>Descubre todos nuestros productos</h3>
          <p>Visita nuestra tienda y encuentra todo lo que necesitas para tu bienestar físico.</p>
          <a href="/productos" className="cta-button">Ver Productos</a>
        </section>
      </main>

      <Footer />
    </div>
  );
}

export default Home;
