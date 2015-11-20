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

        <div class="form-container gray-background">
            <h1 class="text-center blue-font">Add options</h1>
            <h3 class="text-center gray-font">${question.getQuestionText()}</h3>
            <h3 class="text-center gray-font">${question.getType()}</h3>

            <div class="box">
                <sf:form method="POST" commandName="option" action="/survey/surveyedit/${question.getSurvey().getId()}/${question.getId()}">
                    <div class="form-group">
                        <label for="optionId"> Option: </label>
                        <sf:input cssClass="form-control" id="optionId" path="optionText" type="text" placeholder="Add option"/>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary" type="submit" VALUE="Add Option"/>
                    </div>
                    <a href="/survey/${question.getSurvey().getId()}">Doneapalooza mcsnoza!</a>
                </sf:form>
            </div>
        </div>

        <div class="form-container">
            <c:choose>
                <c:when test="${not empty question.getOptions()}">
                    <table class="surveys">
                        <c:forEach var="option" items="${question.getOptions()}">
                            <td>
                                <p>${option.getOptionText()}</p>
                            </td>
                            <td>
                                <form method = "post" action = "/survey/surveyedit/delete/${option.getQuestion().getSurvey().getId()}/${option.getQuestion().getId()}/${option.getId()}">
                                    <input class="btn btn-default" type="submit" value="Delete">
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
        </div>
    </body>
</html>

