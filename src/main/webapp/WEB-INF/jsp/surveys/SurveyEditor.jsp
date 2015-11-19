<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <title>Survey Editor</title>

    <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
</head>
    <body>
        <h1>${survey.getName()}</h1>
        <p>by ${survey.getAuthor()}</p>
        <sf:form method="POST" commandName="question" action="/survey/surveyedit/${survey.getId()}">
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
            <a href="/survey">No more Questions</a>
        </sf:form>


        <c:choose>
            <%--If the model has an attribute with the name `surveys`--%>
            <c:when test="${not empty survey.getQuestions()}">
                <table class="notes">
                    <c:forEach var="question" items="${questions}">
                        <tr>
                            <td><a href="/survey/surveyedit/${question.getSurvey().getId()}/${question.getId()}">${question.getQuestionText()}</a></td>
                            <td>${question.type}</td>
                            <td>
                                <form method = post action = "/survey/surveyedit/delete/${question.getSurvey().getId()}/${question.getId()}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
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