const getCurrentPageUserId = () => {
    let currentUserId = window.location.href;
    let tempArr = currentUserId.split("/");
    return tempArr[tempArr.length - 2];
}

console.log("verification-form.js loaded");
$('#search').keyup(function () {
    let userSearch = $('#search').val();
    $('#result').html('');

    // If search input is not empty
    if (userSearch !== "" ){

        fetch('/search?username=' + userSearch)
            .then(response => response.json())

    .then(users => users.map((user) => {
            if (userSearch === ""){
            $('#result').hide();

        }else {
            let url = '/verification/' + getCurrentPageUserId() + '/form/send/' + user.id;
            console.log(url);
            $('#result').append('<a href=' + url + '  ><li class=" searchResultItem list-group-item link-class"><p class="messageLi">' + user.id + " " + user.username + " " + user.city + ", " + user.state +'</p></li></a>');
            console.log(user.username);
        }
    }))

    }

});// end of keyup eventlistner


// $('#searchBtn').click(function () {
//     let userSearch = $('#search').val();
//     if (userSearch !== ""){
//         fetch('/search?username=' + userSearch)
//             .then(response => response.json())
//             .then(users => users.map((user) => {
//                 if (userSearch === ""){
//                     $('#result').hide();
//                 } else {
//                     $('#result').html('<a href="/send/${user.id}/message"><li class="list-group-item link-class"><p class="messageLi">' + user.id + " " + user.username + " " + user.city + ", " + user.state +'</p></th></a>')
//
//                 }
//             }))
//     }
// });