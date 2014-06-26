<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.edu.tpc.domain.UserType;" %> 

<!-- This javascript is required to support IE version under 9 -->
<!--<script type="text/javascript" src="javascript/ajax/jquery.js"></script> -->
<!--<script type="text/javascript" src="javascript/jqplot/jquery.min.js"></script>-->
<script type="text/javascript">
	$(document).ready(function() {
		$("#menuwrapper ul li").mouseover(function(e) {$(this).addClass(" iehover ");});
		$("#menuwrapper ul li").mouseout(function(e) {$(this).removeClass(" iehover ");});
	});
</script>  
<script  type="text/javascript">
    function confirmLogout() { 
        var confirmSubmit = confirm("Are you sure to logout of the application?");
        if(confirmSubmit==true)
            return true;
        return false;
    }
    function getCurrentDate() { 
        var months=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug",
                             "Sep","Oct","Nov","Dec");
        var date=new Date();
        var currDate = date.getDate();
        var currMonth = date.getMonth();
        currMonth = currMonth + 1;
        var currYear = date.getFullYear();
        document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        document.write("Date: "+months[currMonth-1] + " " +currDate +  ", " + currYear);
    }
    function getRole() { 
        var roleNames=new Array("Admin","Undergraduate","Graduate"); 
        document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        document.write("Role: "+roleNames[${sessionScope.userType}-1]);
    }
    function getOrgName() { 
        var orgName="${sessionScope.orgName}";
        document.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        document.write("Organization: "+orgName);  
    }
</script>

<div class="headerStrip">Welcome: ${sessionScope.userId}
        <script type="text/javascript">
            getCurrentDate();
            getRole();
            getOrgName();
        </script>
        <a href="logout.html" onClick="return confirmLogout()">Sign out</a>
</div>
<div id="menuwrapper">
        <ul>
            <li><a href="#">Training</a>
                <ul>
                    <li><a href="#">Course Material</a></li>
                    <li><a href="#">Tests</a></li>
                    <li><a href="#">Forum</a></li> 
                </ul>
            </li> 
            <li><a href="#">Candidates</a>
                <ul>
                    <c:if test="${sessionScope.userType==1}">
                        <li><a href="#">Register&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&raquo;</a>
                            <ul>
                                <li><a href="registerugcandidate.html">UG Candidate</a></li>
                                <li><a href="registerpgcandidate.html">PG Candidate</a></li>
                            </ul>
                        </li>
                        <li><a href="editcandidate.html">Edit</a></li>
                        <li><a href="searchcandidate.html">Search</a></li>
                        <li><a href="notifycandidate.html">Notify</a></li>
                        <li><a href="deletecandidate.html">Delete</a></li>
                        <li><a href="#">Placement Info&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;&raquo;</a>
                            <ul>
                                <li><a href="addcandidatepi.html">Add</a></li>
                                <li><a href="viewcandidatepi.html">View</a></li>
                                <li><a href="editcandidatepi.html">Edit</a></li>
                                <li><a href="deletecandidatepi.html">Delete</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.userType==2}">
                        <li><a href="registerugcandidate.html">Register</a></li>
                        <li><a href="edit.html">Edit</a></li>
                        <li><a href="viewcandidatepi.html">View Placement Info</a></li>
                    </c:if>
                    <c:if test="${sessionScope.userType==3}">
                        <li><a href="registerpgcandidate.html">Register</a></li>
                        <li><a href="edit.html">Edit</a></li>
                        <li><a href="viewcandidatepi.html">View Placement Info</a></li>
                    </c:if> 
                    <li><a href="uploadresume.html">Upload Resume</a></li>
                </ul>
            </li>
            <li><a href="#">Companies</a>
                <ul>
                    <li><a href="registercompany.html">Register</a></li>
                    <li><a href="editcompany.html">Edit</a></li>
                    <li><a href="deletecompany.html">Delete</a></li>
                    <li><a href="companycommunicate.html">Communicate</a></li>
                    <li><a href="searchcompany.html">Search</a></li>
                    <li><a href="viewcomppi.html">View Placement Info</a></li>
                </ul>
            </li>
            <li><a href="#">Dashboard</a>
                <ul>
                    <li><a href="eligibilityreport.html">Eligibility Report</a></li>
                    <li><a href="consolidatedreport.html">Consolidated Report</a></li>
                    <li><a href="streamwisereport.html">Stream-wise Report</a></li>
                    <li><a href="statistics.html">Statistics</a></li>
                    <li><a href="trend.html">Trend</a></li>
                </ul>
            </li>
            <li><a href="#">Tools</a>
                <ul>
                    <li><a href="#">Print</a></li>
                    <c:if test="${sessionScope.userType=='1'}">
                        <li><a href="configure.html">Configure</a></li>
                        <li><a href="#">User Management</a></li>
                        <li><a href="#">Auto Generate</a></li>
                        <li><a href="#">Export</a></li>
                        <li><a href="#">Import</a></li>
                        <li><a href="#">Backup</a></li>
                        <li><a href="#">Restore</a></li>
                    </c:if>  
                </ul>
            </li>
            <li><a href="#">Help</a>
                <ul>
                    <li><a href="#">About</a></li>
                </ul>
            </li>
        </ul>
</div>