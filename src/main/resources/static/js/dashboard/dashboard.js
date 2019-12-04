// // Script is specific for dashboard.html

let allUsersArray = [];
let ArrayAllUsersPoints = [];
let trimmedpercentage = 0;
let TotalUsersAvgPointsFixed = 0;
let TotalUsersAvgSmokeFreeDays = 0;
let TotalUsersAvgCigSavings = 0;
let TotalUsersPoints = 0;
let TotalUsersDaysSmokeFree = 0;
let TotalUsersSavings = 0;

document.addEventListener("DOMContentLoaded", () => {
    let totalUsersPoints = 0;
    let totalUsers = 0;
    let highestUserPoints = 0;
    let totalUsersSmokeFree = 0;
    let totalUsersCigsSavings = 0;

    // Fetch Request for all users
    fetch('/searchUser')
        .then(response => response.json())
        .then(user =>  {
            TotalUsersPoints = user[0].smokerInfo.points;
            TotalUsersDaysSmokeFree = user[0].smokerInfo.total_days_smoke_free;
            TotalUsersSavings = user[0].smokerInfo.cost_of_cigs_saved;
        });

    // Fetch Request for all users
    fetch('/searchAll')
        .then(response => response.json())

        .then(users =>  {
            allUsersArray = users;

            // for each loop
            allUsersArray.forEach(loopThruAllUsers);

            function loopThruAllUsers(user) {
                let  username = user.username;
                let userTotalPoints = user.smokerInfo.points;
                let userTotalDaysSmokeFree = user.smokerInfo.total_days_smoke_free;
                let userTotalCigsSavings = user.smokerInfo.cost_of_cigs_saved;
                ArrayAllUsersPoints.push(userTotalPoints);

                totalUsersPoints += userTotalPoints;
                totalUsersSmokeFree += userTotalDaysSmokeFree;
                totalUsersCigsSavings += userTotalCigsSavings;

                // User Counter
                totalUsers++;
            }

            // Calculate Avg points by all users
            let TotalUsersAvgPoints = (totalUsersPoints / totalUsers);
            TotalUsersAvgPointsFixed = parseInt( TotalUsersAvgPoints.toFixed() );

            // Calculate highest points by users
            let mostPoints = Math.max(...ArrayAllUsersPoints);
            let percentage = (TotalUsersAvgPointsFixed * 100 / mostPoints); // TotalAvgPoints / highestUserPoints
            trimmedpercentage = parseInt( percentage.toFixed() );

            // Calculate All Users Avg Smoke free Days
            TotalUsersAvgSmokeFreeDays = parseInt( (totalUsersSmokeFree / totalUsers).toFixed() );

             // Calculate All Users Avg Cigs Savings
            TotalUsersAvgCigSavings = parseInt( (totalUsersCigsSavings / totalUsers).toFixed() );

        }).then(users =>  {
            // Start of 3rd Fetch .then

    // Gradient Radial Bar Chart
        // ================================== 1st chart (Community Stats)

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
            series: [TotalUsersAvgPointsFixed,TotalUsersAvgSmokeFreeDays,TotalUsersAvgCigSavings],
            labels: ['AVG Points Earned', 'AVG Days Smoke Free', 'AVG Savings in USD'],
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
            document.querySelector("#avgPointChart1"),
            options
        );

        chart.render();

        // ================================== 2nd chart (Users Stats)

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
            series: [TotalUsersPoints,TotalUsersDaysSmokeFree,TotalUsersSavings],
            labels: ['Points Earned', 'Days Smoke Free', 'Savings in USD'],
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


    }); // end of of 4th fetch .then


}) // Load when document is ready END