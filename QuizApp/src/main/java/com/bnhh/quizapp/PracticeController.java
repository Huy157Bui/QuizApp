/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bnhh.quizapp;

import com.bnhh.pojo.Category;
import com.bnhh.pojo.Level;
import com.bnhh.pojo.Question;
import com.bnhh.services.FlyweightFactory;
import com.bnhh.services.questions.BaseQuestionServices;
import com.bnhh.services.questions.CategoryQuestionServicesDecorator;
import com.bnhh.services.questions.LevelQuestionServicesDecorator;
import com.bnhh.services.questions.LimitQuestionServicesDecorator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.bnhh.utils.Configs;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    @FXML
    private TextField txtNum;
    @FXML
    private VBox vboxChoices;
    @FXML
    private Text txtContent;
    @FXML
    private Text txtRusult;
    @FXML
    private ComboBox<Category> cbSearchCates;
    @FXML
    private ComboBox<com.bnhh.pojo.Level> cbSearchLevels;
    private List<Question> question;
    private int currentQuestion = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.cateService, "categories")));
            this.cbSearchLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "levels")));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handleStart(ActionEvent event) {
//        try {
//            BaseQuestionServices s = Configs.questionServices;
//            Category c = this.cbSearchCates.getSelectionModel().getSelectedItem();
//            if(c != null)
//                s = new CategoryQuestionServicesDecorator(s, c);
//            Level l = this.cbSearchLevels.getSelectionModel().getSelectedItem();
//            if(l != null)
//                s = new LevelQuestionServicesDecorator(s, l);
//            s = new LimitQuestionServicesDecorator(s, Integer.parseInt(this.txtNum.getText()));
//            this.question = s.list();
//            loadQuestion();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        System.out.println("== Bắt đầu luyện tập ==");

        try {
            String txt = this.txtNum.getText();
            int limit = Integer.parseInt(txt);
            System.out.println("Limit: " + limit);

            BaseQuestionServices s = Configs.questionServices;

            Category c = this.cbSearchCates.getSelectionModel().getSelectedItem();
            System.out.println("Selected Category: " + c);
            if (c != null) {
                s = new CategoryQuestionServicesDecorator(s, c);
            }

            Level l = this.cbSearchLevels.getSelectionModel().getSelectedItem();
            System.out.println("Selected Level: " + l);
            if (l != null) {
                s = new LevelQuestionServicesDecorator(s, l);
            }

            s = new LimitQuestionServicesDecorator(s, limit);

            this.question = s.list();
            System.out.println("Tìm thấy " + this.question.size() + " câu hỏi");

            if (this.question.isEmpty()) {
                this.txtRusult.setText("Không có câu hỏi phù hợp!");
                return;
            }

            loadQuestion();
        } catch (NumberFormatException ex) {
            this.txtRusult.setText("Vui lòng nhập số hợp lệ!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            this.txtRusult.setText("Lỗi truy vấn dữ liệu!");
        }
    }

    public void handleNext(ActionEvent event) {
        if (this.currentQuestion < this.question.size() - 1) {
            this.currentQuestion++;
            this.loadQuestion();
        }
    }

    public void handleCheck(ActionEvent event) {
        this.txtRusult.getStyleClass().removeAll("Correct", "Wrong");
        Question q = this.question.get(this.currentQuestion);
        for (int i = 0; i < q.getChoices().size(); i++) {
            if (q.getChoices().get(i).isCorrect()) {
                RadioButton rdo = (RadioButton) this.vboxChoices.getChildren().get(i);
                if (rdo.isSelected()) {
                    this.txtRusult.setText("Congratulations! Exactly!");
                    this.txtRusult.getStyleClass().add("Correct");
                } else {
                    this.txtRusult.setText("Sorry! Incorrect!");
                    this.txtRusult.getStyleClass().add("Wrong");
                }
                break;
            }
        }
    }

    private void loadQuestion() {
        Question q = this.question.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());

        this.vboxChoices.getChildren().clear();
        ToggleGroup t = new ToggleGroup();
        for (var c : q.getChoices()) {
            RadioButton r = new RadioButton(c.getContent());
            r.setToggleGroup(t);
            this.vboxChoices.getChildren().add(r);
        }
    }

}
