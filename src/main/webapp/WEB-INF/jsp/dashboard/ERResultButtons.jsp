<p>
    <input  class="allButtons"  type="submit" onclick="return excelReport();"
                value="<spring:message code='erresult.button1.label'/>"/>
    <input class="allButtons"  type="submit" onclick="return pdfReport();"
               value="<spring:message code='erresult.button2.label'/>">
    <input class="allButtons"  type="submit" onclick="return chartReport();"
               value="<spring:message code='erresult.button3.label'/>">
    <input class="allButtons"  type="button" onClick="homePage();"
           value="<spring:message code='erresult.button4.label'/>">
</p>