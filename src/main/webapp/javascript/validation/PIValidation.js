function initReqdPIFields()
{
    requiredPIFields=new Array("candidateId","passingYear","courseId",
                               "streamId","companyId","dateOfPlacement");
}
function fieldsCheck()
{
    if(!validateRequiredFields(requiredPIFields))
    {
        alert('Please provide mandatory fields in all tabs indicated by an asterick.');
        hideAllTabsExcept(allTabs,'course');
        setFocus('candidateId');
        return false;
    }
    return true;
}
function placementDateCheck(dopField,passingYearField)
{
    var dop=document.getElementById(dopField).value;
    var passingYear=document.getElementById(passingYearField).value; 
    if(isEmpty(dop))
        return true;
    if(!isValidDate(dop))
    {
        alert('Date of placement should be in the format dd/mm/yyyy. For eg. 19/07/1995.');
        setFocus(dopField);
        return false;
    }  
    if(passingYear-getYear(dop)>2)
    {
        alert('Difference between year of placement and passing year can be at most 2.');
        setFocus(dopField); 
        return false;
    }
    dop=new Date(getYear(dop),
                     getMonth(dop)-1,
                     getDay(dop));
    dop.setHours(0, 0, 0, 0);
    var todaysDate=new Date();
    todaysDate.setHours(0, 0, 0, 0);  
    if(dop>todaysDate)
    {
        alert("Date of placement must be less or equal to today's date.");
        setFocus(dopField);
        return false;
    }
    return true;
}
function joiningDateCheck(dojField,dopField,passingYearField)
{
    var dateOfJoining=document.getElementById(dojField).value;
    var dateOfPlacement=document.getElementById(dopField).value;
    var passingYear=document.getElementById(passingYearField).value; 
    if(isEmpty(dateOfJoining))
        return true;
    if(!isValidDate(dateOfJoining))
    {
        alert('Date of joining should be in the format dd/mm/yyyy. For eg. 19/07/1995.');
        setFocus(dojField);
        return false;
    }
    if(!isEmpty(dateOfJoining))  
    {
        if(!(getYear(dateOfJoining)>=passingYear))
        {
            alert('Year of joining must be greater than or equal to passing year.');
            setFocus(dojField);
            return false;
        } 
        var dop=new Date(getYear(dateOfPlacement),
                         getMonth(dateOfPlacement)-1,
                         getDay(dateOfPlacement));
        dop.setHours(0, 0, 0, 0);

        var doj=new Date(getYear(dateOfJoining),
                         getMonth(dateOfJoining)-1,
                         getDay(dateOfJoining));
        dop.setHours(0, 0, 0, 0);  
        if(doj<dop)
        {
            alert('Date of joining must be greater than date of placement.'); 
            setFocus(dojField);
            return false;
        }
    }
    return true;
}

function validateEditPIReqdFields()
{
   var table=document.getElementById("editPITable");
   var rowCount=table.rows.length;
   alert('rowCount='+rowCount);
   var i,j;
   for(i=0;i<rowCount-1;i++)
   {
       for(j=0;j<requiredEditPIFields.length;j++)
       {
           alert('fieldId='+requiredEditPIFields[j]+i);
            if(!validateRequiredFields(requiredEditPIFields[j])+i)
            {
                alert('Please provide mandatory fields indicated by an asterick.');
                setFocus('companyId0');
                return false;
            }
       }
    }
    return true;
}

