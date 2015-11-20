<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Survey Editor</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>" />
        <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />--%>
    </head>
    <body>

        <h1>${survey.getName()}</h1>
        <p>by ${survey.getAuthor()}</p>

        <c:choose>
            <%--If the model has an attribute with the name `surveys`--%>
            <c:when test="${not empty survey.getQuestions()}">
                <sf:form method="POST" commandName="results" action="/">
                    <table class="notes">
                        <c:forEach var="question" items="${survey.getQuestions()}">
                            <tr>
                                <td>
                                    <a href="/survey/surveyedit/${question.getSurvey().getId()}/${question.getId()}">${question.getQuestionText()}</a>
                                </td>
                                <td>
                                    ${question.type}
                                </td>
                                <td>
                                    <c:choose>
                                        <%--Dropdown options--%>
                                        <c:when test="${question.getType() == 'dropDown'}">
                                            <select>
                                                <option value="Select answer">${"Select answer"}</option>
                                                <c:forEach var="option" items="${question.getOptions()}">
                                                    <p>${option.getId()}</p>
                                                    <option value="${option.getId()}">${option.getOptionText()}</option>
                                                </c:forEach>
                                            </select>
                                        </c:when>

                                        <%--radio button options--%>
                                        <c:when test="${question.getType() == 'radioButton'}">
                                            <c:forEach var="option" items="${question.getOptions()}">
                                                <label>
                                                    <input type="radio" name="${question.getId()}" value="${option.getId()}" />${option.getOptionText()}
                                                </label>
                                                <br>
                                            </c:forEach>
                                        </c:when>

                                        <%--Input option--%>
                                        <c:when test="${question.getType() == 'input'}">
                                            <input placeholder="Enter answer" type="text" name="${option.getId()}" />
                                        </c:when>

                                        <%--Multiple question option--%>
                                        <c:when test="${question.getType() == 'multiQuestion'}">
                                            <c:forEach var="option" items="${question.getOptions()}">
                                                <label>
                                                    <input type="checkbox" name="${question.getId()}" value="${option.getId()}" />${option.getOptionText()}
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
                    <input type="submit" VALUE="Post your answers!"/>
                </sf:form>
            </c:when>

            <%--If all tests are false, then do this--%>
            <c:otherwise>
                <h3>No questions!</h3>
            </c:otherwise>
        </c:choose>

    </body>
</html>
