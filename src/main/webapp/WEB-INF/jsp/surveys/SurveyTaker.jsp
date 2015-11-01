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

        <h1>${survey.name}</h1>
        <p>by ${survey.author}</p>

        <c:choose>
            <%--If the model has an attribute with the name `surveys`--%>
            <c:when test="${not empty questions}">
                <table class="notes">
                    <c:forEach var="question" items="${questions}" varStatus="status">
                        <tr>
                            <td>
                                <a href="/survey/surveyedit/${question.surveyId}/${question.id}">${question.questionText}</a>
                            </td>
                            <td>
                                ${question.type}
                            </td>
                            <td>
                                <form method = post action = "/survey/surveyedit/delete/${question.surveyId}/${question.id}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                            <td>
                                <c:choose>
                                    <%--Dropdown options--%>
                                    <c:when test="${question.type == 'dropDown'}">
                                        <select>
                                            <option value="Select answer">${"Select answer"}</option>
                                            <c:forEach var="option" items="${options.get(status.index)}">
                                                <p>${option.id}</p>
                                                <option value="${option.id}">${option.optionText}</option>
                                            </c:forEach>
                                        </select>
                                    </c:when>

                                    <%--radio button options--%>
                                    <c:when test="${question.type == 'radioButton'}">
                                        <c:forEach var="option" items="${options.get(status.index)}">
                                            <label>
                                                <input type="radio" name="${question.id}" value="${option.id}" />${option.optionText}
                                            </label>
                                            <br>
                                        </c:forEach>
                                    </c:when>

                                    <%--Input option--%>
                                    <c:when test="${question.type == 'input'}">
                                        <input placeholder="Enter answer" type="text" name="${option.id}" />
                                    </c:when>

                                    <%--Multiple question option--%>
                                    <c:when test="${question.type == 'multiQuestion'}">
                                        <c:forEach var="option" items="${options.get(status.index)}">
                                            <label>
                                                <input type="checkbox" name="${question.id}" value="${option.id}" />${option.optionText}
                                            </label>
                                            <br>
                                        </c:forEach>
                                    </c:when>

                                    <c:otherwise>
                                        <p> No options </p>
                                    </c:otherwise>
                                </c:choose>
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
