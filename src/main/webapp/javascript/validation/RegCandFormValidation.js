function initReqdRCandFields()
{
    requiredBasicFields=new Array("firstName","lastName","gender",
                                  "dateOfBirth","primEmail","primCell",
                                  "primLandLine","addrLine1","city","state",
                                  "pin");
    requiredSecEduFields=new Array("secondaryId","secondaryCourse",
                                   "secondaryStream","secondaryPY","secondaryUniv",
                                   "secondaryMM","secondaryOM","secondaryPM");
    requiredUGFields=    new Array("ugId","ugCourse","ugStream","ugPY","ugUniv",
                                   "ugLiveKT","ugPastKT");
    requiredPGFields=    new Array("pgId","pgCourse","pgStream","pgPY","pgUniv",
                                   "pgLiveKT","pgPastKT");
    requiredUGSemFields= new Array("ugSemMM","ugSemOM","ugSemPM");
    requiredPGSemFields= new Array("pgSemMM","pgSemOM","pgSemPM");
}

function validateReqdFields(fields)
{
    var counter;

    for(counter=0;counter<fields.length;counter++)
    {
        if(isEmpty(document.getElementById(fields[counter]).value))
        {
            alert('Please provide mandatory fields in all tabs indicated by an asterick.');
            hideAllTabsExcept(allTabs,allTabs[currentTab]);
            setFocus(firstFields[currentTab]);
            return false;
        }
    }
    return true;
}
function validateReqdSEduFields(tableId)
{
    var counter,nSecEduRows;
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;

    for(nSecEduRows=0;nSecEduRows<rowCount-1;nSecEduRows++)
    {
        for(counter=0;counter<requiredSecEduFields.length;counter++)
        {
            if(document.getElementById(requiredSecEduFields[counter]+nSecEduRows).value=="")
            {
                alert('Please provide secondary education fields indicated by an asterick.');
                hideAllTabsExcept(allTabs,'secondary');
                setFocus(firstFields[1]);
                return false;
            }
        }
    }
    return true;
}
function validateRegCandReqdFields(userType)
{
    var candidateType=parseInt(document.getElementById("candidateType").value);
    if(!validateReqdFields(requiredBasicFields))
        return false;
    if(!validateReqdSEduFields('seduDataTable'))
        return false;
    if(!validateReqdFields(requiredUGFields))
        return false;
    if(userType!=2&&candidateType!=2)     // Not a UG Student
    {
        if(!validateReqdFields(requiredPGFields))
            return false;
    }
    return true;
}

function validateSEduBR1(tableId)
{
    var table = document.getElementById(tableId); 
    var rowCount = table.rows.length;
    var loopIndexA,loopIndexB;

    if(rowCount<3)
    {
        alert('Please provide SSC/HSC details.');
        hideAllTabsExcept(allTabs,'secondary');
        setFocus('secondaryId0');
        return false;
    }
    else
    {
        for(loopIndexA=0;loopIndexA<rowCount-1;loopIndexA++)
        { 
            var courseA=document.getElementById('secondaryCourse'+loopIndexA).value;
            for(loopIndexB=loopIndexA+1;loopIndexB<rowCount-1;loopIndexB++)
            {
                var courseB=document.getElementById('secondaryCourse'+loopIndexB).value;
                if(parseInt(courseA)==parseInt(courseB))
                {
                    alert('Two courses cannot be same. Please supply different value of course.');
                    hideAllTabsExcept(allTabs,'secondary');
                    setFocus('secondaryId0');
                    return false;
                }
            } 
        }
    }
    return true;
}

function validateSERows(tableId)
{
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;
    var loopIndexA,loopIndexB;
    var sscRow=false;

    if(!validateSEduBR1(tableId))
        return false;
    for(loopIndexA=0;loopIndexA<rowCount-1;loopIndexA++)
    { 
        if(parseInt(document.getElementById('secondaryCourse'+loopIndexA).value)==1)
            sscRow=true;
    } 
    if(!sscRow)
    {
        alert('SSC qualification is mandatory.')
        hideAllTabsExcept(allTabs,'secondary');
        setFocus('secondaryId0');
        return false;
    }
    // SE Rows in the order of SSC, HSC, Diploma
    for(loopIndexA=0;loopIndexA<rowCount-1;loopIndexA++)
    {
        var courseA=document.getElementById('secondaryCourse'+loopIndexA).value;
        for(loopIndexB=loopIndexA+1;loopIndexB<rowCount-1;loopIndexB++)
            {
                var courseB=document.getElementById('secondaryCourse'+loopIndexB).value;
                if(parseInt(courseA)>parseInt(courseB))
                    {
                        alert('Secondary courses must be in the order of SSC,HSC/Diploma.');
                        hideAllTabsExcept(allTabs,'secondary');
                        setFocus('secondaryId0'); 
                        return false;
                    }
            }
    }
    return true;
}

