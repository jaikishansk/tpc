<%-- 
    Document   : NotifyCandidate
    Created on : Feb 29, 2012, 8:19:52 AM
    Author     : Jaikishan
--%>

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
        
        <title><spring:message code="notifycandidate.title"/></title> 
        
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/ajax/jquery.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
        <script type="text/javascript" src="javascript/validation/NCValidation.js"></script> 
        <script type="text/javascript" src="javascript/form/NCOps.js"></script>
        
        <script type="text/javascript">
            var currentTab=0;
            var allTabs=new Array("candidate", "class", "criteria"); 
            var firstNCFields=new Array("candidateId","courseId","startPercent");  
        </script>
     </head>
    <body onLoad="initializeNCForm()">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>

        <div class="pageHeading">
            <center>
                <spring:message code="notifycandidate.title"/>
            </center>
        </div>
            
        <div id="navbar3">
            <div id="holder3">
                <ul id="outer3">
                    <li id="tab1">
                        <a href="javascript:void(0)" id="candidateTab"
                             onclick="hideAllTabsExcept(allTabs,'candidate');setFocus('candidateId');currentTab=0;">
                           <spring:message code="notifycandidate.option1"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="classTab"
                             onclick="hideAllTabsExcept(allTabs,'class');setFocus('course');currentTab=1;">
                           <spring:message code="notifycandidate.option2"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="criteriaTab"
                             onclick="hideAllTabsExcept(allTabs,'criteria');setFocus('startPercent');currentTab=2;">
                           <spring:message code="notifycandidate.option3"/>
                        </a>
                    </li>
                </ul>
            </div> <!-- end holder div -->
        </div> <!-- end navbar div -->
        <form:form name="ncForm" method="post" commandName="ncCriteria">
            <form:hidden id="notificationScope" path="notificationScope"/>
            <div id="candidate" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="notifycandidate.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label class="firstField" path="candidateId">
                        <spring:message code="notifycandidate.candidateId"/>:*
                        <form:input id="candidateId" cssClass="firstFieldInput"
                                    onkeypress="return onlyAlphaNumeric(event);"
                                    tabindex="1"
                                    maxlength="15" path="candidateId"/><br>
                    </form:label>
                    <form:label class="secondField" path="message">
                        <spring:message code="notifycandidate.message"/>:*
                        <form:label path="message">
                            <form:textarea  id="candMessage" tabindex="2" maxlength="200" 
                                                        cssClass="secondFieldInput" path="message"/>
                        </form:label>
                    </form:label>
                    <br><br>
                    <form:label class="firstField" path="notificationType">
                        <spring:message code="notifycandidate.method"/>:*<br>
                        <form:radiobutton id="candNotificationPref"
                                         tabindex="3" path="notificationType" value="1"/>Email
                        <form:radiobutton id="candNotificationPref"
                                        path="notificationType" value="2"/>SMS
                        <form:radiobutton  
                                        id="candNotificationPref"
                                        path="notificationType" value="3" checked="yes"/>Both<br>
                    </form:label>  
                    <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="NCandButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="class" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="notifycandidate.class.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label class="firstField" path="course">
                        <spring:message code="candidatepi.course.course"/>:*
                        <form:select cssClass="firstFieldInput"
                                    path="course" id="courseId" tabindex="1">
                              <form:options items="${courses}"></form:options>
                        </form:select>
                    </form:label>
                    <form:label class="secondField" path="stream">
                        <spring:message code="candidatepi.course.stream"/>:*
                        <form:select cssClass="secondFieldInput" tabindex="2"
                                     id="streamId" path="stream">
                             <form:options items="${streams}"></form:options>
                        </form:select>
                    </form:label>
                    <br><br>
                    <form:label path="passingYear" class="firstField">
                    <spring:message code="candidatepi.course.py"/>:*
                    <input class="firstFieldInput" id="passingYear"  
                                onkeypress="return onlyNumbers(event);" tabindex="3"
                                maxlength="4" name="passingYear"/><br>
                    </form:label>
                    <form:label class="secondField" path="message">
                        <spring:message code="notifycandidate.message"/>:*
                        <form:label path="message">
                            <form:textarea  id="classMessage" tabindex="4" maxlength="200" 
                                                        cssClass="secondFieldInput" path="message"/>
                        </form:label>
                    </form:label>
                     <br><br>
                    <form:label class="firstField" path="notificationType">
                        <spring:message code="notifycandidate.method"/>:*<br>
                        <form:radiobutton id="classNotificationPref"
                                         tabindex="5" path="notificationType" value="1"/>Email
                        <form:radiobutton id="classNotificationPref"
                                        path="notificationType" value="2"/>SMS
                        <form:radiobutton   
                                        id="classNotificationPref"
                                        path="notificationType" value="3" checked="yes"/>Both<br>
                    </form:label>     
                    <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="NClassButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="criteria" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="notifycandidate.class.criteria"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label class="firstField" path="startPercent">
                        <spring:message code="notifycandidate.criteria.startpercent"/>:
                        <form:input type="text" id="startPercent" tabindex="1"
                                           maxlength="6" class="firstFieldInput" 
                                           path="startPercent"
                                           onkeypress="return validatePercentageOnChar(this, event);"/><br>
                    </form:label>
                    <form:label class="secondField" path="endPercent">
                        <spring:message code="notifycandidate.criteria.endpercent"/>:
                        <form:input type="text" id="endPercent" tabindex="2"
                                           maxlength="6" class="secondFieldInput"
                                           path="endPercent"
                                           onkeypress="return validatePercentageOnChar(this, event);"/><br>
                    </form:label>
                    <br><br>
                    <form:label class="firstField" path="liveKT">
                        <spring:message code="notifycandidate.criteria.livekt"/>:
                        <form:input type="text" id="livekt" tabindex="3"
                                           maxlength="2" class="firstFieldInput"
                                           path="liveKT"
                                           onkeypress="return onlyNumbers(event);"/><br>
                    </form:label>
                    <form:label class="secondField" path="numPlacements">
                        <spring:message code="notifycandidate.criteria.placements"/>:
                        <form:input type="text" id="numPlacements" tabindex="4"
                                           maxlength="2" class="secondFieldInput"
                                           path="numPlacements"
                                           onkeypress="return onlyNumbers(event);"/><br>
                    </form:label>
                    <br><br><br>  
                    <div class="typeBButn">
                            <p>
                                <input  class="allButtons" type="button"  onclick="return findNCPotentialMatch();"
                                        value="<spring:message code='notifycandidate.button5.label'/>">
                            </p>
                    </div>
                </div>
            </div>
        </form:form>
    </body>
</html>
