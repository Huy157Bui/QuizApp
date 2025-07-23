/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services;



import com.bnhh.utils.JbdcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public abstract class BaseServices<T>{
    public abstract PreparedStatement getStatement(Connection conn) throws SQLException;
    public abstract List<T> getResults(ResultSet rs) throws SQLException; 
    
    public List<T> list() throws SQLException{
        
        Connection conn = JbdcConnector.getInstance().connect();
        PreparedStatement stm = this.getStatement(conn);
        
        return this.getResults(stm.executeQuery());
    }
}
