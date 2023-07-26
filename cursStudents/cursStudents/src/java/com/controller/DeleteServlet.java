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
@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String studentIdString = request.getParameter("studentId");
            int studentId = Integer.parseInt(studentIdString);
            RequestDispatcher dispatcher;
            Connection con;

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("DELETE FROM students WHERE id = ?");

            pst.setInt(1, studentId);
            
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
