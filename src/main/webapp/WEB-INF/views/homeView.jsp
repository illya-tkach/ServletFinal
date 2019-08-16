<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Products List</title>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<%--    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>--%>

    <style type="text/css">
        body{
            height: 200px; /* Высота блока */
            /*border: 2px solid #000; !* Параметры рамки *!*/
            background: url("${contextPath}/resources/images/barber.jpeg") 100% 100% no-repeat; /* Добавляем фон */
            background-size: cover; /* Масштабируем фон */
        }
    </style>

    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

    <script defer src="${contextPath}/resources/fontawesome-free-5.6.1-web/js/all.min.js"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#"><i class="fab fa-monero"></i><fmt:message key="name" /></a>
    <div class="collapse navbar-collapse justify-content-between" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:choose>
                <c:when test= "${sessionScope.loginedUser != null}">
                        <c:forEach items="${sessionScope.loginedUser.roles}" var="role">
                            <c:choose>
                                <c:when test= "${role == 'ADMIN'}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/recordList"><fmt:message key="menu.records" /><span class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                                <c:when test= "${role == 'CLIENT'}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="/employeeTask"><fmt:message key="menu.booking" /><span class="sr-only">(current)</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="/personal"><fmt:message key="menu.personal"/><span class="sr-only">(current)</span></a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                </c:when>
                <c:otherwise>
                    <li  class="nav-item">
                        <a href="/registration" class="nav-link"><fmt:message key="menu.registration" /><span class="sr-only">(current)</span></a>
                    </li>
                    <li  class="nav-item">
                        <a href="/login" class="nav-link"><fmt:message key="menu.login" /><span class="sr-only">(current)</span></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
        <ul class="navbar-nav ">
            <li class="nav-item">
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
            <c:if test="${loginedUser!= null}">
            <li class="nav-item mr-2">
                <a class="nav-link" href="/logout"><fmt:message key="menu.logout" /></a>
            </li>
            </c:if>
        </ul>

    </div>
</nav>
<div class="container d-flex justify-content-center" >
<c:if test="${bookingSuccess != null}">
    <div class="modal" id="informModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Your record</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>${bookingSuccess}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        $("#informModal").modal("show");
    </script>
</c:if>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("#locales").change(function () {
            var selectedOption = $('#locales').val();
            if (selectedOption != ''){
                window.location.replace('/?sessionLocale=' + selectedOption);
            }
        });
    });
</script>

</body>
</html>
