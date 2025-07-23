package com.bnhh.quizapp;

import com.bnhh.pojo.Category;
import com.bnhh.pojo.Level;
import com.bnhh.utils.theme.DefaultFactory;
import com.bnhh.utils.theme.Theme;
import static com.bnhh.utils.theme.Theme.DEFAULT;
import com.bnhh.utils.MyAlert;
import com.bnhh.utils.MyStage;
import com.bnhh.utils.theme.DarkFactory;
import com.bnhh.utils.theme.LightFactory;
import com.bnhh.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    @FXML private ComboBox<Theme> cbTheme;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbTheme.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    
    public void changeTheme(ActionEvent event){
        this.cbTheme.getSelectionModel().getSelectedItem().updateTheme(this.cbTheme.getScene());
//        switch(this.cbTheme.getSelectionModel().getSelectedItem()){
//            case DEFAULT:
//                ThemeManager.setThemeFactory(new DefaultFactory());
//                ThemeManager.applyTheme(this.cbTheme.getScene());
////                this.cbTheme.getScene().getRoot().getStylesheets().clear();
////                this.cbTheme.getScene().getRoot().getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
//                break;
//            case DARK:
//                ThemeManager.setThemeFactory(new DarkFactory());
//                ThemeManager.applyTheme(this.cbTheme.getScene());
////                this.cbTheme.getScene().getRoot().getStylesheets().clear();
////                this.cbTheme.getScene().getRoot().getStylesheets().add(App.class.getResource("Dark.css").toExternalForm());
//                break;
//            case LIGHT:
//                ThemeManager.setThemeFactory(new LightFactory());
//                ThemeManager.applyTheme(this.cbTheme.getScene());
////                this.cbTheme.getScene().getRoot().getStylesheets().clear();
////                this.cbTheme.getScene().getRoot().getStylesheets().add(App.class.getResource("Light.css").toExternalForm());
//                break;
//        }
    }
    
    public void handleQuestManagement(ActionEvent event) throws IOException{
        
        MyStage.getInstance().showStage("questions.fxml");
                
                
//        Scene scene = new Scene(new FXMLLoader(App.class.getResource("questions.fxml")).load());
//        
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.setTitle("Quiz App");
//        stage.show();
    }
    public void handlePractice(ActionEvent event) throws IOException{
         MyStage.getInstance().showStage("practice.fxml");
    }
    public void handleExam(ActionEvent event) {
        MyAlert.getInstance().showMsg("Comming soon...");
    }
    
    public void handleRegister(ActionEvent event) {
        MyAlert.getInstance().showMsg("Comming soon...");
    }
    
    public void handleLogin(ActionEvent event) {
        MyAlert.getInstance().showMsg("Comming soon...");
    }  
}
