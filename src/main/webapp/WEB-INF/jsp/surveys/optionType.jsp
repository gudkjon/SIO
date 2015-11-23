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
    <h3>Choose a predetermined option</h3>
    <ul>
        <li>Example1</li>
        <li>Example2</li>
        <li>Example3</li>
    </ul>

    <h3>Make custom options</h3>

    <sf:form method="POST" commandName="option" action="/survey/surveyedit/${question.getSurvey().getId()}/${question.getId()}">
        <input type="submit" class="btn btn-primary" value="Custom"/>
    </sf:form>

    <br>
    <input class="btn btn-success" type="submit" value="Submit"/>
    </body>
</html>
