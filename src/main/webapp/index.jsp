<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<%!
String getFormattedDate(){
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    return sdf.format(new Date());
}
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CoffeeMachine</title>
</head>
    <body>
    <ul>
        <li><a href="?sessionLocale=en"><fmt:message key="label.lang.en"/></a></li>
        <li><a href="?sessionLocale=ua"><fmt:message key="label.lang.ua"/></a></li>
    </ul>
        <h2>
            <fmt:message key="hello" /> CoffeeMachine! <br/>
            <i>Сегодня <%= getFormattedDate() %></i>
        </h2>

        <br/>
        <a href="${pageContext.request.contextPath}/login"><fmt:message key="login"/></a>
              <br>
        <a href="${pageContext.request.contextPath}/exception">Exception</a>
              <br>

    </body>
</html>
