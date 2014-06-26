var digitsOnly = /[1234567890]/g;
var isNonBlank = /\S/;
var alphaOnly = /[A-Za-z]/g;
var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
var marksOutOfHundred = /[0-100]/;
var pinCode= /^([1-9])([0-9]){5}$/;
var cellNo=/^\d{10}$/;
var landLine=/^\d{6,12}$/;

function onlyNumbers(evt)
{
    //var e = event || evt; // for trans-browser compatibility
    //var charCode = e.which || e.charCode;
    var charCode = (evt.which) ? evt.which : event.charCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
    return true;
}

function onlyFloat(evt)
{
    var charCode = (evt.which) ? evt.which : event.charCode;
     if (charCode > 31 && (charCode < 48 || charCode > 57) &&(charCode!=46))
            return false;
     return true;
}

function onlyAlphabets(evt)
{
    var charCode = (evt.which) ? evt.which : event.charCode;
    if ((charCode < 65 || charCode > 90)
            && (charCode < 97 || charCode > 123) && charCode > 31)
            return false;
    return true;
}

function onlyDate(evt)
{
    var charCode = (evt.which) ? evt.which : event.charCode;
    if(charCode==47)
        return true;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
    return true;
}

function onlyAlphabetsAndSpace(evt)
{
    var charCode = (evt.which) ? evt.which : event.charCode;
    if ((charCode < 65 || charCode > 90)
            && (charCode < 97 || charCode > 123) 
            && charCode > 32)
            return false;
    return true;
}

function onlyAlphaNumericAndSpace(evt)
{ 
   var keycode = (evt.which) ? evt.which : event.charCode;

    if ((keycode < 48 || keycode > 57) &&   
        (keycode < 65 || keycode > 90)&&
        (keycode < 97 || keycode > 122)&& 
        (keycode>32)) {
        return false;
    }
    else {
    return true;
    }
}

function onlyAlphaNumericSpaceAndComma(evt) {
    var keycode = (evt.which) ? evt.which : event.charCode;

    if(keycode==44)
        return true;

    if ((keycode < 48 || keycode > 57) &&
        (keycode < 65 || keycode > 90)&&
        (keycode < 97 || keycode > 122)&& 
        (keycode>32)) {
        return false;
    }
    else {
    return true;
    }
}


function onlyAlphaNumeric(evt)
{
   var keycode = (evt.which) ? evt.which : event.charCode;

    if ((keycode < 48 || keycode > 57) &&
        (keycode < 65 || keycode > 90)&&
        (keycode < 97 || keycode > 122)&& 
        (keycode>31)) {
        return false;
    }
    else {
    return true;
    }
}

function validatePercentageOnChar(el, e)
{ 
    if (!e) var e = window.event
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
    var character = String.fromCharCode(code);
    // if they pressed esc... remove focus from field...
    if (code == 27) {this.blur();return false;}
 
    // ignore if they are press other keys
    // strange because code: 39 is the down key AND ' key...
    // and DEL also equals .
    if (!e.ctrlKey && code != 9 && code != 8 && code != 36 &&
                      code != 37 && code != 38 &&
                      (code != 39 || (code == 39 && character == "'")) &&
                      code != 40)
    {
        if (character.match(digitsOnly))      //entered char is digit
        { 
            //alert(el.value.search(/[.]/));
            if (el.value.search(/[.]/) == -1)      //no dot present
            {
                if (el.value == "10")                  //if 10 is already entered
                { 
                    if (character == "0") {
                        el.value = el.value + "0.0";
                        return true;
                    }
                    else
                        return false;
                }
                else { 
                    if (el.value.length <= 1)      //only one digit is already there, then display current digit
                        return true;
                    else {
                        el.value = el.value + ".";    //two digits are there, append dot
                        return false;
                    }
                }
            }
            else {                                          //dot already present
                //var percentArray = null; 
                var percentArray = el.value.split(/[.]/);    //get the digits after the dot
                if (percentArray[1].length <= 1) {
                     return true;
                }
                else
                    return false;
                //alert(percentArray[0]);
                /*if (percentArray[0].value.length <= 1)      //if the length of digits after dot is one or less (0), display current digit
                return true;
                else
                return false;           //there are two digits already after dot, do not show current digit    */
            }
        }
        else if (character.match(/[.]/))        //entered char is dot
        {
            //alert(el.value);
            if (el.value.search(/[.]/) == -1)   //if dot is already present
            {
                //alert('false'+el.value);
                if (el.value.length == 0)  //if there is no prior input & dot is pressed as firest char
                    el.value = "0"         //display zero
                return true;               //do not print current dot\
            }
            else {
                //alert('true'+el.value);
                return false;                //display dot
            }
        }
        else       //if anything other than digit or dot pressed return false
            {
            return false;
            }
    }
    else {return false;}

}

