package laba5_1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.stream.IntStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        add(borderPane);

        Scene scene = new Scene(borderPane, 600, 500);
        primaryStage.setTitle("Перенос строк");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void add(BorderPane borderPane) {
        HBox hBoxCheckbox = new HBox(10);
        hBoxCheckbox.setAlignment(Pos.CENTER);
        CheckBox checkBox1 = new CheckBox("Чётные");
        CheckBox checkBox2 = new CheckBox("Нечётные");
        hBoxCheckbox.getChildren().addAll(checkBox1, checkBox2);

        TextArea textArea = new TextArea("sdfsdfs\nfss\na");
        textArea.setMaxSize(300, 500);
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setMinSize(280,20);

        checkBox1.setOnMouseClicked(mouseEvent -> {
            changeChoiceBox(checkBox1, checkBox2, textArea, choiceBox);
        });

        checkBox2.setOnMouseClicked(mouseEvent -> {
            changeChoiceBox(checkBox1, checkBox2, textArea, choiceBox);
        });


        textArea.textProperty().addListener((observableValue, s, t1) -> {
            changeChoiceBox(checkBox1, checkBox2, textArea, choiceBox);
        });


//        textArea.setOnKeyPressed(keyEvent -> {
//            List<String> text = List.of(textArea.getText().split("\n"));
//            ObservableList<String> even = FXCollections.observableList(IntStream.range(0, text.size()).filter(i -> i % 2 == 1).mapToObj(text::get).toList());
//            ObservableList<String> odd = FXCollections.observableList(IntStream.range(0, text.size()).filter(i -> i % 2 == 0).mapToObj(text::get).toList());
//            ObservableList<String> even_odd = FXCollections.observableList(text);
//
//            if(checkBox1.isSelected() && !checkBox2.isSelected()){
//                choiceBox.setItems(even);
//            } else if (!checkBox1.isSelected() && checkBox2.isSelected()){
//                choiceBox.setItems(odd);
//            } else if (!checkBox1.isSelected() && !checkBox2.isSelected()){
//                choiceBox.setItems(FXCollections.observableList(new ArrayList<>()));
//            } else {
//                choiceBox.setItems(even_odd);
//            }
//            choiceBox.show();
//        });


        HBox hBox = new HBox();
        hBox.setSpacing(8);
        hBox.setPadding(new Insets(10));


        hBox.getChildren().addAll(textArea, choiceBox);

        borderPane.setTop(hBoxCheckbox);
        borderPane.setCenter(hBox);
    }

    private void changeChoiceBox(CheckBox checkBox1, CheckBox checkBox2, TextArea textArea, ChoiceBox<String> choiceBox) {
        List<String> text = List.of(textArea.getText().split("\n"));
        ObservableList<String> even = FXCollections.observableList(IntStream.range(0, text.size()).filter(i -> i % 2 == 1).mapToObj(text::get).toList());
        ObservableList<String> odd = FXCollections.observableList(IntStream.range(0, text.size()).filter(i -> i % 2 == 0).mapToObj(text::get).toList());
        ObservableList<String> even_odd = FXCollections.observableList(text);

        if(checkBox1.isSelected() && !checkBox2.isSelected()){
            choiceBox.setItems(even);
        } else if (!checkBox1.isSelected() && checkBox2.isSelected()){
            choiceBox.setItems(odd);
        } else if (!checkBox1.isSelected() && !checkBox2.isSelected()){
            choiceBox.setItems(FXCollections.observableList(new ArrayList<>()));
        } else {
            choiceBox.setItems(even_odd);
        }
        choiceBox.show();
    }
}
