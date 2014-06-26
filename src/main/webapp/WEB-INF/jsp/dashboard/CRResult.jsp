<%-- 
    Document   : CRResult
    Created on : Mar 6, 2012, 12:44:06 PM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*" %>
<%@page import="org.jfree.chart.JFreeChart" %>
<%@page import="org.jfree.chart.ChartUtilities" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <script type="text/javascript" src="javascript/validation/JSValidation.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="dashboard.consolidated.title"/></title>
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
                    <spring:message code="dashboard.consolidated.title"/>
                </center>
        </div>
        <%
            response.setContentType("image/png");
            ChartUtilities.writeChartAsPNG(response.getOutputStream(),
                                            (JFreeChart)session.getAttribute("chart"), 500, 500);
            out.close(); 
        %>
    </body>
</html>
