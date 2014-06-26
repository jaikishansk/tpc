 <%--
    Document   : CompanyCriteria
    Created on : 9/2/13
    Author        : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<!DOCTYPE html>
<html> 
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title><spring:message code="company.search.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>

        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
                function initCmpnyForm() {
                    setFocus('companyName');
                }
                function resetCmpnyFields() { 
                    document.forms[0].reset();
                    setFocus('companyName');
                }
                function submitCmpnyForm() { 
                        if(isEmpty(document.getElementById('companyName').value)) { 
                            alert('Please supply company name.');
                            setFocus('companyName');
                            return false;
                        }
                    return true;
                }
        </script>
    </head>
    <body onload="initCmpnyForm()">
            <jsp:include page="../common/Header.jsp"></jsp:include>
            <jsp:include page="../common/Menu.jsp"></jsp:include> 
            <div class="pageHeading">
                <center><spring:message code="company.search.title"/></center>
            </div> 
         <form:form name="srchCmpnyForm" method="post" commandName="srchCmpnyCriteria">
            <div id="company" style="color:black;display: block;">
                <div style="margin-top:-2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="company.search.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="" class="firstField">
                        <spring:message code="company.search.name"/>:*
                        <form:input id="companyName" cssClass="firstFieldInput"
                                    onkeypress="return onlyAlphaNumericAndSpace(event);" tabindex="1"
                                    maxlength="100" path=""/><br>
                    </form:label>
                    <br><br>
                    <div class="typeBButn">
                        <%@ include file="SearchCompanyButtons.jsp" %>
                    </div>
                </div>
           </div>
        </form:form>
        <div style="margin-top:-10%">
            <jsp:include page="../common/Footer.jsp"></jsp:include>
        </div>    
    </body>
</html>
