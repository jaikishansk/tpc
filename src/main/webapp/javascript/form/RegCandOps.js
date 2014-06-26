// AJAX function to avoid creation of candidates with duplicate id
function checkIfCandidateExists(candidateIdField) {
    var userType=document.getElementById('userType').value;
    var editMode=document.getElementById('editFlag').value;
    var candidateType=parseInt(document.getElementById('candidateType').value);

    // Applicable only for Admin, non editing mode
    if(userType==2||userType==3)
        return;
    if(editMode=='true')
        return;
    var candidateId=document.getElementById(candidateIdField).value;
    if(isEmpty(candidateId))
        return;
    if(candidateIdField=='ugId' && candidateType==3)
        return;

    $.ajax({
        type: "POST",
        url: "dynacandexistsinfo.html",
        data: "candidateId=" + candidateId,
        success: function(response)
        {
            showCandidateExistsInfo(candidateId,response);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    });
}
// Error if candidate with given Id already exists
function showCandidateExistsInfo(candidateId,response) {
    var candidateType=parseInt(document.getElementById("candidateType").value);
    var fields=new Array("ug","pg");
    if(response=='true')
    {
        alert("Candidate with Id '"+candidateId+"' already exists.");
        hideAllTabsExcept(allTabs,fields[candidateType-2]);
        setFocus(fields[candidateType-2]+'Id');
        return false;
    }
    return true;
}
// UG/PG qualifications
function reset1DFields(fieldsArray) {
    for(var i=0;i<fieldsArray.length;i++)
        document.getElementById(fieldsArray[i]).value='';
}
// Sec edu rows
function reset2DFields(fieldsArray,times) {
    var i,j;
    for(i=0;i<times;i++)
        for(j=0;j<fieldsArray.length;j++)
            document.getElementById(fieldsArray[j]+i).value='';
}
// Sem rows
function resetSemFields(fieldsArray,times) {
    var i,j;
    for(i=0;i<times;i++)
        for(j=0;j<fieldsArray.length;j++) {
            document.getElementById(fieldsArray[j]+2*i).value='';
            document.getElementById(fieldsArray[j]+(2*i+1)).value='';
        }
}
// Reset fields when clear button is pressed
function resetRegCandFields() {
    var i;
    var userType=parseInt(document.getElementById("userType").value);
    var candidateType=parseInt(document.getElementById("candidateType").value);
    var editMode=document.getElementById("editFlag").value;
    var ugRows=parseInt(document.getElementById("ugRows").value);
    var pgRows=parseInt(document.getElementById("pgRows").value);

    reset1DFields(basicFields);
    // Do not reset when candidates (UG/PG) are reseting fields
    if((userType==2 || userType==3)&& (editMode=='true'))
    {}
    else{
        reset2DFields(secEduFields,2);
        reset1DFields(ugFields);
        resetSemFields(ugSemFields,ugRows/2);
        if(userType==3||userType==1)
        {
            if(candidateType==3)
            {
                reset1DFields(pgFields);
                resetSemFields(pgSemFields,pgRows/2);
            }
        }
    } 
    // Reset placement pref options
    var candRegForm=document.getElementById('regcandform'); 
    with(candRegForm) {
            for(i=0;i<placementPref.length;i++) {  
                placementPref[i].checked=false;
            }    
    }  
    hideAllTabsExcept(allTabs,allTabs[currentTab]);
    setFocus(firstFields[currentTab]);
}

// In case of edit mode for UG/PG candidates
function readOnly1DFields(fieldsArray)
{
    var i;
    for(i=0;i<ugFields.length;i++)
        document.getElementById(fieldsArray[i]).setAttribute("readonly", "true");
}
function readOnlySEduFields(fieldsArray)
{
    var i,j;
    for(i=0;i<2;i++)
            for(j=0;j<fieldsArray.length;j++)
                document.getElementById(fieldsArray[j]+i).setAttribute("readonly", "true");
}
function readOnlySemFields(fieldsArray,times)
{
    var i,j;
    for(i=0;i<times;i++)
        for(j=0;j<fieldsArray.length;j++)
        {
            document.getElementById(fieldsArray[j]+2*i).setAttribute("readonly", "true");
            document.getElementById(fieldsArray[j]+(2*i+1)).setAttribute("readonly", "true");
        }
}
// function to make qualification data read only if UG/PG is in edit mode
function makeQualFieldsReadOnly()
{
    var userType=parseInt(document.getElementById("userType").value);
    var ugRows=parseInt(document.getElementById("ugRows").value);
    var pgRows=parseInt(document.getElementById("pgRows").value);

    // Admin can always edit
    if(userType==1)
        return ;

    readOnlySEduFields(secEduFields);
    readOnly1DFields(ugFields);
    readOnlySemFields(ugSemFields,ugRows/2);
    // PG candidate
    if(userType==3)
    {
        readOnly1DFields(pgFields);
        readOnlySemFields(pgSemFields,pgRows/2);
    }
}

// select placment pref check boxes
function initCB(index) {   
    document.getElementById("pp"+index).checked=true;
}
            
// Pre populate Ids in UG/PG mode, else candidates can overwrite/create
// other candidates data
function setId()
{
    var userType=parseInt(document.getElementById('userType').value);
    // For admin Id fields are empty
    if(userType==1)
        return ;
    else if(userType==2)
        document.getElementById('ugId').value=document.getElementById('userId').value;
    else
        document.getElementById('pgId').value=document.getElementById('userId').value;
}
// Initialize form on start up
function initRCECForm()
{
    var userType=parseInt(document.getElementById('userType').value);
    var editMode=document.getElementById("editFlag").value;

    // For validation
    initReqdRCandFields();
    // Prepopulate Id in UG/PG candidate
    if(userType==2||userType==3)
        setId();
    // UG/PG cannot edit qualifications
    if((userType==2||userType==3)&&(editMode=='true'))
        makeQualFieldsReadOnly(); 
    // First tab as open
    hideAllTabsExcept(allTabs,'personal');
    setFocus('firstName');
}

// submit form
function submitRCandForm(userType)
{
    if(!validateRegCandForm(userType))
    {
        hideAllTabsExcept(allTabs,allTabs[currentTab]);
        setFocus(firstFields[currentTab]);
        return false;
    }
    // only admin can edit marks
    var myForm=document.getElementById('regcandform');
    myForm.action='registercandidate.html';
    // Confirmation from UG/PG students
    if(userType!=1)
    {
        var confirmSubmit = confirm("Marks cannot be edited after submitting the form. Do you wish to submit the form?");
        if(confirmSubmit==true)
        {
            myForm.submit();
            return true;
        }
        else
        {
            hideAllTabsExcept(allTabs,allTabs[currentTab]);
            setFocus(firstFields[currentTab]);
            return false;
        }
    }
    else
    {
        myForm.submit();
        return true;
    }
}
// Register and new option, available only to admin
// Apply all validation rules as Register option
function submitRCandFormAndNew(userType)
{
    if(!validateRegCandForm(userType))
    {
        hideAllTabsExcept(allTabs,allTabs[currentTab]);
        setFocus(firstFields[currentTab]);
        return false;
    }
    var myForm=document.getElementById('regcandform');
    myForm.action='registercandidateandnew.html';
    myForm.submit();
    return true;
}
// Edit candidate
function submitECandForm(userType)
{
    // For Admin validate all fields
    if(userType==1)
    {
        if(!validateRegCandForm(userType))
        {
            if(!isEmpty(firstFields[currentTab]))
                setFocus(firstFields[currentTab]);
            return false;
        }
        var myForm=document.getElementById('regcandform');
        myForm.action='edit.html';
        myForm.submit();
        return true;
    }
    // UG, PG students
    if(!validateReqdFields(requiredBasicFields))
        return false;
    if(!validateDOB())
        return false;
    return true;
}

