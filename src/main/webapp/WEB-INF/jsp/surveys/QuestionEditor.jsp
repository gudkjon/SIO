<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Survey Editor</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>"/>
</head>
<body>
<h1>${question.get(0).questionText}</h1>
<p>type: ${question.get(0).type}</p>

<sf:form method="POST" commandName="option" action="/survey/surveyedit/${question.get(0).containingSurvey}/${question.get(0).id}">

    <table>
        <tr>
            <td> Option:</td>
        <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
            <td><sf:input path="optionText" type="text" placeholder="option"/></td>
        </tr>

    </table>

    <input type="submit" VALUE="Add Option!"/>

</sf:form>

<c:choose>
    <%--If the model has an attribute with the name `surveys`--%>
    <c:when test="${not empty options}">
        <%--Create a table for the Postit Notes--%>
        <table class="notes">

                <%--For each postit note, that is in the list that was passed in the model--%>
                <%--generate a row in the table--%>
                <%--Here we set `postit` as a singular item out of the list `surveys`--%>
            <c:forEach var="option2" items="${options}">
                <tr>
                        <%--We can reference attributes of the Entity by just entering the name we gave--%>
                        <%--it in the singular item var, and then just a dot followed by the attribute name--%>
                        <%--Create a link based on the name attribute value--%>
                        <p>${option2.optionText}</p>
                </tr>
            </c:forEach>
        </table>
    </c:when>

    <%--If all tests are false, then do this--%>
    <c:otherwise>
        <h3>No options!</h3>
    </c:otherwise>
</c:choose>


</body>
</html>
