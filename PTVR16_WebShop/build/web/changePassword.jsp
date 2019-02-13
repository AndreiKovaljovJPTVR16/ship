<%-- 
    Document   : changePassword
    Created on : Jan 24, 2019, 8:38:11 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменение пароля</title>
    </head>
    <body>
        <div class="container">
	<div class="row">
		<div class="col-sm-4">
                    <h1>Изменение пароля</h1>
		    <label>Нынешний пароль</label>
                    <form action="changePassword" method="POST">

		    <div class="form-group pass_show"> 
                <input name="oldPassword" type="text" value="" class="form-control" placeholder="Current Password"> 
            </div> 
		       <label>Новый пароль</label>
            <div class="form-group pass_show"> 
                <input name="newPassword1" type="text" value="" class="form-control" placeholder="New Password"> 
            </div> 
		       <label>Подтвердите пароль</label>
            <div class="form-group pass_show"> 
                <input name="newPassword2" type="text" value="" class="form-control" placeholder="Confirm Password"> 
            </div> 
                       <input type="submit" name="change" value="Изменить">
            
		                </form>
</div>  
	</div>
</div>
    </body>
</html>
