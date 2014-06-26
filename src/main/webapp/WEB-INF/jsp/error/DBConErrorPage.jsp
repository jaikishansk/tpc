<%-- 
    Document   : DBConErrorPage
    Created on : Nov 4, 2011, 12:00:23 PM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="errorpage.dbcon.title"/></title>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include> 
        <div class="loginErr" style="margin-top: 10%">
                <spring:message code="errorpage.dbcon.message"/>  
        </div>
    </body>
</html>
