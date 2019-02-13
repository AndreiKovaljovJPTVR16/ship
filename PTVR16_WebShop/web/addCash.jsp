<%-- 
    Document   : addMoney
    Created on : Jan 28, 2019, 10:47:25 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пополнить баланс</title>
    </head>
    <body>
        <h1>Добавьте деньги на свою карту!</h1>
        <p>${info}</p><br>
        
        <form action="addCash" method="POST">
            Внести деньги: <input type="text" name="money"><br>
            <input type="submit" value="Добавить" >
        </form>
        
    </body>
</html>
