
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Админ-панель системы «Факультатив»</title>
        <link href="css/adminNewStyle.css" rel="stylesheet" />
    </head>

    <body>
        <!-- Добро пожаловать-->
        <div class="header">
            <h2 class="header-title">Добро пожаловать в Админ-панель системы «Факультатив», <%= session.getAttribute("name")%> <%= session.getAttribute("middleName")%></h2>
        </div>
        <!-- Добавление студента -->
        <div class="forms-container">
            <div class="login">
                <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
                <form method="post" action="registerStudents" class="register-form" id="register-form">
                    <h2 class="active_1">Добавить студента</h2>
                    <input type="number" min="0" name="userID" id="userID" placeholder="ID преподавателя" class="text" required />
                    <input type="text" name="lastName" id="lastName" placeholder="Фамилия" class="text" required />
                    <input type="text" name="firstName" id="firstName" placeholder="Имя" class="text" required />
                    <input type="text" name="middleName" id="middleName" placeholder="Отчество" class="text" required />
                    <input type="date" name="birthDate" id="birthDate" placeholder="Дата рождения" class="text" required />
                    <input type="text" name="phoneNumber" id="phoneNumber" placeholder="Номер телефона" class="text" required />
                    <input type="text" name="faculty" id="faculty" placeholder="Факультет" class="text" required />
                    <input type="number" min="0" name="course" id="course" placeholder="Курс" class="text" required />
                    <input type="text" name="groupName" id="groupName" placeholder="Группа" class="text" required />            
                    <br>
                    <input type="submit" name="signup" id="signup" class="signin" value="Добавить" required />
                </form>
            </div>
            <div class="login">
                <!-- Добавление пользователя -->
                <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
                <form method="post" action="register" class="register-form" id="register-form">
                    <h2 class="active_2">Добавить пользователя</h2>
                    <input type="text" name="login" id="login" placeholder="Логин" class="text" required />
                    <input type="password" name="pass" id="pass" placeholder="Пароль" class="text" required />
                    <input type="text" name="lastName" id="lastName" placeholder="Фамилию" class="text" required />
                    <input type="text" name="firstName" id="firstName" placeholder="Имя" class="text" required=""/>
                    <input type="text" name="middleName" id="middleName" placeholder="Отчество" class="text" required />
                    <input type="email" name="email" id="email" placeholder="Email" class="text" required />
                    <select name="status" id="status" class="text_select" required>
                        <option value="" disabled selected hidden>Выберите статус</option>
                        <option value="user">Пользователь</option>
                        <option value="admin">Администратор</option>
                    </select>

                    <br>
                    <input type="submit" name="signup" id="signup" class="signin_reg" value="Добавить" />
                </form>
            </div>
        </div>

        <div class="main_part">       
            <div class="forms-container">
                <form method="post" action="delete" class="delete_student">
                    <h2 class="active_main_part_1">Удалить студента</h2>
                    <input type="number" class="text" min="1" name="studentId" id="studentId" placeholder="ID студента" />
                    <input type="submit" name="signup" id="signup" class="signin_student" value="Удалить" />
                </form>

                <form method="post" action="deleteUserServlet" class="delete_user">
                    <h2 class="active_main_part_2">Удалить пользователя</h2>
                    <input type="number" class="text" min="1" name="id" id="id" placeholder="ID пользователя" />
                    <br>
                    <input type="submit" name="signup" id="signup" class="signin_user" value="Удалить" />
                </form>
            </div>
            <!-- Вывод данных -->
            <div class="forms-container">
                <form action="allStudentsServlet" method="post" class="all_students">
                    <h2 class="active_main_part_3">Вывод и редактирование студентов</h2>
                    <input class="signin_all_students" type="submit" value="Список данных всех студентов"/>
                </form>
                <form action="usersServlet" method="post" class="all_users">
                    <h2 class="active_main_part_4">Вывод и редактирование пользователей</h2>
                    <input class="signin_all_users" type="submit" value="Список данных всех пользователей"/>
                </form>
            </div>
        </div>
        <form action="logout">
            <input type="hidden" name="logout" value="true"/>
            <input class="signin_leave" type="submit" value="Покинуть админ-панель"/>
        </form> 
                
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">

        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status == "success") {
                swal("Поздравляем!", "Операция прошла успешно!", "success");
            } else if (status == "failed") {
                swal("Извините", "Кажется, вы ввели неверные данные", "warning");
            }
        </script>
    </body>
</html>

