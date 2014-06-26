<%-- 
    Document   : Statistics
    Created on : Mar 5, 2012, 8:24:13 PM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <script type="text/javascript" src="javascript/validation/JSValidation.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="dashboard.statistics.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
     </head>
    <body onLoad="setFocus('passingYear')">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="dashboard.statistics.title"/>
            </center>
        </div>
         <div class="tabHeader">
             <center><spring:message code="dashboard.statistics.title"/></center>
        </div>
        <div class="tabContent">
            <br>
            <form:form name="statsForm" id="statsForm" method="post" commandName="statsCriteria">
                <form:label path="passingYear" class="firstField">
                        <spring:message code="dashboard.eligibility.py"/>:
                        <input class="firstFieldInput" id="passingYear"
                               onkeypress="return onlyNumeric(event);" tabindex="1"
                               maxlength="4" name="passingYear"/><br>
                </form:label>
                <br><br>
                <div class="typeBButn">
                        <%@ include file="AddStatsButtons.jsp" %>
                </div>
            </form:form>
        </div>
    </body>
</html>
