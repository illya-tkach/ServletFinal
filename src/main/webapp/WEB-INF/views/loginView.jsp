<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">

    <title>Log in with your account</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


</head>

<body>

<div class="container">

    <form method="POST" action="${contextPath}/login" class="form-signin">
        <input type="hidden" name="redirectId" value="${param.redirectId}" />
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="userName" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <span style="color: red;">${userNameError}</span>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <span style="color: red;">${passwordError}</span>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>

    </form>

</div>

</body>
</html>