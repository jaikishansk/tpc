function assignNonSecFieldNames(prefix,index)
{
    var element =document.getElementById(prefix+'Id');
    element.setAttribute("name", "quals["+index+"].primaryKey.qualRollNo");
    element=document.getElementById(prefix+'Course');
    element.setAttribute("name", "quals["+index+"].course");
    element=document.getElementById(prefix+'Stream');
    element.setAttribute("name", "quals["+index+"].stream");
    element=document.getElementById(prefix+'PY');
    element.setAttribute("name", "quals["+index+"].passingYear");
    element=document.getElementById(prefix+'Univ');
    element.setAttribute("name", "quals["+index+"].university");
    element=document.getElementById(prefix+'LiveKT');
    element.setAttribute("name", "quals["+index+"].liveKT");
    element=document.getElementById(prefix+'PastKT');
    element.setAttribute("name", "quals["+index+"].pastKT");
    element=document.getElementById(prefix+'KtSubjects');
    element.setAttribute("name", "quals["+index+"].ktSubjects");
}
function assignSemFieldValues()
{
    var i,j;
    var prefix;
    var userType=document.getElementById("userType").value;
    //populate sem fields
    for(i=0;i<semMMData.length;i++)
    {
        prefix=(i==ugDataIndex)?'ug':'pg';
        for(j=0;j<semMMData[i].length;j++)
        {
            document.getElementById(prefix+'SemMM'+j).value=semMMData[i][j];
            if(userType!=1)
                document.getElementById(prefix+'SemMM'+j).setAttribute("readonly", "true");
            document.getElementById(prefix+'SemOM'+j).value=semOMData[i][j];
            if(userType!=1)
                document.getElementById(prefix+'SemOM'+j).setAttribute("readonly", "true");
            document.getElementById(prefix+'SemPM'+j).value=semPMData[i][j];
            if(userType!=1)
                document.getElementById(prefix+'SemPM'+j).setAttribute("readonly", "true");
        }
    }
}
function assignQualFieldValues()
{
    var i,j;
    var prefix;
    var userType=document.getElementById("userType").value;
    var seFieldIds=new Array("secondaryId","secondaryCourse","secondaryStream",
                             "secondaryPY","secondaryUniv","secondaryMM",
                             "secondaryOM","secondaryPM");
    var nonSEFieldIds=new Array("Id","Course","Stream","PY","Univ",
                                "LiveKT","PastKT","KtSubjects");

    for(i=0;i<secQualData.length;i++)
    {
        for(j=0;j<secQualData[i].length;j++)
        {
            document.getElementById(seFieldIds[j]+i).value=secQualData[i][j];
            if(userType!=1)
                document.getElementById(seFieldIds[j]+i).setAttribute("readonly", "true");
        }
    }

    for(i=0;i<nonsecQualData.length;i++)
    {
        prefix=(i==ugDataIndex)?'ug':'pg';
        for(j=0;j<nonsecQualData[i].length;j++)
        {
             document.getElementById(prefix+nonSEFieldIds[j]).value=nonsecQualData[i][j];
             if(userType!=1)
                document.getElementById(prefix+nonSEFieldIds[j]).setAttribute("readonly", "true");
        }
    }
    assignSemFieldValues();
}
function assignSemFieldNames(prefix,index,times)
{
    var i;

    for(i=0;i<times;i++)
    { 
        var element=document.getElementById(prefix+'Sem'+(2*i));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i)+"].primaryKey.semesterId");
        element =document.getElementById(prefix+'SemMM'+(2*i));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i)+"].maxMarks");
        element=document.getElementById(prefix+'SemOM'+(2*i));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i)+"].marksObtd");
        element=document.getElementById(prefix+'SemPM'+(2*i));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i)+"].percentMarks");
        element =document.getElementById(prefix+'Sem'+(2*i+1));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i+1)+"].primaryKey.semesterId");
        element =document.getElementById(prefix+'SemMM'+(2*i+1));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i+1)+"].maxMarks");
        element=document.getElementById(prefix+'SemOM'+(2*i+1));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i+1)+"].marksObtd");
        element=document.getElementById(prefix+'SemPM'+(2*i+1));
        element.setAttribute("name", "quals["+index+"].semesters["+(2*i+1)+"].percentMarks");
    }
}

