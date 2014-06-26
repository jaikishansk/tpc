 <%--
    Document   : EditCompanyCriteria
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
        
        <title><spring:message code="company.edit.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>

        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
                function initEditCmpnyForm() {
                    setFocus('companyName');
                }
                function resetEditCmpnyFields() { 
                    document.forms[0].reset();
                    setFocus('companyName');
                }
                function submitEditCmpnyForm() { 
                        if(isEmpty(document.getElementById('companyName').value)) { 
                            alert('Please supply company name.');
                            setFocus('companyName');
                            return false;
                        }
                    return true;
                }
        </script>
    </head>
    <body onload="initEditCmpnyForm()">
            <jsp:include page="../common/Header.jsp"></jsp:include>
            <jsp:include page="../common/Menu.jsp"></jsp:include> 
            <div class="pageHeading">
                <center><spring:message code="company.edit.title"/></center>
            </div> 
         <form:form name="editCmpnyForm" method="post" commandName="editCmpnyCriteria">
            <div id="company" style="color:black;display: block;">
                <div style="margin-top:-2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="company.edit.title"/></center>
                </div>
                <div class="tabContent">
                    <br>
                    <form:label path="name" class="firstField">
                        <spring:message code="editcompany.companyname"/>:*
                        <form:input id="companyName" cssClass="firstFieldInput"
                                    onkeypress="return onlyAlphaNumericAndSpace(event);" tabindex="1"
                                    maxlength="100" path="name"/><br>
                    </form:label>
                    <br><br>
                    <div class="typeBButn">
                        <%@ include file="EditCompanyButtons.jsp" %>
                    </div>
                </div>
           </div>
        </form:form>
        <div style="margin-top:-10%">
            <jsp:include page="../common/Footer.jsp"></jsp:include>
        </div>    
    </body>
</html>
