
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

const picker = client.picker(options);

// DOM elements
const form = document.getElementById('photo');
const fileInput = document.getElementById('fileupload');
const pictureUploadbtn = document.getElementById('picker');
const editProfileImg = document.getElementById('profileImg');

// // Add our event listeners
pictureUploadbtn.addEventListener('click', function (e) {
    e.preventDefault();
    picker.open();
});

let photo = "";