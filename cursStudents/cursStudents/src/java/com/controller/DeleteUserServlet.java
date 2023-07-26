package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(name = "DeleteUserServlet", urlPatterns = {"/deleteUserServlet"})
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String idStringUser = request.getParameter("id");
            int idUser = Integer.parseInt(idStringUser); // id пользователя, которого хотим удалить
            
            String idString = request.getSession().getAttribute("id").toString();
            int id = Integer.parseInt(idString); // наше текущее id
            
            RequestDispatcher dispatcher;
            Connection con;

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");
            PreparedStatement asd = (PreparedStatement) con.prepareStatement("UPDATE students SET user_id = ? WHERE user_id = ?");
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("DELETE FROM users WHERE id = ?");

            asd.setInt(1, id);
            asd.setInt(2, idUser);
            
            int rowCount = asd.executeUpdate();
            
            pst.setInt(1, idUser);
            
            int rowCountUsers = pst.executeUpdate();

            dispatcher = request.getRequestDispatcher("admin.jsp");

            if (rowCountUsers > 0 && id != idUser) {
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