function validateSemFields(tableId,reqdFields)
{  
    var counter,nSemRows;
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;
    var maxSemMarksReqd=rowCount-1;    // Require marks only upto 6th sem for ug and 2 sem for pg
    
    for(nSemRows=0;nSemRows<maxSemMarksReqd;nSemRows++)
    {
        for(counter=0;counter<reqdFields.length;counter++)
        { 
            if(document.getElementById(reqdFields[counter]+2*nSemRows).value==""
                                            ||
               document.getElementById(reqdFields[counter]+(2*nSemRows+1)).value=="")
            {
                alert('Please provide semester marks as indicated by an asterick.');
                return false;
            }
        }
    }
    return true;
}

function validateMarks(maxMarks,obtdMarks,percentMarks,index)
{
    var toCheckmaxMarks=document.getElementById(maxMarks+(index)).value;
    var toCheckobtdMarks=document.getElementById(obtdMarks+(index)).value;
    var toCheckpercentMarks=document.getElementById(percentMarks+(index)).value;

    if(!isEmpty(toCheckmaxMarks)&&parseInt(toCheckmaxMarks)==0)
    {
        alert('Max. marks can not be 0.');
        setFocus(maxMarks+(index));
        return false;
    }
    if(!isEmpty(toCheckmaxMarks)&&!isEmpty(toCheckobtdMarks))
    {
            if(parseInt(toCheckobtdMarks)>parseInt(toCheckmaxMarks))
            {
                alert('Marks obtained can not be greater than maximum marks.'); 
                setFocus(obtdMarks+(index));
                return false;
            }
    }
    if(!isEmpty(toCheckpercentMarks))
    { 
        var percentM = (100*parseInt(toCheckobtdMarks))/parseInt(toCheckmaxMarks);
        if(Math.abs(toCheckpercentMarks-percentM) > 0.01)
        {
            alert('Percent marks, maximum marks and obtained marks do not match.');
            setFocus(percentMarks+(index));
            return false;
        }
    } 
    return true;
}

function validateNumOfKTs(course)
{
    var kts=parseInt(document.getElementById(course).value);
    if(kts>5)
    {
        alert('Max. number of KTs allowed is 5.');
        hideAllTabsExcept(allTabs,allTabs[currentTab]);
        setFocus(course);
        return false;
    }
    return true;
}

function validateKTSubjects(ktValue,ktSub)
{
    var liveKTs=parseInt(document.getElementById(ktValue).value);
    var ktSubjects=String(document.getElementById(ktSub).value); 

    if(ktSub=='ugKtSubjects')
        hideAllTabsExcept(allTabs,'ug');
    else
        hideAllTabsExcept(allTabs,'pg');

    if((liveKTs>0) && isEmpty(ktSubjects))
    {
        alert('KT subjects cannot be empty. Please provide KT subjects.'); 
        setFocus(ktSub);
        return false;
    }
    if(!isEmpty(ktSubjects))
    {
        var temp=new Array();
        temp=ktSubjects.split(','); 
        if(temp.length!=liveKTs)
        {
            alert('Number of live KTs and number of KT subjects do not match. KT subjects should be seperated by comma.');
            setFocus(ktSub);
            return false;
        }
    }
    return true;
}

function canEdit()
{ 
    var userType=parseInt(document.getElementById("userType").value);
    var editMode=document.getElementById("editFlag").value;
    if((userType==2 || userType==3)&& (editMode=='true'))
    {
        alert('Can not edit this element.'); 
        return false;
    } 
    return true;
}

function validateDOB()
{ 
    var date=document.getElementById("dateOfBirth").value;
    var dob = new Date(getYear(date),getMonth(date),getDay(date));
    if(isEmpty(dob))
        return true;
    if(!isValidDate(date))
    {
        alert('Please supply valid date of birth in the format dd/mm/yyyy. For eg. 19/07/1995.');
        hideAllTabsExcept(allTabs,'personal');
        setFocus('dateOfBirth');
        return false;
    } 
    var age = (new Date() - dob) / 1000 / 60 / 60 / 24 / 365;
    if(parseInt(Math.abs(Math.ceil(age)))<15 || parseInt(Math.abs(Math.ceil(age)))>100)
    {
        alert('Age should be between 15 and 100. Please update DOB.');
        hideAllTabsExcept(allTabs,'personal');
        setFocus('dateOfBirth');
        return false;
    }
    var sscPY=parseInt(document.getElementById('secondaryPY0').value); 
    if(!isNaN(sscPY))
    {
        if(!(sscPY>=getYear(date)+15))
        {
            alert('Difference between DOB and SSC passing should be at least 15 years. Please update either.');
            hideAllTabsExcept(allTabs,'personal');
            setFocus('dateOfBirth');
            return false;
        }
    }
    return true;
}

