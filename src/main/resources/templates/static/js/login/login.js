// Enable verbose mode for development.
const verbose = true;

// Verify js page loaded. 
(verbose) ? console.log("login.js loaded") : "";

// Define DOM elements
const submitBtn = document.getElementById("submit");
const resetBtn = document.getElementById("reset");
const username = document.getElementById("username");
const password = document.getElementById("password");
const csrf = document.getElementById("csrf");

// Var to hold data from axios request info
let login;

// Event Listeners
submitBtn.addEventListener("click", function(e) {
  e.preventDefault;
  (verbose) ? console.log("submit button was clicked") : "";
  submitWasClicked();
});

resetBtn.addEventListener("click", function(e) {
  e.preventDefault;
  username.value = "";
  password.value = "";
})

const submitWasClicked = () => {
  (verbose) ? console.log("submitWasClicked function was ran") : "";

  let params = new URLSearchParams();
  params.append("username", username.value);
  params.append("password", password.value);
  params.append("_csrf", csrf.value);

  axios.post("http://localhost:8080/api/login", params)
  .then((res) => login = res)
  .then(() => {
    console.log(login);
    if (login.data.isLoggedIn == "false") {
      console.error("invalid login information");
    } else {
      console.log(login.data);
      assignValuesToCookies();
      window.location.href="/dashboard";
    }
  })
  .catch((err) => console.log(err))
};