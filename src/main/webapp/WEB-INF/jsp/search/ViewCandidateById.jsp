<%--
    Document   : ViewCandidateById
    Created on : May 22, 2012, 1:55:36 AM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="searchcandidate.title"/></title>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="searchcandidate.title"/>
            </center>
        </div>
         <div style="margin-top: -3%"></div>   
        <div class="tabHeader">
            <center>
                <spring:message code="candidateid.title"/>
            </center>
        </div>
        <div class="tabContent">
            <br>
            <div class="viewPI">
                <table id="candidatedataTable" class="viewPI">
                    <tr>
                       <th><spring:message code="candidatesplaced.view.candidateid"/></th>
                        <th><spring:message code="candidatesplaced.view.firstname"/></th>
                        <th><spring:message code="candidatesplaced.view.lastname"/></th>
                        <th><spring:message code="candidatesplaced.view.course"/></th>
                        <th><spring:message code="candidatesplaced.view.stream"/></th>
                        <th><spring:message code="candidatesplaced.view.passingyear"/></th>
                        <th><spring:message code="candidatesplaced.view.primemail"/></th>
                        <th><spring:message code="candidatesplaced.view.primcell"/></th>
                        <th>Actions</th> 
                    </tr> 
                        <tr>
                            <td>${candidate.candidateId}</td>
                            <td>${candidate.firstName}</td>
                            <td>${candidate.lastName}</td>
                            <td>${candidate.course}</td>
                            <td>${candidate.stream}</td>
                            <td>${candidate.passingYear}</td>
                            <td>${candidate.primEmail}</td>
                            <td>${candidate.primCell}</td>
                             <td> 
                                <a href="update.html?candidateId=${candidate.candidateId}" class="">Update</a>
                                <a href="delete.html?candidateId=${candidate.candidateId}" class="">Delete</a>
                            </td>
                        </tr> 
                </table>
            </div>
            <%@ include file="ViewSRButtons.jsp" %>      
        </div>
    </body>
</html>
