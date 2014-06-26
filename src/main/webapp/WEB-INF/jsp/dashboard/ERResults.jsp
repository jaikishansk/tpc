<%-- 
    Document   : ERResults
    Created on : May 16, 2012, 4:06:44 PM
    Author     : Jaikishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.*"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="erreport.title"/></title>
        
        <link href="css/menu/menu.css" rel="stylesheet" type="text/css"/>
        <link href="css/menu/tabs.css" rel="stylesheet" type="text/css"/>
        <link href="css/jqplot/jquery.jqplot.min.css" rel="stylesheet" type="text/css" />
        
        <script type="text/javascript" src="javascript/menu/menuutils.js"></script>
        <script type="text/javascript" src="javascript/common/UIJScript.js"></script> 
         
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
        <script  type="text/javascript" src="javascript/jqplot/jquery.jqplot.min.js"></script>
        <script  type="text/javascript" src="javascript/jqplot/excanvas.min.js"></script>
        <script type="text/javascript" src="javascript/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script  type="text/javascript" src="javascript/jqplot/plugins/jqplot.barRenderer.min.js"></script>


        <script type="text/javascript">
            function excelReport() {
                document.erresultForm.action='erexcelview.html';
                document.erresultForm.submit();
            }
            function pdfReport() {
                document.erresultForm.action='erpdfview.html';
                document.erresultForm.submit();
            } 
            function chartReport() { 
                document.erresultForm.action='erchartview.html';
                document.erresultForm.submit();
            }
        </script>
     </head>
    <body> 
        <jsp:include page="../common/Header.jsp"></jsp:include>
        <jsp:include page="../common/Menu.jsp"></jsp:include>
        <div class="pageHeading">
            <center>
                <spring:message code="erreport.title"/>
            </center>
        </div>
          <div style="margin-top: -3%"></div>      
         <div class="tabHeader">
            <center>
                <spring:message code="erreport.title"/>
            </center>
        </div>
        <div class="tabContent">
                <br>
                <form name="erresultForm" id="erresultForm"  method="post"/>
                    Found 10 results for given criteria:
                    <br><br>
                    <%@ include file="ERResultButtons.jsp" %>
                </form>
        </div> 
    </body>
    <div id="chart1" style="margin-top: 10%; margin-left: 20%;width: 60%; height: 65%; position: relative;"></div>
    <div><span>Moused Over: </span><span id="info1">Nothing</span></div>   
</html>
