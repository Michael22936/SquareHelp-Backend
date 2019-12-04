let resultsArray = [];

fetch('/searchAll')
    .then(response => response.json())
    .then(users =>  {
        resultsArray = users;
    });

$('#searchUserMessage').keyup(function () {
    let userSearch = $('#searchUserMessage').val();
    console.log("fire 🔥🔥🔥🔥🔥🔥🔥"); // lol
    $('#messageUserResults').html('');

    // If search input is not empty
    if (userSearch !== "" ){

        if (userSearch === ""){
            $('#messageUserResults').hide();

        } else {
            let filteredUsersMsg = resultsArray.filter(tempArray => tempArray.username.indexOf(userSearch) !== -1 );
            console.log("Message Users filtered: " + filteredUsersMsg.toString());

            filteredUsersMsg.forEach(filterMsgUsers); // Looping through filtered list

            function filterMsgUsers(user){

            let url = '/message/' + user.id + '/send' ;
            console.log(url);
            $('#messageUserResults').append('<form class=" searchResultItem list-group-item list-group-item-action link-class" action="'+url+'" method="post">' +
                '<a class="text-dark text-decoration-none messageLi"' + ' href=' + url + '  >' + user.id + " " + user.username + " " + user.city + ", " + user.state +'</a></form>');
            console.log(user.username);
            } // End filterMsgUsers
        }
    }

});