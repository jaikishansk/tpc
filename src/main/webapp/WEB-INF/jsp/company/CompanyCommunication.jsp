<%-- 
    Document   : CompanyComunication
    Created on : 9th Feb 2013
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="company.commn.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>

        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
                function initCmpnyComnForm() {
                    setFocus('emailId');
                }
                function resetCmpnyCommnFields() { 
                    document.forms[0].reset();
                    setFocus('emailId');
                }
                function submitCmpnyCommnForm() {  
                    return true;
                }
        </script>
    </head>
    <body onload="initCmpnyComnForm()">
         <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="company.commn.title"/>
            </center>
        </div> 
        <div style="margin-top:-2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="company.commn.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:form name="notifycompForm" id="notifycompForm" method="post" 
                                                                             commandName="notifyCompany">
                    <form:label path="emailId" class="firstField">
                        <spring:message code="company.commn.emailid"/>:
                        <form:input id="emailId" cssClass="firstFieldInput"
                                    onblur="return validateEmail('emailId');" tabindex="1"
                                    maxlength="50" path="emailId"/><br>
                    </form:label>
                    <form:label path="subject" class="secondField">
                        <spring:message code="company.commn.subject"/>:
                        <form:input id="subject" cssClass="secondFieldInput"
                                    onblur="return onlyAlphaNumericAndSpace(event);" tabindex="2"
                                    maxlength="100" path="subject"/><br>
                    </form:label>   
                    <br>
                     <form:label class="firstField" path="message">
                        <spring:message code="company.commn.message"/>:
                        <form:label path="message">
                            <form:textarea  id="classMessage" tabindex="3" maxlength="200" 
                                                        cssClass="firstFieldInput" path="message"/><br>
                        </form:label>
                    </form:label> 
                    <label class="secondField">
                            <spring:message code="company.commn.attachment"/>:
                            <input  id="fileName" class="secondFieldInput" type="File"
                                    onkeypress="return onlyAlphaNumeric(event);" tabindex="4"/><br>  
                    </label>                                   
                    <br><br><br><br>
                    <div class="typeBButn">
                        <%@ include file="CompanyCommnButtons.jsp" %>
                    </div>
                    </form:form> 
                </div>
                <div style="margin-top:-10%">
                        <jsp:include page="../common/Footer.jsp"></jsp:include>
                </div>  
    </body>
</html>
