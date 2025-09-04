import React, { useState, useEffect } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Carrito.css";

function Carrito() {
  const [productos, setProductos] = useState([]);
  const [status, setStatus] = useState("");

  useEffect(() => {
    const fetchCarrito = async () => {
      try {
        const email = localStorage.getItem("email");
        if (!email) {
          setStatus("Debes iniciar sesión para ver tu carrito.");
          return;
        }

        // 🔹 Obtener usuario por email
        const userRes = await fetch(`http://localhost:8080/usuarios/email/${email}`);
        const usuario = await userRes.json();

        if (!usuario) {
          setStatus("Usuario no encontrado.");
          return;
        }

        // 🔹 Obtener carrito del usuario
        const carritoRes = await fetch(`http://localhost:8080/carrito/usuario/${usuario.id}`);
        const carritoData = await carritoRes.json();
        setProductos(carritoData);
      } catch (error) {
        console.error("Error al cargar el carrito:", error);
        setStatus("Error al cargar el carrito. Intenta de nuevo.");
      }
    };

    fetchCarrito();
  }, []);

  const total = productos.reduce(
    (acc, producto) => acc + producto.precio * producto.cantidad,
    0
  );

  return (
    <div className="carrito-container">
      <Header />

      <main className="carrito-main">
        <h2>Tu Carrito</h2>
        {status && <p className="status">{status}</p>}
        {productos.length === 0 && !status && <p>Tu carrito está vacío.</p>}

        {productos.length > 0 && (
          <div className="carrito-items">
            {productos.map((producto) => (
              <div key={producto.id} className="carrito-item">
                <p className="nombre">{producto.nombre}</p>
                <p className="cantidad">Cantidad: {producto.cantidad}</p>
                <p className="precio">Precio: ${producto.precio.toFixed(2)}</p>
              </div>
            ))}
            <div className="total">
              <p>Total: ${total.toFixed(2)}</p>
            </div>
            <button className="checkout-btn">Finalizar Compra</button>
          </div>
        )}
      </main>

      <Footer />
    </div>
  );
}

export default Carrito;
