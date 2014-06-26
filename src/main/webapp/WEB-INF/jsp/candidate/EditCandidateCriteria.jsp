<%-- 
    Document   : EditCandidateCriteria
    Created on : Apr 3, 2012, 4:54:41 PM
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
        <title><spring:message code="editcandidate.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>

        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
        function submitEditCandForm() { 
            if(isEmpty(document.getElementById('candidateId').value)) { 
                alert('Please supply Candidate Id.');
                setFocus('candidateId');
                return false;
            }
            return true;
        }
        function resetEditCandFields() { 
            document.forms[0].reset();
            setFocus('candidateId');
        }
        </script>
    </head>
    <body onLoad="setFocus('candidateId')">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="editcandidate.title"/>
            </center>
        </div> 
        <form:form name="ecForm" method="post" commandName="editCandCriteria">
            <div id="candidate" style="color:black;display: block;">
                <div style="margin-top:-2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="editcandidate.option1.message"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="candidateId" class="firstField">
                        <spring:message code="editcandidate.cd.candidateId"/>:*
                        <form:input id="candidateId" cssClass="firstFieldInput"
                                    onkeypress="return onlyAlphaNumeric(event);" tabindex="1"
                                    maxlength="15" path="candidateId"/><br>
                    </form:label>
                    <br><br>
                    <div class="typeBButn">
                        <%@ include file="EditCandButtons.jsp" %>
                    </div>
                </div>
           </div>
        </form:form>
        <div style="margin-top:-10%">
            <jsp:include page="../common/Footer.jsp"></jsp:include>
        </div>
    </body>
</html>
