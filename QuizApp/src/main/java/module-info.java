module com.bnhh.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires lombok;
    requires java.sql;

    
    opens com.bnhh.quizapp to javafx.fxml;
    exports com.bnhh.quizapp;
    exports com.bnhh.pojo;

}
