// This script is for sending quick replys and updating the conversation on messages-view-one.html

// let response = document.getElementById("messageQuickResponse");
const send = document.getElementById("sendBtn");
const messageArea = document.getElementById("messageArea");

const author = document.getElementById("author").value;
const authorName = document.getElementById("authorName").value;
const authorPic = document.getElementById("authorPic").value;

const recip = document.getElementById("recip").value;
const recipName = document.getElementById("recipUsername").value;
const recipPic = document.getElementById("recipPic").value;

let convo = "";
let data = [];
let outPut = "";
let oldOutPut = "";

const updateConversation = () => {
    fetch("/message/view/" + recip + "/quick")
        .then(res => res.json())
        .then(res => {
            data = res;
            outPut = outHTML();
            if (oldOutPut != outPut) {
                messageArea.innerHTML = "";
                messageArea.innerHTML = outPut;
                oldOutPut = outPut;
                convertDates();
            }
        });
};

const outHTML = () => {
    let html = "";
    html += "<div class='convoWrapper'>";
    data.map((e) => {
        if (e.author_user_id == author) html += "<p class='messageText bg-info'><span class='messageConvoItem dates'>" + e.last_updated + "</span><img src='" + authorPic + "' class='messageConvoItem messageProfilePic'><span class='messageConvoItem'>" + authorName + ":</span><span class='messageConvoItem'>" + e.message + "</span></p>";
        if (e.author_user_id == recip) html += "<p class='messageText messageThem'><span class='messageConvoItem dates'>" + e.last_updated + "</span><img src='" + recipPic + "' class='messageConvoItem messageProfilePic'><span class='messageConvoItem'>" + recipName + ":</span><span class='messageConvoItem'>" + e.message + "</span></p>";
    });
    html += "<div>";
    return html;
}

updateConversation();
setInterval(updateConversation, 1000);