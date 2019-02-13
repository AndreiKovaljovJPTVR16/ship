<%-- 
    Document   : page2
    Created on : Dec 10, 2018, 10:49:24 AM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> WEB-SHOP  </title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <h1>Список покупателей</h1><br><a href="index.html">Главная</a>
        ${info}
        
        <ul>
           
            <c:forEach var="buyer" items="${listBuyers}">
                <li>${buyer.name} ${buyer.surname}, Баланс: ${buyer.cash} </li>
            </c:forEach>
        </ul>
        
    </body>
</html>
