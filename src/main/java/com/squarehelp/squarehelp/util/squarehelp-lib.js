// Methods in this file are used to help various things on the website.

// Define constants
const leapYears = [1972,1976,1980,1984,1988,1992,1996,2000,2004,2008,2012,2016,2020]
const startDays = [1,32,60,91,121,152,182,213,244,274,305,335];

// Convert date from date-picker input to julian date to store into database.
const dateToJulian = (d) => {
    // Split parameter passed in and turn into an array
    temp = d.split('-');

    // Store values from date as strings in variables
    const y = temp[0];
    const m = temp[1];
    let day = temp[2] - 1;

    // Check if leap year
    let isLeapYear;

    leapYears.map( e => {
        if (parseInt(y) == e) {
            isLeapYear = true;
        }
    });

    (!isLeapYear) ? isLeapYear = false : "";

    // Add a day
    if (m >= 2 && isLeapYear) {
        day++;
    }

    // figure out day of year
    let dayOfYear = 0;
    dayOfYear = startDays[m - 1] + parseInt(day);

    // Format number of days if smaller than 100 (and 10)
    let cc = "";

    if (dayOfYear < 100 && dayOfYear > 10) {
        cc = "0" + dayOfYear;
    }

    if (dayOfYear < 10) {
        cc = "00" + dayOfYear;
    }

    if (dayOfYear >= 100) {
        cc = dayOfYear;
    }

    dayOfYear = cc;

    // Construct output
    let out;

    out = y.slice(2,4);
    out += dayOfYear

    return out;
}

// Convert Julian date to Gregorian date "Month day, Year"
const julianToDate = (j) => {
    // Extra check for julian string from json
    if((typeof j) != "string"){
        j = j.toString();
    }

    // Format Year (Not expecting centurians)
    y = j.slice(0,2);

    if (y > 20) {
        y = "19" + y;
    } else {
        y = "20" + y;
    }

    // Check if leap year
    let isLeapYear;

    leapYears.map( e => {
        if (parseInt(y) == e) {
            isLeapYear = true;
        }
    });

    (!isLeapYear) ? isLeapYear = false : "";

    // figure out month
    let temp = j.slice(2,5);
    let tempNum = parseInt(temp);

    console.log(tempNum);
    console.log((tempNum >= 1 && tempNum < 32));

    let m = "";
    let d = 0;
    switch(true) {
        case (tempNum >= 1 && tempNum < 32):
            m = "January";
            d = tempNum;
            break;
        case (tempNum >= 32 && tempNum < 60):
            m = "February";
            d = tempNum - 31;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 60 && tempNum < 91):
            m = "March";
            d = tempNum - 59;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 91 && tempNum < 121):
            m = "April";
            d = tempNum - 90;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 121 && tempNum < 152):
            m = "June";
            d = tempNum - 120;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 152 && tempNum < 182):
            m = "July";
            d = tempNum - 151;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 213 && tempNum < 244):
            m = "August";
            d = tempNum - 212;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 244 && tempNum < 274):
            m = "September";
            d = tempNum - 243;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 274 && tempNum < 305):
            m = "October";
            d = tempNum - 273;
            (isLeapYear) ? d++ : "";
            break;
        case (tempNum >= 305 && tempNum < 335):
            m = "November";
            d = tempNum - 304;
            (isLeapYear) ? d++ : "";
            break;
        default:
            m = "December";
            d = tempNum - 354;
            (isLeapYear) ? d++ : "";
    }

    // create output
    let out = m + " " + d + ", " + y;

    return out;
}


console.log(dateToJulian("1972-11-10"));

console.log(julianToDate(72315));