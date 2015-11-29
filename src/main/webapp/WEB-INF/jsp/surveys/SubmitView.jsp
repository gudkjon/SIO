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
<div class="container gray-background">
    <h1 class="text-center blue-font">Results</h1>
    <p class="text-center">${survey.getName()}</p>
    <p class="text-center">You scored: ${sumPickedWeight/survey.getTotalWeight()*100}%</p>
</div>
</body>
</html>