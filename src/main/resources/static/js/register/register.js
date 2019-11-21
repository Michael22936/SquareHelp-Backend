// script is specific to register.html

// Display this message if the user does not enter a number
// <div class="invalid-tooltip">
//     Please choose a unique and valid username.
// </div>
document.querySelector('#costOfCigs').addEventListener('onkeyup', function (evt) { console.log("button pushed!@!" + evt) });
document.querySelector('#costOfCigs').style.backgroundColor = 'red';
document.querySelector('#costOfCigs').style.backgroundColor = 'red';
document.querySelector('.test').style.backgroundColor = 'pink';
alert("I'm active");


const costOfCigs = document.getElementbyById('costOfCigs');

costOfCigs.addEventListener('onkeyup', (e) => {
    costOfCigs.style.backgroundColor = 'red';
    console.log("FIRE!!")
});

// $("#costOfCigs").keyup( );
