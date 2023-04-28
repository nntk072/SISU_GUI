module fi.tuni.prog3.sisu {
    requires javafx.controls;
    requires com.google.gson;
    requires javafx.fxml;
    requires org.testfx.junit5;
    requires org.testfx;

    opens fi.tuni.prog3.sisu to javafx.fxml;
    opens org.junit.jupiter.api;
    exports fi.tuni.prog3.sisu;
}
