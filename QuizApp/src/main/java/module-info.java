module com.bnhh.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.bnhh.quizapp to javafx.fxml;
    exports com.bnhh.quizapp;
}
