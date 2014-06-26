<%-- 
    Document   : StreamWiseReport
    Created on : Mar 5, 2012, 1:20:58 PM
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
        <title><spring:message code="dashboard.streamwise.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
     </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="dashboard.streamwise.title"/>
            </center>
        </div>
         <div class="tabHeader">
             <center><spring:message code="dashboard.streamwise.title"/></center>
        </div>
        <div class="tabContent">
            <br>
            <form:form name="swReportForm" id="swReportForm" method="post" commandName="swReportCriteria">
                <form:label path="" class="firstField">
                    <spring:message code="dashboard.streamwise.stream"/>: 
                        <form:select path="streamId" tabindex="1" cssClass="firstFieldInput" >
                                <form:options items="${streams}" />
                        </form:select>
                </form:label>
                <form:label path="passingYear" class="secondField">
                    <spring:message code="dashboard.streamwise.py"/>:
                    <input class="secondFieldInput" id="py"
                                onkeypress="return onlyNumeric(event);" tabindex="2"
                                maxlength="4" name="passingYear"/><br>
                </form:label>
            </form:form>
        </div>
    </body>
</html>
