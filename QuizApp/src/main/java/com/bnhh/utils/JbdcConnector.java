/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class JbdcConnector {

    private static JbdcConnector instance;

    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JbdcConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JbdcConnector() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/quesdb", "root", "root");
        //this.conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "Huy12345@");

        //this.conn = DriverManager.getConnection("jdbc:mysql://localhost/quizdb", "root", "root");
    }

    public static JbdcConnector getInstance() throws SQLException {
        if (instance == null) {
            instance = new JbdcConnector();
        }
        return instance;
    }

    public Connection connect() {
        return this.conn;
    }

    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
