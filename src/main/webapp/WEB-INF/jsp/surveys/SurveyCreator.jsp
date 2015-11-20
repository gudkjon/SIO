<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Create survey</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>"/>
        <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />--%>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    </head>

    <body>

        <div class="form-container gray-background">
            <h1 class="text-center blue-letters">Create a survey</h1>

            <div class="box">
                <sf:form method="POST" cssClass="form-horizontal" role="form" commandName="survey" action="/survey">

                    <div class="form-group">
                        <label for="nameInput"> Name: </label>
                        <sf:input path="name" cssClass="form-control col-xs-*" id="nameInput" type="text" placeholder="Enter survey name"/>
                    </div>
                    <div class="form-group">
                        <label for="authorInput">Author:</label>
                        <sf:input path="author" cssClass="form-control" id="authorInput" type="text" placeholder="Enter author name"/>
                    </div>
                    <div class="form-group">
                        <input class="btn btn-primary" type="submit" VALUE="Create Survey"/>
                    </div>

                </sf:form>
            </div>
        </div>
        <div class="form-container">
            <c:choose>
                <%--If the model has an attribute with the name `surveys`--%>

                <c:when test="${not empty surveys}">
                    <%--Create a table for the Postit Notes--%>
                    <table class="notes">

                        <tr>
                            <td>
                                <a href="/survey/${survey.getId()}">${survey.getName()}</a>
                            </td>
                                <%--The String in the note attribute--%>
                            <td>${survey.getAuthor()}</td>
                            <td>
                                <a href="/result/">result</a>
                            </td>
                        </tr>

                        <c:forEach var="survey" items="${surveys}">
                            <tr>
                                <td>
                                    <a href="/survey/${survey.getId()}">${survey.getName()}</a>
                                </td>
                                <%--The String in the note attribute--%>
                                <td>${survey.getAuthor()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>

                <%--If all tests are false, then do this--%>
                <c:otherwise>
                    <h3>No surveys!</h3>
                    <a href="/survey">Click here to create a survey!</a>
                </c:otherwise>
            </c:choose>
        </div>

    </body>
</html>
