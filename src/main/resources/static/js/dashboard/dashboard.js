// Script is specific for dashboard.html

const getCurrentPageUserId = () => {
    let currentUserId = window.location.href;

    let tempArr = currentUserId.split("/");

    return tempArr[tempArr.length - 1]
};


$('#search').keyup(function () {
    let userSearch = $('#search').val();
    let tempArray ="";
    $('#result').html('');

    // If search input is not empty
    if (userSearch !== "" ){

        fetch('/searchAll')
            .then(response => response.json())

            .then(users =>  {
                if (userSearch === ""){
                    $('#result').hide();

                }else {
                    tempArray = users;
                    console.log(users);
                    // Filter users based on users input.
                    let fiteredUsers = tempArray.filter(user => user.username === userSearch);
                    console.log("Users filtered: " + fiteredUsers);
                    console.log("fetch request");


                    users.forEach(filteredUsers);

                    function filteredUsers(user) {
                        // arr[index] = item * 10;
                        // console.log(user);
                        // console.log(user.username);
                    }


                    // let url = '/message/' + user.id ;
                    // let url = '/message/' + user.id + '/send' ;
                    // console.log(url);
                    // $('#result').append('<form action="'+url+'" method="post">' +
                    //     '<a href=' + url + '  >' +
                    //     '<li class=" searchResultItem list-group-item link-class"><p class="messageLi">' + user.id + " " + user.username + " " + user.city + ", " + user.state +'</p></li></a></form>');
                    // console.log(user.username);
                }
            })

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
