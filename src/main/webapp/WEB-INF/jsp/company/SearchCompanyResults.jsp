<%-- 
    Document   : SearchCompanyResults
    Created on : 9 Feb, 2013, 3:59:52 PM
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
        <title><spring:message code="company.searchresult.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
    </head>
    <body>
            <jsp:include page="../common/Header.jsp"></jsp:include>
            <jsp:include page="../common/Menu.jsp"></jsp:include>
            <div class="pageHeading">
                <center>
                    <spring:message code="company.searchresult.title"/>
                </center>
            </div>
            <div class="tabHeader">
                        <center><spring:message code="company.searchresult.title"/></center>
            </div>
            <div class="tabContent">
                <br> 
                <div class="typeBButn">
                    <%@ include file="SearchCompanyButtons.jsp" %>
                </div>
            </div> 
    </body>
</html>
