<%-- 
    Document   : SearchByStreamResults
    Created on : Oct 15, 2011, 12:35:23 PM
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
        <title>All candidates</title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="register"><center>All Candidates</center></div>
        <br>
        <div class="candidateData">
            <c:if test="${!personList.firstPage}">
                <a href="searchcandidate.html?page=previous"><b>&lt;&lt; Prev</b></a>
            </c:if>
            <c:if test="${!personList.lastPage}">
                <a href="searchcandidate.html?page=next"><b>Next &gt;&gt;</b></a>
            </c:if>
            <table id="candidatedataTable" width="350px" border="1">
                <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Gender</th>
                    <th>Date of Birth</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="candidate" items="${candidateList.pageList}">
                    <tr>
                        <td>${candidate.candidateId}</td>
                        <td>${candidate.firstName}</td>
                        <td>${candidate.lastName}</td>
                        <td>${candidate.gender}</td>
                        <td>${candidate.dateOfBirth}</td>
                        <td>
                            <a href="view.html?candidateId=${candidate.candidateId}" class="">View</a>
                            <a href="update.html?candidateId=${candidate.candidateId}" class="">Update</a>
                            <a href="delete.html?candidateId=${candidate.candidateId}" class="">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div> 
    </body>
</html>
