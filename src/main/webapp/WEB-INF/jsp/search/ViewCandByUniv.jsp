<%-- 
    Document   : ViewCandByUniv
    Created on : May 25, 2012, 2:34:42 AM
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
                <spring:message code="searchcandidate.result.title"/>
            </center>
        </div>
            <div style="margin-top: -3%"></div>
        <div class="tabHeader"> 
            <center>
                <spring:message code="candidateuniv.title"/>
            </center>
        </div>
        <div class="tabContent">
            <br>
            <div class="viewPI">
                 <c:if test="${!candidateByUnivPLH.firstPage}">
                    <a id="placedPrev" href="showcandidatebyuniv.html?page=previous"><b>&lt;&lt; Prev</b></a>
                </c:if>
                <c:if test="${!candidateByUnivPLH.lastPage}">
                    <a  id="placedNext" href="showcandidatebyuniv.html?page=next"><b>Next &gt;&gt;</b></a>
                </c:if> 
                <table id="candidatedataTable" class="viewPI">
                    <tr>
                        <th><spring:message code="candidatesplaced.view.candidateid"/></th>
                        <th><spring:message code="candidatesplaced.view.firstname"/></th>
                        <th><spring:message code="candidatesplaced.view.lastname"/></th>
                        <th><spring:message code="candidatesplaced.view.course"/></th>
                        <th><spring:message code="candidatesplaced.view.stream"/></th>
                        <th><spring:message code="candidatesplaced.view.passingyear"/></th>
                        <th><spring:message code="searchcandidate.edu.univ"/></th> 
                        <th><spring:message code="candidatesplaced.view.primemail"/></th>
                        <th><spring:message code="candidatesplaced.view.primcell"/></th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="candidate" items="${candidateByUnivPLH.pageList}">
                        <tr>
                            <td>${candidate.candidateId}</td>
                            <td>${candidate.firstName}</td>
                            <td>${candidate.lastName}</td>
                            <td>${candidate.courseId}</td>
                            <td>${candidate.streamId}</td>
                            <td>${candidate.passingYear}</td>
                            <td>${candidate.university}</td> 
                            <td>${candidate.primaryEmail}</td>
                            <td>${candidate.primaryCellNo}</td>
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
