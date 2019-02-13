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
      
            <h2>Регистрация</h2> 
            <form class="form-horizontal" action="registration" method="POST">
<fieldset>

<!-- Form Name -->
<legend>Form Name</legend>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Имя</label>
  <div class="controls">
    <input name="name" type="text" placeholder="" class="input-xlarge">
    
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Фамилия</label>
  <div class="controls">
    <input name="surname" type="text" placeholder="" class="input-xlarge">
    
  </div>
</div>
<div class="control-group">
  <label class="control-label" for="textinput">Логин</label>
  <div class="controls">
    <input name="login" type="text" placeholder="" class="input-xlarge">
    
  </div>
</div>
<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="textinput">Email</label>
  <div class="controls">
    <input name="email" type="text" placeholder="" class="input-xlarge">
    
  </div>
</div>
<div class="control-group">
  <label class="control-label" for="textinput">Пароль</label>
  <div class="controls">
    <input name="password1" type="password" placeholder="" class="input-xlarge">
    
  </div>
</div>
<div class="control-group">
  <label class="control-label" for="textinput">Пароль повторно</label>
  <div class="controls">
    <input name="password2" type="password" placeholder="" class="input-xlarge">
    
  </div>
</div>


<!-- Button -->
<div class="control-group">
  
  <div class="controls">
    <button id="singlebutton" name="send" class="btn btn-primary">Зарегистрироваться</button>
  </div>
    <br>
    ${info}
    
    <a href="index.html">Главная</a>
</div>

</fieldset>
</form>

            
    </body>
</html>
