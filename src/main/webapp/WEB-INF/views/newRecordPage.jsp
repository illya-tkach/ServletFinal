<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Products List</title>

    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <%--    <script type="text/javascript" src="${contextPath}/resources/jQuery-3.2.1/jquery-3.2.1.js"></script>--%>
    <%--    <script type="text/javascript" src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet"/>

    <style type="text/css">
        body{
            height: 200px; /* Высота блока */
            /*border: 2px solid #000; !* Параметры рамки *!*/
            <%--background: url("${contextPath}/resources/images/barber.jpeg") 100% 100% no-repeat; /* Добавляем фон */--%>
            background-size: cover; /* Масштабируем фон */
        }
    </style>

    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

    <script defer src="${contextPath}/resources/fontawesome-free-5.6.1-web/js/all.min.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
</head>
<body>
<div class="container">
    <div class="generic-container">
        <div class="well lead">Record Form</div>
        <form action="${contextPath}/newRecord" method="post" class="needs-validation form-signin" novalidate>
            <h2 class="form-heading">Add new Record</h2>
            <div class="form-group col-12">
                <label for="datepicker">Date</label>
                <span style="color: red;">${dateAndTimeError}</span>
                <input type="text" name="date" class="form-control" id="datepicker" placeholder="Example input" autocomplete="off" required>
                <div class="invalid-feedback">
                    Please choose a date.
                </div>
            </div>
            <div class="form-group col-12">
                <label for="exampleInputPassword1">Time</label>
                <input type="time" name="time" class="form-control" id="exampleInputPassword1" placeholder="Password" required>
                <div class="invalid-feedback">
                    Please type a time.
                </div>
            </div>
            <div class="form-group col-12">
                <label for="exampleInputPassword1">Барбер</label>
                <select name="barberId" class="form-control">
<%--                <option value="">----Select-----</option>--%>
                <c:forEach var="current" items="${barbers}">
                    <option value="${current.id}">${current.lastName}</option>
                </c:forEach>
                </select>
                <span style="color: red;">${barberError}</span>
            </div>
            <input type="submit" value="Сохранить" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/recordList' />">Отменить</a>
        </form>
    </div>
</div>

</body>
</html>
