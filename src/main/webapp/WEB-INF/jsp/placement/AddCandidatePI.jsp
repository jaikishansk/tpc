<%-- 
    Document   : AddCandidatePI
     Created on : Oct 14, 2011, 11:24:06 AM
        Author     : Jaikishan
      Version     : 1.0
--%>

<!--
    Add candidates placement info. Gets candidate's qualification info using AJAX.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><spring:message code="candidatepi.add.title"/></title>

        <script type="text/javascript" src="javascript/ajax/jquery.js"></script>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/form/AddCandPIOps.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
        <script type="text/javascript" src="javascript/validation/PIValidation.js"></script> 
        
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        
        <script type="text/javascript">
            currentTab=0;
            allTabs=new Array("course", "personal", "company"); 
            fieldsToPopulate=new Array("firstName","lastName","courseId","streamId","passingYear");
            firstPIFields=new Array("candidateId","firstName","dateOfPlacement");  
        </script>  
        
    </head>
    <body onLoad="initPIForm();">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="candidatepi.add.title"/>
            </center>
        </div> 
        <div id="navbar3">
             <div id="holder3">
                <ul id="outer3">
                    <li id="tab1">
                        <a href="javascript:void(0)" id="courseTab"
                           onclick="hideAllTabsExcept(allTabs,'course');
                                    setFocus('candidateId');
                                    currentTab=0;">
                           <spring:message code="candidatepi.option1"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="personalTab"
                           onclick="hideAllTabsExcept(allTabs,'personal');
                                    setFocus('firstName');currentTab=1;">
                           <spring:message code="candidatepi.option2"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="companyTab"
                           onclick="hideAllTabsExcept(allTabs,'company');
                                    setFocus('dateOfPlacement');currentTab=2;">
                           <spring:message code="candidatepi.option3"/>
                        </a>
                    </li>
                </ul>
            </div> <!-- end holder div -->
        </div> <!-- end navbar div -->
        <form:form name="candidatePIForm" id="candidatePI" method="post" commandName="candidatePI"> 
            <div id="course" style="color:black;display: none;">
                <div style="margin-top:2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="candidatepi.option1.message"/></center>
                </div> 
                <div class="tabContent">
                    <br> 
                    <form:label path="" class="firstField">
                        <spring:message code="candidatepi.course.candidateId"/>:*
                        <input  id="candidateId" class="firstFieldInput"
                                    onkeydown="return ajaxCandIdDetails(event);"
                                    tabindex="1"
                                    onblur="return ajaxCandIdDetails(event);"
                                    maxlength="15" name="candidateId"/><br>
                    </form:label> 
                    <form:label path="" class="secondField">
                        <spring:message code="candidatepi.course.py"/>:*
                        <input class="secondFieldInput" id="passingYear" readonly="true"
                                    onkeypress="return onlyNumbers(event);" tabindex="2"
                                    maxlength="4" name="passingYear"/><br>
                    </form:label>
                    <br><br>
                    <form:label class="firstField" path="">
                        <spring:message code="candidatepi.course.course"/>:*
                        <input class="firstFieldInput" id="courseId" readonly="true"
                                    onkeypress="return onlyNumbers(event);" tabindex="3"
                                    maxlength="4" name="courseId"/><br> 
                    </form:label>
                    <form:label class="secondField" path="">
                        <spring:message code="candidatepi.course.stream"/>:*
                        <input class="secondFieldInput" id="streamId" readonly="true"
                                    onkeypress="return onlyNumbers(event);" tabindex="4"
                                    maxlength="4" name="streamId"/><br>  
                    </form:label>
                     <div style="margin-top:15%"></div>
                    <div class="typeBButn">
                        <%@ include file="AddPIButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="personal" style="color:black;display: none;">
                <div style="margin-top:2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="candidatepi.option2.message"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="candidatepi.pd.firstName"/>:
                        <form:input id="firstName" cssClass="firstFieldInput" readonly="true"
                                    onkeypress="return onlyAlphabets(event);" tabindex="1"
                                    maxlength="30" path=""/><br>
                    </form:label>
                    <form:label path="" class="secondField">
                        <spring:message code="candidatepi.pd.lastName"/>:
                        <form:input cssClass="secondFieldInput" id="lastName" readonly="true"
                                    onkeypress="return onlyAlphabets(event);" tabindex="2" 
                                    maxlength="30"  path=""/><br>
                    </form:label>
                    <div style="margin-top:15%"></div>
                    <div class="typeBButn">
                        <%@ include file="AddPIButtons.jsp" %>
                    </div> 
                </div>
            </div> 
            <div id="company" style="color:black;display: none;">
                <div style="margin-top:2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="candidatepi.option3.message"/></center>
                </div> 
                <div class="tabContent">
                    <br>
                    <form:label class="firstField" path="primaryKey.companyId">
                        <spring:message code="candidatepi.company.company"/>:*
                        <form:select path="primaryKey.companyId" tabindex="1"
                                     id="companyId" cssClass="firstFieldInput" >
                                <form:options items="${companies}" />
                        </form:select>
                    </form:label>
                    <form:label class="secondField" path="dateOfPlacement">
                         <spring:message code="candidatepi.company.dateofplacement"/>:*
                         <input class="secondFieldInput" id="dateOfPlacement"
                                     onkeypress="return onlyDate(event);"
                                     tabindex="2" name="dateOfPlacement"
                                     onblur="return placementDateCheck('dateOfPlacement','passingYear');"
                                     maxlength="10"/><br>
                          <script type="text/javascript">
                            new tcal ({
                            // form name
                            'formname': 'candidatePIForm',
                            // input name
                            'controlname': 'dateOfPlacement'
                            }); 
                           </script>
                    </form:label>
                    <br><br>
                    <form:label path="salaryOffered" class="firstField">
                        <spring:message code="candidatepi.company.package"/>:
                        <input  class="firstFieldInput" id="package"
                                onkeypress="return onlyFloat(event)"  
                                tabindex="3" maxlength="5" name="salaryOffered"/><br>
                    </form:label>
                    <form:label path="bondPeriod" class="secondField">
                        <spring:message code="candidatepi.company.bond"/>:
                        <input class="secondFieldInput" id="bondPeriod"
                                    onkeypress="return onlyNumbers(event);" tabindex="4"
                                    maxlength="2"  name="bondPeriod"/><br>
                    </form:label>
                    <br><br>
                    <form:label path="dateOfJoining" class="firstField">
                        <spring:message code="candidatepi.company.DOJ"/>:
                        <form:input cssClass="secondFieldInput"
                                    onkeypress="return onlyDate(event);"  
                                    id="dateOfJoining" tabindex="5"
                                    onblur="return checkJoiningDate('dateOfJoining','dateOfPlacement','passingYear');"
                                    maxlength="10" path="dateOfJoining"/><br>
                        <script type="text/javascript">
                            new tcal ({
                            // form name
                            'formname': 'candidatePIForm',
                            // input name
                            'controlname': 'dateOfJoining'
                            });
                        </script>
                    </form:label>
                   <div style="margin-top:15%"></div>
                    <div class="typeBButn">
                        <%@ include file="AddPIButtons.jsp" %>
                    </div>
                </div> 
            </div>
        </form:form>
    </body>
</html>
