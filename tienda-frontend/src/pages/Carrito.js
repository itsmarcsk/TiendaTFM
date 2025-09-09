import React, { useState, useEffect } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "../styles/Carrito.css";

// Firebase
import { collection, query, where, onSnapshot, deleteDoc, doc, getDocs } from "firebase/firestore";
import { db } from "../firebase";

function Carrito() {
  const [productos, setProductos] = useState([]);
  const [status, setStatus] = useState("");

  useEffect(() => {
    const email = localStorage.getItem("email");
    if (!email) {
      setStatus("Debes iniciar sesión para ver tu carrito.");
      return;
    }

    const carritoRef = collection(db, "carritos");
    const q = query(carritoRef, where("email", "==", email));

    // 🔹 Escuchar cambios en tiempo real
    const unsubscribe = onSnapshot(q, (snapshot) => {
      const carritoData = snapshot.docs.map((doc) => ({
        id: doc.id,
        ...doc.data(),
      }));
      setProductos(carritoData);
    });

    return () => unsubscribe();
  }, []);

  // 🔹 Ocultar el status automáticamente a los 4s
  useEffect(() => {
    if (status) {
      const timer = setTimeout(() => setStatus(""), 4000);
      return () => clearTimeout(timer);
    }
  }, [status]);

  const vaciarCarrito = async () => {
    const email = localStorage.getItem("email");
    if (!email) return;

    const carritoRef = collection(db, "carritos");
    const q = query(carritoRef, where("email", "==", email));
    const snapshot = await getDocs(q);

    snapshot.forEach(async (docSnap) => {
      await deleteDoc(doc(db, "carritos", docSnap.id));
    });

    setStatus("Carrito vaciado ✅");
  };

  const finalizarCompra = async () => {
    const email = localStorage.getItem("email");
    if (!email) return;

    const carritoRef = collection(db, "carritos");
    const q = query(carritoRef, where("email", "==", email));
    const snapshot = await getDocs(q);

    snapshot.forEach(async (docSnap) => {
      await deleteDoc(doc(db, "carritos", docSnap.id));
    });

    setStatus("Compra finalizada ✅");
  };

  const total = productos.reduce((acc, p) => acc + p.precio * p.cantidad, 0);

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
                <p className="nombre">{producto.productoNombre}</p>
                <p className="cantidad">Cantidad: {producto.cantidad}</p>
                <p className="precio">
                  Precio unitario: ${producto.precio.toFixed(2)} | Total: ${(producto.precio * producto.cantidad).toFixed(2)}
                </p>
              </div>
            ))}

            <div className="total">
              <p>Total carrito: ${total.toFixed(2)}</p>
            </div>

            <div className="carrito-buttons">
              <button className="vaciar-btn" onClick={vaciarCarrito}>Vaciar Carrito</button>
              <button className="checkout-btn" onClick={finalizarCompra}>Finalizar Compra</button>
            </div>
          </div>
        )}
      </main>
      <Footer />
    </div>
  );
}

export default Carrito;