function isEmpty(value)
{
    return ( value == "" || value == null);
}

function checkIfValidEmail(email)
{ 
    var atPos = email.indexOf("@");
    var stopPos = email.lastIndexOf(".");
    var validEmail=true;

    if (email == "") {
        validEmail=false;
    }
    if (atPos == -1 || stopPos == -1) {
        validEmail=false;
    }
    if (stopPos < atPos) {
        validEmail=false;
    }
    if (stopPos - atPos == 1) {
        validEmail=false;
    } 
    return validEmail;
} 

// Validates that the input string is a valid date formatted as "dd/mm/yyyy"
function isValidDate(dateString)
{
    // First check for the pattern
    if(!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
        return false;

    // Parse the date parts to integers
    var parts = dateString.split("/");
    var day = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[2], 10);

    // Check the ranges of month 
    if(month <= 0 || month > 12)
        return false;

    var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

    // Adjust for leap years
    if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
        monthLength[1] = 29;
 
    // Check the range of the day
    return day > 0 && day <= monthLength[month - 1];
}

function inRange(field, start,end)
{
    return (field>=start && field <=end);
}

function isValidMobile(mobileNo)
{
    return cellNo.test(mobileNo);
}

function isValidPinCode(pinCodeNo)
{
    return pinCode.test(pinCodeNo);
}

function validateRequiredFields(fields)
{
    var counter;

    for(counter=0;counter<fields.length;counter++)
    {
        if(isEmpty(document.getElementById(fields[counter]).value))
            return false; 
    }
    return true;
}

function getYear(dateString)
{
    var parts = dateString.split("/");
    return parseInt(parts[2], 10);
}

function getMonth(dateString)
{
    var parts = dateString.split("/");
    return parseInt(parts[1], 10);
}

function getDay(dateString)
{
    var parts = dateString.split("/");
    return parseInt(parts[0], 10);
}

function isValidLandLine(landLineNo)
{
    return landLine.test(landLineNo);
}

function validateMobile(field) {
    var mobileNo=document.getElementById(field).value;
    if(isEmpty(mobileNo))
        return true;
    if(!isValidMobile(mobileNo)) {
        alert('Mobile number must of 10 digits.');
        setFocus(field);
        return false;
    }
   return true;
}

function validateMobile(field) {
    var mobileNo=document.getElementById(field).value;
    if(isEmpty(mobileNo))
        return true;
    if(!isValidMobile(mobileNo)) {
        alert('Mobile number must of 10 digits.');
        setFocus(field);
        return false;
    }
   return true;
} 

function validateLandLine(llNum) {
    var landLine=document.getElementById(llNum).value;
    if(isEmpty(landLine))
        return true;
    if(!isValidLandLine(landLine)) {
        alert('Landline number should be at least 6 digits and maximum 12 digits.');
        hideAllTabsExcept(allTabs,'contact');
        setFocus(llNum);
        return false;
    }
    return true;
}

function validateEmail(emailId) {
    var mailId=document.getElementById(emailId).value;
    if(isEmpty(mailId))
        return true;
    if(!checkIfValidEmail(mailId)) {
        alert('Please supply a valid email id.');
        setFocus(emailId);
        return false;
    }
    return true;
}

function validatePinCode(pinCode) {
    var pin=document.getElementById(pinCode).value;
    if(isEmpty(pin))
        return true;
    if(!isValidPinCode(pin)) {
        alert('Please enter valid pin code. First digit starts with any number between 1 to 9 followed by 5 digits.'); 
        setFocus(pinCode);
        return false;
    }
    return true;
} 