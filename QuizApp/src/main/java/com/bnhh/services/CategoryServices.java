/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services;

import com.bnhh.pojo.Category;
import com.bnhh.utils.JbdcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CategoryServices extends BaseServices<Category> {

//    public List<Category> getCates() throws SQLException {
//        // B2: Thiết lập kết nối
//        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quesdb", "root", "root");
//        Connection conn = JbdcConnector.getInstance().connect();
//
//        // B3: Xử lý truy vấn
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM category");
//
//        List<Category> cates = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//
//            Category c = new Category(id, name);
//            cates.add(c);
//        }
//
//        // B4: Đóng kết nối
//        return cates;
//    }

    @Override
    public PreparedStatement getStatement(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM category");
    }

    @Override
    public List<Category> getResults(ResultSet rs) throws SQLException {
        List<Category> cates = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Category c = new Category(id, name);
            cates.add(c);
        }
        return cates;
    }
}
