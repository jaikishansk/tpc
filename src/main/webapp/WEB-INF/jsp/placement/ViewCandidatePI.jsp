<%-- 
    Document   : ViewCandidatePI
    Created on : Feb 21, 2012, 6:25:38 PM
    Author     : Jaikishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@page import="com.edu.tpc.domain.Candidate"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <title><spring:message code="candidatepi.view.title"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="candidatepi.view.title"/>
            </center>
        </div>
        <div style="margin-top: -2%"/>
        <div class="tabHeader">
            <center>
                <spring:message code="candidatepi.title"/>
            </center>
        </div>
        <div class="tabContent">
            <br>
            <%
                    String[] qual={"UG","PG"};
                    int candidateType=((Candidate)request.getAttribute("candidate")).getCandidateType();
            %>
            <div style="margin-left: 2%">
                <b><spring:message code="candidatepi.result.message"/>:
                <c:out value="${candidate.candidateId}"/>
                (<c:out value="${candidate.firstName}"/>
                <c:out value="${candidate.lastName}"/>)-
                (<c:out value="<%=qual[candidateType-2]%>"/>,
                <c:out value="${passingYear}"/>)
                </b>
            </div>
            <table class="viewPI">
                <tr>
                    <th><spring:message code="candidatepi.company.company"/></th>
                    <th><spring:message code="candidatepi.company.dateofplacement"/></th>
                    <th><spring:message code="candidatepi.company.package"/></th>
                    <th><spring:message code="candidatepi.company.bond"/></th>
                    <th><spring:message code="candidatepi.company.DOJ"/></th> 
                </tr>
                <c:forEach var="placement" items="${placements}">
                    <tr>
                        <td> 
                            <c:forEach var="i" items="${companies}"> 
                                    <c:if test="${placement.primaryKey.companyId==i.key}">
                                        <c:out value="${i.value}"/>
                                    </c:if>
                            </c:forEach>
                        </td>
                        <td><fmt:formatDate value="${placement.dateOfPlacement}" pattern="dd-MM-yyyy"/></td>                           
                        <td><c:out value="${placement.salaryOffered}"/></td>
                        <td><c:out value="${placement.bondPeriod}"/></td>
                        <td><fmt:formatDate value="${placement.dateOfJoining}" pattern="dd-MM-yyyy"/></td>
                    </tr>
                </c:forEach>
            </table>
            <div style="margin-left:35%">
                <input  style="background:#A52A2A;color:white;"
                        type="submit" onclick="homePage();"
                        value="<spring:message code='candidatepi.button5.label'/>"
            </div>
        </div>
    </body>
</html>
