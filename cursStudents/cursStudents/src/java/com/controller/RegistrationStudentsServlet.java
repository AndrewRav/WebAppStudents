package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
@WebServlet(urlPatterns = {"/registerStudents"})
public class RegistrationStudentsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            String stringUserID = request.getParameter("userID");
            int userID = Integer.parseInt(stringUserID);
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String middleName = request.getParameter("middleName");
            String birthDate = request.getParameter("birthDate");
            String phoneNumber = request.getParameter("phoneNumber");
            String faculty = request.getParameter("faculty");
            String stringCourse = request.getParameter("course");
            int course = Integer.parseInt(stringCourse);
            String groupName = request.getParameter("groupName");
            
            RequestDispatcher dispatcher;
            Connection con;

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");

            PreparedStatement pst = con.prepareStatement("insert into students(user_id, last_name, first_name, middle_name, birth_date, tel_number, faculty, course, groupName) values(?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, userID);
            pst.setString(2, lastName);
            pst.setString(3, firstName);
            pst.setString(4, middleName);
            pst.setString(5, birthDate);
            pst.setString(6, phoneNumber);
            pst.setString(7, faculty);
            pst.setInt(8, course);
            pst.setString(9, groupName);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("admin.jsp");

            if (rowCount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("status", "failed");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin.jsp");
            rd.forward(request, response);
        }
    }
}
