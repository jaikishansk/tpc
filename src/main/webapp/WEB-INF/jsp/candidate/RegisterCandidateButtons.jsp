<br> 
<p class="registerButn">
    <%
        if(session.getAttribute("edit").equals("true"))
        {
            // Edit button
    %>
            <input  class="allButtons"
            type="submit" 
            value="<spring:message code='registercandidate.button5.label'/>"
            onClick="return submitECandForm(<%=Integer.valueOf((Integer)session.getAttribute("userType"))%>);"
            />
    <%
        }
        else
        {
            // Register button
    %>
            <input  class="allButtons"
            type="submit"
            value="<spring:message code='registercandidate.button1.label'/>"
            onClick="return submitRCandForm(<%=Integer.valueOf((Integer)session.getAttribute("userType"))%>)"
            />
    <%
        }
    %>
    <%
        if(Integer.valueOf((Integer)session.getAttribute("userType"))==1
                && !(session.getAttribute("edit").equals("true")))
        {
    %>
    <input class="allButtons"
           type="submit"
           value="<spring:message code='registercandidate.button2.label'/>"
           onClick="return submitRCandFormAndNew(<%=Integer.valueOf((Integer)session.getAttribute("userType"))%>)">
    <%
        }
    %>
    <input class="allButtons"  type="button"  onClick="resetRegCandFields();"
           value="<spring:message code='registercandidate.button3.label'/>">
    <input class="allButtons" type="button"  onclick="homePage();"
           value="<spring:message code='registercandidate.button4.label'/>">
</p>