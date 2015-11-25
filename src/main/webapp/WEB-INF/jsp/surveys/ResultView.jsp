<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Viewing A Result</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    </head>

    <body>
        <h1>
            <b>Hi There!</b>
            <c:choose>
                <c:when test="${not empty questions}">
                    <table class="surveys">
                        <c:forEach var="question" items="${questions}" varStatus="questionCounter">
                            <tr>
                                <td>
                                    <p>${question.getQuestionText()}</p>
                                </td>
                                <td>
                                    <p>${totalAnswersPerQuestion.get(questioncounter.status)}</p>
                                </td>
                                <c:choose>
                                    <c:when test="${not empty optionPercentages}">
                                        <c:forEach var="option" items="${question.getOptions()}" varStatus="optionCounter">
                                            <td>
                                                <p>${option.getOptionText()}:</p>
                                                <c:forEach var="optionCount" items="${optionPercentages.get(optioncounter.status)}">
                                                    <c:choose>
                                                        <c:when test="${optionCount.getValue().equals(option.getOptionText())}">
                                                            <p>${optionCount.getKey()}</p>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>fuck off</p>
                </c:otherwise>
            </c:choose>
        </h1>
    </body>

</html>