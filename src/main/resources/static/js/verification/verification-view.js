console.log("Verification view.js loaded");

isApproved = document.getElementById("isApproved");
yes = document.getElementById("yesBtn");
no = document.getElementById("no");

yes.addEventListener("mousedown", () => {
    isApproved.value = "on";
});

no.addEventListener("mousedown", () => {
    isApproved.value = "off";
});