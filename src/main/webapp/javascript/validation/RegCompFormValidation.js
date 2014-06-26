function validateReqdFields(fields) { 
    var counter;

    for(counter=0;counter<fields.length;counter++) { 
        if(isEmpty(document.getElementById(fields[counter]).value)) {
            alert('Please provide mandatory fields in all tabs indicated by an asterick.');
            hideAllTabsExcept(allTabs,allTabs[currentTab]);
            setFocus(firstFields[currentTab]);
            return false;
        }
    }
    alert('validate form end');
    return true;
}

function submitRegCompanyForm() { 
    return validateReqdFields(reqdFields); 
}