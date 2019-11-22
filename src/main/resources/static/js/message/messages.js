
$('#search').keyup(function () {
let userSearch = $('#search').val();
    let expression = new RegExp(userSearch, 'i');
    $('#result').html('');
fetch('/search?username=' + userSearch)
.then(response => response.json())

.then(users => users.map((user) => {
    user.username
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
