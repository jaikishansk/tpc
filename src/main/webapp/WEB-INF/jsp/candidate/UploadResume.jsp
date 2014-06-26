<%-- 
    Document   : UploadResume
    Created on : 9 Feb, 2013, 5:18:54 PM
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
        <title><spring:message code="candidate.uploadresume.title"/></title>
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script>
        <script type="text/javascript" src="javascript/common/JSValidation.js"></script>

        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="candidate.uploadresume.title"/>
            </center>
        </div> 
       <div id="uploadResume" style="color:black;display: block;">
                <div style="margin-top:-2%"></div>
                <div class="tabHeader">
                        <center><spring:message code="candidate.uploadresume.title"/></center>
                </div>
                <div class="tabContent">
                    <br> 
                    <form:form commandName="uploadForm" enctype="multipart/form-data" method="post"> 
                        <div style="margin-left:10%">
                            <spring:message code="candidate.uploadresume.message"/>   
                            <form:input  id="file" cssClass="firstFieldInput" type="file" path="file"/><br>  
                       </div>     
                        <br><br>
                        <div class="typeBButn">
                            <%@ include file="UploadResumeButtons.jsp" %>
                        </div>
                    </form:form>
                </div>
       </div>
    </body>
</html>
