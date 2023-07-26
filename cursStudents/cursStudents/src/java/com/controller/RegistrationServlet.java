package com.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/register"})
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {

            String login = request.getParameter("login");
            String password = request.getParameter("pass");
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String middleName = request.getParameter("middleName");
            String email = request.getParameter("email");
            String status = request.getParameter("status");
            RequestDispatcher dispatcher;
            Connection con;

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");

            PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into users(login, password, last_name, first_name, middle_name, email, status) values(?,?,?,?,?,?,?)");
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, lastName);
            pst.setString(4, firstName);
            pst.setString(5, middleName);
            pst.setString(6, email);
            pst.setString(7, status);
            
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
