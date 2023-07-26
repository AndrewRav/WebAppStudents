package com.controller;

import com.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(name = "PesonalDataServlet", urlPatterns = {"/pesonalDataServlet"})
public class PesonalDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ArrayList<User> users = new ArrayList();

            String idString = request.getSession().getAttribute("id").toString();
            int id = Integer.parseInt(idString);
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            
            pst.setInt(1, id);
           
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("login"),
                        rs.getString("password"), rs.getString("last_name"), rs.getString("first_name"),
                        rs.getString("middle_name"), rs.getString("email"), rs.getString("status"));
                users.add(user);
            }
            
            RequestDispatcher rd;
            request.getSession().setAttribute("users", users);
            rd = getServletContext().getRequestDispatcher("/updatePersonalData.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
