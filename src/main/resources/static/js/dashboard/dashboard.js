// // Script is specific for dashboard.html

let allUsersArray = [];
let ArrayAllUsersPoints = [];
let trimmedpercentage = 0;
let TotalUsersAvgPointsFixed = 0;

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

        // Calculate Avg points by all users
        let TotalUsersAvgPoints = (totalUsersPoints / totalUsers);
        TotalUsersAvgPointsFixed = parseInt( TotalUsersAvgPoints.toFixed() );

            // Calculate highest points by users
            let mostPoints = Math.max(...ArrayAllUsersPoints);
        let percentage = (TotalUsersAvgPointsFixed * 100 / mostPoints) // TotalAvgPoints / highestUserPoints
             trimmedpercentage = parseInt( percentage.toFixed() );

        console.log("Gauge percentage: " + percentage + " "+ trimmedpercentage + " "+ typeof trimmedpercentage);
        console.log("Most points by user: " + mostPoints);
        console.log("Total users : " + totalUsers);
        console.log("Total users points: " + totalUsersPoints);
        console.log("Total users points: " + (totalUsersPoints / totalUsers));
        console.log("Total users points: " + TotalUsersAvgPointsFixed + " " + typeof TotalUsersAvgPointsFixed);



    }).then(users =>  {
        // Start of 3rd Fetch .then


// Gradient Radial Bar Chart



    // ================================== 2nd chart

    var options = {
        chart: {
            height: 350,
            type: 'radialBar',
        },
        plotOptions: {
            radialBar: {
                offsetY: -10,
                startAngle: 0,
                endAngle: 270,
                hollow: {
                    margin: 5,
                    size: '30%',
                    background: 'transparent',
                    image: undefined,
                },
                dataLabels: {
                    name: {
                        show: false,

                    },
                    value: {
                        show: false,
                    }
                }
            }
        },
        colors: ['#1ab7ea', '#0084ff', '#39539E', '#0077B5'],
        series: [TotalUsersAvgPointsFixed,67,61],
        labels: ['Average Points Earned', 'Average Days Smoke Free', 'Average Savings'],
        legend: {
            show: true,
            floating: true,
            fontSize: '16px',
            position: 'left',
            offsetX: 160,
            offsetY: 10,
            labels: {
                useSeriesColors: true,
            },
            markers: {
                size: 0
            },
            formatter: function(seriesName, opts) {
                return seriesName + ":  " + opts.w.globals.series[opts.seriesIndex]
            },
            itemMargin: {
                horizontal: 1,
            }
        },
        responsive: [{
            breakpoint: 480,
            options: {
                legend: {
                    show: false
                }
            }
        }]
    }

    var chart = new ApexCharts(
        document.querySelector("#avgPointChart2"),
        options
    );

    chart.render();



// end of 4rd fetch .then
}).then(users => { users

    //Load text in gauge
    // let el = document.querySelector(".chartText");
    // let guageChart1 = document.querySelector("#guageBox1");
    // let guageChart2 = document.querySelector("#guageBox2");
    // // el.style.visibility = "visible"; // show element
    // console.log("Showing the gauge points!!");
    //
    // setTimeout(function(){
    //     // alert("Hello");
    //     guageChart1.classList.remove("hideElement");
    //     // guageChart2.classList.remove("hideElement");
    //     // add Animate classes
    //     guageChart1.classList.add('animated', 'fadeInUp');
    //     // guageChart2.classList.add('animated', 'fadeInDown');
    // }, 500);

}); // end of of 4th fetch .then


}) // Load when document is ready END