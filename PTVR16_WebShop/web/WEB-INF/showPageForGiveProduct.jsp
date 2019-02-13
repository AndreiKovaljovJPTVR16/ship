<%-- 
    Document   : page4
    Created on : Dec 10, 2018, 2:15:58 PM
    Author     : pupil
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WEB-SHOP</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <h1>Проекта</h1>
        <form action="makePurchase" method="POST">
            <h2>Список товаров</h2> 
            <select name="productId">
                <c:forEach var="product" items="${listProducts}">
                <option value="${product.id}">${product.name}, Количество: ${product.count}, Цена за один: ${product.price}</option>
            </c:forEach>
        
    
  
            </select><br>
            
  
            </select> <br><br>
             <h2>Количество</h2> 
             <input type="number" name="purchaseCount" min="1">
            
            <input type="submit">
        </form>
        <a href="index.html">Главная</a>
        ${info}
    </body>
</html>
