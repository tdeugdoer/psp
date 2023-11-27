module psp6.psp6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens psp6.psp6 to javafx.fxml;
    exports psp6.psp6;
}