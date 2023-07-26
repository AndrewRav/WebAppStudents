package com.controller;

import com.entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "AllStudentsServlet", urlPatterns = {"/allStudentsServlet"})
public class AllStudentsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
//            String idString = request.getSession().getAttribute("id").toString();
//            int id = Integer.parseInt(idString);
            ArrayList<Student> students = new ArrayList<>();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("SELECT * FROM students");
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Student student = new Student(rs.getInt("id"), rs.getInt("user_id"),
                        rs.getString("last_name"), rs.getString("first_name"), rs.getString("middle_name"),
                        rs.getString("birth_date"), rs.getString("tel_number"), rs.getString("faculty"),
                        rs.getInt("course"), rs.getString("groupName"));
                students.add(student);
            }

            RequestDispatcher rd;
            if (request.getSession().getAttribute("status").equals("admin")) {
                request.getSession().setAttribute("students", students);
                rd = getServletContext().getRequestDispatcher("/infoStudentsAdmin.jsp");
            } else {
                request.getSession().setAttribute("students", students);
                rd = getServletContext().getRequestDispatcher("/info.jsp");
            }
            rd.forward(request, response);

        } catch (IOException e) {
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }
    }
}
