// Import the functions you need from the SDKs you need
import firebase from "firebase/compat/app";
import "firebase/compat/firestore"

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: process.env.REACT_APP_FIREBASE_API_KEY,
  authDomain: "data-app-e5385.firebaseapp.com",
  projectId: "data-app-e5385",
  storageBucket: "data-app-e5385.appspot.com",
  messagingSenderId: "21115070277",
  appId: "1:21115070277:web:765d7089eff3c47052402c"
};

// Initialize Firebase
const app = firebase.initializeApp(firebaseConfig);

// Activate firestore database
export const db = firebase.firestore();
