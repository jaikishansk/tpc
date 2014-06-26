<%-- 
    Document   : DeleteCandidate
    Created on : Feb 13, 2012, 11:57:22 AM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="deletecandidate.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/validation/DCFormValidation.js"></script>
        
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">

        <script type="text/javascript">
            function confirmDelete()
            { 
                return confirm('All information of given candidate will be deleted permanently. Are you sure to delete the records?');
            }
            function validateCandidateId()
            {
                if(document.getElementById("candidateId").value=="")
                {
                    alert('Please supply Candidate Id.');
                    setFocus('candidateId')
                    return false;
                }
                return true;
            }
            function clearDCForm()
            {
                document.forms[0].reset();
                setFocus('candidateId');
                return false;
            }
        </script>
    </head>
    
    <body onLoad="setFocus('candidateId');">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="deletecandidate.title"/>
            </center>
        </div>
        
        <form:form name="dcForm" method="post" commandName="deleteCriteria" onSubmit="return confirmDelete();">
            <div id="candidate" style="color:black;display: block;">
                <div class="tabHeader">
                        <center><spring:message code="deletecandidate.option1.message"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="candidateId" class="firstField">
                        <spring:message code="deletecandidate.cd.candidateId"/>:*
                        <form:input id="candidateId" cssClass="firstFieldInput"
                                    onkeypress="return onlyAlphabets(event);" tabindex="1"
                                    maxlength="15" path="candidateId"/><br>
                    </form:label>
                    <br><br>
                    <div class="typeBButn">
                        <%@ include file="DCButtons.jsp" %>
                    </div>
                </div> 
           </div>
        </form:form>
    </body>
</html>
