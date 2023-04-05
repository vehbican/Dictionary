module com.teamfour.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires VirtualizedFX;
    requires org.apache.commons.collections4;
    requires com.google.gson;

    opens com.teamfour.dictionary to javafx.fxml,com.google.gson;
    exports com.teamfour.dictionary;
}