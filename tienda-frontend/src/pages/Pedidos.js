import React, { useState, useEffect } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Pedidos.css";

function Pedidos() {
  const [pedidos, setPedidos] = useState([]);
  const [status, setStatus] = useState("");

  useEffect(() => {
    const fetchPedidos = async () => {
      try {
        const email = localStorage.getItem("email");
        if (!email) {
          setStatus("Debes iniciar sesión para ver tus pedidos.");
          return;
        }

        // 🔹 Obtener usuario por email
        const userRes = await fetch(`http://localhost:8080/usuarios/email/${email}`);
        const usuario = await userRes.json();

        if (!usuario) {
          setStatus("Usuario no encontrado.");
          return;
        }

        // 🔹 Obtener pedidos del usuario
        const pedidosRes = await fetch(`http://localhost:8080/pedidos/usuario/${usuario.id}`);
        const pedidosData = await pedidosRes.json();
        setPedidos(pedidosData);
      } catch (error) {
        console.error("Error al cargar los pedidos:", error);
        setStatus("Error al cargar los pedidos. Intenta de nuevo.");
      }
    };

    fetchPedidos();
  }, []);

  return (
    <div className="pedidos-container">
      <Header />

      <main className="pedidos-main">
        <h2>Mis Pedidos</h2>
        {status && <p className="status">{status}</p>}
        {pedidos.length === 0 && !status && <p>No tienes pedidos realizados.</p>}

        {pedidos.length > 0 && (
          <div className="pedidos-list">
            {pedidos.map((pedido) => (
              <div key={pedido.id} className="pedido-card">
                <p><strong>Pedido ID:</strong> {pedido.id}</p>
                <p><strong>Fecha:</strong> {new Date(pedido.fecha).toLocaleDateString()}</p>
                <p><strong>Total:</strong> ${pedido.total.toFixed(2)}</p>
                <p><strong>Estado:</strong> {pedido.estado}</p>
              </div>
            ))}
          </div>
        )}
      </main>

      <Footer />
    </div>
  );
}

export default Pedidos;
