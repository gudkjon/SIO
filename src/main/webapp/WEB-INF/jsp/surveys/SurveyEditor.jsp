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


<h1>${survey.get(0).name}</h1>
<p>by ${survey.get(0).author}</p>

<sf:form method="POST" commandName="question" action="/survey/surveyedit/${survey.get(0).name}">

    <table>
        <tr>
            <td> Question:</td>
                <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
            <td><sf:input path="questionText" type="text" placeholder="Question"/></td>
        </tr>
        <tr>
            <td>Type:</td>
                <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
            <td>
                <sf:select path="type">
                    <sf:option value="dropDown">DropDown</sf:option>
                    <sf:option value="multiQuestion">Multiple answer</sf:option>
                    <sf:option value="input">Input box</sf:option>
                    <sf:option value="radioButton">Radio Button</sf:option>
                </sf:select>
            </td>
        </tr>
    </table>

    <input type="submit" VALUE="Add Question!"/>

</sf:form>


<c:choose>
    <%--If the model has an attribute with the name `surveys`--%>
    <c:when test="${not empty questions}">
        <%--Create a table for the Postit Notes--%>
        <table class="notes">

                <%--For each postit note, that is in the list that was passed in the model--%>
                <%--generate a row in the table--%>
                <%--Here we set `postit` as a singular item out of the list `surveys`--%>
            <c:forEach var="question2" items="${questions}">
                <tr>
                        <%--We can reference attributes of the Entity by just entering the name we gave--%>
                        <%--it in the singular item var, and then just a dot followed by the attribute name--%>

                        <%--Create a link based on the name attribute value--%>
                    <td><p>${question2.questionText}</p></td>
                    <td>${question2.type}</td>
                    <td><a href = "/survey/surveyedit/delete/${question2.containingSurvey}/${question2.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>

    <%--If all tests are false, then do this--%>
    <c:otherwise>
        <h3>No questions!</h3>
    </c:otherwise>
</c:choose>

</body>
</html>
