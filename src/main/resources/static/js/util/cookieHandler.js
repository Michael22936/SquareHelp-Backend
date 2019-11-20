// Verify cookie handler is loaded
// (verbose) ? console.log("cookieHandler.js is loaded") : "";s

isCookies = () => console.log("I like cookies!");

// define methods
const setCookie = (cname, cvalue, exdays) => {
  let d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  let expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

const getCookie = (cname) => {
  let name = cname + "=";
  let ca = document.cookie.split(';');
  for(let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

const checkCookie = () => {
  let user = getCookie("username");
  if (user != "") {
    alert("Welcome again " + user);
  } else {
    user = prompt("Please enter your name:", "");
    if (user != "" && user != null) {
      setCookie("username", user, 365);
    }
  }
}

const fields = ["city", "dateCreated", "dob", "email", "isLoggedIn", "phone", "state", "username"];

const assignValuesToCookies = () => {
  setCookie(fields[0], login.data.city, 1);
  setCookie(fields[1], login.data.dateCreated, 1);
  setCookie(fields[2], login.data.dob, 1);
  setCookie(fields[3], login.data.email, 1);
  setCookie(fields[4], login.data.isLoggedIn, 1);
  setCookie(fields[5], login.data.phone, 1);
  setCookie(fields[6], login.data.state, 1);
  setCookie(fields[7], login.data.username, 1);
}