import React from "react";
import "../styles/Home.css";

function Home() {
  return (
    <div className="home-container">
      {/* Header */}
      <header className="header">
        <div className="logo">FisioStore</div>
        <nav>
          <ul className="nav-links">
            <li><a href="/">Inicio</a></li>
            <li><a href="/nosotros">Nosotros</a></li>
            <li><a href="/servicios">Servicios</a></li>
            <li><a href="/contacto">Contacto</a></li>
          </ul>
        </nav>
      </header>

      {/* Hero */}
      <section className="hero">
        <div className="hero-text">
          <h1>Recupera tu bienestar</h1>
          <p>
            Somos especialistas en fisioterapia y rehabilitación. Nuestro
            compromiso es tu salud, movilidad y calidad de vida.
          </p>
        </div>
      </section>

      {/* Beneficios */}
      <section className="beneficios">
        <h2>¿Por qué elegirnos?</h2>
        <div className="beneficios-grid">
          <div className="beneficio">
            <img src="https://via.placeholder.com/200" alt="Profesionales" />
            <h3>Profesionales expertos</h3>
            <p>Equipo formado en las últimas técnicas de fisioterapia.</p>
          </div>
          <div className="beneficio">
            <img src="https://via.placeholder.com/200" alt="Calidad" />
            <h3>Calidad garantizada</h3>
            <p>Materiales y servicios diseñados para tu recuperación.</p>
          </div>
          <div className="beneficio">
            <img src="https://via.placeholder.com/200" alt="Confianza" />
            <h3>Atención personalizada</h3>
            <p>Cuidamos de cada paciente con un plan adaptado a sus necesidades.</p>
          </div>
        </div>
      </section>

      {/* Sobre nosotros */}
      <section className="sobre-nosotros">
        <h2>Sobre nosotros</h2>
        <p>
          En FisioStore nos apasiona ayudarte a recuperar tu movilidad y
          bienestar. Nuestra misión es ofrecerte soluciones prácticas,
          accesibles y seguras en el mundo de la fisioterapia y la rehabilitación.
        </p>
      </section>

      {/* Footer */}
      <footer className="footer">
        <p>&copy; 2025 FisioStore | Salud y bienestar en movimiento</p>
      </footer>
    </div>
  );
}

export default Home;
