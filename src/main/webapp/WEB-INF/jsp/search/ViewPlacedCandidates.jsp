<%--
    Document   : Viewcandidatesplaced
    Created on : May 13, 2012, 6:25:38 PM
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
        <title><spring:message code="candidatesplaced.view.title"/></title>
        <script type="text/javascript" src="javascript/ajax/jquery.js"></script>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script> 
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="candidatesplaced.view.title"/>
            </center>
        </div>
        <div style="margin-top: -3%"></div>
        <div class="tabHeader">
            <center>
                <spring:message code="searchcandidate.result.title"/>
            </center>
        </div>
        <div class="tabContent">
            <br>
            <div class="viewPI">
                <c:if test="${!placementPLH.firstPage}">
                    <a id="placedPrev" href="showplacedcandidates.html?page=previous"><b>&lt;&lt; Prev</b></a>
                </c:if>
                <c:if test="${!placementPLH.lastPage}">
                    <a  id="placedNext" href="showplacedcandidates.html?page=next"><b>Next &gt;&gt;</b></a>
                </c:if> 
                <table id="candidatedataTable" class="viewPI">
                    <tr>
                        <th><spring:message code="candidatesplaced.view.candidateid"/></th>
                        <th><spring:message code="candidatesplaced.view.firstname"/></th>
                        <th><spring:message code="candidatesplaced.view.lastname"/></th>
                        <th><spring:message code="candidatesplaced.view.course"/></th>
                        <th><spring:message code="candidatesplaced.view.stream"/></th>
                        <th><spring:message code="candidatesplaced.view.company"/></th>
                        <th><spring:message code="candidatesplaced.view.dateOfPlacement"/></th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="candidate" items="${placementPLH.pageList}">
                        <tr>
                            <td>${candidate.candidateId}</td>
                            <td>${candidate.firstName}</td>
                            <td>${candidate.lastName}</td>
                            <td>${candidate.course}</td>
                            <td>${candidate.stream}</td>
                            <td>${candidate.company}</td>
                            <td>${candidate.placementDate}</td>
                            <td> 
                                <a href="update.html?candidateId=${candidate.candidateId}" class="">Update</a>
                                <a href="delete.html?candidateId=${candidate.candidateId}" class="">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <%@ include file="ViewSRButtons.jsp" %>
        </div>
    </body>
</html>
