// This script is for sending quick replys and updating the conversation on messages-view-one.html
console.log("message-one-api.js loaded");

// let response = document.getElementById("messageQuickResponse");
const send = document.getElementById("sendBtn");
const messageArea = document.getElementById("messageArea");

const author = document.getElementById("author").value;
const recip = document.getElementById("recip").value;
const recipName = document.getElementById("recipUsername").value;

let convo = "";
let data = [];

const updateConversation = () => {
    fetch("/message/view/" + recip + "/quick")
        .then(res => res.json())
        .then(res => {
            data = res;
            messageArea.innerHTML = "";
            messageArea.innerHTML = outHTML();
        });
}

const outHTML = () => {
    let html = "";
    html += "<div class='convoWrapper'>";
    data.map((e) => {
        if (e.author_user_id == author) html += "<p class='messageText messageThem'>" + e.last_updated + " Me:<strong> " + e.message + "</strong></p>";
        if (e.author_user_id == recip) html += "<p class='messageText messageThem'>" + e.last_updated + " " + recipName + ": " + e.message + "</p>";
    });
    html += "<div>";
    return html;
}

setInterval(updateConversation, 10000);