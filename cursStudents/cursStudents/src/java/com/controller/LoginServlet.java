package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andrew
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userEmail = request.getParameter("useremail");
        String password = request.getParameter("pass");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        Connection con = null;
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");
            PreparedStatement pst = con.prepareStatement("select * from users where email = ? and password = ?");

            pst.setString(1, userEmail);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                session.setAttribute("name", rs.getString("first_name"));
                session.setAttribute("middleName", rs.getString("middle_name"));
                session.setAttribute("id", rs.getString("id"));
                String status = rs.getString("status");
                session.setAttribute("status", status);
                if (status.equals("admin")) {
                    dispatcher = request.getRequestDispatcher("admin.jsp");
                } else {
                    dispatcher = request.getRequestDispatcher("index.jsp");
                }
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);
                
        } catch (IOException | SQLException | ServletException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}