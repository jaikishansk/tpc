function initializeNCForm() { 
    initReqdNCFields();
    hideAllTabsExcept(allTabs,'candidate');
    setFocus('candidateId');
}

function clearNCForm() {
    var i;
    var ncFieldsToClear=new Array("candidateId","candMessage", "classNotificationPref","candNotificationPref",
                                                        "passingYear", "classMessage","startPercent","endPercent",
                                                        "livekt","numPlacements");
    for(i=0;i<ncFieldsToClear.length;i++)
        document.getElementById(ncFieldsToClear[i]).value=''; 
    hideAllTabsExcept(allTabs,allTabs[currentTab]); 
    setFocus(firstNCFields[currentTab]);
    return false;
}

function submitNCandForm() { 
    document.getElementById('notificationScope').value=1;
    return validateNCandForm(); 
}

function submitNClassForm() {
    document.getElementById('notificationScope').value=2;
    return validateNClassForm(); 
}

function showPMCount(response) {
    alert('Potential matches for given criteria: '+response);
}

function findNCPotentialMatch() {
    var courseId=$('#courseId').val();
    var streamId=$('#streamId').val();
    var passingYear=$('#passingYear').val();
    if(isEmpty(passingYear)){
        alert("Please provide passing year.");
        return ;
    }
    var startPercent=$('#startPercent').val(); 
    var endPercent=$('#endPercent').val(); 
    var livekt=$('#livekt').val();
    var numPlacements=$('#numPlacements').val();  
    var param =  {  courseId: courseId, streamId:streamId, passingYear: passingYear, startPercent:startPercent,
                            endPercent:endPercent, livekt:livekt, numPlacements:numPlacements }; 
     $.ajax({
        type: "POST",
        url: "potentialNCMatch.html",
        data: param,
        success: function(response) { 
            showPMCount(response);
        },
        error: function(e){
            alert('Error: ' + e);
        }
    }); 
}