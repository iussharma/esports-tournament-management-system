/**
 * Firebase Configuration
 * Connected to: esports-tournament-1b0eb Firebase Project
 */
const firebaseConfig = {
  apiKey: "AIzaSyD5JFEbo6Tt6PKbGdZRMb9ZsS1tNU6ReSQ",
  authDomain: "esports-tournament-1b0eb.firebaseapp.com",
  projectId: "esports-tournament-1b0eb",
  storageBucket: "esports-tournament-1b0eb.firebasestorage.app",
  messagingSenderId: "71793969723",
  appId: "1:71793969723:web:f50fd341a5e16d2a5fea1e",
  measurementId: "G-8ZELBSTX4Y"
};

// Initialize Firebase
let db = null;
let auth = null;
let USE_DEMO_MODE = false;

// Try to initialize Firebase, fallback to demo mode if not configured
function initializeFirebase() {
  try {
    // Check if Firebase SDK is loaded
    if (typeof firebase === 'undefined') {
      console.warn('Firebase SDK not loaded. Using demo mode.');
      USE_DEMO_MODE = true;
      return;
    }

    // Check if configuration is valid (not default values)
    if (firebaseConfig.apiKey === "YOUR_API_KEY_HERE" || 
        firebaseConfig.projectId === "your-project-id") {
      console.warn('Firebase configuration not set. Using demo mode.');
      USE_DEMO_MODE = true;
      return;
    }

    firebase.initializeApp(firebaseConfig);
    db = firebase.firestore();
    auth = firebase.auth();
    
    console.log('Firebase initialized successfully');
  } catch (error) {
    console.warn('Firebase initialization failed:', error);
    console.log('Switching to demo mode');
    USE_DEMO_MODE = true;
  }
}

// Get Firestore reference (or demo data object)
function getDb() {
  return db;
}

// Get Auth reference
function getAuth() {
  return auth;
}

// Initialize Firebase immediately (before DOM load)
initializeFirebase();
