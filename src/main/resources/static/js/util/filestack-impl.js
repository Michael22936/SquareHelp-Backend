console.log("filestack impl.js loaded");

// Set up the picker
const client = filestack.init("AXimJ9eQiTEaAeo5kJ9tmz");

let updateForm = (result) => {
    let fileData = result.filesUploaded[0];
    photo = (fileData.url);
    fileInput.value = photo;
    updateEditProfilePicture();
};

let updateEditProfilePicture = () => {
    editProfileImg.src = fileInput.value;
}

// client.picker().open();
const options = {
    onUploadDone: updateForm,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false
};

let picker = client.picker(options);

// DOM elements
let form = document.getElementById('photo');
let fileInput = document.getElementById('fileupload');
let pictureUploadbtn = document.getElementById('picker');
// let nameBox = document.getElementById('nameBox');
let editProfileImg = document.getElementById('profileImg');

// // Add our event listeners
pictureUploadbtn.addEventListener('click', function (e) {
    e.preventDefault();
    picker.open();
});

var photo = "";