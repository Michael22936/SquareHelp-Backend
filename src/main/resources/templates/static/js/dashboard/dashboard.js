// Enable verbose mode for development.
const verbose = true;

// Verify dashboard js page loaded
(verbose) ? console.log("dashboard.js loaded") : "";

// Verify user is logged in when visiting dashboard page.
// (Redirect them to login if not)

if (getCookie("isLoggedIn") == "") {
  window.location.href="/login";
}

// Define DOM elements
let liu = document.getElementById("loggedInUser");
liu.innerHTML = getCookie("username");