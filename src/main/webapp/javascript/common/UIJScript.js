/*  Comments here:
 *
 *  Date: 03/11/11
*/

function setFocus(field)
{
    window.setTimeout(function ()
    {
        document.getElementById(field).focus();
    }, 0);  
}
function hideAllTabsExcept(allTabs,id)
{ 
    for (var i = 0;i<allTabs.length;i++)
    {
        var div = document.getElementById(allTabs[i]);
        var href= document.getElementById(allTabs[i]+'Tab'); 
        if (id != allTabs[i]) {
            div.style.display = "none";
            href.className='unselected';
        }else{
            div.style.display = "block";
            href.className='selected'; 
        }
    }
} 
function highLightTab(id)
{
    var element=document.getElementById(id);
    //element.style=
}

function getTableRowCount(tableName)
{
    return document.getElementById(tableName).rows.length;
}

function appendLabel(options)
{
    var cell = options.row.insertCell(options.index);
    var input = document.createElement("label");
    input.innerHTML=options.text;
    input.className =options.className;
    cell.appendChild(input);
}

function appendCheckBox(options)
{ 
    var cell = options.row.insertCell(options.index);
    var input = document.createElement("input");
    input.type = "checkbox";
    input.className =options.className;
    cell.appendChild(input);
}

function appendTextField(options)
{ 
    var cell = options.row.insertCell(options.index);
    var input = document.createElement("input");  
    input.type = "text"; 
    input.maxLength=options.maxLength;
    input.id=options.id;
    input.name=options.name;
    input.tabIndex=options.tabIndex;
    input.className =options.className; 
    
    if(options.fieldType==1)
        input.onkeypress=onlyNumbers;
    else if(options.fieldType==2)
        input.onkeypress=onlyAlphabets;
    else if(options.fieldType==3)
        input.onkeypress=onlyAlphabetsAndSpace;
    else if(options.fieldType==4)
        input.onkeypress=onlyAlphaNumeric;
    else if(options.fieldType==5)
        input.onkeypress=onlyAlphaNumericAndSpace;
    else if(options.fieldType==6)
        {  
        input.onkeypress=function(){return validatePercentageOnChar(this,event);}
        }

    if(options.constraintType==1)
    { 
        input.onblur=function(){
            return validateMarks(options.firstField,options.secondField,options.thirdField,options.start);
        }
    }  
    cell.appendChild(input);
}

function appendDropDown(options)
{
    var dropDownBox = document.createElement("select");
    var cell = options.row.insertCell(options.index);

    for(var i = 0 ; i < options.labels.length; i++)
    {
        dropDownBox.options[i]= new Option( options.labels[i],
                                            options.values[i]);
    } 
    dropDownBox.id=options.id;
    dropDownBox.name=options.name;
    dropDownBox.tabIndex=options.tabIndex;
    dropDownBox.className =options.className; 
    dropDownBox.selectedIndex=options.selectedIndex;
    dropDownBox.onclick=function(){canEdit(dropDownBox.id);}
    cell.appendChild(dropDownBox);
}

function deleteTableRow(tableID)
{
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && true == chkbox.checked) {
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
    }catch(e) {
        alert(e);
    }
}

function homePage()
{ 
    window.location="home.html";
}
