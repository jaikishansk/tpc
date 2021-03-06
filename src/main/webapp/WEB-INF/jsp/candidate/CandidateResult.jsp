<%-- 
    Document   : CandidateResult.jsp
    Created on : Aug 22, 2011, 6:21:31 PM
    Author     : Jaikishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="registercandidate.result"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
            <jsp:include page="../common/Header.jsp"></jsp:include>
            <jsp:include page="../common/Menu.jsp"></jsp:include>
             <div class="resultMessage">
                ${candidateStatus}
            </div>
            <c:if test="${! empty candidateError}">
                <div class="errorMessage">
                      Error: ${candidateError}
                </div>
            </c:if>
            <div style="margin-top:40%">
                <jsp:include page="../common/Footer.jsp"></jsp:include>
            </div>
    </body>
</html>
