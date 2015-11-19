<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>Survey Creator</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/survey.css"/>"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    </head>
    <body>

        <h1>surveys</h1>
        <%--Note that the `commandName` given here HAS TO MATCH the name of the attribute--%>
        <%--that is added to the model that is passed to the view.--%>
        <%--See PostitNoteController, method postitNoteViewGet(), and find where this attribute is added to the model.--%>
            <sf:form method="POST"  commandName="survey" action="/survey">
            <table>
                <tr>
                    <td> Name:</td>
                    <%--the `path` attribute matches the `name` attribute of the Entity that was passed in the model--%>
                    <td><sf:input path="name" type="text" placeholder="Enter name"/></td>
                </tr>
                <tr>
                    <td>Author:</td>
                        <%--the `path` attribute matches the `note` attribute of the Entity that was passed in the model--%>
                    <td><sf:input path="author" type="text" placeholder="Author here"/></td>
                </tr>
            </table>

            <input type="submit" VALUE="Post It!"/>

        </sf:form>

        <%--Choose what code to generate based on tests that we implement--%>

        <c:choose>
            <%--If the model has an attribute with the name `surveys`--%>
            <c:when test="${not empty surveys}">
                <%--Create a table for the Postit Notes--%>
                <table class="notes">

                    <c:forEach var="survey" items="${surveys}">
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
                    </c:forEach>
                </table>
            </c:when>

            <%--If all tests are false, then do this--%>
            <c:otherwise>
                <h3>No surveys!</h3>
                <a href="/survey">Click here to create a survey!</a>
            </c:otherwise>
        </c:choose>
    </body>
</html>
