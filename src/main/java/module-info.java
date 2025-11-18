module CalculadoraIMC {
    requires javafx.controls;
    requires javafx.fxml;

    opens imcapp to javafx.fxml;
    exports imcapp;
}
