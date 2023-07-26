<%
    if (session.getAttribute("name") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Личный кабинет</title>
        <link rel="stylesheet" href="css/indexStyle1.css">
    </head>
    <body>
        <h1>Добро пожаловать, <%= session.getAttribute("name")%> <%= session.getAttribute("middleName")%>!</h1>
        <form action="allStudentsServlet" method="post" class="form_1">
            <input class="signin" type="submit" value="Список всех студентов в системе"/>
        </form>
        <form action="studentsUserServlet" method="post" class="form_2">
            <input class="signin" type="submit" value="Список студентов, которые записались к вам на факультатив"/>
        </form>
        <form id="facultyForm" action="facultyServlet" method="post" class="form_3">
            <label for="faculty">Введите название факультета:</label>
            <input type="text" class="text" id="faculty" name="faculty" placeholder="Например, ФЭБУ" required/><br/>
            <input class="signin" type="submit" value="Список студентов заданного факультета"/>
        </form>
        <form action="birthDateServlet" method="post" class="form_4">
            <label for="faculty">Введите год рождения:</label>
            <input type="text" class="text" id="birthDate" name="birthDate" placeholder="Например, 2002" required/><br/>
            <input class="signin" type="submit" value="Список студентов, родившихся после заданного года"/>
        </form>
        <form action="groupServlet" method="post" class="form_5">
            <label for="group">Введите группу:</label>
            <input type="text" class="text" id="group" name="group" placeholder="Например, ЭЭБ-1" required/><br/>
            <input class="signin" type="submit" value="Список учебной группы"/>
        </form>
        <form action="pesonalDataServlet" method="post" class="form_6">
            <input class="signin" type="submit" value="Редактирование ваших личных данных"/>
        </form>
        <form action="logout" class="form_7">
            <input type="hidden" name="logout" value="true"/>
            <input class="signin_leave" type="submit" value="Выйти из аккаунта"/>
        </form>
    </body>
</html>
