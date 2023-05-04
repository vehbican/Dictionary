package com.teamfour.dictionary;

import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class WordCard extends AnchorPane{

    private ImageView flagImage;
    private ListView<Word> definitionsListView;


    public WordCard() {

        BackgroundFill b = new BackgroundFill(Config.background,CornerRadii.EMPTY, Insets.EMPTY);

        this.setBackground(new Background(b));

        definitionsListView = new ListView<>();
        definitionsListView.setStyle("-fx-control-inner-background: #2b2f32;-fx-background-insets: 0;");
        definitionsListView.setEditable(false);

        AnchorPane.setLeftAnchor(definitionsListView, 5d);
        AnchorPane.setRightAnchor(definitionsListView, 5d);
        AnchorPane.setTopAnchor(definitionsListView,5d);
        AnchorPane.setBottomAnchor(definitionsListView,5d);

        this.getChildren().add(definitionsListView);

    }

    public ImageView getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(ImageView flagImage) {
        this.flagImage = flagImage;
    }

    public ListView<Word> getDefinitionsListView() {
        return definitionsListView;
    }

    public void setDefinitionsListView(ListView<Word> definitionsListView) {
        this.definitionsListView = definitionsListView;
    }
}
