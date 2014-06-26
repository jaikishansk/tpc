<%--
    Document   : Login.jsp
    Created on : Sep 8, 2011, 7:32:36 PM
    Author     : Jaikishan
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <title><spring:message code="login.title"/></title>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>
        <script type="text/javascript" >
            function initForm()
            {
                document.getElementById('loginId').value='';
                document.getElementById('password').value='';
                setFocus('loginId');
            }
            function submitLoginForm()
            {
                var loginId=document.getElementById('loginId').value;
                var password=document.getElementById('password').value;  
                if(isEmpty(loginId)||isEmpty(password))
                {
                    alert('Please supply credentials.');
                    setFocus('loginId');
                    return false;
                }
                document.getElementById('loginForm').submit();
                return true;
            }
            function setColor(id,color){ 
                document.getElementById(id).style.backgroundColor=color;
            }
        </script>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css" />
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
    </head> 
    <body onLoad="initForm()">
       
        <jsp:include page="../common/Header.jsp"></jsp:include>
        
        <form:form name="loginForm" id="loginForm"
                   method="post" action="login.html" commandName="user">
            <div class="loginHeader">
                    <b><spring:message code="login.message"/></b> 
            </div>
            <div class="loginContent">
                <br>
                <form:label path="userId" class="loginCred">
                    <spring:message code="login.userid"/>:*
                    <form:input cssClass="loginCredInput" path="userId"
                                id="loginId"
                                tabindex="1" maxlength="30"/><br>
                    <form:errors path="userId" cssClass="fieldError1"/>
                </form:label>    
                <form:label path="password" class="loginCred">
                    <spring:message code="login.password"/>:*
                    <form:input cssClass="loginCredInput" path="password"
                                id="password"
                                tabindex="2" maxlength="30" type="password"/><br>
                    <form:errors path="password" cssClass="fieldError2"/>
                </form:label>
                 
                <div class="loginButn">
                    <br>
                    <input  id="loginButton"  class="allButtons" type="submit" tabindex="3"   
                                onClick="return submitLoginForm();"
                                value="<spring:message code='login.button1.label'/>">
                    <input  id="clearButton"  class="allButtons"
                                type="button" tabindex="4" onClick="initForm()"
                                value="<spring:message code='login.button2.label'/>">
                </div>
            </div>
        </form:form> 
        <div class="loginErr">
            <c:if test="${! empty loginError}">
                <c:out value="${loginError}"/>
            </c:if>
        </div>
        <div style="margin-top: 21%">
             <jsp:include page="../common/Footer.jsp"></jsp:include>
        </div> 
    </body>
</html>