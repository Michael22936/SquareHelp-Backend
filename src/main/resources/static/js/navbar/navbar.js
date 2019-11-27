let unreadAlertsArray =[];
// id for the results
// notifyResult

// Wait for the DOM to be loaded
document.addEventListener("DOMContentLoaded", () => {
    console.log( "ready!" );

    // Fetch Request for all users with unread notifications
    fetch('/unreadAlert')
        .then(response => response.json())

        .then(alerts =>  {
            unreadAlertsArray = alerts;
            console.log('fetch to alert🙃🙃');
            console.log(unreadAlertsArray);
        });
});





$('#notifyResult').html('');

// If array is empty return 0
if (unreadAlertsArray === null){
    $('#notifyResult').append('0');
    console.log("Array is null");
}else{
    // Foreach loop
    unreadAlertsArray.forEach(loopThruAlerts);
    function loopThruAlerts(alert){

        console.log("❤️");
        console.log(alert);
        console.log(alert.is_viewed);

    }



    $('#notifyResult').append('1');

}