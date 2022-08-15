package com.example.Avooto.repositories;

import com.example.Avooto.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
@Service
//public class ProductJDBC {
//
//    static final String DB_URL = "jdbc:mysql://localhost/avooto";
//    static final String USER = "root";
//    static final String PASS = "!Hhh787898";
//
//    public ResultSet searchingByWord(String title) {
//        ResultSet rs = null;
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             Statement stmt = conn.createStatement();) {
//                    String sql = "SELECT title FROM products" +
//                    " WHERE title LIKE '%" + title + "%'";
//                     rs = stmt.executeQuery(sql);
//                     while (rs.next()) {
//                         rs.getString("title");
//                     }
//                     rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rs;
//    }
//}

public class ProductJDBC {

    static final String DB_URL = "jdbc:mysql://localhost/avooto";
    static final String USER = "root";
    static final String PASS = "!Hhh787898";

    public List<Product> searchingByWord(String title) {
        ResultSet rs = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();) {
            String sql = "SELECT * FROM products" +
                    " WHERE title LIKE '%" + title + "%'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rs.getString("title");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}