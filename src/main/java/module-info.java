module com.teamfour.dictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires VirtualizedFX;
    requires org.apache.commons.collections4;

    opens com.teamfour.dictionary to javafx.fxml;
    exports com.teamfour.dictionary;
}