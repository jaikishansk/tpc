<%--
    Document   : EditCandidatePI
    Created on : Feb 21, 2012, 6:25:38 PM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@page import="com.edu.tpc.domain.Candidate"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="candidatepi.edit.title"/></title>

        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/form/AddCandPIOps.js"></script>
        <script type="text/javascript" src="javascript/form/EditCandPIOps.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
        <script type="text/javascript" src="javascript/validation/PIValidation.js"></script> 

        <script type="text/javascript">
            var requiredEditPIFields=new Array("companyId","dateOfPlacement");
        </script>
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include> 
        <br>
        <div class="pageHeading">
            <center>
                <spring:message code="candidatepi.edit.title"/>
            </center>
        </div>
        <form:form name="ecPIForm" method="post" action="saveEditedPI.html" commandName="pic">
            <input type="hidden" id="passingYear" value="${passingYear}"/>
            <input type="hidden" name="primaryKey.candidateId" value="${candidate.candidateId}"/> 
            <div style="margin-top: -2%"/>
            <div class="tabHeader">
                    <center><spring:message code="candidatepi.title"/></center>
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
                    <c:out value="${candidate.lastName}"/>)
                    (<c:out value="<%=qual[candidateType-2]%>"/>,
                     <c:out value="${passingYear}"/>) 
                </b>
            </div>
            <table id="editPITable" class="viewPI">
                <tr> 
                    <th><spring:message code="candidatepi.company.company"/>*</th>
                    <th><spring:message code="candidatepi.company.dateofplacement"/>*</th>
                    <th><spring:message code="candidatepi.company.package"/></th>
                    <th><spring:message code="candidatepi.company.bond"/></th>
                    <th><spring:message code="candidatepi.company.DOJ"/></th> 
                </tr>
                <c:forEach var="placement" items="${pic.placements}" varStatus="status">
                    <form:hidden path="placements[${status.index}].createdBy"/>
                    <form:hidden path="placements[${status.index}].createdOn"/>
                    <tr>
                        <td>
                            <form:select id="companyId${status.index}" tabindex="2"
                                         class="eduTbleDD"
                                         path="placements[${status.index}].primaryKey.companyId">
                                <form:options  items="${companies}"/>
                            </form:select>
                        </td>
                        <td> 
                             <form:input id="dateOfPlacement${status.index}"
                                    path='placements[${status.index}].dateOfPlacement'
                                    tabindex="2" size="12"
                                    onkeypress="return onlyDate(event);"
                                    onblur="return placementDateCheck('dateOfPlacement${status.index}','passingYear');"
                                    maxlength="10"/>
                        </td>
                        <td>
                            <input type="text" size="5" tabindex="3" id="salaryOffered${status.index}"
                                   name="placements[${status.index}].salaryOffered"
                                   onkeypress="return onlyFloat(event)"
                                   maxlength="5"
                                   value="<c:out value="${placement.salaryOffered}"/>"
                        </td>
                        <td>
                            <input type="text" size="5" tabindex="4" id="bondPeriod${status.index}"
                                   name="placements[${status.index}].bondPeriod"
                                   onkeypress="return onlyNumbers(event)"
                                   maxlength="2" 
                                   value="<c:out value="${placement.bondPeriod}"/>"
                        </td>
                        <td>
                            <form:input id="dateOfJoining${status.index}"
                                        path="placements[${status.index}].dateOfJoining"
                                        tabindex="5" size="12" 
                                        onkeypress="return onlyDate(event);"
                                        onblur="return joiningDateCheck('dateOfJoining${status.index}',
                                                                                             'dateOfPlacement${status.index}',
                                                                                             'passingYear');"
                                        maxlength="10"/>
                        </td> 
                    </tr>
                </c:forEach>
            </table> 
            <div class="typeBButn">
                <%@ include file="EditPIButtons.jsp" %>
            </div>
        </div>
        </form:form>
    </body>
</html>
