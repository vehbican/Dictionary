module com.teamfour.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires VirtualizedFX;

    opens com.teamfour.dictionary to javafx.fxml;
    exports com.teamfour.dictionary;
}