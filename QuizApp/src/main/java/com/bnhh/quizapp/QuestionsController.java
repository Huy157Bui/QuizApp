package com.bnhh.quizapp;

import com.bnhh.pojo.Category;
import com.bnhh.services.CategoryServices;
import com.bnhh.utils.JbdcConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionsController implements Initializable {

    @FXML
    private ComboBox<Category> cbCates;

    
    private static CategoryServices cateService = new CategoryServices();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // B1: Nạp driver
            //Class.forName("com.mysql.cj.jdbc.Driver");

//            // B2: Thiết lập kết nối
//            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quesdb", "root", "root");
//            Connection conn = JbdcConnector.getInstance().connect();
//            
//            
//            // B3: Xử lý truy vấn
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM category");
//
//            List<Category> cates = new ArrayList<>();
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//
//                Category c = new Category(id, name);
//                cates.add(c);
//            }
//
//            // B4: Đóng kết nối
//            conn.close();

            // Đưa dữ liệu lên ComboBox
            this.cbCates.setItems(FXCollections.observableArrayList(cateService.getCates()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
