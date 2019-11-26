
let tempArray ="";

const getCurrentPageUserId = () => {
    let currentUserId = window.location.href;

    let tempArr = currentUserId.split("/");

    return tempArr[tempArr.length - 1]
};

// Fetch Request for all users
        fetch('/searchAll')
            .then(response => response.json())

            .then(users =>  {
                tempArray = users;
            });

$('#navbarSearch').keyup(function () {
    let userSearch = $('#navbarSearch').val();

    $('#dashboardResult').html('');

    // If search input is not empty
    if (userSearch !== "" ){

        if (userSearch === ""){
            $('#dashboardResult').hide();

        }else {

            console.log(tempArray);
            // Filter users based on users input.
            let fiteredUsers = tempArray.filter(tempArray => tempArray.username.indexOf(userSearch) !== -1 );
            console.log("Users filtered: " + fiteredUsers);
            console.log("fetch request");

            //
            fiteredUsers.forEach(filteredUsers);

            function filteredUsers(user) {
                // arr[index] = item * 10;
                console.log(user);
                console.log(user.username);

                let url = '/profile/' + user.id ;
                // let url = '/message/' + user.id + '/send' ;
                // console.log(url);
                $('#dashboardResult').append('<a  class=" text-decoration-none text-dark searchResultItem list-group-item-action list-group-item link-class" href=' + url + '  >' +
                     + user.id + " " + user.username + " " + user.city + ", " + user.state + '</a>');
                // console.log(user.username);


            }



        }


    }

});// end of keyup eventlistner


// // $('#searchBtn').click(function () {
// //     let userSearch = $('#search').val();
// //     if (userSearch !== ""){
// //         fetch('/search?username=' + userSearch)
// //             .then(response => response.json())
// //             .then(users => users.map((user) => {
// //                 if (userSearch === ""){
// //                     $('#result').hide();
// //                 } else {
// //                     $('#result').html('<a href="/send/${user.id}/message"><li class="list-group-item link-class"><p class="messageLi">' + user.id + " " + user.username + " " + user.city + ", " + user.state +'</p></th></a>')
// //
// //                 }
// //             }))
// //     }
// // });
