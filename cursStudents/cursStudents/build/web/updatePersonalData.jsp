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
        <title>Редактирование данных студентов</title>
        <c:url var="css" value="/css/infoStyle.css"></c:url>
        <link rel="stylesheet" href="${css}" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <span class="header-title">Редактирование личных данных</span>
            <form>
                <a href="index.jsp" class="header-link">Назад</a>
            </form>
        </div>
        <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
        <form method="post" action="updatePersonalDataServlet">
            <table>
                <thead>
                <th>id</th>
                <th>Логин</th>
                <th>Пароль</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Email</th>
                <th>Статус</th>
                </thead>
                <c:forEach var="ab" items="${users}">
                    <tr>
                        <td>${ab.id}</td>
                        <td><input type="text" name="login" value="${ab.login}" required></td>
                        <td><input type="text" name="pass" value="${ab.password}" required></td>
                        <td><input type="text" name="lastName" value="${ab.lasName}" required></td>
                        <td><input type="text" name="firstName" value="${ab.firstName}" required></td>
                        <td><input type="text" name="middleName" value="${ab.middleName}" required></td>
                        <td><input type="text" name="email" value="${ab.email}" required></td>
                        <td>${ab.status}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="form-group form-button">
                <input type="submit" name="signup" id="signup"
                       class="form-submit" value="Обновить" />
            </div>

        </form>
     
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">

        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status == "success") {
                swal("Поздравляю", "Данные успешно обновлены", "success");
            } else if (status == "failed") {
                swal("Ошибка!", "Неверный ввод данных", "error");
            }
        </script>
    </body>
</html>
