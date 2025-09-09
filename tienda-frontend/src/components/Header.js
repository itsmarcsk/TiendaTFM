import React, { useState, useEffect } from "react";
import { FaShoppingCart, FaUser, FaBars, FaTimes } from "react-icons/fa";
import "../styles/Header.css";

function Header() {
  const [menuOpen, setMenuOpen] = useState(false);
  const [email, setEmail] = useState(null);

  const toggleMenu = () => setMenuOpen(!menuOpen);

  useEffect(() => {
    const storedEmail = localStorage.getItem("email");
    if (storedEmail) {
      setEmail(storedEmail);
    }
  }, []);

  return (
    <header className="header">
      <div className="logo">
        <a href="/">🧘‍♂️</a> Tienda Fisioterapia
      </div>

      <nav className={`nav ${menuOpen ? "nav-active" : ""}`}>
        <ul className="nav-links">
          <li><a href="/">Inicio</a></li>    
          <li><a href="/productos">Productos</a></li>
          <li><a href="/contacto">Contacto</a></li>
          <li>
            <a href="/carrito">
              <FaShoppingCart size={18} />
            </a>
          </li>
          <li>
            {email ? (
              <a href="/usuario">
                <FaUser size={18} />
              </a>
            ) : (
              <a href="/login">Iniciar Sesión / Crear Cuenta</a>
            )}
          </li>
        </ul>
      </nav>

      <button
        className="hamburger"
        onClick={toggleMenu}
        onKeyDown={(e) => {
          if (e.key === "Enter" || e.key === " ") toggleMenu();
        }}
        aria-label={menuOpen ? "Cerrar menú" : "Abrir menú"}
      >
        {menuOpen ? <FaTimes size={25} /> : <FaBars size={25} />}
      </button>
    </header>
  );
}

export default Header;
