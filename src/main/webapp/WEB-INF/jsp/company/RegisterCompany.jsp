<%-- 
    Document   : RegisterCompany
     Created on : 26/1/2013
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
        <title><spring:message code="company.add.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/form/cocontacts.js"></script>
        <script type="text/javascript" src="javascript/form/cocriteria.js"></script>
        <script type="text/javascript" src="javascript/calendar/calendar_ws.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>  
        <script type="text/javascript" src="javascript/validation/RegCompFormValidation.js"></script>
        <script type="text/javascript">
                var currentTab=0;
                var allTabs = new Array("general", "address", "contact","offer", "secondary", "ug", "pg"); 
                // basic required fields
                var reqdFields=new Array("companyName","streams","yearOfPlacement",
                                                "addrline1","city","state","pin","contactname0","email0","landline0","cellno0",
                                                "payPackage","bondPeriod","sscpercentage","hscpercentage","ugCourse",
                                                "ugpercentage","pgCourse","pgpercentage");
                var firstFields=new Array("companyName","addrline1","contactname0", "payPackage","sscpercentage",
                                                         "ugpercentage","pgpercentage");                                
                function initRegCompanyForm() {
                     hideAllTabsExcept(allTabs,'general');
                     setFocus('companyName');
                }
                function editCompany() {
                    document.companyForm.action='updatecompany.html';
                    document.companyForm.submit();
                }
        </script>
    </head>
    <body onLoad="initRegCompanyForm()">
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center> 
                 <% 
                    if(session.getAttribute("edit").equals("true")) { 
                        // Edit company
                %>
                        <spring:message code="company.edit.title"/> 
                <%
                    }
                    else { 
                %>
                        <spring:message code="company.add.title"/> 
                <%
                    }
                %> 
            </center>
        </div>  
        <div id="navbar">
            <div id="holder">
                <ul id="outer">
                    <li id="tab1">
                        <a href="javascript:void(0)" id="generalTab"  onclick="hideAllTabsExcept(allTabs,'general');currentTab=0;
                           setFocus('companyName');"> <spring:message code="company.option1"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="addressTab" onclick="hideAllTabsExcept(allTabs,'address');currentTab=1;
                           setFocus('addrline1');"> <spring:message code="company.option2"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="contactTab" onclick="hideAllTabsExcept(allTabs,'contact');currentTab=2;
                           setFocus('contactname0')"> <spring:message code="company.option3"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="offerTab" onclick="hideAllTabsExcept(allTabs,'offer');currentTab=3;
                           setFocus('payPackage');">  <spring:message code="company.option4"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="secondaryTab" onclick="hideAllTabsExcept(allTabs,'secondary');currentTab=4;
                           setFocus('sscpercentage')"> <spring:message code="company.option5"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="ugTab" onclick="hideAllTabsExcept(allTabs,'ug');currentTab=5;
                           setFocus('ugpercentage')"> <spring:message code="company.option6"/>
                        </a>
                    </li>
                    <li><a href="javascript:void(0)" id="pgTab" onclick="hideAllTabsExcept(allTabs,'pg');currentTab=6;
                           setFocus('pgpercentage')">  <spring:message code="company.option7"/>
                        </a>
                    </li>
                </ul>
            </div> <!-- end holder div -->
        </div> <!-- end navbar div -->
        <form:form name="companyForm" id="companyForm" method="post"
                   commandName="companyForm">
            <div id="general" style="color:black;display: none;">
                 <div class="tabHeader">
                        <center><spring:message code="company.option1"/></center>
                </div>
                <div class="tabContent">
                    <br> 
                    <form:label path="companyName" class="firstField">
                        <spring:message code="registercompany.general.companyName"/>:*
                        <form:input cssClass="firstFieldInput" onkeypress="return onlyAlphabetsAndSpace(event);"
                                    id="companyName" tabindex="1" maxlength="100"
                                    path="companyName"/><br>
                    </form:label>  
                    <form:label path="companyType" class="secondField">
                        <spring:message code="registercompany.general.companyType"/>:
                        <form:input  cssClass="secondFieldInput" onkeypress="return onlyAlphabetsAndSpace(event);"
                                    id="companyType" tabindex="2" maxlength="100"
                                    path="companyType"/><br>
                    </form:label>
                    <br><br>   
                    <form:label path="" class="firstField">
                        <spring:message code="registercompany.general.companyStream"/>:*
                        
                        <select class="firstFieldInput" multiple="true" tabindex="3" name="streams" id="streams">
                                <c:forEach items="${allStreams}" var="stream" varStatus="x">  
                                    <option  value="${stream.key}">${stream.value}
                                </c:forEach>
                        </select>
                        <%--`
                        <form:select  cssClass="firstFieldInput" multiple="true" path="companyStreams.primaryKey.streamId" 
                                                 tabindex="3"> 
                                    <form:options items="${allStreams}" />
                        </form:select><br>
                        --%>
                    </form:label>
                    <form:label path="yearOfPlacement" class="secondField">
                        <spring:message code="registercompany.general.placementYear"/>:*
                        <form:input cssClass="secondFieldInput" onkeypress="return onlyNumbers(event);" 
                                    id="yearOfPlacement" tabindex="4" maxlength="4"
                                    path="yearOfPlacement"/><br>
                    </form:label>
                    <br><br><br><br><br>
                    <div class="typeBButn">
                          <%@ include file="RegCompButtons.jsp" %>
                    </div>
                </div>
            </div>  
             <div id="address" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="company.option2"/></center>
                </div>
               <div class="tabContent">
                    <br>
                    <form:label class="firstField" path="address.addressLine1">
                        <spring:message code="registercompany.address.addrLine1"/>:*
                        <form:input cssClass="firstFieldInput" id="addrline1" tabindex="1"
                                   onkeypress="return onlyAlphaNumericSpaceAndComma(event);"  maxlength="50" 
                                   path="address.addressLine1"/><br>
                    </form:label>
                    <form:label class="secondField" path="address.addressLine2">
                        <spring:message code="registercompany.address.addrLine2"/>:
                        <form:input cssClass="secondFieldInput" id="addrline2" tabindex="2"
                                   onkeypress="return onlyAlphaNumericSpaceAndComma(event);" maxlength="50" 
                                   path="address.addressLine2"/><br>
                    </form:label>
                    <form:label class="firstField" path="address.city">
                            <spring:message code="registercompany.address.city"/>:*
                            <form:input cssClass="firstFieldInput" id="city" tabindex="3"
                                        maxlength="30" onkeypress="return onlyAlphabets(event);" 
                                        path="address.city"/><br>
                    </form:label>
                    <form:label class="secondField" path="address.residentState">
                        <spring:message code="registercompany.address.residentState"/>:*
                        <form:input cssClass="secondFieldInput" id="state" tabindex="4"
                                    onkeypress="return onlyAlphabetsAndSpace(event);"
                                    maxlength="30" path="address.residentState"/><br>
                    </form:label>
                    <form:label class="firstField" path="address.pin">
                            <spring:message code="registercompany.address.pin"/>:*
                            <form:input cssClass="firstFieldInput" id="pin" tabindex="5"
                                        maxlength="6" onkeypress="return onlyNumbers(event);"
                                        path="address.pin"/><br>
                    </form:label>
                    <br><br><br><br><br><br>
                    <div class="typeBButn">
                          <%@ include file="RegCompButtons.jsp" %>
                    </div>
                </div>
            </div>     
            <div id="contact" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="company.option3"/></center>
                </div>
                <div class="tabContent">
                    <br>   
                        <c:forEach var="i" begin="0" end="1">
                             <form:label class="firstField" path="contacts[${i}].name">
                                    <spring:message code="registercompany.contact.name"/>${i+1}:
                                    <c:if test="i eq 0"><c:out value="*"></c:out></c:if>
                                    <form:input cssClass="firstFieldInput" id="contactname${i}" tabindex="${3*i+(i+1)}"
                                           onkeypress="return onlyAlphabetsAndSpace(event);"
                                           maxlength="50" path="contacts[${i}].name"/><br>
                            </form:label>
                            <form:label class="secondField" path="contacts[${i}].emailId">
                                <spring:message code="registercompany.contact.emailId"/>${i+1}:
                                <c:if test="i eq 0"><c:out value="*"></c:out></c:if>
                                        <form:input cssClass="secondFieldInput" id="email${i}" tabindex="${3*i+(i+2)}"
                                            onblur="return validateEmail('email${i}');"
                                            maxlength="50" path="contacts[${i}].emailId"/><br>
                            </form:label>
                            <br><br>
                            <form:label class="firstField" path="contacts[${i}].landLine">
                                    <spring:message code="registercompany.contact.landLine"/>${i+1}:
                                    <form:input cssClass="firstFieldInput" id="landline${i}" tabindex="${3*i+(i+3)}"
                                                onkeypress="return onlyNumbers(event);"
                                                onblur="validateLandLine('landline${i}');"
                                                maxlength="15" path="contacts[${i}].landLine"/><br>
                            </form:label>
                            <form:label class="secondField" path="contacts[${i}].cellNumber">
                                <spring:message code="registercompany.contact.cellNo"/>${i+1}:
                                        <form:input cssClass="secondFieldInput" id="cellno${i}" tabindex="${3*i+(i+4)}"
                                                     onkeypress="return onlyNumbers(event);"
                                                      onblur="return validateMobile('cellno${i}');"
                                                     maxlength="15" path="contacts[${i}].cellNumber"/><br>
                            </form:label>
                            <br><br> 
                        </c:forEach>
                            <br><br>
                            <div class="typeBButn"><%@ include file="RegCompButtons.jsp" %></div>
                    </div> 
            </div>
                    <div id="offer" style="color:black;display: none;">
                        <div class="tabHeader">
                                <center><spring:message code="company.option4"/></center>
                        </div>
                        <div class="tabContent">
                            <br>
                            <form:label path="payPackage" class="firstField">
                                <spring:message code="registercompany.offer.payPackage"/>:*
                                <form:input cssClass="firstFieldInput" id="payPackage"
                                            onkeypress="return onlyFloat(event);"
                                            tabindex="1" maxlength="4"  path="payPackage"/><br>
                            </form:label>
                            <form:label path="bondPeriod" class="secondField">
                                <spring:message code="registercompany.offer.bondPeriod"/>:*
                                <form:input cssClass="secondFieldInput" id="bondPeriod"
                                            onkeypress="return onlyNumbers(event);"
                                            tabindex="2" maxlength="2"  path="bondPeriod"/><br>
                            </form:label>
                             <br><br>
                            <div class="typeBButn">
                                  <%@ include file="RegCompButtons.jsp" %>
                            </div>
                        </div>
                    </div>   
                <div id="secondary" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="company.option5"/></center>
                </div>
                <div class="tabContent">  
                    <br> 
                    <form:label class="firstField" path="companyQC[0].percentage">
                                    <spring:message code="registercompany.secondary.sscpercentage"/>:
                                    <form:input cssClass="firstFieldInput" id="sscpercentage" tabindex="1"
                                            onkeypress="return validatePercentageOnChar(this, event);"
                                            maxlength="6" path="companyQC[0].percentage"/><br>
                    </form:label> 
                    <input type="hidden" value="1"  name="companyQC[0].primaryKey.courseId"/>
                    <form:label class="secondField" path="companyQC[1].percentage">
                                <spring:message code="registercompany.secondary.hscpercentage"/>:
                                        <form:input cssClass="secondFieldInput" id="hscpercentage" tabindex="2"
                                            onkeypress="return validatePercentageOnChar(this, event);"
                                            maxlength="6" path="companyQC[1].percentage"/><br>
                    </form:label>
                    <input type="hidden" value="2"  name="companyQC[1].primaryKey.courseId"/>                        
                    <br><br> 
                    <div class="typeBButn">
                          <%@ include file="RegCompButtons.jsp" %>
                    </div>
                </div> 
            </div>        
              <div id="ug" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="company.option6"/></center>
                </div>
                <div class="tabContent">  
                    <br> 
                    <form:label class="firstField" path="companyQC[2].primaryKey.courseId">
                        <spring:message code="registercandidate.ed.course"/>: *
                        <form:select id="ugCourse"  path="companyQC[2].primaryKey.courseId" tabindex="1" 
                            cssClass="firstFieldInput">
                                        <form:options  items="${ugCourses}"/>
                        </form:select> 
                    </form:label>
                    <form:label class="secondField" path="companyQC[2].percentage">
                                    <spring:message code="registercompany.ug.percentage"/>:*
                                    <form:input cssClass="secondFieldInput" id="ugpercentage" tabindex="2"
                                                onkeypress="return validatePercentageOnChar(this, event);"
                                                maxlength="6" path="companyQC[2].percentage"/><br>
                    </form:label>
                    <br><br>                            
                    <form:label class="firstField" path="companyQC[2].liveKT">
                                <spring:message code="registercompany.ug.liveKT"/>:
                                        <form:input cssClass="firstFieldInput" id="ugliveKT" tabindex="3"
                                            onkeypress="return onlyNumbers(event);"
                                            maxlength="2" path="companyQC[2].liveKT"/><br>
                    </form:label> 
                     <form:label class="secondField" path="companyQC[2].pastKT">
                                    <spring:message code="registercompany.ug.pasKT"/>:
                                    <form:input cssClass="secondFieldInput" id="ugPastKT" tabindex="3"
                                                onkeypress="return onlyNumbers(event);"
                                            maxlength="2" path="companyQC[2].pastKT"/><br>
                    </form:label>
                    <br><br>                         
                    <form:label class="firstField" path="companyQC[2].kTSubjects">
                                <spring:message code="registercompany.ug.ktSubjects"/>:
                                        <form:input cssClass="firstFieldInput" id="ugktSubjects" tabindex="4"
                                            onkeypress="return onlyAlphaNumeric(event);"
                                            maxlength="50" path="companyQC[2].kTSubjects"/><br>
                    </form:label>
                    <br><br> 
                    <div class="typeBButn">
                          <%@ include file="RegCompButtons.jsp" %>
                    </div>
                </div> 
            </div>   
            <div id="pg" style="color:black;display: none;">
                <div class="tabHeader">
                        <center><spring:message code="company.option7"/></center>
                </div>
                <div class="tabContent">  
                    <br> 
                    <form:label class="firstField" path="companyQC[3].primaryKey.courseId">
                        <spring:message code="registercandidate.ed.course"/>: 
                        <form:select id="pgCourse"  path="companyQC[3].primaryKey.courseId" tabindex="1" 
                            cssClass="firstFieldInput">
                                        <form:options  items="${pgCourses}"/>
                        </form:select> 
                    </form:label>
                    <form:label class="secondField" path="companyQC[3].percentage">
                                    <spring:message code="registercompany.pg.percentage"/>:
                                    <form:input cssClass="secondFieldInput" id="pgpercentage" tabindex="1"
                                            onkeypress="return validatePercentageOnChar(this, event);"
                                            maxlength="6" path="companyQC[3].percentage"/><br>
                    </form:label>
                    <br><br>                      
                    <form:label class="firstField" path="companyQC[3].liveKT">
                                <spring:message code="registercompany.pg.liveKT"/>:
                                        <form:input cssClass="firstFieldInput" id="pgliveKT" tabindex="2"
                                            onkeypress="return onlyNumbers(event);"
                                            maxlength="2" path="companyQC[3].liveKT"/><br>
                    </form:label> 
                     <form:label class="secondField" path="companyQC[3].pastKT">
                                    <spring:message code="registercompany.pg.pastKT"/>:
                                    <form:input cssClass="secondFieldInput" id="pgPastKT" tabindex="3"
                                            onkeypress="return onlyNumbers(event);"
                                            maxlength="2" path="companyQC[3].pastKT"/><br>
                    </form:label>
                    <br><br>                            
                    <form:label class="firstField" path="companyQC[3].kTSubjects">
                                <spring:message code="registercompany.pg.ktSubjects"/>:
                                        <form:input cssClass="firstFieldInput" id="pgktSubjects" tabindex="4"
                                             onkeypress="return onlyAlphaNumeric(event);"
                                            maxlength="50" path="companyQC[3].kTSubjects"/><br>
                    </form:label>
                    <br><br> 
                    <div class="typeBButn">
                          <%@ include file="RegCompButtons.jsp" %>
                    </div>
                </div> 
            </div>                   
        </form:form>
    </body>
</html>
