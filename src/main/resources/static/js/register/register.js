// Enable verbose mode for development. 
const verbose = true;

// Verify js is loaded. 
(verbose) ? console.log("register.js loaded") : "";

// Define DOM elements
const username = document.getElementById("username");
const password = document.getElementById("password");
const email = document.getElementById("email");
const dob = document.getElementById("dob");
const phone = document.getElementById("phone");
const gender = document.getElementById("genderRadioWrapper");
const dateQuitSmoking = document.getElementById("dateQuitSmoking");
const costOfCigs = document.getElementById("costOfCigs");
const city = document.getElementById("city");
const state = document.getElementById("state");

const submitBtn = document.getElementById("submit");
const resetBtn = document.getElementById("reset");

// variable to hold data from axios request info
let register;

// Event Listeners
submitBtn.addEventListener("click", function(e) {
  e.preventDefault;
  (verbose) ? console.log("submit button was clicked") : "";
  registerWasClicked();
});

// Initialize dropdown
document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('.dropdown-trigger');
  var instances = M.Dropdown.init(elems);
});

resetBtn.addEventListener("click", function(e) {
  e.preventDefault;
  username.value = "";
  password.value = "";
  email.value = "";
  dob.value = "";
  phone.value = "";
  gender.value = "";
  dateQuitSmoking.value = "";
  costOfCigs.value = "";
});

const registerWasClicked = () => {
  (verbose) ? console.log("registerWasClicked function was ran") : "";

  let params = new URLSearchParams();
  params.append("_csrf", csrf.value);
  params.append("username", username.value);
  params.append("password", password.value);
  params.append("email", email.value);
  params.append("dob", dob.value);
  params.append("phone", phone.value);
  params.append("gender", gender.value);
  params.append("dateQuitSmoking", dateQuitSmoking.value);
  params.append("costOfCigs", costOfCigs.value);
  params.append("city", city.value);
  params.append("state", state.value);

  axios.post('http://localhost:8080/api/register')
    .then((res) => register = res);
}

// Pending completion of backend route to register controller