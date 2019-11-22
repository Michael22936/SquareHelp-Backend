
$('#search').keyup(function () {
let userSearch = $('#search').val();
    let expression = new RegExp(userSearch, 'i');
    $('#result').html('');
fetch('/search?username=' + userSearch)
.then(response => response.json())

.then(users => users.map((user) => {
    console.log(user.username);
    $('#result').append('<li class="list-group-item link-class"><p class="messageLi">' + user.id + " " + user.username +'</p></li>')
}))

});// end of keyup eventlistner


$('#searchBtn').click(function () {
    let userSearch = $('#search').val();
    fetch('/search?username=' + userSearch)
        .then(response => response.json())
        .then(body => console.log(body));
});

// .then(body => $('#result').html(''),
// let expression = new RegExp(userSearch, 'i');
// $('#result').append('<li class="list-group-item">body.</li>'));
