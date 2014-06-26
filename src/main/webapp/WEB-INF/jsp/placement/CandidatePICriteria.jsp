<%-- 
    Document   : CandidatePICriteria
    Created on : Feb 21, 2012, 8:52:22 PM
    Author     : Jaikishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="candidatepi.edit.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
        <script type="text/javascript">
            function validatePIC()
            {
                if(isEmpty(document.getElementById("candidateId").value))
                {
                    alert('Please supply Candidate Id.');
                    setFocus('candidateId');
                    return false;
                }
                return true;
            }
            function resetEditPICrteria()
            {
                document.forms[0].reset();
                setFocus('candidateId');
            }
            function submitEditPI()
            {
                if(!validatePIC())
                    return false;
                var form=document.getElementById('PIOpsForm');
                form.action='editcandidatepi.html';
                form.submit();
            }
            function submitViewPI()
            {
                if(!validatePIC())
                    return false;
                var form=document.getElementById('PIOpsForm');
                form.action='viewcandidatepi.html';
                form.submit();
            }
            function confirmDelete() { 
                return confirm('All placement information of given candidate will be deleted permanently. Are you sure to delete the records?');
            }
            function submitDeletePI()
            {
                 if(!validatePIC())
                    return false; 
                if( confirmDelete()) {
                    var form=document.getElementById('PIOpsForm');
                    form.action='deletecandidatepi.html';
                    form.submit();
                }
            }
        </script>
    </head>
    <body onLoad="setFocus('candidateId');">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>

        <br>
        <div class="pageHeading">
            <center>
                <spring:message code="candidatepi.title"/>
            </center>
        </div>
        <form:form name="PIOpsForm" id="PIOpsForm" method="post" commandName="cPICriteria">
            <div class="tabHeader">
                <center><spring:message code="candidatepi.title"/></center>
            </div>
            <div class="tabContent">
                <br>
                <form:label path="candidateId" class="firstField">
                    <spring:message code="candidatepi.course.candidateId"/>:
                    <form:input id="candidateId" cssClass="firstFieldInput"
                                onkeypress="return onlyAlphaNumeric(event);" tabindex="1"
                                maxlength="15" path="candidateId"/><br>
                </form:label>
                <br><br>
                <p class="typeBButn">
                    <%
                        if(session.getAttribute("piop").equals("edit"))
                        {
                    %>
                    <input  style="background:#A52A2A;color:white;" type="submit"
                            onClick="return submitEditPI();"
                            value="<spring:message code="candidatepi.button4.label"/>">
                    <%
                        }
                        else if(session.getAttribute("piop").equals("view"))
                        {
                    %>
                    <input  style="background:#A52A2A;color:white;" type="submit"
                            onClick="return submitViewPI();"
                            value="<spring:message code="candidatepi.button3.label"/>">
                    <%
                        }
                        else if(session.getAttribute("piop").equals("delete"))
                        {
                    %>
                    <input  style="background:#A52A2A;color:white;" type="submit"
                            onClick="return submitDeletePI();"
                            value="<spring:message code="candidatepi.button7.label"/>">
                    <%
                        }
                    %>
                    <input  style="background:#A52A2A;color:white;" type="button"
                            onclick="resetEditPICrteria();"
                            value="<spring:message code='candidatepi.button2.label'/>">
                    <input  style="background:#A52A2A;color:white;" type="button"
                            onclick="homePage();"
                            value="<spring:message code='candidatepi.button5.label'/>">
                </p>
            </div>
        </form:form>
    </body>
</html>

