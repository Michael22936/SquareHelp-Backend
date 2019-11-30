// // Script is specific for dashboard.html
let allUsersArray = [];
let ArrayAllUsersPoints = [];
let trimmedpercentage = 0;

document.addEventListener("DOMContentLoaded", () => {



let totalUsersPoints = 0;
let totalUsers = 0;
let highestUserPoints = 0;

// Fetch Request for all users
fetch('/searchAll')
    .then(response => response.json())

    .then(users =>  {
        allUsersArray = users;

        console.log("Array of all users " + allUsersArray);



// for each loop
        allUsersArray.forEach(loopThruAllUsers);

        function loopThruAllUsers(user) {
            let  username = user.username;
            let userTotalPoints = user.smokerInfo.points;
            console.log("userTotalPoints typeof " +typeof userTotalPoints);
            ArrayAllUsersPoints.push(userTotalPoints);

            console.log("=============  FIRE  ================");

            console.log("Username and user points======: " + username + " " + userTotalPoints);

            totalUsersPoints += userTotalPoints;

            // User Counter
            totalUsers++;


        }

        let TotalUsersAvgPoints = (totalUsersPoints / totalUsers);
        let singleDigit = TotalUsersAvgPoints.toFixed();

            // Calculate highest points by users
            let mostPoints = Math.max(...ArrayAllUsersPoints);
        let percentage = (singleDigit * 100 / mostPoints) // TotalAvgPoints / highestUserPoints
             trimmedpercentage = parseInt( percentage.toFixed() );

        console.log("Gauge percentage: " + percentage + " "+ trimmedpercentage + " "+ typeof trimmedpercentage);
        console.log("Most points by user: " + mostPoints);
        console.log("Total users : " + totalUsers);
        console.log("Total users points: " + totalUsersPoints);
        console.log("Total users points: " + (totalUsersPoints / totalUsers));
        console.log("Total users points: " + singleDigit);



    }).then(users =>  {
        // Start of 3rd Fetch .then


// Gradient Radial Bar Chart
    var options = {
        chart: {
            height: 350,
            type: 'radialBar',
            toolbar: {
                show: true
            }
        },
        plotOptions: {
            radialBar: {
                startAngle: -135,
                endAngle: 225,
                hollow: {
                    margin: 0,
                    size: '70%',
                    background: '#fff',
                    image: undefined,
                    imageOffsetX: 0,
                    imageOffsetY: 0,
                    position: 'front',
                    dropShadow: {
                        enabled: true,
                        top: 3,
                        left: 0,
                        blur: 4,
                        opacity: 0.24
                    }
                },
                track: {
                    background: '#fff',
                    strokeWidth: '67%',
                    margin: 0, // margin is in pixels
                    dropShadow: {
                        enabled: true,
                        top: -3,
                        left: 0,
                        blur: 4,
                        opacity: 0.35
                    }
                },

                dataLabels: {
                    showOn: 'always',
                    name: {
                        offsetY: -10,
                        show: false, // change
                        color: '#888',
                        fontSize: '17px'
                    },
                    value: {
                        formatter: function(val) {
                            return parseInt(val);
                        },
                        color: '#111',
                        fontSize: '36px',
                        show: false, // change

                    }
                }
            }
        },
        fill: {
            type: 'gradient',
            gradient: {
                shade: 'dark',
                type: 'horizontal',
                shadeIntensity: 0.5,
                gradientToColors: ['#ABE5A1'],
                inverseColors: true,
                opacityFrom: 1,
                opacityTo: 1,
                stops: [0, 100] // Min and max for dial
            }
        },

        series: [ trimmedpercentage], // Number inside of dial
        stroke: {
            lineCap: 'round'
        },
        // labels: ['Average Points Earned'],


    }

    var chart = new ApexCharts(
        document.querySelector("#avgPointChart"),
        options
    );

    chart.render();


    // ================================== 2nd chart
// Gradient Radial Bar Chart
    var options = {
        chart: {
            height: 350,
            type: 'radialBar',
            toolbar: {
                show: true
            }
        },
        plotOptions: {
            radialBar: {
                startAngle: -135,
                endAngle: 225,
                hollow: {
                    margin: 0,
                    size: '70%',
                    background: '#fff',
                    image: undefined,
                    imageOffsetX: 0,
                    imageOffsetY: 0,
                    position: 'front',
                    dropShadow: {
                        enabled: true,
                        top: 3,
                        left: 0,
                        blur: 4,
                        opacity: 0.24
                    }
                },
                track: {
                    background: '#fff',
                    strokeWidth: '67%',
                    margin: 0, // margin is in pixels
                    dropShadow: {
                        enabled: true,
                        top: -3,
                        left: 0,
                        blur: 4,
                        opacity: 0.35
                    }
                },

                dataLabels: {
                    showOn: 'always',
                    name: {
                        offsetY: -10,
                        show: false, // change
                        color: '#888',
                        fontSize: '17px'
                    },
                    value: {
                        formatter: function(val) {
                            return parseInt(val);
                        },
                        color: '#111',
                        fontSize: '36px',
                        show: false, // change

                    }
                }
            }
        },
        fill: {
            type: 'gradient',
            gradient: {
                shade: 'dark',
                type: 'horizontal',
                shadeIntensity: 0.5,
                gradientToColors: ['#ABE5A1'],
                inverseColors: true,
                opacityFrom: 1,
                opacityTo: 1,
                stops: [0, 100] // Min and max for dial
            }
        },

        series: [ trimmedpercentage], // Number inside of dial
        stroke: {
            lineCap: 'round'
        },
        // labels: ['Average Points Earned'],


    }

    var chart = new ApexCharts(
        document.querySelector("#avgPointChart2"),
        options
    );

    chart.render();




// end of 3rd fetch .then
}).then(users => { users

    //Load text in gauge
    let el = document.querySelector('.chartText')
    el.style.visibility = "visible"; // show element



}); // end of of 4th fetch .then


}) // Load when document is ready END