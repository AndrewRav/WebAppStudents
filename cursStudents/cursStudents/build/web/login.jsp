<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Авторизация</title>
        <link rel="stylesheet" href="css/loginStyle.css">
    </head>
    <body>
        <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
        <div class="login">
            <h2 class="active">Авторизация</h2>
            <form method="post" action="login">
                <input type="email" class="text" name="useremail" id="useremail" placeholder="" required/>
                <span>Почта</span>
                <br>
                <br>
                <input type="password" class="text" name="pass" id="pass" placeholder="" required/>
                <span>Пароль</span>
                <input type="submit" name="signin" id="signin" class="signin" value="Войти"/>
                <hr>
                <div class="system">Система — «Факультатив»</div>
            </form>
        </div>

        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">

        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status == "failed") {
                swal("Извините", "Кажется, вы ввели неверные данные", "warning");
            }
        </script>
    </body>
</html>