package com.teamfour.dictionary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class WordCard extends AnchorPane{

    //private AnchorPane card;
    private ListView<AnchorPane> parent;
    private HBox hBox;
    private VBox vBox;
    private ImageView flagImage;
    private Text label_def;
    private Text label_synonyms;
    private ListView<String> definitionsListView;
    private ListView<String> synonymsListView;


    public WordCard(ListView<AnchorPane> parent) {

        this.parent = parent;
        this.setViewOrder(10);
        parent.setViewOrder(9);
        this.setPrefSize(50,500);

        BackgroundFill b = new BackgroundFill(Config.background,CornerRadii.EMPTY, Insets.EMPTY);
        BackgroundFill transparent = new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY);

        this.setBackground(new Background(b));

        hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setLeftAnchor(hBox, 5d);
        AnchorPane.setRightAnchor(hBox, 5d);
        AnchorPane.setTopAnchor(hBox,5d);
        hBox.setFillHeight(true);
        hBox.setPrefWidth(200);
        hBox.setPrefHeight(50);
        hBox.setBackground(new Background(b));
        //hBox.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderStroke.THICK,Insets.EMPTY)));
        hBox.setStyle("-fx-border-style : solid hidden solid hidden; -fx-border-color : white; -fx-border-width: 2");

        flagImage = new ImageView();
        flagImage.setFitWidth(70d);
        flagImage.setFitHeight(70d);
        HBox.setMargin(flagImage,new Insets(10,5,10,5));
        hBox.getChildren().add(flagImage);
        hBox.setBackground(new Background(transparent));

        vBox = new VBox();
        vBox.setViewOrder(3);
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.TOP_CENTER);
        AnchorPane.setLeftAnchor(vBox, 5d);
        AnchorPane.setRightAnchor(vBox, 5d);
        AnchorPane.setTopAnchor(vBox,125d);
        AnchorPane.setBottomAnchor(vBox,5d);
        vBox.setFillWidth(true);
        vBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
        vBox.setPrefHeight(Region.USE_COMPUTED_SIZE);


        label_def = new Text();
        label_def.setText("Definitions");
        label_def.setFill(Color.WHITE);
        label_def.setTextAlignment(TextAlignment.LEFT);
        label_def.setWrappingWidth(parent.getWidth());
        label_def.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,15));

        HBox hBox1 = new HBox();
        hBox1.getChildren().add(label_def);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        //hBox1.setStyle("-fx-border-style : hidden hidden solid hidden; -fx-border-color : white;");

        definitionsListView = new ListView<>();
        definitionsListView.setEditable(false);

        vBox.getChildren().add(hBox1);
        vBox.getChildren().add(definitionsListView);
        label_def.setViewOrder(15);
        definitionsListView.setViewOrder(2);

        label_synonyms = new Text();
        label_synonyms.setText("Synonyms");
        label_synonyms.setFill(Color.WHITE);
        label_synonyms.setTextAlignment(TextAlignment.LEFT);
        label_synonyms.setWrappingWidth(parent.getWidth());
        label_synonyms.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,15));

        HBox hBox2 = new HBox();
        hBox2.getChildren().add(label_synonyms);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        //hBox2.setStyle("-fx-border-style : hidden hidden solid hidden; -fx-border-color : white;");

        synonymsListView = new ListView<>();


        vBox.getChildren().add(hBox2);
        vBox.getChildren().add(synonymsListView);
        label_synonyms.setViewOrder(2);
        synonymsListView.setViewOrder(2);


        this.getChildren().add(hBox);
        this.getChildren().add(vBox);

    }

    public ImageView getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(ImageView flagImage) {
        this.flagImage = flagImage;
    }

    public ListView<String> getDefinitionsListView() {
        return definitionsListView;
    }

    public void setDefinitionsListView(ListView<String> definitionsListView) {
        this.definitionsListView = definitionsListView;
    }

    public ListView<String> getSynonymsListView() {
        return synonymsListView;
    }

    public void setSynonymsListView(ListView<String> synonymsListView) {
        this.synonymsListView = synonymsListView;
    }
}
