module com.lzw.algorithm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lzw.algorithm to javafx.fxml;
    exports com.lzw.algorithm;
}