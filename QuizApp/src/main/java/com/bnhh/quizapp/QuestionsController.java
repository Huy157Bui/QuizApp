/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bnhh.quizapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // B1: Nap driver
            Class.forName("com.mysql.cj.jbdc.Driver");
            
                // B2: thiet lap ket noi
                Connection conn = DriverManager.getConnection("jbdc:mysql://localhost/quesdb", "root", "root");
            
        } catch (ClassNotFoundException ex |SQLException ex) {
            ex.printStackTrace();
        }
    }    
    
}
