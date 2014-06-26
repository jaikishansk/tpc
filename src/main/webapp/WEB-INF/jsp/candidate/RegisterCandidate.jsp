<!--
    Purpose:
        This JSP is for creating and editing UG/PG candidates.

    Various hidden fields and their purpose
         userType: Admin/UG Candidate/PG Candidate
         candidateType: UG Candidate/PG Candidate (Admin can create UG/PG candidate)
         userId: Logged in user id
         editFlag: Whether in edit mode
         ugRows: No. of semester fields in UG qualification
         pgRows: No. of semester fields in PG qualification

     Business Logic:
         1. Admin can create UG/PG candidates
         2. Upon creation, UG/PG Id entered by Admin becomes candidate id, subsequent
            change in this id is not reflected as a new candidate id
         3. Candidate Id created by Admin remains unchanged even after editing id.
         4. Admin can edit any data
         5. UG/PG candidates cannot edit qualifications
-->

<%--
    Document   : RegisterCandidate
    Created on : Sep 10, 2011, 7:32:36 PM
    Author     : Jaikishan 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@page import="com.edu.tpc.domain.UserType"%>
<%@page import="com.edu.tpc.domain.Candidate"%>
<%@page import="com.edu.tpc.domain.Semester"%>
<%@page import="com.edu.tpc.domain.Qualification"%>
<%@page import="com.edu.tpc.domain.CourseStreamInfo"%>
<%@page import="com.edu.tpc.domain.CandidatePlacementPref"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title><spring:message code="registercandidate.title"/></title>

        <script type="text/javascript" src="javascript/form/RegCandOps.js"></script>
        <script type="text/javascript" src="javascript/ajax/jquery.js"></script>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>  
        <script type="text/javascript" src="javascript/validation/RegCandFormValidation.js"></script>
        
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">

        <script type="text/javascript">
            // The register candidate screen options tab
            allTabs=new Array("personal", "secondary", "ug", "pg",
                              "contact", "address", "preferences");
            // Enable a tab to correct any data entry error
            currentTab=0;
            // In case of error, highlight first field of current tab
            firstFields=new Array("firstName","secondaryId0","ugId","pgId","primEmail",
                                   "addrLine1","placementPref");

            // basic required fields
            basicFields=new Array("firstName","lastName","fathersName",
                                                "mothersName","dateOfBirth","primEmail",
                                                "secondEmail","primCell","secondCell",
                                                "primLandLine","secondLandLine","addrLine1",
                                                "addrLine2","city","state","pin");
            // Secondary education required fields
            secEduFields=new Array("secondaryId","secondaryCourse",
                               "secondaryStream","secondaryPY","secondaryUniv",
                               "secondaryMM","secondaryOM","secondaryPM");
            // UG required fields
            ugFields=new Array("ugId","ugCourse","ugStream","ugPY","ugUniv",
                                           "ugLiveKT","ugPastKT","ugKtSubjects");
            // PG required fields
            pgFields=new Array("pgId","pgCourse","pgStream","pgPY","pgUniv",
                                           "pgLiveKT","pgPastKT","pgKtSubjects");
            // UG sem fields
            ugSemFields=new Array("ugSemMM","ugSemOM","ugSemPM");
            // PG sem fields
            pgSemFields=new Array("pgSemMM","pgSemOM","pgSemPM");    
        </script> 
    </head> 
    
    <body onLoad="initRCECForm()">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <%
                    String[] qual={"Under Graduate","Post Graduate"};
                    int candidateType=((Integer)session.getAttribute("candidateType")).intValue();
                    if(session.getAttribute("edit").equals("true")) { 
                        // Edit candidate
                %>
                        <spring:message code="editcandidate.title"/>-
                        <c:out value="${sessionScope.candidateId}"/>
                        (<c:out value="<%=qual[candidateType-2]%>"/>)
                <%
                    }
                    else { 
                %>
                        <spring:message code="registercandidate.title"/>-
                        <c:out value="${sessionScope.candidateId}"/>
                        (<c:out value="<%=qual[candidateType-2]%>"/>)
                <%
                    }
                %>
            </center>
        </div>
        <div id="navbar">
            <div id="holder">
                <ul id="outer">
                    <li id="tab1">
                        <a href="javascript:void(0)" id="personalTab"
                           onclick="hideAllTabsExcept(allTabs,'personal');
                                    setFocus('firstName');currentTab=0;">
                           <spring:message code="registercandidate.option1"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="secondaryTab"
                           onclick="hideAllTabsExcept(allTabs,'secondary'); 
                                    setFocus('secondaryId0');currentTab=1;">
                           <spring:message code="registercandidate.option2"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="ugTab"
                           onclick="hideAllTabsExcept(allTabs,'ug');
                                    setFocus(firstFields[2]);
                                    currentTab=2;">
                           <spring:message code="registercandidate.option3"/>
                        </a>
                    </li>
                    <li>
                         <a href="javascript:void(0)" id="pgTab"
                           onclick="hideAllTabsExcept(allTabs,'pg');
                                    setFocus(firstFields[3]);
                                    currentTab=3;">
                           <spring:message code="registercandidate.option4"/>
                         </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="contactTab"
                           onclick="hideAllTabsExcept(allTabs,'contact');
                                    setFocus('primEmail');currentTab=4;">
                           <spring:message code="registercandidate.option5"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="addressTab"
                           onclick="hideAllTabsExcept(allTabs,'address');
                                    setFocus('addrLine1');currentTab=5;">
                           <spring:message code="registercandidate.option6"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="preferencesTab"
                           onclick="hideAllTabsExcept(allTabs,'preferences');
                                    setFocus('placementPref');currentTab=6;">
                           <spring:message code="registercandidate.option7"/>
                        </a>
                    </li>
                </ul>
            </div> <!-- end holder div -->
        </div> <!-- end navbar div --> 
        <form:form name="regCandForm" id="regcandform" method="post" commandName="candidate">
            <%
                int userType=Integer.valueOf((Integer)session.getAttribute("userType")); 
            %>

            <!-- Purpose explained in the begining  -->
            <input type="hidden" id="userType" value="${sessionScope.userType}"/>
            <input type="hidden" id="userId" value="${sessionScope.userId}"/> 
            <input type="hidden" id="editFlag" value="${sessionScope.edit}"/> 
            <input type="hidden" id="candidateType" value="${sessionScope.candidateType}"/>
            <input type="hidden" id="ugRows" value="${sessionScope.ugMaxSemDisplay}"/>
            <input type="hidden" id="pgRows" value="${sessionScope.pgMaxSemDisplay}"/> 
            <form:hidden path="candidateUID"/>  

            <div id="personal" style="color:black;display: none;"> 
                <div class="tabHeader">
                        <center><spring:message code="registercandidate.option1.message"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="firstName" class="firstField">
                        <spring:message code="registercandidate.pd.firstName"/>:*
                        <form:input id="firstName" cssClass="firstFieldInput"
                                    onkeypress="return onlyAlphabets(event);" tabindex="1"
                                    maxlength="50" path="firstName"/><br>
                    </form:label>
                    <form:label path="lastName" class="secondField">
                        <spring:message code="registercandidate.pd.lastName"/>:*
                        <form:input id="lastName" cssClass="secondFieldInput"
                                    onkeypress="return onlyAlphabets(event);" tabindex="2"
                                    maxlength="50"  path="lastName"/><br>
                    </form:label>
                    <br><br>
                    <form:label path="fathersName" class="firstField">
                        <spring:message code="registercandidate.pd.fathersName"/>:
                        <form:input id="fathersName" cssClass="firstFieldInput"
                                    onkeypress="return onlyAlphabets(event);" tabindex="3"
                                    maxlength="50" path="fathersName"/><br>
                    </form:label>
                    <form:label path="mothersName" class="secondField">
                        <spring:message code="registercandidate.pd.mothersName"/>:
                        <form:input id="mothersName" cssClass="secondFieldInput"
                                    onkeypress="return onlyAlphabets(event);" tabindex="4"
                                    maxlength="50" path="mothersName"/><br>
                    </form:label>
                    <br><br>
                    <form:label class="firstField" path="gender">
                        <spring:message code="registercandidate.pd.gender"/>:*<br>
                        <form:radiobutton id="gender" checked="yes" path="gender"
                                          value="1" tabindex="5"/>
                        <spring:message code="registercandidate.pd.gender.male"/>
                        <form:radiobutton id="gender" path="gender" value="2"/>
                        <spring:message code="registercandidate.pd.gender.female"/>
                    </form:label>
                    <form:label class="secondField" path="dateOfBirth">
                         <spring:message code="registercandidate.pd.dob"/>:*
                         <form:input id="dateOfBirth" cssClass="secondFieldInput"
                                     tabindex="6"
                                     onkeypress="return onlyDate(event);"  
                                     path="dateOfBirth" maxlength="10"/><br>
                          <script type="text/javascript">
                            new tcal ({
                            // form name
                            'formname': 'regCandForm',
                            // input name
                            'controlname': 'dateOfBirth'
                            });
                           </script>
                    </form:label>
                    <br> 
                    <div style="margin-top:5%"></div>
                    <%@ include file="RegisterCandidateButtons.jsp" %>
                </div>
            </div>
            <div id="secondary" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="registercandidate.option2.message"/></center>
                </div>
                 <div class="tabContent">
                    <br> 
                        <table id="seduDataTable" class="viewPI">
                            <tr>
                                <th></th>
                                <th><spring:message code="registercandidate.ed.id"/>*</th>
                                <th><spring:message code="registercandidate.ed.course"/>*</th>
                                <th><spring:message code="registercandidate.ed.stream"/>*</th>
                                <th><spring:message code="registercandidate.ed.py"/>*</th>
                                <th><spring:message code="registercandidate.ed.board"/>*</th>
                                <th><spring:message code="registercandidate.ed.mm"/>*</th>
                                <th><spring:message code="registercandidate.ed.mo"/>*</th>
                                <th><spring:message code="registercandidate.ed.pm"/>*</th>
                            </tr>
                            <c:forEach var="i" begin="0" end="1">
                            <tr>
                                <td>
                                <td><form:input cssClass="eduTbleTxt" maxlength="15"
                                           id="secondaryId${i}"
                                           path="quals[${i}].primaryKey.qualRollNo"
                                           tabindex="1" 
                                           onkeypress="return onlyAlphaNumeric(event);"/> 
                                </td>
                                <td>
                                    <form:select id="secondaryCourse${i}"
                                                 onClick="canEdit();"
                                                 path="quals[${i}].course"
                                                 tabindex="2" cssClass="eduTbleDD">
                                        <form:options  items="${secondaryCourses}"/>
                                    </form:select>
                                </td>
                                <td>
                                    <form:select id="secondaryStream${i}" 
                                                 onClick="canEdit();"
                                                 path="quals[${i}].stream"
                                                 tabindex="3" cssClass="eduTbleDD">
                                        <form:options  items="${secondaryStreams}"/>
                                    </form:select>
                                </td>
                                <td>
                                    <form:input cssClass="eduTbleNum" maxlength="4"
                                            id="secondaryPY${i}"
                                            path="quals[${i}].passingYear"
                                            tabindex="4"
                                            onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input cssClass="eduTbleTxt" maxlength="50"
                                            id="secondaryUniv${i}"
                                            path="quals[${i}].university"
                                            tabindex="5"
                                            onkeypress="return onlyAlphabetsAndSpace(event);"/>
                                </td>
                                <td>
                                    <form:input cssClass="eduTbleNum" maxlength="4"
                                           id="secondaryMM${i}" 
                                           path="quals[${i}].maxMarks"
                                           onblur="return validateMarks('secondaryMM',
                                                                     'secondaryOM','secondaryPM',${i});"
                                           tabindex="6"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input cssClass="eduTbleNum" maxlength="4"
                                            id="secondaryOM${i}"
                                            path="quals[${i}].obtdMarks"
                                            onblur="return validateMarks('secondaryMM',
                                                                     'secondaryOM','secondaryPM',${i});"
                                           tabindex="7"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input cssClass="eduTbleNum" maxlength="6"
                                            id="secondaryPM${i}"
                                            path="quals[${i}].percentMarks"
                                            onblur="return validateMarks('secondaryMM',
                                                                     'secondaryOM','secondaryPM',${i});"
                                            tabindex="8" 
                                            onkeypress="return validatePercentageOnChar(this, event);"/>
                                </td>
                            </tr>
                            </c:forEach>
                        </table> 
                            <%@ include file="RegisterCandidateButtons.jsp" %>
                        </div>
            </div>
            <div id="ug" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center>
                        <spring:message code="registercandidate.option3.message"/>
                    </center>
                </div>
                <div class="ugTabContent">
                        <table id="ugDataTable" class="viewPI">
                        <tr>
                            <th></th>
                            <th><spring:message code="registercandidate.ed.id"/>*</th>
                            <th><spring:message code="registercandidate.ed.course"/>*</th>
                            <th><spring:message code="registercandidate.ed.stream"/>*</th>
                            <th><spring:message code="registercandidate.ed.py"/>*</th>
                            <th><spring:message code="registercandidate.ed.univ"/>*</th>
                            <th><spring:message code="registercandidate.ed.liveKT"/>*</th>
                            <th><spring:message code="registercandidate.ed.pastKT"/>*</th>
                            <th><spring:message code="registercandidate.ed.ktSubjects"/></th> 
                        </tr>
                        <tr>
                                <td/>
                                <td>
                                    <form:input id="ugId" tabindex="1" 
                                           onblur="setId();checkIfCandidateExists('ugId')"
                                           path="quals[2].primaryKey.qualRollNo"
                                           maxlength="15" cssClass="eduTbleTxt"
                                           onkeypress="return onlyAlphaNumeric(event);"/>
                                </td>
                                <td>
                                    <form:select id="ugCourse"
                                                 onClick="canEdit();"
                                                 path="quals[2].course"
                                                 tabindex="2" cssClass="eduTbleDD">
                                        <form:options  items="${ugCourses}"/>
                                    </form:select> 
                                </td>
                                <td>
                                    <form:select id="ugStream"
                                                 onClick="canEdit();"
                                                 path="quals[2].stream"
                                                 tabindex="3" cssClass="eduTbleDD">
                                        <form:options  items="${secondaryStreams}"/>
                                    </form:select> 
                                </td>
                                <td>
                                    <form:input tabindex="4" id="ugPY"
                                           path="quals[2].passingYear"
                                           maxlength="4" cssClass="eduTbleNum"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input id="ugUniv" path="quals[2].university"
                                           tabindex="5"
                                           maxlength="50" cssClass="eduTbleTxt"
                                           onkeypress="return onlyAlphabetsAndSpace(event);"/>
                                </td>
                                <td>
                                    <form:input id="ugLiveKT" tabindex="6"
                                           path="quals[2].liveKT"
                                           maxlength="2" cssClass="eduTbleNum"
                                           onblur="validateNumOfKTs('ugLiveKT')"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input id="ugPastKT" tabindex="7"
                                           path="quals[2].pastKT"
                                           maxlength="2" class="eduTbleNum"
                                           onblur="validateNumOfKTs('ugPastKT')"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input id="ugKtSubjects" tabindex="8"
                                           path="quals[2].ktSubjects"
                                           maxlength="200" class="eduTbleNum"
                                           onkeypress="return onlyAlphaNumericSpaceAndComma(event);"/>
                                </td>
                            </tr>
                        </table>
                        <table id="ugSemDataTable" class="viewPI">
                        <tr>
                            <th></th>
                            <th><spring:message code="registercandidate.ed.semester"/></th>
                            <th><spring:message code="registercandidate.ed.mm"/>*</th>
                            <th><spring:message code="registercandidate.ed.mo"/>*</th>
                            <th><spring:message code="registercandidate.ed.pm"/>*</th>
                            <th><spring:message code="registercandidate.ed.semester"/></th>
                            <th><spring:message code="registercandidate.ed.mm"/>*</th>
                            <th><spring:message code="registercandidate.ed.mo"/>*</th>
                            <th><spring:message code="registercandidate.ed.pm"/>*</th>
                        </tr>
                            <c:forEach var="i" begin="0" end="${sessionScope.ugMaxSemDisplay/2-1}">
                                <tr>
                                    <td/>
                                    <td>
                                        <label id="ugSem${2*i}"
                                               name="quals[2].semesters[${2*i}].primaryKey.semesterId">
                                               ${2*i+1}
                                        </label>
                                    </td>
                                    <td>
                                        <form:input id="ugSemMM${2*i}"
                                               maxlength="4" onkeypress="return onlyNumbers(event);"
                                               path="quals[2].semesters[${2*i}].maxMarks"
                                               onblur="return validateMarks('ugSemMM',
                                                                     'ugSemOM','ugSemPM',${2*i});"
                                               cssClass="eduTbleNum"
                                               tabindex="1"/>
                                    </td>
                                    <td>
                                        <form:input id="ugSemOM${2*i}"
                                               path="quals[2].semesters[${2*i}].marksObtd"
                                               maxlength="4" onkeypress="return onlyNumbers(event);"
                                               cssClass="eduTbleNum"
                                               onblur="return validateMarks('ugSemMM',
                                                                     'ugSemOM','ugSemPM',${2*i});"
                                               tabindex="2"/>
                                    </td>
                                    <td>
                                        <form:input id="ugSemPM${2*i}"
                                               maxlength="6"
                                               path="quals[2].semesters[${2*i}].percentMarks"
                                               onkeypress="return validatePercentageOnChar(this, event);"
                                               onblur="return validateMarks('ugSemMM',
                                                                     'ugSemOM','ugSemPM',${2*i});"
                                               cssClass="eduTbleNum"
                                               tabindex="3"/>
                                    </td>
                                     <td>
                                         <label id="ugSem${2*i+1}"
                                                name="quals[2].semesters[${2*i+1}].primaryKey.semesterId">
                                             ${2*i+2} 
                                         </label>
                                    </td>
                                    <td>
                                        <form:input id="ugSemMM${2*i+1}"
                                               maxlength="4" onkeypress="return onlyNumbers(event);"
                                               cssClass="eduTbleNum"
                                               path="quals[2].semesters[${2*i+1}].maxMarks"
                                               onblur="return validateMarks('ugSemMM',
                                                                     'ugSemOM','ugSemPM',${2*i+1});"
                                               tabindex="4"/>
                                    </td>
                                    <td>
                                        <form:input id="ugSemOM${2*i+1}"
                                               maxlength="4" onkeypress="return onlyNumbers(event);"
                                               path="quals[2].semesters[${2*i+1}].marksObtd"
                                               cssClass="eduTbleNum"
                                               onblur="return validateMarks('ugSemMM',
                                                                     'ugSemOM','ugSemPM',${2*i+1});"
                                               tabindex="5"/>
                                    </td>
                                    <td>
                                        <form:input id="ugSemPM${2*i+1}"
                                               path="quals[2].semesters[${2*i+1}].percentMarks"
                                               maxlength="6"
                                               onkeypress="return validatePercentageOnChar(this, event);"
                                               cssClass="eduTbleNum"
                                               onblur="return validateMarks('ugSemMM',
                                                                     'ugSemOM','ugSemPM',${2*i+1});"
                                               tabindex="6"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div style="margin-top:-5%">
                            <%@ include file="RegisterCandidateButtons.jsp" %>
                        </div> 
                </div>
            </div>
            <div id="pg" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="registercandidate.option4.message"/></center>
                </div>
                    <%
                     
                        if((userType==UserType.UGSTUDENT)||
                                (Integer.valueOf((Integer)session.getAttribute("candidateType"))==2))
                        {
                            // UG candidate cannot see these fields
                    %>
                            <div class="tabContent">
                                <div class="pgMessage">
                                    <spring:message code="registercandidate.pg.message1"/>
                                </div>
                            </div>
                    <%
                        }
                        else if(userType==UserType.PGSTUDENT||userType==UserType.ADMIN)
                        {
                            // Only PG candidate and Admin can see these fields
                    %>
                    <div class="tabContent"> 
                    <table id="pgDataTable" class="viewPI">
                        <tr>
                            <th></th>
                            <th><spring:message code="registercandidate.ed.id"/>*</th>
                            <th><spring:message code="registercandidate.ed.course"/>*</th>
                            <th><spring:message code="registercandidate.ed.stream"/>*</th>
                            <th><spring:message code="registercandidate.ed.py"/>*</th>
                            <th><spring:message code="registercandidate.ed.univ"/>*</th>
                            <th><spring:message code="registercandidate.ed.liveKT"/>*</th>
                            <th><spring:message code="registercandidate.ed.pastKT"/>*</th>
                            <th><spring:message code="registercandidate.ed.ktSubjects"/></th>
                        </tr>
                        <tr>
                                <td/>
                                <td> 
                                    <form:input id="pgId" tabindex="1"
                                           onblur="setId();checkIfCandidateExists('pgId')"
                                           path="quals[3].primaryKey.qualRollNo"
                                           maxlength="15" cssClass="eduTbleTxt"
                                           onkeypress="return onlyAlphaNumeric(event);"/>
                                </td>
                                <td>
                                    <form:select path="quals[3].course" id="pgCourse"
                                                 onClick="canEdit();"
                                                 cssClass="eduTbleDD" tabindex="2">
                                        <form:options items="${pgCourses}"/>
                                    </form:select>
                                </td>
                                <td>
                                    <form:select path="quals[3].stream" id="pgStream"
                                                 onClick="canEdit();"
                                                 cssClass="eduTbleDD" tabindex="3">
                                        <form:options items="${pgStreams}" />
                                    </form:select>
                                </td>
                                <td>
                                    <form:input tabindex="4" id="pgPY"
                                           path="quals[3].passingYear"
                                           maxlength="4" cssClass="eduTbleNum"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input id="pgUniv" tabindex="5"
                                           path="quals[3].university"
                                           maxlength="50" cssClass="eduTbleTxt"
                                           onkeypress="return onlyAlphabetsAndSpace(event);"/>
                                </td>
                                <td>
                                    <form:input id="pgLiveKT" tabindex="6"
                                           path="quals[3].liveKT"
                                           maxlength="2" cssClass="eduTbleNum"
                                           onblur="validateNumOfKTs('pgLiveKT')"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input id="pgPastKT" tabindex="7"
                                           path="quals[3].pastKT"
                                           maxlength="2" cssClass="eduTbleNum"
                                           onblur="validateNumOfKTs('pgPastKT')"
                                           onkeypress="return onlyNumbers(event);"/>
                                </td>
                                <td>
                                    <form:input id="pgKtSubjects" tabindex="8"
                                           maxlength="200" cssClass="eduTbleNum"
                                           path="quals[3].ktSubjects"
                                           onkeypress="return onlyAlphaNumericSpaceAndComma(event);"/>
                                </td>
                            </tr>
                    </table>
                     <table id="pgSemDataTable" class="viewPI">
                        <tr>
                            <th></th>
                            <th><spring:message code="registercandidate.ed.semester"/></th>
                            <th><spring:message code="registercandidate.ed.mm"/>*</th>
                            <th><spring:message code="registercandidate.ed.mo"/>*</th>
                            <th><spring:message code="registercandidate.ed.pm"/>*</th>
                            <th><spring:message code="registercandidate.ed.semester"/></th>
                            <th><spring:message code="registercandidate.ed.mm"/>*</th>
                            <th><spring:message code="registercandidate.ed.mo"/>*</th>
                            <th><spring:message code="registercandidate.ed.pm"/>*</th>
                        </tr>
                        <c:forEach var="i" begin="0" end="${sessionScope.pgMaxSemDisplay/2-1}">
                                <tr>
                                    <td/>
                                    <td>
                                        <label  id="pgSem${2*i}"
                                                name="quals[3].semesters[${2*i}].primaryKey.semesterId">
                                                ${2*i+1}
                                        </label>
                                    </td>
                                    <td>
                                        <form:input type="text" id="pgSemMM${2*i}"
                                               path="quals[3].semesters[${2*i}].maxMarks"
                                               maxlength="4"
                                               onkeypress="return onlyNumbers(event);"
                                               onblur="return validateMarks('pgSemMM',
                                                                     'pgSemOM','pgSemPM',${2*i});"
                                               cssClass="eduTbleNum"
                                               tabindex="1"/>
                                    </td>
                                    <td>
                                        <form:input id="pgSemOM${2*i}"
                                               path="quals[3].semesters[${2*i}].marksObtd"
                                               maxlength="4"
                                               onkeypress="return onlyNumbers(event);"
                                               onblur="return validateMarks('pgSemMM',
                                                                     'pgSemOM','pgSemPM',${2*i});"
                                               cssClass="eduTbleNum"
                                               tabindex="2"/>
                                    </td>
                                    <td>
                                        <form:input id="pgSemPM${2*i}"
                                               path="quals[3].semesters[${2*i}].percentMarks"
                                               maxlength="6"
                                               onkeypress="return validatePercentageOnChar(this, event);"
                                               onblur="return validateMarks('pgSemMM',
                                                                     'pgSemOM','pgSemPM',${2*i});"
                                               cssClass="eduTbleNum"
                                               tabindex="3"/>
                                    </td>
                                     <td>
                                         <label id="pgSem${2*i+1}"
                                                name="quals[3].semesters[${2*i+1}].primaryKey.semesterId">
                                                ${2*i+2}
                                         </label>
                                    </td>
                                    <td>
                                        <form:input id="pgSemMM${2*i+1}"
                                               maxlength="4"
                                               onkeypress="return onlyNumbers(event);"
                                               path="quals[3].semesters[${2*i+1}].maxMarks"
                                               onblur="return validateMarks('pgSemMM',
                                                                     'pgSemOM','pgSemPM',${2*i+1});"
                                               cssClass="eduTbleNum"
                                               tabindex="4"/>
                                    </td>
                                    <td>
                                        <form:input id="pgSemOM${2*i+1}"
                                               path="quals[3].semesters[${2*i+1}].marksObtd"
                                               maxlength="4"
                                               onkeypress="return onlyNumbers(event);"
                                               onblur="return validateMarks('pgSemMM',
                                                                     'pgSemOM','pgSemPM',${2*i+1});"
                                               cssClass="eduTbleNum"
                                               tabindex="5"/>
                                    </td>
                                    <td>
                                        <form:input id="pgSemPM${2*i+1}"
                                               maxlength="6"
                                               onkeypress="return validatePercentageOnChar(this, event);"
                                               path="quals[3].semesters[${2*i+1}].percentMarks"
                                               onblur="return validateMarks('pgSemMM',
                                                                     'pgSemOM','pgSemPM',${2*i+1});"
                                               cssClass="eduTbleNum"
                                               tabindex="6"/>
                                    </td>
                                </tr>
                        </c:forEach>
                     </table> 
                    <div style="margin-top:-5%"></div>
                    <%@ include file="RegisterCandidateButtons.jsp" %>
                </div>
               <%
                   }
               %>
            </div>
            <div id="contact" style="color:black;display: none;"> 
                 <div class="tabHeader">
                     <center><spring:message code="registercandidate.option5.message"/></center>
                 </div>
                 <div class="tabContent">
                     <br>
                     <form:label class="firstField" path="contact.primaryEmail">
                        <spring:message code="registercandidate.cd.pemail"/>:*
                        <form:input cssClass="firstFieldInput" id="primEmail"
                                    onblur="return validateEmail('primEmail');"
                                    maxlength="50" tabindex="1"
                                    path="contact.primaryEmail"/><br>
                    </form:label>
                    <form:label class="secondField" path="contact.secondaryEmail">
                        <spring:message code="registercandidate.cd.semail"/>:
                        <form:input cssClass="secondFieldInput" id="secondEmail"
                                    onblur="return validateEmail('secondEmail');"
                                    maxlength="50" tabindex="2"
                                    path="contact.secondaryEmail"/><br>
                    </form:label>
                    <form:label class="firstField" path="contact.primaryCellNo">
                        <spring:message code="registercandidate.cd.pcell"/>:*
                        <form:input cssClass="firstFieldInput" id="primCell"
                                    maxlength="10" tabindex="3"
                                    onkeypress="return onlyNumbers(event);"
                                    onblur="return validateMobile('primCell');"
                                    path="contact.primaryCellNo"/><br>
                    </form:label>
                    <form:label class="secondField" path="contact.secondaryCellNo">
                        <spring:message code="registercandidate.cd.scell"/>:
                        <form:input cssClass="secondFieldInput" id="secondCell"
                                    maxlength="10" tabindex="4"
                                    onkeypress="return onlyNumbers(event);"
                                    onblur="return validateMobile('secondCell');"
                                    path="contact.secondaryCellNo"/><br>
                    </form:label>
                    <form:label class="firstField" path="contact.primaryLandLineNo">
                        <spring:message code="registercandidate.cd.plandLine"/>:*
                        <form:input cssClass="firstFieldInput" id="primLandLine"
                                    maxlength="15" tabindex="5"
                                    onblur="validateLandLine('primLandLine');"
                                    onkeypress="return onlyNumbers(event);"
                                    path="contact.primaryLandLineNo"/><br>
                    </form:label>
                    <form:label class="secondField" path="contact.secondaryLandLineNo">
                        <spring:message code="registercandidate.cd.slandLine"/>:
                        <form:input cssClass="secondFieldInput" id="secondLandLine"
                                    maxlength="15" tabindex="6"
                                    onblur="validateLandLine('secondLandLine');"
                                    onkeypress="return onlyNumbers(event);"
                                    path="contact.secondaryLandLineNo"/><br>
                    </form:label>
                    <br><br>
                    <div style="margin-top:15%"></div>
                        <%@ include file="RegisterCandidateButtons.jsp" %>
                 </div>
            </div>
            <div id="address" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="registercandidate.option6.message"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label class="firstField" path="address.addressLine1">
                        <spring:message code="registercandidate.address.addrLine1"/>:*
                        <form:input cssClass="firstFieldInput" id="addrLine1"
                                    tabindex="1"  maxlength="50"
                                    path="address.addressLine1"/><br>
                    </form:label>
                    <form:label class="secondField" path="address.addressLine2">
                        <spring:message code="registercandidate.address.addrLine2"/>:
                        <form:input cssClass="secondFieldInput" id="addrLine2"
                                    tabindex="2"  maxlength="50"
                                    path="address.addressLine2"/><br>
                    </form:label> 
                    <form:label class="firstField" path="address.city">
                            <spring:message code="registercandidate.address.city"/>:*
                            <form:input cssClass="firstFieldInput" id="city"
                                        tabindex="3"  maxlength="30"
                                        onkeypress="return onlyAlphabets(event);"
                                        path="address.city"/><br>
                    </form:label>
                    <form:label class="secondField" path="address.residentState">
                        <spring:message code="registercandidate.address.residentState"/>:*
                        <form:input cssClass="secondFieldInput" id="state"
                                    tabindex="4" maxlength="30"
                                    onkeypress="return onlyAlphabetsAndSpace(event);"
                                    path="address.residentState"/><br>
                    </form:label>
                    <form:label class="firstField" path="address.pin">
                            <spring:message code="registercandidate.address.pin"/>:*
                            <form:input cssClass="firstFieldInput" id="pin"
                                        tabindex="5" maxlength="6"
                                        onblur="validatePinCode('pin')"
                                        onkeypress="return onlyNumbers(event);"
                                        path="address.pin"/><br>
                    </form:label>
                    <br><br>
                    <div style="margin-top:15%"></div>
                    <%@ include file="RegisterCandidateButtons.jsp" %>
                </div>
            </div>
            <div id="preferences" style="color:black; display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="registercandidate.option7.message"/></center>
                </div>
                <div class="tabContent">
                <br> 
                <form:label class="placementPref" path="">   
                    <spring:message code="registercandidate.placement.preference"/>:
                    <form:checkbox id="pp1" path="placementPrefs[0].primaryKey.placementPref" 
                                   value="1"/>Same stream
                    <form:checkbox id="pp2" path="placementPrefs[1].primaryKey.placementPref" 
                                value="2"/>Core streams
                    <form:checkbox id="pp3" path="placementPrefs[2].primaryKey.placementPref" 
                                value="3"/>All streams  
                    <%
                        Candidate cand=(Candidate)request.getAttribute("candidate");
                        List<CandidatePlacementPref> placementPrefs=cand.getPlacementPrefs(); 
                        int index=-1; 
                        for(int i=0;i<placementPrefs.size();i++){
                                Integer pref=placementPrefs.get(i).getPrimaryKey().getPlacementPref();  
                                if(pref!=null) {
                                    index=pref.intValue(); 
                    %>
                    <script>
                        initCB(<%=index%>);
                     </script>
                    <%
                                }
                        }
                    %>   
                </form:label> 
                <br><br>
                    <div style="margin-top:15%"></div>
                        <%@ include file="RegisterCandidateButtons.jsp" %>
                    </div>
                </div>
            </div>
        </form:form>
        <div style="margin-top:-10%">
             <jsp:include page="../common/Footer.jsp"></jsp:include>
        </div>
    </body>
</html>
