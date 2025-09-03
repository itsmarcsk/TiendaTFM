import React from "react";
import "../styles/Footer.css";

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-container">
        <p>© {new Date().getFullYear()} Tienda Fisioterapia. Todos los derechos reservados.</p>
        <p>📞 Contacto: info@tiendafisioterapia.com | ☎ +34 600 000 000</p>
      </div>
    </footer>
  );
}

export default Footer;
