<%-- 
    Document   : Logout
    Created on : Nov 5, 2011, 5:38:53 AM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <title><spring:message code="logout.title"/></title>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <div class="logOut">
            <spring:message code="logout.message"/>
                <a href="login.html">'Login'.</a>
        </div>
    </body>
</html>
