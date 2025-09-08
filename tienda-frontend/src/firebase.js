import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";


const firebaseConfig = {
  apiKey: "AIzaSyAr8FULF1sP-bTUycDNy2LOrk3Deq1Aj94",
  authDomain: "tiendatfm-8008b.firebaseapp.com",
  projectId: "tiendatfm-8008b",
  storageBucket: "tiendatfm-8008b.firebasestorage.app",
  messagingSenderId: "388309214772",
  appId: "1:388309214772:web:ada3dcf73046e096e20c61"
};

// Inicializa Firebase
const app = initializeApp(firebaseConfig);

// Exporta Firestore
export const db = getFirestore(app);

