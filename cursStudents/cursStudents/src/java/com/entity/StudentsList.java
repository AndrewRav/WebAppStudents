package com.entity;

/**
 *
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentsList {
    private static Student[] students;

    public static Student[] getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useLegacyDatetimeCode=false", "root", "zxzx");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM students");
            ResultSet rs = pst.executeQuery();
          
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), 
                        rs.getInt(9), rs.getString(10));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
          return students;
    }

    public static Student[] getStudents() {
        return students;
    }

}
