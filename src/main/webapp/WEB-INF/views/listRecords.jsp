<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Users List</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>

</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name"/></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/"><fmt:message key="menu.main" /><span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul class="navbar-nav ">
            <li class="nav-item mr-2">
                <span class="navbar-text"><fmt:message key="lang.change" /></span>:
                <select id="locales">
                    <option value=""></option>
                    <option value="ua"><fmt:message key="lang.ua" /></option>
                    <option value="en"><fmt:message key="lang.en" /></option>
                </select>
            </li>
            <li class="nav-item mr-2">
                <span style="color:red">[ ${loginedUser.userName} ]</span>
            </li>
            <li class="nav-item mr-2">
                <a class="nav-link" href="/logout"><fmt:message key="menu.logout" /></a>
            </li>
        </ul>

    </div>
</nav>
<div class="container d-flex justify-content-center">
    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">List of Records </span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Barber</th>
                <th>Service Type</th>
                <th>Client name</th>
                <th>Client email</th>
                <th>Date</th>
                <th>Time</th>
                <c:forEach items="${sessionScope.loginedUser.roles}" var="role">
                    <c:if test = "${role == 'ADMIN'}">
                <th width="100"></th>
                    </c:if>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${recordList}" var="record">
                <tr>
                    <td>${record.barber.firstName} ${record.barber.lastName}</td>
                    <td>${record.service.serviceName}</td>
                    <td>${record.client.firstName} ${record.client.lastName}</td>
                    <td>${record.client.email}</td>
                    <td>${record.localDate}</td>
                    <td>${record.localTime}</td>
                    <c:forEach items="${sessionScope.loginedUser.roles}" var="role">
                    <c:if test = "${role == 'ADMIN'}">
                        <td><a href="<c:url value='/newRecord?id=${record.id}' />" class="btn btn-secondary custom-width"><fmt:message key="recordPage.button.served" /></a></td>
                    </c:if>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
<c:forEach items="${sessionScope.loginedUser.roles}" var="role">
    <c:if test = "${role == 'ADMIN'}">
        <div align="center" class="well">
            <a class="btn btn-primary" href="<c:url value='/newRecord' />"><fmt:message key="recordPage.button.addNew" /></a>
        </div>
    </c:if>
</c:forEach>
<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/recordList?sessionLocale=' + selectedOption);
            }
        });
    });
</script>
<!-- /container -->
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
<%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
</body>
</html>