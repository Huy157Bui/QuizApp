package com.bnhh.quizapp;

import com.bnhh.pojo.Category;
import com.bnhh.pojo.Choice;
import com.bnhh.pojo.Level;
import com.bnhh.pojo.Question;
import com.bnhh.services.CategoryServices;
import com.bnhh.services.LevelServices;
import com.bnhh.services.QuestionServices;
import com.bnhh.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionsController implements Initializable {

    @FXML
    private TextArea txtContent;
    @FXML
    private ComboBox<Category> cbCates;
    @FXML
    private ComboBox<Level> cbLevels;
    @FXML
    private TableView<Question> tbQuestions;
    @FXML
    private VBox vboxChoices;
    @FXML
    private TextField txtSearch;
    @FXML
    private ToggleGroup toggleChoice;

    private final static CategoryServices cateService = new CategoryServices();
    private final static LevelServices levelServices = new LevelServices();
    private final static QuestionServices questionServices = new QuestionServices();

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
            this.cbCates.setItems(FXCollections.observableList(cateService.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));

            this.loadColumns();
            //this.tbQuestions.setItems(FXCollections.observableList(questionServices.getQuestion()));
            this.loadQuestions(questionServices.getQuestion());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.txtSearch.textProperty().addListener((e) -> {
            try {
                this.loadQuestions(questionServices.getQuestions(this.txtSearch.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
    }

    public void addChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");

        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);

        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        h.getChildren().addAll(r, txt);

        this.vboxChoices.getChildren().add(h);
    }

    public void addQuestion(ActionEvent event) {
        try {
            Question.Builder b = new Question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());
            for (var c : this.vboxChoices.getChildren()) {
                HBox h = (HBox) c;

                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                        ((RadioButton) h.getChildren().get(0)).isSelected());
            }
//            Question q = b.build();
            questionServices.addQuestion(b.build());
            MyAlert.getInstance().showMsg("Them cau hoi thanh cong!!!");

            // bo sung
            this.tbQuestions.getItems().add(0, b.build());
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Them cau hoi that bai!!!");
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Du lieu khong hop le!!!");
        }
    }

    private void loadColumns() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);

        TableColumn colContent = new TableColumn("Noi dung cau hoi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((e) -> {
            TableCell cell = new TableCell();

            Button btn = new Button("Xoa");
            btn.setOnAction((envent) -> {
                Optional<ButtonType> t = MyAlert.getInstance().showMsg("Xoa cau hoi thi cac lua chon cung bi xoa theo. Ban co chac chan khong?",
                        Alert.AlertType.CONFIRMATION);
                if (t.isPresent() && t.get().equals(ButtonType.OK)) {
                    Question q = (Question) cell.getTableRow().getItem();
                    try {
                        questionServices.deleteQuestion(q.getId());

                        this.tbQuestions.getItems().remove(q);
                        MyAlert.getInstance().showMsg("Xoa thanh cong");

                    } catch (SQLException ex) {
                        MyAlert.getInstance().showMsg("Xoa that bai", Alert.AlertType.WARNING);
                    }
                }
            });

            cell.setGraphic(btn);

            return cell;
        });

        this.tbQuestions.getColumns().addAll(colId, colContent, colAction);
    }

    private void loadQuestions(List<Question> questions) {
        this.tbQuestions.setItems(FXCollections.observableList(questions));

    }
}
