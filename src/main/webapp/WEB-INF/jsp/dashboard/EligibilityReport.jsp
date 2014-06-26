<%-- 
    Document   : EligibilityReport
    Created on : Mar 6, 2012, 6:09:50 AM
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
        <title><spring:message code="dashboard.eligibility.title"/></title>
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/calendar/calendar.css" rel="stylesheet" type="text/css">
        
        <script type="text/javascript" src="javascript/ajax/jquery.js"></script>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script> 
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>
        
        <script type="text/javascript">
                currentTab=0;
                allTabs=new Array("ssc","hsc", "ug", "pg");  
                firstECFields=new Array("passingYear","passingYear","PassingYear","passingYear");  
                function initERForm() {
                     hideAllTabsExcept(allTabs,'ssc');
                }
        </script>
     </head>
    <body onLoad="initERForm()"> 
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="dashboard.eligibility.title"/>
            </center>
        </div>
        <div id="navbar3">
             <div id="holder3">
                <ul id="outer3">
                    <li id="tab1">
                        <a href="javascript:void(0)" id="sscTab"
                           onclick="hideAllTabsExcept(allTabs,'ssc');
                                    /*setFocus('candidateId');*/
                                    currentTab=0;">
                           <spring:message code="eligibilityreport.option1"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="hscTab"
                           onclick="hideAllTabsExcept(allTabs,'hsc');
                                    /*setFocus('firstName');*/currentTab=1;">
                           <spring:message code="eligibilityreport.option2"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="ugTab"
                           onclick="hideAllTabsExcept(allTabs,'ug');
                                    /*setFocus('firstName');*/currentTab=2;">
                           <spring:message code="eligibilityreport.option3"/>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="pgTab"
                           onclick="hideAllTabsExcept(allTabs,'pg');
                                    /*setFocus('dateOfPlacement');*/currentTab=3;">
                           <spring:message code="eligibilityreport.option4"/>
                        </a>
                    </li>
                </ul>
            </div> <!-- end holder div -->
        </div> <!-- end navbar div -->
         <form:form name="eRForm" id="eRForm" method="post" commandName="erCriteria"> 
             <div id="ssc" style="color:black;display: none;">
                <div style="margin-top:2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="eligibilityreport.option1.title"/></center>
                </div> 
                <div class="tabContent">
                    <br>   
                    <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.course"/>:*
                            <form:select path="" tabindex="1" class="firstFieldInput">
                                <form:options items="${secondaryCourses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="" class="secondField">
                            <spring:message code="eligibilityreport.stream"/>:*
                            <form:select path="" tabindex="2" class="secondFieldInput">
                                <form:options items="${secondaryStreams}"/>
                            </form:select>
                    </form:label>
                     <br><br>     
                     <form:label class="firstField" path="">
                            <spring:message code="eligibilityreport.startPercentage"/>:
                            <form:input cssClass="firstFieldInput" id="startPercent"
                                        tabindex="3" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label> 
                        <form:label class="secondField" path="">
                            <spring:message code="eligibilityreport.endPercentage"/>:
                            <form:input cssClass="secondFieldInput" id="endPercent"
                                        tabindex="4" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label>                 
                     <br><br>          
                      <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.passingYear"/>:
                            <form:input path="" tabindex="5" class="firstFieldInput" maxlength="4" /> 
                    </form:label>
                     <br><br>               
                    <div class="typeBButn">
                        <%@ include file="AddERButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="hsc" style="color:black;display: none;">
                <div style="margin-top:2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="eligibilityreport.option2.title"/></center>
                </div> 
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.course"/>:*
                            <form:select path="" tabindex="1" class="firstFieldInput">
                                <form:options items="${secondaryCourses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="" class="secondField">
                            <spring:message code="eligibilityreport.stream"/>:*
                            <form:select path="" tabindex="2" class="secondFieldInput">
                                <form:options items="${secondaryStreams}"/>
                            </form:select>
                    </form:label>
                     <br><br>     
                     <form:label class="firstField" path="">
                            <spring:message code="eligibilityreport.startPercentage"/>:
                            <form:input cssClass="firstFieldInput" id="startPercent"
                                        tabindex="3" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label> 
                        <form:label class="secondField" path="">
                            <spring:message code="eligibilityreport.endPercentage"/>:
                            <form:input cssClass="secondFieldInput" id="endPercent"
                                        tabindex="4" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label>                 
                     <br><br>          
                      <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.passingYear"/>:
                            <form:input path="" tabindex="5" class="firstFieldInput" maxlength="4" /> 
                    </form:label>
                     <br><br>               
                    <div class="typeBButn">
                        <%@ include file="AddERButtons.jsp" %>
                    </div>
                </div>
            </div>
            <div id="ug" style="color:black;display: none;">
                <div style="margin-top:2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="eligibilityreport.option3.title"/></center>
                </div> 
                <div class="tabContent">
                    <br>  
                    <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.course"/>:*
                            <form:select path="" tabindex="1" class="firstFieldInput">
                                <form:options items="${ugCourses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="" class="secondField">
                            <spring:message code="eligibilityreport.stream"/>:*
                            <form:select path="" tabindex="2" class="secondFieldInput">
                                <form:options items="${ugStreams}"/>
                            </form:select>
                    </form:label>
                    <br><br>     
                     <form:label class="firstField" path="">
                            <spring:message code="eligibilityreport.startPercentage"/>:
                            <form:input cssClass="firstFieldInput" id="startPercent"
                                        tabindex="3" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label> 
                        <form:label class="secondField" path="">
                            <spring:message code="eligibilityreport.endPercentage"/>:
                            <form:input cssClass="secondFieldInput" id="endPercent"
                                        tabindex="4" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label>                 
                     <br><br>   
                      <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.liveKT"/>:
                            <form:input path="" tabindex="5" class="firstFieldInput" maxlength="2" /> <br>
                    </form:label> 
                    <form:label path="" class="secondField">
                            <spring:message code="eligibilityreport.placements"/>:
                            <form:input path="" tabindex="5" class="secondFieldInput" maxlength="2" /> <br>
                    </form:label>     
                    <br><br>         
                      <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.passingYear"/>:
                            <form:input path="" tabindex="5" class="firstFieldInput" maxlength="4" /> 
                    </form:label>        
                    <br><br>                
                    <div class="typeBButn">
                        <%@ include file="AddERButtons.jsp" %>
                    </div>
                </div>
            </div>  
            <div id="pg" style="color:black;display: none;">
                <div style="margin-top:2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="eligibilityreport.option4.title"/></center>
                </div> 
                <div class="tabContent">
                    <br> 
                    <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.course"/>:*
                            <form:select path="" tabindex="1" class="firstFieldInput">
                                <form:options items="${pgCourses}"/>
                            </form:select>
                    </form:label>
                    <form:label path="" class="secondField">
                            <spring:message code="eligibilityreport.stream"/>:*
                            <form:select path="" tabindex="2" class="secondFieldInput">
                                <form:options items="${pgStreams}"/>
                            </form:select>
                    </form:label>
                    <br><br>     
                     <form:label class="firstField" path="">
                            <spring:message code="eligibilityreport.startPercentage"/>:
                            <form:input cssClass="firstFieldInput" id="startPercent"
                                        tabindex="3" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label> 
                        <form:label class="secondField" path="">
                            <spring:message code="eligibilityreport.endPercentage"/>:
                            <form:input cssClass="secondFieldInput" id="endPercent"
                                        tabindex="4" path="" 
                                        onkeypress="return validatePercentageOnChar(this, event);"
                                        maxlength="6"/><br>
                        </form:label>                 
                     <br><br>  
                      <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.liveKT"/>:
                            <form:input path="" tabindex="5" class="firstFieldInput" maxlength="2" /> <br>
                    </form:label> 
                    <form:label path="" class="secondField">
                            <spring:message code="eligibilityreport.placements"/>:
                            <form:input path="" tabindex="5" class="secondFieldInput" maxlength="2" /> <br>
                    </form:label> 
                     <br><br>           
                      <form:label path="" class="firstField">
                            <spring:message code="eligibilityreport.passingYear"/>:
                            <form:input path="" tabindex="5" class="firstFieldInput" maxlength="4" /> 
                    </form:label>        
                    <br><br>        
                    <div class="typeBButn">
                        <%@ include file="AddERButtons.jsp" %>
                    </div>
                </div>
            </div>            
         </form:form>
    </body>
</html>
