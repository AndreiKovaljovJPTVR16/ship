<%-- 
    Document   : page5
    Created on : Dec 10, 2018, 2:15:58 PM
    Author     : pupil
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <h1>WEB-SHOP</h1>
            <h2>Добавить товар</h2> 
            <form class="form-horizontal" action="addNewProduct" method="POST">
<fieldset>

<!-- Form Name -->
<legend>Form Name</legend>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Название товара</label>
  <div class="controls">
    <input name="name" type="text" placeholder="" class="input-xlarge">
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Цена</label>
  <div class="controls">
    <input name="price" type="number" placeholder="" min="0" class="input-xlarge">
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  
  <label class="control-label" for="textinput">Количество</label>
  <div class="controls">
    <input name="count" type="number" placeholder="" min="0" class="input-xlarge">
    
  </div>
</div>

<!-- Button -->
<div class="control-group">
  
  <div class="controls">
    <button id="singlebutton" name="send" class="btn btn-primary">Добавить</button>
  </div>
    
    <br>
    
    ${info}
    
    <a href="index.html">Главная</a>
</div>

</fieldset>
</form>

            
    </body>
</html>