function addSERow(tableId)
{
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;
    var index;
    var fieldName,fieldId;
    var userType=parseInt(document.getElementById("userType").value);
    var candidateType=parseInt(document.getElementById("candidateType").value);
    var ugRows=parseInt(document.getElementById("ugRows").value);
    var pgRows=parseInt(document.getElementById("pgRows").value);
    if(rowCount<=3)
    {
        var row = table.insertRow(rowCount);
        appendCheckBox({row:row,index:0});
        index=(getTableRowCount(tableId)-2);
        //id
        fieldName='quals['+index+'].primaryKey.qualRollNo';
        fieldId='secondaryId'+index;
        appendTextField({row:row,index:1,maxLength:15,id:fieldId,name:fieldName,tabIndex:1,
                         className:'eduTbleTxt',
                         fieldType:4});
        //course
        fieldName='quals['+index+'].course';
        fieldId='secondaryCourse'+index;
        appendDropDown({row:row,index:2,labels:new Array("SSC","HSC","Diploma","Other"),
                        values:new Array("1","2","3","4"),id:fieldId,name:fieldName,
                        tabIndex:2,className:'eduTbleDD',selectedIndex:index});
        //stream
        fieldName='quals['+index+'].stream';
        fieldId='secondaryStream'+index;
        appendDropDown({row:row,index:3,labels:new Array("PCM","PCB","CSE","IT","Mech","Extc","Elect"),
                        values:new Array("1","2","3","4","5","6","7"),id:fieldId,name:fieldName,
                        tabIndex:3,className:'eduTbleDD'});
        //passing year
        fieldName='quals['+index+'].passingYear';
        fieldId='secondaryPY'+index;
        appendTextField({row:row,index:4,maxLength:4,id:fieldId,name:fieldName,tabIndex:4,
                        className:'eduTbleNum',
                        fieldType:1});
         //univ
         fieldName='quals['+index+'].university';
         fieldId='secondaryUniv'+index;
         appendTextField({row:row,index:5,maxLength:50,id:fieldId,name:fieldName,tabIndex:5,
                        className:'eduTbleTxt',
                        fieldType:3});
         //MM
         fieldName='quals['+index+'].maxMarks';
         fieldId='secondaryMM'+index;
         appendTextField({row:row,index:6,maxLength:4,id:fieldId,name:fieldName,tabIndex:6,
                          className:'eduTbleNum',constraintType:1,
                          firstField:'secondaryMM',secondField:'secondaryOM',
                          thirdField:'secondaryPM',start:index,fieldType:1});
         //MO
         fieldName='quals['+index+'].obtdMarks';
         fieldId='secondaryOM'+index;
         appendTextField({row:row,index:7,maxLength:4,id:fieldId,name:fieldName,
                          tabIndex:7,className:'eduTbleNum',constraintType:1,
                          firstField:'secondaryMM',secondField:'secondaryOM',
                          thirdField:'secondaryPM',start:index,fieldType:1});
         //PM
         fieldName='quals['+index+'].percentMarks';
         fieldId='secondaryPM'+index;
         appendTextField({row:row,index:8,maxLength:6,id:fieldId,name:fieldName,tabIndex:8,
                          className:'eduTbleNum',constraintType:1,
                          firstField:'secondaryMM',secondField:'secondaryOM',
                          thirdField:'secondaryPM',start:index,fieldType:6});
        setFocus('secondaryId'+index);
        secEduCount++;
        assignNonSecFieldNames("ug",secEduCount);
        assignSemFieldNames("ug",secEduCount,ugRows/2);
        if((userType==1 && candidateType==3) || userType==3)
        {
            assignNonSecFieldNames("pg",secEduCount+1);
            assignSemFieldNames("pg",secEduCount+1,pgRows/2);
        } 
    } 
} 
function ifAtLeast2SEQual(tableId)
{
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;
    var i,noOfRowsChecked=0;
    for(i=0; i<rowCount; i++)
    {
        var row = table.rows[i];
        var chkbox = row.cells[0].childNodes[0];
        if(null != chkbox && true == chkbox.checked)
            noOfRowsChecked++;
    }
    return (rowCount-noOfRowsChecked)>=3?true:false;
}

function anyRowSelected(tableId)
{
    var table = document.getElementById(tableId); 
    for(var i=0; i<table.rows.length; i++)
    { 
        var row = table.rows[i];
        var chkbox = row.cells[0].childNodes[0];
        if(null != chkbox && true == chkbox.checked)
            return true;
    }
    return false;
}

function deleteSERow(tableId)
{
    var i,row,chkbox;
    var table = document.getElementById(tableId);
    var rowCount = table.rows.length;

    if(!anyRowSelected(tableId))
    {
        alert('Please select a row to delete.');
        return;
    } 
    if(!ifAtLeast2SEQual(tableId))
    {
        alert('At least 2 secondary education qualifications must be present.');
        hideAllTabsExcept(allTabs,'secondary');
        setFocus('secondaryId0');
        return;
    } 
    var userType=document.getElementById("userType").value;
    if(userType==1&&(document.getElementById('editFlag').value)=='true')
    {
        var numRowsDeleted=0; 
        for(i=0; i<rowCount; i++)
        {
            row = table.rows[i];
            chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && true == chkbox.checked)
            {
                table.deleteRow(i);
                secQualData.splice(i-1,1);
                secEduCount--;
                numRowsDeleted++;
                rowCount--;
                i--;
            }
        } 
        rowCount=table.rows.length;
        for(i=0;i<rowCount-numRowsDeleted;i++)
        {
            table.deleteRow(table.rows.length-1);
            secEduCount--;
        }
        for(i=0;i<secQualData.length;i++)
            addSERow(tableId); 
        assignQualFieldValues(); 
    }
    else
    {
        var j,cell; 
        var rowsData=new Array();
        secEduCount--;
        deleteTableRow(tableId);
        rowCount=table.rows.length-1; 
        for(i=rowCount;i>0;i--)
        {
            rowsData[i-1]=new Array(8);
            row=table.rows[i];
            for(j=1;j<=8;j++)
            {
                cell=row.cells[j];
                rowsData[i-1][j-1]=cell.firstChild.value;
            }
            table.deleteRow(i);
            secEduCount--; 
        }
        addSERow(tableId);
        addSERow(tableId);
        rowCount=table.rows.length-1;
        for(i=0;i<rowCount;i++)
        {
            row=table.rows[i+1];
            for(j=1;j<=8;j++)
            {
                cell=row.cells[j];
                cell.firstChild.value=rowsData[i][j-1];
            }
        }
    } 
    //setFocus('secondaryId0');
}
