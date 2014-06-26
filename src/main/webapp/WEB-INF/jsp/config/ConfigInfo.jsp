<%-- 
    Document   : ConfigInfo
    Created on : Oct 17, 2011, 7:47:27 AM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <script type="text/javascript" src="javascript/validation/JSValidation.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="configinfo.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
        <script type="text/javascript">
            var allTabs = new Array("org", "results", "placement", "courses",
                                     "streams","notification", "resume"); 
            function populateCourseNames()
            { 
                var index=getCoursesRowCount()-2;
                var dropDownBoxInStreams = document.getElementById('confingCourseNameInStreams');
                var textBoxInCourses=document.getElementById("course["+index+"]");
                dropDownBoxInStreams.options[index]=new Option(textBoxInCourses.value,(index+1));
            } 
        </script>
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="configinfo.title"/>
            </center>
        </div>
                <div id="navbar">
            <div id="holder">
                <ul id="outer">
                    <li id="tab1">
                            <a href="javascript:void(0)" id="orgTab"
                               onclick="hideAllTabsExcept(allTabs,'org');setFocus('configOrgId');">
                               <spring:message code="configinfo.option1"/>
                            </a>
                    </li>
                    <li><a href="javascript:void(0)" id="resultsTab"
                           onclick="hideAllTabsExcept(allTabs,'results');setFocus('configSearchResults');">
                            <spring:message code="configinfo.option2"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="placementTab"
                           onclick="hideAllTabsExcept(allTabs,'placement');setFocus('configPILimit')">
                            <spring:message code="configinfo.option3"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="coursesTab"
                           onclick="hideAllTabsExcept(allTabs,'courses');setFocus('course[0]');">
                            <spring:message code="configinfo.option4"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="streamsTab"
                           onclick="hideAllTabsExcept(allTabs,'streams');populateCourseNames();setFocus('configStreamName')">
                            <spring:message code="configinfo.option5"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="notificationTab"
                           onclick="hideAllTabsExcept(allTabs,'notification');setFocus('configNotifyEmailId')">
                            <spring:message code="configinfo.option6"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="resumeTab"
                           onclick="hideAllTabsExcept(allTabs,'resume');setFocus('resume')">
                            <spring:message code="configinfo.option7"/>
                            Default branch for data entry.....
                        </a>
                    </li>
                </ul>
            </div> <!-- end holder div -->
        </div> <!-- end navbar div -->
        <form:form name="configForm" id="configForm" method="post" commandName="configInfo">
            <div id="org" style="color:black;display: none;">
                <br>
                <div class="tabHeader">
                        <center><spring:message code="configinfo.org.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="configinfo.org.configOrgId"/>
                        <form:input id="configOrgId" name="configOrgId"
                                    cssClass="firstFieldInput" tabindex="1"
                                    onkeypress="return onlyAlphabets(event);"
                                    maxlength="30" path=""/><br>
                    </form:label>
                    <form:label path="" class="secondField">
                        <spring:message code="configinfo.org.configOrgName"/>
                        <form:input id="configOrgName" name="configOrgName"
                                    cssClass="secondFieldInput" onkeypress="return onlyAlphabets(event);"
                                    tabindex="2" maxlength="30" path=""/><br>
                    </form:label>
                    <br><br>
                    <form:label class="firstField" path="">
                        <spring:message code="configinfo.org.addrline1"/>:
                        <form:input cssClass="firstFieldInput" id="addrline1"
                                    tabindex="3"  maxlength="30" path=""/><br>
                    </form:label>
                    <form:label class="secondField" path="">
                        <spring:message code="configinfo.org.addrline2"/>:
                        <form:input cssClass="secondFieldInput" id="addrline2"
                                    tabindex="4"  maxlength="30" path=""/><br>
                    </form:label>
                    <br><br>
                    <form:label class="firstField" path="">
                        <spring:message code="configinfo.org.city"/>:
                        <form:input cssClass="firstFieldInput" id="city" tabindex="5"
                                    maxlength="30" onkeypress="return onlyAlphabets(event);"
                                    path=""/><br>
                    </form:label>
                    <form:label class="secondField" path="">
                        <spring:message code="configinfo.org.residentstate"/>:
                        <form:input cssClass="secondFieldInput" id="state" tabindex="6"
                                    maxlength="30" path=""/><br>
                    </form:label>
                    <br><br>
                    <form:label class="firstField" path="">
                        <spring:message code="configinfo.org.pin"/>:
                        <form:input cssClass="firstFieldInput" id="pin" tabindex="7"
                                    maxlength="6" onkeypress="return onlyNumbers(event);"
                                    path=""/><br>
                    </form:label>
                    <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="CIButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="results" style="color:black;display: none;">
                <div class="tabHeader">
                    <center><spring:message code="configinfo.results.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="configinfo.results.configSearchResults"/>
                        <form:input id="configSearchResults" name="configSearchResults"
                                    cssClass="firstFieldInput" onkeypress="return onlyAlphabets(event);"
                                    tabindex="1" maxlength="30" path=""/><br>
                    </form:label>
                    <form:label path="" class="secondField">
                        <spring:message code="configinfo.results.configReportResults"/>
                        <form:input id="configReportResults" name="configReportResults" 
                                    cssClass="secondFieldInput" onkeypress="return onlyAlphabets(event);"
                                    tabindex="2" maxlength="30" path=""/><br>
                    </form:label>
                     <br><br>
                    <form:label path="" class="firstField">
                        <spring:message code="configinfo.results.configExportResults"/>
                        <form:input id="configExportResults" name="configExportResults" 
                                    cssClass="firstFieldInput" onkeypress="return onlyAlphabets(event);"
                                    tabindex="3" maxlength="30" path=""/><br>
                    </form:label>
                    <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="CIButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="placement" style="color:black;display: none;">
                <div class="tabHeader">
                    <center><spring:message code="configinfo.placement.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="configinfo.placement.configPILimit"/>
                        <form:input id="configPILimit" name="configPILimit" 
                                    cssClass="firstFieldInput" onkeypress="return onlyAlphabets(event);"
                                    tabindex="1" maxlength="30" path=""/><br>
                    </form:label>
                     <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="CIButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="courses" style="color:black;display: none;">
                <div class="tabHeader">
                    <center><spring:message code="configinfo.courses.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <div class="addCoursesbutn">
                        <input type="button" value="<spring:message 
                            code="configinfo.courses.button1.label"/>"
                            onclick="addCoursesRow('coursesdataTable')"/>
                        <input type="button" value="<spring:message 
                            code="configinfo.courses.button2.label"/>"
                            onclick="deleteCoursesRow('coursesdataTable')"/>
                    </div>
                    <table class="courseTable" id="coursesdataTable" width="250px" border="1">
                        <tr>
                            <th></th>
                            <th><spring:message code="configinfo.courses.coursename"/>*</th>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"/></td>
                            <td><form:input id="course[0]" name="course[0]" 
                                        path="" tabindex="1" size="25" onblur="populateCourseNames()"/> 
                            </td>
                        </tr>
                    </table>
                    <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="CIButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="streams" style="color:black;display: none;">
                <div class="tabHeader">
                    <center><spring:message code="configinfo.streams.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="configinfo.courses.coursename"/>
                        <form:select path="" id="confingCourseNameInStreams"
                                     onchange="showNewStreams(this.value)"
                                     cssClass="firstFieldInput" tabindex="1">
                        </form:select>
                    </form:label>
                    <br><br>
                    <div class="addStreamsbutn">
                        <input type="button" value="<spring:message 
                               code="configinfo.streams.button1.label"/>"
                               onclick="addStreamsRow('streamsdataTable')"/>
                        <input type="button" value="<spring:message 
                            code="configinfo.streams.button2.label"/>"
                               onclick="deleteStreamsRow('streamsdataTable')"/>
                    </div>
                    <table class="streamTable" id="streamsdataTable" width="350px" border="1">
                        <tr>
                            <th></th>
                            <th><spring:message code="configinfo.streams.streamname"/>*</th>
                            <th><spring:message code="configinfo.streams.intake"/>*</th>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="chk"/></td>
                            <td><form:input id="configStreamName"
                                        path="" tabindex="1" size="30"></form:input></td>
                            <td><form:input id="" path="" tabindex="2" size="10"/></td>
                        </tr>
                    </table>
                    <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="CIButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="notification" style="color:black;display: none;">
                <div class="tabHeader">
                    <center><spring:message code="configinfo.notification.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="configinfo.notification.configNotifyEmailId"/>
                        <form:input id="configNotifyEmailId" name="configNotifyEmailId" 
                                    cssClass="firstFieldInput" onkeypress="return onlyAlphabets(event);"
                                    tabindex="1" maxlength="30" path=""/><br>
                    </form:label>
                     <br><br><br>
                    <div class="typeBButn">
                        <%@ include file="CIButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="resume" style="color:black;display: none;">
                <div class="tabHeader">
                    <center>To do</center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="configinfo.resume.uploadfolder"/>
                        <form:input id="upload" name="upload" cssClass="firstFieldInput" 
                                    onkeypress="return onlyAlphabets(event);" tabindex="1"
                                    maxlength="30" path=""/><br>
                    </form:label>
                    <div class="typeBButn">
                        <%@ include file="CIButtons.jsp" %>
                    </div>
                </div>
            </div>
        </form:form>
    </body>
</html>
