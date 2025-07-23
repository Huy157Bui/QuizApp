/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.bnhh.quizapp;

import com.bnhh.pojo.Choice;
import com.bnhh.pojo.Question;
import com.bnhh.services.exam.ExamStrategy;
import com.bnhh.services.exam.ExamTypes;
import com.bnhh.services.exam.FixedExamStrategy;
import com.bnhh.services.exam.SpecificExamStrategy;
import com.bnhh.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
//state
public class ExamController implements Initializable {

    @FXML
    private ComboBox<ExamTypes> cbExamTypes;
    @FXML
    private TextField txtNum;
    @FXML
    private ListView<Question> lvQuestions;

    private ExamStrategy s;
    private List<Question> questions;
    private Map<Integer, Choice> results = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        this.txtNum.setVisible(false);

        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC) {
                this.txtNum.setVisible(true);
            } else {
                this.txtNum.setVisible(false);
            }
        });
    }

    public void handleStart(ActionEvent event) throws SQLException {
        if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC) {
            s = new SpecificExamStrategy(Integer.parseInt(this.txtNum.getText()));
        } else {
            s = new FixedExamStrategy();
        }
        questions = s.getQuestions();
        this.lvQuestions.setItems(FXCollections.observableArrayList(questions));

        this.lvQuestions.setCellFactory(params -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty);
                if (q == null || empty == true) {
                    this.setGraphic(null);
                } else {
                    VBox v = new VBox(5);
                    v.setStyle("-fx-border-width: 3; -fx-border-color: gray; -fx-padding: 5");

                    Text t = new Text(q.getContent());
                    v.getChildren().add(t);
                    ToggleGroup toggle = new ToggleGroup();
                    for (var c : q.getChoices()) {
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(toggle);

                        // update UI
                        if (results.get(q.getId()) == c) {
                            r.setSelected(true);
                        }

                        r.setOnAction(e -> {
                            results.put(q.getId(), c);
                        });

                        v.getChildren().add(r);
                    }

                    this.setGraphic(v);
                }
            }

        });
    }

    public void handleFinish(ActionEvent event) {
        if (!results.isEmpty()) {
            int count = 0;
            for (var c : results.values()) {
                if (c.isCorrect() == true) {
                    count++;
                }
            }
            MyAlert.getInstance().showMsg(String.format("Bạn làm đúng %d/%d", count, questions.size()), Alert.AlertType.INFORMATION);

        }
    }

    public void handleSave(ActionEvent event) {
        if (questions == null || questions.isEmpty()) {
            MyAlert.getInstance().showMsg("Khong co cau hoi gi de luu!", Alert.AlertType.WARNING);

        } else {
            try {
                s.saveExam(questions);
                MyAlert.getInstance().showMsg("Them cau hoi thanh cong");
            } catch (SQLException ex) {
                MyAlert.getInstance().showMsg("He thong bi loi, li do: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
}
