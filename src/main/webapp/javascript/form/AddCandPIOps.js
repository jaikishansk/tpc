/* 
 * 22/7/12
 * Author: Jaikishan
 */

function ajaxCandIdDetails(evt) {
    var keycode;
    if(window.event)
        keycode=window.event.keyCode;
    else if(evt)
        keycode=e.which;
    else
        return true; 
    if(keycode==9) {
        getCandidateDetails();
        return true;
    }
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
function getCandidateDetails()
 {
    var candidateId=$('#candidateId').val();
    if(isEmpty(candidateId))
        return;
    $.ajax({
        type: "POST",
        url: "dynaplacementinfo.html",
        data: "candidateId=" + candidateId,
        success: function(response)
        {
            populateFields(response);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    });
 }
 function populateFields(response)
 {  
     if(isEmpty(response)) { 
        alert('Candidate with given id is not registered.');
        clearPIForm();
        setFocus('candidateId');
        return;
     }
    if(response=="exceeds") { 
        alert("Adding placement info for this candidate will exceed maximum placement offers. Please provide a different Candiate Id.");
    }
     var data=response.split(",");
     for(var i=0;i<data.length;i++)
        document.getElementById(fieldsToPopulate[i]).value=data[i];
} 

function initPIForm()
{
    initReqdPIFields();
    hideAllTabsExcept(allTabs,'course');
    setFocus('candidateId');
    document.getElementById('candidateId').onkeydown=ajaxCandIdDetails;
}
function validatePIForm()
{
    var dateOfPlacement=document.getElementById('dateOfPlacement').value;
    var dateOfJoining=document.getElementById('dateOfJoining').value;
    var passingYear=parseInt(document.getElementById('passingYear').value);
    
    if(!fieldsCheck())
        return false;
    if(!placementDateCheck(dateOfPlacement,passingYear))
    {
        hideAllTabsExcept(allTabs,'company');
        setFocus('dateOfPlacement');
        return false;
    }
    if(!joiningDateCheck(dateOfJoining,dateOfPlacement,passingYear))
    {
        hideAllTabsExcept(allTabs,'company');
        setFocus('dateOfJoining');
        return false;
    }
    return true;
}
function submitPIForm()
{
    if(!validatePIForm())
    {
        hideAllTabsExcept(allTabs,allTabs[currentTab]);
        setFocus(firstPIFields[currentTab]);
        return false;
    } 
    return true;
}
function submitPIFormAndNew()
{
    if(!validatePIForm())
    {
        hideAllTabsExcept(allTabs,allTabs[currentTab]);
        setFocus(firstPIFields[currentTab]); 
        return false;
    }
    var myForm=document.getElementById('candidatePI');
    myForm.action='addcandpiandnew.html';
    myForm.submit();
    return true;
}
function clearPIForm()
{
    var i;
    var piFieldsToClear=new Array("candidateId","courseId","streamId",
                                    "passingYear","firstName",
                                    "lastName","dateOfPlacement","package",
                                    "bondPeriod","dateOfJoining");
    for(i=0;i<piFieldsToClear.length;i++)
        document.getElementById(piFieldsToClear[i]).value='';
    hideAllTabsExcept(allTabs,allTabs[currentTab]);
    setFocus(firstPIFields[currentTab]);
    return false;
}
