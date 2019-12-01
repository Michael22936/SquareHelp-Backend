// used to convert dates from the sql YYYY-MM-DD to MM-DD-YYYY
//  create a span with the class of "dates" and load this file to the page.
//  Date will automatically be converted.

let sqlDates = document.getElementsByClassName("dates");

const changeDateFormat = (dateStr) => {
    let temp = dateStr.split("-");
    return temp[1] + "-" + temp[2] + "-" + temp[0];
}

const convertDates = () => {
    let temp = "";
    for (i = 0; i < sqlDates.length; i++) {
        temp = changeDateFormat(sqlDates[i].innerText);
        sqlDates[i].innerHTML = temp;
        temp = "";
    }
}

convertDates();