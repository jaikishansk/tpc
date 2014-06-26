<p>
    <%
        if(session.getAttribute("edit").equals("true"))
        {
            // Edit button
    %>
     <input  class="allButtons" type="submit"
             value="<spring:message code='editcompany.button1.label'/>" onclick="return editCompany()">
     <%
        }
        else
        {
            // Register button
    %> 
    <input  class="allButtons" type="submit"
            value="<spring:message code='registercandidate.button1.label'/>"
            onclick="return submitRegCompanyForm()">
     <%
        }
    %> 
    <input  class="allButtons" type="reset"
            value="<spring:message code='registercandidate.button3.label'/>">
    <input  class="allButtons" type="submit"
            value="<spring:message code='registercandidate.button4.label'/>">
</p>