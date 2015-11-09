<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Survey Editor</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    </head>
    <body>
        <h1>${question.getQuestionText()}</h1>
        <p>type: ${question.getType()}</p>

        <sf:form method="POST" commandName="option" action="/survey/surveyedit/${question.getSurvey().getId()}/${question.getId()}">
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
                    <c:forEach var="option" items="${options}">
                        <td>
                            <p>${option.optionText}</p>
                        </td>
                        <td>
                            <form method = "post" action = "/survey/surveyedit/delete/${option.surveyId}/${option.questionId}/${option.id}">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
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
