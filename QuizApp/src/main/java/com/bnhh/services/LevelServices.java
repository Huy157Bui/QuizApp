/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services;

import com.bnhh.pojo.Level;
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
public class LevelServices extends BaseServices<Level> {

//    public List<Level> getLevels() throws SQLException {
//        Connection conn = JbdcConnector.getInstance().connect();
//
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM level");
//
//        List<Level> levels = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String name = rs.getString("name");
//            String note = rs.getString("note");
//
//            Level c = new Level(id, name, note);
//            levels.add(c);
//        }
//
//        return levels;
//    }

    @Override 
    public PreparedStatement getStatement(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM level");
    }

    @Override
    public List<Level> getResults(ResultSet rs) throws SQLException {
        List<Level> levels = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String note = rs.getString("note");

            Level c = new Level(id, name, note);
            levels.add(c);
        }

        return levels;
    }
}
