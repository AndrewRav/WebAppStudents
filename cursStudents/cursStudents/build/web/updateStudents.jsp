<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Редактирование данных студентов</title>
        <c:url var="css" value="/css/updateStudentStyle.css"></c:url>
        <link rel="stylesheet" href="${css}" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <span class="header-title">Редактирование данных студентов</span>
            <form action="allStudentsServlet" method="post">
                <input class="button" type="submit" value="Назад"/>
            </form>
        </div>
        <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
        <form method="post" action="updateStudentsServlet">
            <table>
                <thead>
                <th>user_id</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
                <th>Дата рождения</th>
                <th>Телефон</th>
                <th>Факультет</th>
                <th>Курс</th>
                <th>Группа</th>
                </thead>
                <tbody>
                    <%
                        String idString = request.getParameter("id");
                        int id = Integer.parseInt(idString);
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");
                            PreparedStatement pst = con.prepareStatement("SELECT * FROM students WHERE id =?");
                            pst.setInt(1, id);
                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                    %>
                    <tr>
                        <td><input type="number" name="userID" value="<%= rs.getString(2)%>" required></td>
                        <td><input type="text" name="lastName" value="<%= rs.getString(3)%>" required></td>
                        <td><input type="text" name="firstName" value="<%= rs.getString(4)%>" required></td>
                        <td><input type="text" name="middleName" value="<%= rs.getString(5)%>" required></td>
                        <td><input type="text" name="birthDate" value="<%= rs.getString(6)%>" required></td>
                        <td><input type="text" name="phoneNumber" value="<%= rs.getString(7)%>" required></td>
                        <td><input type="text" name="faculty" value="<%= rs.getString(8)%>" required></td>
                        <td><input type="number" min="0" name="course" value="<%= rs.getString(9)%>" required></td>
                        <td><input type="text" name="groupName" value="<%= rs.getString(10)%>" required></td>
                            <td><input type="hidden" name="id" value="<%= rs.getString(1)%>" required></td>
                    </tr>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>
                </tbody>
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
