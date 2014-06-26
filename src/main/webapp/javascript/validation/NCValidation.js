function initReqdNCFields()
{
    requiredNCandFields=new Array("candidateId","candMessage");
    requiredNClassFields=new Array("courseId","streamId","passingYear","classMessage");
}
function validateNCandForm()
{ 
    if(!validateRequiredFields(requiredNCandFields))
    {
        alert('Please provide mandatory fields in all tabs indicated by an asterick.');
        hideAllTabsExcept(allTabs,'candidate');
        setFocus('candidateId');
        return false;
    }
    return true;
}
function validateNClassForm()
{
     if(!validateRequiredFields(requiredNClassFields))
    {
        alert('Please provide mandatory fields in all tabs indicated by an asterick.');
        hideAllTabsExcept(allTabs,'class');
        setFocus('passingYear');
        return false;
    }
    return true;
}