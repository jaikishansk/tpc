function validateReqdEditPIFields()
{
    var table = document.getElementById('editPITable');
    var rowCount = table.rows.length;
    var times,index;

    for(times=0;times<rowCount-1;times++)
    {
        for(index=0;index<requiredEditPIFields.length;index++)
        { 
            if(isEmpty(document.getElementById(requiredEditPIFields[index]+times).value))
            {
                alert('Please provide all mandatory fields indicated by asterick.');
                setFocus(requiredEditPIFields[index]+times);
                return false;
            }
        }
    }
    return true;
}
function checkDuplicateCompanyId()
{
    var loopIndexA,loopIndexB;
    var table = document.getElementById('editPITable');
    var rowCount = table.rows.length;

    for(loopIndexA=0;loopIndexA<rowCount-1;loopIndexA++)
    {
        var courseA=document.getElementById('companyId'+loopIndexA).value;
        for(loopIndexB=loopIndexA+1;loopIndexB<rowCount-1;loopIndexB++)
        {
            var courseB=document.getElementById('companyId'+loopIndexB).value;
            if(parseInt(courseA)==parseInt(courseB))
            {
                alert('Two companies cannot be same. Please provide different value of companies.');
                setFocus('companyId'+loopIndexB);
                return false;
            }
        }
    }
    return true;
}
function validateDOP()
{
    var i,dop;
    var table=document.getElementById("editPITable");
    var rowCount = table.rows.length; 
    var passingYear=document.getElementById('passingYear').value;
 
    for(i=0;i<rowCount-1;i++)
    {
        dop=document.getElementById('dateOfPlacement'+i).value; 
        if(!placementDateCheck(dop,passingYear))
        {
            setFocus('dateOfPlacement'+i);
            return false;
        }
    }
    return true;
}
function validateDOJ()
{ 
    var i,doj,dop;
    var table=document.getElementById("editPITable");
    var rowCount = table.rows.length;
    var passingYear=document.getElementById('passingYear').value;

    for(i=0;i<rowCount-1;i++)
    {
        dop=document.getElementById('dateOfPlacement'+i).value;
        doj=document.getElementById('dateOfJoining'+i).value; 
        if(!joiningDateCheck(doj,dop,passingYear))
        {
            setFocus('dateOfJoining'+i);
            return false;
        }
    }
    return true;
}

function validateEditPIForm()
{
    if(!validateReqdEditPIFields())
        return false;
    if(!checkDuplicateCompanyId())
        return false;
    if(!validateDOP())
        return false;
    if(!validateDOJ())
        return false; 
    return true;
}
function submitEditPIForm()
{ 
   if(!validateEditPIForm())
    {
        return false;
    }
    return true;
}

function clearEditPIForm()
{
    var i,j;
    var editPIFields=new Array("companyId","dateOfPlacement","salaryOffered",
                               "bondPeriod","dateOfJoining");
    var table=document.getElementById("editPITable");
    var rowCount = table.rows.length;
 
    for(i=0;i<rowCount-1;i++)
    {
        for(j=0;j<editPIFields.length;j++)
            document.getElementById(editPIFields[j]+i).value='';
    } 
}


