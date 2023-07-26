package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
@WebServlet(name = "UpdatePersonalDataServlet", urlPatterns = {"/updatePersonalDataServlet"})
public class UpdatePersonalDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String id = request.getSession().getAttribute("id").toString();
            String login = request.getParameter("login");
            String password = request.getParameter("pass");
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String middleName = request.getParameter("middleName");
            String email = request.getParameter("email");

            RequestDispatcher dispatcher;
            Connection con = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");

                PreparedStatement pst = con.prepareStatement("UPDATE users SET login = ?, password = ?, last_name = ?, first_name = ?, middle_name = ?, email = ? WHERE id = ?");

                pst.setString(1, login);
                pst.setString(2, password);
                pst.setString(3, lastName);
                pst.setString(4, firstName);
                pst.setString(5, middleName);
                pst.setString(6, email);
                pst.setString(7, id);

                int rowCount = pst.executeUpdate();

//                String updatePersonalDataPage = "/updatePersonalData.jsp";
//                dispatcher = request.getRequestDispatcher(updatePersonalDataPage);
                dispatcher = request.getRequestDispatcher("/pesonalDataServlet");

                if (rowCount > 0) {
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("status", "failed");
                }
                dispatcher.forward(request, response);

            } catch (IOException e) {
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AllStudentsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
