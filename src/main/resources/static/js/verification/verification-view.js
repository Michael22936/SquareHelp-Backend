console.log("Verification view.js loaded");

saveBtn = document.getElementById("saveBtn");
approvedChkBx = document.getElementById("isApproved");

saveBtn.addEventListener("click", (e) => {
    if (approvedChkBx == false) {
        e.preventDefault();
        location.href = '/verification';
    }
});


