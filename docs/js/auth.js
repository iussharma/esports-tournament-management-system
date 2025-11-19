/**
 * Authentication Module
 * Handles login, signup, logout, and session management using Firebase Auth
 * Replaces server-side session logic from LoginServlet, LogoutServlet
 */

// Current logged-in user
let currentUser = null;
let currentUserRole = null;

/**
 * Initialize auth state listener
 * Checks if user is logged in on page load
 */
function initAuthStateListener() {
  if (USE_DEMO_MODE) {
    // Demo mode: check localStorage for demo user
    const demoUser = localStorage.getItem('demoUser');
    if (demoUser) {
      currentUser = JSON.parse(demoUser);
      currentUserRole = currentUser.role || 'admin';
      console.log('Demo mode: User restored from localStorage');
      updateAuthUI();
    }
    return;
  }

  const auth = getAuth();
  if (!auth) return;

  auth.onAuthStateChanged(async (user) => {
    if (user) {
      currentUser = { uid: user.uid, username: user.email };
      
      // Fetch user role from Firestore
      try {
        const db = getDb();
        const userDoc = await db.collection('users').doc(user.uid).get();
        if (userDoc.exists) {
          currentUserRole = userDoc.data().role || 'user';
        } else {
          currentUserRole = 'user';
        }
      } catch (error) {
        console.error('Error fetching user role:', error);
        currentUserRole = 'user';
      }

      console.log('User logged in:', currentUser.username, 'Role:', currentUserRole);
      updateAuthUI();
    } else {
      currentUser = null;
      currentUserRole = null;
      console.log('User logged out');
      updateAuthUI();
    }
  });
}

/**
 * Login with username and password
 * Replaces LoginServlet POST logic
 * 
 * @param {string} username - Username (email)
 * @param {string} password - Password
 * @returns {Promise<boolean>} - True if login successful
 */
async function login(username, password) {
  if (USE_DEMO_MODE) {
    return loginDemo(username, password);
  }

  try {
    const auth = getAuth();
    if (!auth) {
      console.error('Firebase Auth not initialized');
      return false;
    }

    // Firebase uses email for authentication
    const userCredential = await auth.signInWithEmailAndPassword(username, password);
    currentUser = { uid: userCredential.user.uid, username: userCredential.user.email };
    
    console.log('Login successful:', username);
    updateAuthUI();
    return true;
  } catch (error) {
    console.error('Login failed:', error.message);
    showError('Login failed: ' + error.message);
    return false;
  }
}

/**
 * Demo mode login (using localStorage)
 * For testing without Firebase configuration
 */
function loginDemo(username, password) {
  // Demo credentials: AYUSH1234 / Ayush@2908
  if (username === 'AYUSH1234' && password === 'Ayush@2908') {
    currentUser = {
      uid: 'demo-user-1',
      username: username,
      email: 'admin@esports.com'
    };
    currentUserRole = 'admin';
    
    // Store in localStorage for persistence
    localStorage.setItem('demoUser', JSON.stringify(currentUser));
    localStorage.setItem('demoUserRole', currentUserRole);
    
    console.log('Demo login successful');
    updateAuthUI();
    return true;
  } else {
    console.error('Invalid demo credentials');
    showError('Invalid username or password');
    return false;
  }
}

/**
 * Logout current user
 * Replaces LogoutServlet logic
 */
async function logout() {
  try {
    if (USE_DEMO_MODE) {
      localStorage.removeItem('demoUser');
      localStorage.removeItem('demoUserRole');
      currentUser = null;
      currentUserRole = null;
      console.log('Demo logout successful');
    } else {
      const auth = getAuth();
      if (auth) {
        await auth.signOut();
      }
      currentUser = null;
      currentUserRole = null;
      console.log('Logout successful');
    }
    
    updateAuthUI();
    // Redirect to home
    window.location.href = '/';
  } catch (error) {
    console.error('Logout failed:', error.message);
    showError('Logout failed: ' + error.message);
  }
}

/**
 * Check if user is logged in
 */
function isLoggedIn() {
  return currentUser !== null;
}

/**
 * Check if current user is admin
 */
function isAdmin() {
  return currentUserRole === 'admin';
}

/**
 * Get current user
 */
function getCurrentUser() {
  return currentUser;
}

/**
 * Get current user role
 */
function getCurrentUserRole() {
  return currentUserRole;
}

/**
 * Protect page - redirect to login if not authenticated
 * Call this at the start of protected pages
 */
function protectPage() {
  if (!isLoggedIn()) {
    console.log('Access denied. Redirecting to login.');
    window.location.href = '/login.html';
  }
}

/**
 * Protect page - redirect to login if not admin
 * Call this at the start of admin-only pages
 */
function protectAdminPage() {
  if (!isLoggedIn() || !isAdmin()) {
    console.log('Admin access denied. Redirecting to login.');
    window.location.href = '/login.html';
  }
}

/**
 * Update UI based on authentication state
 */
function updateAuthUI() {
  // Update navbar/header
  const loginLink = document.querySelector('[data-auth="login-link"]');
  const logoutBtn = document.querySelector('[data-auth="logout-btn"]');
  const userDisplay = document.querySelector('[data-auth="user-display"]');

  if (isLoggedIn()) {
    if (loginLink) loginLink.style.display = 'none';
    if (logoutBtn) logoutBtn.style.display = 'inline-block';
    if (userDisplay) userDisplay.textContent = `${currentUser.username}`;
  } else {
    if (loginLink) loginLink.style.display = 'inline-block';
    if (logoutBtn) logoutBtn.style.display = 'none';
    if (userDisplay) userDisplay.textContent = '';
  }

  // Dispatch custom event for other scripts to listen to
  window.dispatchEvent(new CustomEvent('authStateChanged', {
    detail: { user: currentUser, role: currentUserRole }
  }));
}

/**
 * Show error message
 */
function showError(message) {
  // Find or create error alert element
  let errorAlert = document.querySelector('[data-alert="error"]');
  if (!errorAlert) {
    errorAlert = document.createElement('div');
    errorAlert.setAttribute('data-alert', 'error');
    errorAlert.className = 'alert alert-error';
    const form = document.querySelector('form');
    if (form) {
      form.parentNode.insertBefore(errorAlert, form);
    } else {
      document.querySelector('.content-section')?.insertBefore(errorAlert, document.querySelector('.content-section').firstChild);
    }
  }
  errorAlert.textContent = message;
  errorAlert.style.display = 'block';
}

/**
 * Show success message
 */
function showSuccess(message) {
  let successAlert = document.querySelector('[data-alert="success"]');
  if (!successAlert) {
    successAlert = document.createElement('div');
    successAlert.setAttribute('data-alert', 'success');
    successAlert.className = 'alert alert-success';
    const form = document.querySelector('form');
    if (form) {
      form.parentNode.insertBefore(successAlert, form);
    }
  }
  successAlert.textContent = message;
  successAlert.style.display = 'block';
  
  // Auto-hide after 5 seconds
  setTimeout(() => {
    successAlert.style.display = 'none';
  }, 5000);
}

// Initialize auth state on page load
document.addEventListener('DOMContentLoaded', function() {
  initAuthStateListener();
});
