<%-- 
    Document   : SearchCandidate
    Created on : Oct 11, 2011, 6:57:23 PM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.edu.tpc.domain.UserType"%>
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
        
        <script type="text/javascript" src="javascript/ajax/jquery.js"></script>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
         <script type="text/javascript" src="javascript/common/JSValidation.js"></script>
        
        <script type="text/javascript">
            var mainDivs=new Array("personal", "education","contact","address",
                                   "eligibility","placed","custom");
            var searchDivs=new Array("sById","sByStream","sByPercent","sByDegree",
                                        "sByKts", "sByUniv","sByPlacement");
            var previous=-1;
            function showSearchEduForm() { 
                var element=document.getElementById("searchOptionsId");
                var option=element.options[element.selectedIndex].value;
                var divA = document.getElementById(searchDivs[option-1]);
                divA.style.display = "block";
                if(previous!=-1) { 
                    var divB = document.getElementById(searchDivs[previous-1]);
                    divB.style.display = "none";
                }
                previous=option;
            }
            function showCandidateByPersonalInfo()
            {
                document.searchcandidateForm.action='showcandidatebyperinfo.html';
                document.searchcandidateForm.submit();
            }
            function showPlacedCandidates()
            {
                document.searchcandidateForm.action='showplacedcandidates.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateById()
            {
                document.searchcandidateForm.action='showcandidatebyid.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByStream(){
                document.searchcandidateForm.action='showcandidatebystream.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByContact()
            {
                document.searchcandidateForm.action='showcandidatebycontact.html';
                document.searchcandidateForm.submit();
            }
            function validSearchPlaced() { 
                var placementYear=$('#placementYear').val();
                if(isEmpty(placementYear)){
                    alert('Please supply placement year.');
                    setFocus('placementYear');
                    return false;
                }
                return true;
            } 
            function showCandidateByPlaced() {  
                if(validSearchPlaced()){ 
                        document.searchPlacedForm.action='showplacedcandidates.html';
                        document.searchPlacedForm.submit(); 
                       //$.post('showplacedcandidates.html', $('#placementYear').val()) ;
                       //window.location="search/ViewPlacedCandidates"; 
                } 
                return false;
            }
            function showCandidateByEligibility() { 
                document.searchcandidateForm.action='showcandidatebyeligibility.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByPercentage()
            {
                document.searchcandidateForm.action='showcandidatebypercentage.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByDegree()
            {
                document.searchcandidateForm.action='showcandidatebydegree.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByKTs()
            {
                document.searchcandidateForm.action='showcandidatebykts.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByPY()
            {
                document.searchcandidateForm.action='showcandidatebypy.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByUniv()
            {
                document.searchcandidateForm.action='showcandidatebyuniv.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByAddress()
            {
                document.searchcandidateForm.action='showcandidatebyaddress.html';
                document.searchcandidateForm.submit();
            }
            function showCandidateByPlacement(){
                document.searchcandidateForm.action='showcandbyplacement.html';
                document.searchcandidateForm.submit();
            }
        </script>
        <script type="text/javascript">
            function initializeSCForm() { 
                hideAllTabsExcept(mainDivs,'personal'); 
                setFocus('firstName');
            }
        </script>
    </head>
    <body onLoad="initializeSCForm();">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="searchcandidate.title"/>
            </center>
        </div>  
        <div id="navbar">
            <div id="holder">
                <ul id="outer">
                    <li><a href="javascript:void(0)" id="personalTab"
                                     onclick="hideAllTabsExcept(mainDivs,'personal');
                                     setFocus('firstName');">
                                    <spring:message code="searchcandidate.option1"/>
                                  </a>
                    </li>
                    <li><a href="javascript:void(0)" id="educationTab"
                           onclick="hideAllTabsExcept(mainDivs,'education');
                                    showSearchEduForm()">
                                    <spring:message code="searchcandidate.option2"/></a>
                    </li>
                    <li><a href="javascript:void(0)" id="contactTab"
                           onclick="hideAllTabsExcept(mainDivs,'contact');
                                    setFocus('primEmail')">
                            <spring:message code="searchcandidate.option3"/></a>
                    </li>
                    <li><a href="javascript:void(0)" id="addressTab"
                           onclick="hideAllTabsExcept(mainDivs,'address');
                                    setFocus('addrLine1');">
                            <spring:message code="searchcandidate.option4"/></a>
                    </li>
                    <li><a href="javascript:void(0)" id="eligibilityTab"
                           onclick="hideAllTabsExcept(mainDivs,'eligibility');
                                    setFocus('passingYear')">
                            <spring:message code="searchcandidate.option5"/></a>
                    </li>
                    <li><a href="javascript:void(0)" id="placedTab"
                           onclick="hideAllTabsExcept(mainDivs,'placed');
                                    setFocus('placementYear')">
                            <spring:message code="searchcandidate.option6"/></a>
                    </li>
                    <li><a href="javascript:void(0)" id="customTab"
                           onclick="hideAllTabsExcept(mainDivs,'custom');
                                    setFocus('custom')">
                            <spring:message code="searchcandidate.option7"/></a>
                    </li>
                </ul>
            </div> <!-- end holder div -->
        </div> <!-- end navbar div --> 
            <div id="personal" style="color:black;display: none;"> 
                <div class="tabHeader">
                        <center><spring:message code="searchcandidate.pd.header"/></center>
                </div>
                <div class="tabContent"> 
                    <br>
                    <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                   commandName="searchCriteria" action="showcandidatebyperinfo.html"> 
                    <form:label path="" class="firstField">
                        <spring:message code="searchcandidate.pd.firstName"/>:
                        <form:input id="firstName" cssClass="firstFieldInput"
                                onkeypress="return onlyAlphabets(event);" tabindex="1"
                                maxlength="30" path="firstName"/>
                    </form:label>
                    <form:label path="" class="secondField">
                        <spring:message code="searchcandidate.pd.lastName"/>:
                        <form:input id="lastName" cssClass="secondFieldInput"
                                onkeypress="return onlyAlphabets(event);" tabindex="2"
                                maxlength="30" path="lastName"/>
                    </form:label>
                    <br><br>
                    <form:label path="" class="firstField">
                        <spring:message code="searchcandidate.pd.dob"/>:
                        <form:input id="dob" cssClass="firstFieldInput"
                                onkeypress="return onlyAlphabets(event);" tabindex="3"
                                maxlength="30" path="dob"/><br>
                        <script type="text/javascript">
                            new tcal ({
                            // form name
                            'formname': 'searchcandidateForm',
                            // input name
                            'controlname': 'dob'
                            });
                           </script>
                    </form:label> 
                    <form:label class="secondField" path="">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <input class="secondFieldInput" id="passingYear"
                                        tabindex="4"  maxlength="4"
                                        onkeypress="return onlyNumbers(event);"
                                        name="passingYear"/><br>
                    </form:label>                                
                    <br><br><br>
                    <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByPersonalInfo()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                    </div>
                     </form:form> 
                 </div>
            </div> 
            <div id="education" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="searchcandidate.edu.header"/></center>
                </div>
                <div class="tabContent">
                    <br> 
                    <label path=""  class="firstField">
                        <spring:message code="searchcandidate.edu.searchOptions"/>
                        <br>
                        <select tabindex="1" class="firstFieldInput"
                                     id="searchOptionsId" onchange="showSearchEduForm()">
                            <c:forEach var="searchOption" items="${eduSearchOptions}">
                                <option  class="firstFieldInput" value="${searchOption.key}"/>${searchOption.value}
                            </c:forEach>
                        </select><br>
                    </label>
                    <br>
                    <div id="sById" style="color:black;display: none;">
                        <br><br>
                        <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                                             commandName="searchCriteria" action="showcandidatebyid.html"> 
                                <form:label class="firstField" path="">
                                        <spring:message code="searchcandidate.edu.id"/>:
                                        <form:input cssClass="firstFieldInput" id="candidateId"
                                                    tabindex="1" path="candidateId" size="30"
                                                    onkeypress="return onlyAlphaNumeric(event);"
                                                    maxlength="15"/><br>
                                </form:label>
                                <br><br><br>
                                <div class="typeBButn">
                                    <input class="allButtons" type="submit"
                                    onClick="javascript:showCandidateById()"
                                    value="<spring:message code='searchcandidate.button1.label'/>">
                                    <%@ include file="SCButtons.jsp" %>
                                </div>
                        </form:form>
                    </div> 
                    <div id="sByStream" style="color:black;display: none;">
                        <br><br>
                        <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                                             commandName="searchCriteria" action="showcandidatebystream.html"> 
                             <form:label path="courseId" class="firstField">
                            <spring:message code="candidatepi.course.course"/>:*
                            <form:select path="courseId" tabindex="1" class="firstFieldInput">
                                <form:options items="${courses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="streamId" class="secondField">
                            <spring:message code="candidatepi.course.stream"/>:*
                            <form:select path="streamId" tabindex="2" class="secondFieldInput">
                                <form:options items="${streams}"/>
                            </form:select>
                    </form:label>
                        <br>
                        <form:label class="firstField" path="passingYear">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <form:input cssClass="firstFieldInput" id="yearofPassing"
                                        tabindex="3" path="passingYear" size="30"
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="4"/><br>
                        </form:label>
                         <br><br><br><br>
                        <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByStream()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                        </div>
                        </form:form>
                    </div> 
                    <div id="sByPercent" style="color:black;display: none;">
                        <br><br>
                         <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                                              commandName="searchCriteria" action="showcandidatebypercentage.html"> 
                         <form:label path="courseId" class="firstField">
                            <spring:message code="candidatepi.course.course"/>:*
                            <form:select path="courseId" tabindex="1" class="firstFieldInput">
                                <form:options items="${courses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="streamId" class="secondField">
                            <spring:message code="candidatepi.course.stream"/>:*
                            <form:select path="streamId" tabindex="2" class="secondFieldInput">
                                <form:options items="${streams}"/>
                            </form:select>
                    </form:label>
                        <br>
                        <form:label class="firstField" path="startPercentage">
                            <spring:message code="searchcandidate.edu.startpercent"/>:
                            <form:input cssClass="firstFieldInput" id="startPercent"
                                        tabindex="3" path="startPercentage" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label>
                        <form:label class="secondField" path="endPercentage">
                            <spring:message code="searchcandidate.edu.endpercent"/>:
                            <form:input cssClass="secondFieldInput" id="endPercent"
                                        tabindex="4" path="endPercentage" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label>
                        <br>
                        <form:label class="firstField" path="passingYear">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <form:input cssClass="firstFieldInput" id="passingYear"
                                        tabindex="5" path="passingYear"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="4"/><br>
                        </form:label>
                        <br><br> <br><br><br>
                        <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByPercentage()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                        </div>
                         </form:form>
                    </div> 
                    <div id="sByDegree" style="color:black;display: none;">
                        <br><br>
                        <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                                              commandName="searchCriteria" action="showcandidatebydegree.html"> 
                        <form:label path="courseId" cssClass="firstField">
                            <spring:message code="candidatepi.course.course"/>:*
                            <form:select path="courseId" tabindex="1" cssClass="firstFieldInput">
                                <form:options items="${courses}"/>
                            </form:select>
                        </form:label>
                        <form:label cssClass="secondField" path="passingYear">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <form:input cssClass="secondFieldInput" id="yearofPassing"
                                        tabindex="2" path="passingYear"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="4"/><br>
                        </form:label>
                        <br><br><br>
                        <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByDegree()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                        </div>
                        </form:form> 
                    </div>  
                    <div id="sByKts" style="color:black;display: none;">
                        <br><br>
                        <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                            commandName="searchCriteria" action="showcandidatebykts.html">    
                        <form:label path="courseId" cssClass="firstField">
                            <spring:message code="candidatepi.course.course"/>:*
                            <form:select path="courseId" tabindex="1" cssClass="firstFieldInput">
                                <form:options items="${courses}"/>
                            </form:select>
                        </form:label>
                         <form:label path="streamId" class="secondField">
                            <spring:message code="candidatepi.course.stream"/>:*
                            <form:select path="streamId" tabindex="2" class="secondFieldInput">
                                <form:options items="${streams}"/>
                            </form:select>
                        </form:label>    
                        <br>
                        <form:label cssClass="firstField" path="liveKTs">
                            <spring:message code="registercandidate.ed.liveKT"/>:
                            <input class="firstFieldInput" id="liveKTs"
                                        tabindex="3" name="liveKTs"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="2"/><br>
                        </form:label>
                        <form:label cssClass="secondField" path="pastKTs">
                            <spring:message code="registercandidate.ed.pastKT"/>:
                            <input class="secondFieldInput" id="pastKTs"
                                        tabindex="3" name="pastKTs"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="2"/><br>
                        </form:label>    
                            <br>
                        <form:label cssClass="firstField" path="passingYear">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <form:input cssClass="firstFieldInput" id="yearofPassing"
                                        tabindex="4" path="passingYear"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="4"/><br>
                        </form:label>    
                        <br><br><br><br><br> 
                        <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByKTs()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                        </div>
                          </form:form>  
                    </div> 
                    <div id="sByUniv" style="color:black;display: none;">
                        <br><br>
                         <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                            commandName="searchCriteria" action="showcandidatebyuniv.html"> 
                        <form:label class="firstField" path="univ">
                            <spring:message code="searchcandidate.edu.univ"/>:
                            <form:input cssClass="firstFieldInput" id="univ"
                                        tabindex="1" path="univ" 
                                        onkeypress="return onlyAlphabetsAndSpace(event);"
                                        maxlength="30"/><br>
                        </form:label>
                        <form:label class="secondField" path="passingYear">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <form:input cssClass="secondFieldInput" id="yearofPassing"
                                        tabindex="2" path="passingYear"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="4"/><br>
                        </form:label>
                        <br><br><br>
                        <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByUniv()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                        </div>
                         </form:form>
                    </div> 
                    <div id="sByPlacement" style="color:black;display: none;">
                        <br><br>
                         <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                            commandName="searchCriteria" action="showcandbyplacement.html"> 
                       <form:label path="courseId" class="firstField">
                            <spring:message code="candidatepi.course.course"/>:*
                            <form:select path="courseId" tabindex="1" class="firstFieldInput">
                                <form:options items="${courses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="streamId" class="secondField">
                            <spring:message code="candidatepi.course.stream"/>:*
                            <form:select path="streamId" tabindex="2" class="secondFieldInput">
                                <form:options items="${streams}"/>
                            </form:select>
                    </form:label>
                            <br>
                         <form:label class="firstField" path="numPlacements">
                            <spring:message code="notifycandidate.criteria.placements"/>:
                            <form:input cssClass="firstFieldInput" id="numPlacements"
                                        tabindex="3" path="numPlacements"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="2"/><br>
                        </form:label>   
                        <form:label class="secondField" path="passingYear">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <form:input cssClass="secondFieldInput" id="yearofPassing"
                                        tabindex="4" path="passingYear"  
                                        onkeypress="return onlyNumeric(event);"
                                        maxlength="4"/><br>
                        </form:label>
                        <br><br><br><br><br>
                        <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByPlacement()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                        </div>
                         </form:form>
                    </div> 
                </div>
            </div>  
            <div id="contact" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="searchcandidate.contact.header"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                   commandName="searchCriteria" action="showcandidatebycontact.html">    
                     <form:label class="firstField" path="">
                        <spring:message code="searchcandidate.cd.pemail"/>:
                        <form:input cssClass="firstFieldInput" id="primEmail"
                                    maxlength="50" tabindex="1" path="primEmail"/><br>
                    </form:label>
                    <form:label class="secondField" path="">
                        <spring:message code="searchcandidate.cd.pcell"/>:
                        <form:input cssClass="secondFieldInput" id="primCell"
                                    maxlength="15" tabindex="2"
                                    onkeypress="return onlyNumbers(event);"
                                    path="primCell"/><br>
                    </form:label>
                <br>
                <form:label class="firstField" path="">
                        <spring:message code="searchcandidate.edu.py"/>:
                        <input class="firstFieldInput" id="passingYear"
                                tabindex="3"  maxlength="4"
                                onkeypress="return onlyNumbers(event);"
                                name="passingYear"/><br>
                </form:label>                    
                    <br><br><br>
                    <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByContact()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                    </div>
                </div>
            </div>
        </form:form> 
            <div id="address" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="searchcandidate.address.header"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                                         commandName="searchCriteria" action="showcandidatebyaddress.html"> 
                    <form:label class="firstField" path="">
                        <spring:message code="searchcandidate.address.addrLine1"/>:
                        <form:input cssClass="firstFieldInput" id="addrLine1"
                                    tabindex="1"  maxlength="50"
                                    path="addressLine1"/><br>
                    </form:label>
                    <form:label class="secondField" path="">
                        <spring:message code="searchcandidate.address.addrLine2"/>:
                        <form:input cssClass="secondFieldInput" id="addrLine2"
                                    tabindex="2"  maxlength="50"
                                    path="addressLine2"/><br>
                    </form:label>
                    <br>
                    <form:label class="firstField" path="">
                            <spring:message code="searchcandidate.address.city"/>:
                            <form:input cssClass="firstFieldInput" id="city"
                                        tabindex="3"  maxlength="30"
                                        onkeypress="return onlyAlphabets(event);"
                                        path="city"/><br>
                    </form:label>
                    <form:label class="secondField" path="">
                        <spring:message code="searchcandidate.address.residentState"/>:
                        <form:input cssClass="secondFieldInput" id="state"
                                    tabindex="4" maxlength="30"
                                    onkeypress="return onlyAlphabetsAndSpace(event);"
                                    path="state"/><br>
                    </form:label>
                     <br>               
                    <form:label class="firstField" path="">
                            <spring:message code="searchcandidate.address.pin"/>:
                            <form:input cssClass="firstFieldInput" id="pin"
                                        tabindex="5" maxlength="6"
                                        onkeypress="return onlyNumbers(event);"
                                        path="pin"/><br>
                    </form:label>
                    <form:label class="secondField" path="">
                            <spring:message code="searchcandidate.edu.py"/>:
                            <input class="secondFieldInput" id="passingYear"
                                        tabindex="6"  maxlength="4"
                                        onkeypress="return onlyNumbers(event);"
                                        name="passingYear"/><br>
                    </form:label>                    
                    <br><br><br><br><br><br><br>
                    <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByAddress()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                    </div>
                </div>
                 </form:form>    
            </div> 
            <div id="eligibility" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="searchcandidate.eligibility.header"/></center>
                </div>
                <div class="tabContent"> 
                    <br>
                     <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                   commandName="searchCriteria" action="showcandidatebyeligibility.html"> 
                     
                    <form:label path="courseId" class="firstField">
                            <spring:message code="candidatepi.course.course"/>:*
                            <form:select path="courseId" tabindex="1" class="firstFieldInput">
                                <form:options items="${courses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="streamId" class="secondField">
                            <spring:message code="candidatepi.course.stream"/>:*
                            <form:select path="streamId" tabindex="2" class="secondFieldInput">
                                <form:options items="${streams}"/>
                            </form:select>
                    </form:label>
                    <br>
                    <form:label class="firstField" path="passingYear">
                            <spring:message code="searchcandidate.eligibility.py"/>:*
                            <input class="firstFieldInput" id="passingYear"
                                        tabindex="3"  maxlength="4"
                                        onkeypress="return onlyNumbers(event);"
                                        name="passingYear"/><br>
                    </form:label>
                    <form:label class="secondField" path="sscPercentage">
                            <spring:message code="searchcandidate.eligibility.sscmarks"/>:
                            <input class="firstFieldInput" id="sscPercentage"  
                                        tabindex="4"  maxlength="6"
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        name="sscPercentage"/><br>
                    </form:label>
                    <br>
                    <form:label class="firstField" path="hscPercentage">
                            <spring:message code="searchcandidate.eligibility.hscmarks"/>:
                            <input class="firstFieldInput" id="hscPercentage"
                                        tabindex="5"  maxlength="6"
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        name="hscPercentage"/><br>
                    </form:label> 
                    <form:label class="secondField" path="ugPercentage">
                            <spring:message code="searchcandidate.eligibility.ugmarks"/>:
                            <input class="secondFieldInput" id="ugPercentage"
                                        tabindex="6"  maxlength="6"
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        name="ugPercentage"/><br>
                    </form:label>
                    <br> 
                    <form:label class="firstField" path="pgPercentage">
                            <spring:message code="searchcandidate.eligibility.pgmarks"/>:
                            <input  class="firstFieldInput" id="pgPercentage"
                                        tabindex="7"  maxlength="6"
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        name="pgPercentage"/><br>
                    </form:label> 
                    <form:label class="secondField" path="liveKTs">
                            <spring:message code="searchcandidate.eligibility.liveKTs"/>:
                            <form:input  cssClass="secondFieldInput" path="liveKTs"
                                        tabindex="8"  maxlength="2"
                                        onkeypress="return onlyNumbers(event);"
                                        id="liveKTs"/><br>
                    </form:label>         
                    <br><br><br>  <br><br><br>
                    <div class="typeBButn">
                            <input class="allButtons" type="submit"
                            onClick="javascript:showCandidateByEligibility()"
                            value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                    </div>
                    </form:form>
                </div>
            </div>   
            <div id="placed" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="searchcandidate.placed.header"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:form name="searchcandidateForm" id="searchcandidate" method="post" 
                            commandName="searchCriteria" action="showplacedcandidates.html"> 
                    <label class="firstField" path="">
                            <spring:message code="searchcandidate.placed.pyear"/>:*
                            <input class="firstFieldInput" id="placementYear"
                                       tabindex="1"  maxlength="4"
                                       onkeypress="return onlyNumbers(event);"
                                       name="placementYear"/><br>
                    </label>
                    <br><br><br>
                    <div class="typeBButn">
                            <input  class="allButtons" type="submit"
                                        onClick="return showCandidateByPlaced()"
                                        value="<spring:message code='searchcandidate.button1.label'/>">
                            <%@ include file="SCButtons.jsp" %>
                    </div>
                    </form:form>
                </div>
            </div>  
            <div id="custom" style="color:black;display: none;"> 
                <div class="tabHeader">
                    <center><spring:message code="searchcandidate.custom.header"/></center>
                </div>
                <div class="tabContent">
                    <br> To be implemented
                </div>
            </div>
      <%--</form:form>  --%>
        <div style="margin-top:1%">
            <jsp:include page="../common/Footer.jsp"></jsp:include>
        </div>
    </body>
</html>
 