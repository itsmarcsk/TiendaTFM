import React, { useState } from "react";
import { FaShoppingCart, FaUser, FaBars, FaTimes } from "react-icons/fa";
import "../styles/Header.css";

function Header() {
  const [menuOpen, setMenuOpen] = useState(false);

  const toggleMenu = () => setMenuOpen(!menuOpen);

  return (
    <header className="header">
      <div className="logo">🧘‍♂️ Tienda Fisioterapia</div>

      <nav className={`nav ${menuOpen ? "nav-active" : ""}`}>
        <ul className="nav-links">
          <li><a href="/">Inicio</a></li>
          <li><a href="/productos">Productos</a></li>
          <li><a href="/contacto">Contacto</a></li>
          <li><a href="/carrito"><FaShoppingCart size={18}/> Carrito</a></li>
          <li><a href="/usuario"><FaUser size={18}/> Usuario</a></li>
        </ul>
      </nav>

      <div className="hamburger" onClick={toggleMenu}>
        {menuOpen ? <FaTimes size={25} /> : <FaBars size={25} />}
      </div>
    </header>
  );
}

export default Header;