function validatePYDiff(field1,field2,diff,label1,label2)
{ 
    if((field1>field2)||(field2-field1)<diff)
    {
        alert(label2+ ' passing year must be '+ diff + ' years greater than '+
              label1+ ' passing year.')
        return false;
    }
   return true;
}

function validateFuturePassingYear(field)
{
    var py=parseInt(document.getElementById(field).value);
    var date=new Date();
    var year=date.getFullYear();
    if(py>(year+1))
    {
        alert('Passing year can be at most 1 year greater than current year.');
        return false;
    }
    return true;
}

function validatePassingYear(userType)
{
    var dob=document.getElementById("dateOfBirth").value;
    var date=new Date(getYear(dob),getMonth(dob),getDay(dob));
    var year=date.getFullYear();
    var passingYear=parseInt(document.getElementById('secondaryPY0').value);
    if(!isNaN(passingYear)&& !(passingYear>=(year+15)))
    {
        alert('SSC passing year must be at least 15 years greater than DOB.');
        hideAllTabsExcept(allTabs,'secondary');
        setFocus('secondaryPY0');
        return false;
    } 
    var firstPY=parseInt(document.getElementById('secondaryPY0').value);
    var secondPY=parseInt(document.getElementById('secondaryPY1').value);
    var firstCourse=parseInt(document.getElementById('secondaryCourse0').value);
    var secondCourse=parseInt(document.getElementById('secondaryCourse1').value);
    var ugPY=parseInt(document.getElementById('ugPY').value);
    var candidateType=parseInt(document.getElementById("candidateType").value);
    if(userType!=2&&candidateType!=2)
        var pgPY=parseInt(document.getElementById('pgPY').value);
 
    //SSC, HSC qualification
    if(firstCourse==1 && secondCourse==2)
    { 
        if(!validatePYDiff(firstPY,secondPY,2,'SSC','HSC'))
            return false;
        if(!validatePYDiff(secondPY,ugPY,4,'HSC','UG'))
            return false;
        if(userType!=2&&candidateType!=2)
        {
            if(!validatePYDiff(ugPY,pgPY,2,'UG','PG'))
                return false;
        }
    }
    //SSC, Diploma qualification
    if(firstCourse==1 && secondCourse==3)
    {
        if(!validatePYDiff(firstPY,secondPY,3,'SSC','Diploma'))
            return false;
        if(!validatePYDiff(secondPY,ugPY,3,'Diploma','UG'))
            return false;
        if(userType!=2&&candidateType!=2)
        {
            if(!validatePYDiff(ugPY,pgPY,2,'UG','PG'))
                return false;
        }
    }
    //UG,PG passing year can't be greater than current year+1
    if(!validateFuturePassingYear('ugPY'))
        return false;
    if(userType!=2&&candidateType!=2)
    {
        if(!validateFuturePassingYear('pgPY'))
            return false;
    }
    return true;
} 
function validateExamIdUniqueness()
{ 
    var candidateType=parseInt(document.getElementById("candidateType").value);
    var examFields=new Array("secondaryId0","secondaryId1","ugId","pgId");
    var endIndex=candidateType==2?2:3;
    var i,j,examIdA,examIdB;

    for(i=0; i<endIndex; i++)
    {
        examIdA=document.getElementById(examFields[i]).value;
        for(j=i+1;j<=endIndex;j++)
        {
            examIdB=document.getElementById(examFields[j]).value; 
            if(examIdA==examIdB)
            {
                alert('All exam Ids must be unique.');
                return false;
            }
        }
    }
    return true;
}

function validateRegCandForm(userType)
{
    var candidateType=parseInt(document.getElementById("candidateType").value); 
    if(!validateRegCandReqdFields(userType))
        return false;
    if(!validateDOB())
        return false;
    if(!validateSERows('seduDataTable'))
        return false;
    if(!validatePassingYear(userType))
        return false;
    if(!validateExamIdUniqueness())
        return false;
    if(!validateSemFields('ugSemDataTable',requiredUGSemFields))
    {
        hideAllTabsExcept(allTabs,'ug');
        setFocus('ugId');
        return false;
    }
    if(!validateKTSubjects('ugLiveKT','ugKtSubjects'))
    {
        hideAllTabsExcept(allTabs,'ug');
        setFocus('ugId');
        return false;
    }
    if(userType!=2&&candidateType!=2)         // PG student or Admin
    {
        if(!validateSemFields('pgSemDataTable',requiredPGSemFields))
        {
            hideAllTabsExcept(allTabs,'pg');
            setFocus('pgId');
            return false;
        }
        if(!validateFuturePassingYear('pgPY'))
            return false; 
        if(!validateKTSubjects('pgLiveKT','pgKtSubjects'))
        {
            hideAllTabsExcept(allTabs,'pg');
            setFocus('pgId');
            return false;
        }
    } 
    return true;
}
