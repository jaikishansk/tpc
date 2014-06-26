<%-- 
    Document   : StatisticsResults
    Created on : Mar 6, 2012, 9:21:51 AM
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
    <body>
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
            <table class="viewPI">
                <tr>
                    <th><spring:message code="dashboard.statistics.company"/></th>
                    <th><spring:message code="dashboard.statistics.totaleligible"/></th>
                    <th><spring:message code="dashboard.statistics.totalappeared"/></th>
                    <th><spring:message code="dashboard.statistics.totalselected"/></th>
                    <th><spring:message code="dashboard.statistics.percentage"/></th>
                </tr>
                 <div style="margin-left: 2%">
                     Placement statistics of : <c:out value="${passingYear}"/>
                 </div>
                <c:forEach var="statResult" items="${statResults}">
                    <tr>
                        <td><c:out value="${statResult.companyName}"/></td>
                        <td><c:out value="${statResult.totalEligible}"/></td>
                        <td><c:out value="${statResult.totalAppeared}"/></td>
                        <td><c:out value="${statResult.totalSelected}"/></td>
                        <td><c:out value="${statResult.totalAppeared}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
