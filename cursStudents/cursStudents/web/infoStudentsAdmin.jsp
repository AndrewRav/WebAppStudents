<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Информация</title>
        <c:url var="css" value="/css/infoStyle.css"></c:url>
        <link rel="stylesheet" href="${css}" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <span class="header-title">Список данных всех студентов</span>
            <form>
                <a href="admin.jsp" class="header-link">Назад</a>
            </form>
        </div>
        <table>
            <thead>
            <th>id</th>
            <th>user id</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Дата рождения</th>
            <th>Телефон</th>
            <th>Факультет</th>
            <th>Курс</th>
            <th>Группа</th>
            <th>Действие</th>
        </thead>
        <c:forEach var="ab" items="${students}">
            <tr>
                <td>${ab.id}</td>
                <td>${ab.user_id}</td>
                <td>${ab.lastName}</td>
                <td>${ab.firstName}</td>
                <td>${ab.middleName}</td>
                <td>${ab.birthDate}</td>
                <td>${ab.phoneNumber}</td>
                <td>${ab.faculty}</td>
                <td>${ab.course}</td>
                <td>${ab.group}</td>
                <td><a href="updateStudents.jsp?id=${ab.id}">Изменить</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>