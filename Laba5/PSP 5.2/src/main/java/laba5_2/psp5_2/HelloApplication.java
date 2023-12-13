package laba5_2.psp5_2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    Scene addScene;
    @Override
    public void start(Stage primaryStage) {
        creatAddScene();

        primaryStage.setTitle("Мини библиотека автобиография");
        primaryStage.setScene(addScene);
        primaryStage.show();
    }

    public void creatAddScene() {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setMaxSize(400, 500);

        TextField nameField = new TextField();
        HBox nameHBox = new HBox(new Label("ФИО: "), nameField);
        nameHBox.setAlignment(Pos.CENTER);
        nameHBox.setSpacing(5);

        RadioButton maleRadioButton = new RadioButton("Мужской");
        maleRadioButton.setSelected(true);
        RadioButton femaleRadioButton = new RadioButton("Женский");
        ToggleGroup radioButtonsGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(radioButtonsGroup);
        femaleRadioButton.setToggleGroup(radioButtonsGroup);
        HBox gender = new HBox(new Label("Пол: "), maleRadioButton, femaleRadioButton);
        gender.setAlignment(Pos.CENTER);
        gender.setSpacing(5);

        ComboBox<String> fieldOfActivityComboBox = new ComboBox<>(FXCollections.observableArrayList("Писатель", "Учёный", "Политик"));
        fieldOfActivityComboBox.setPrefSize(150,50);
        HBox activity = new HBox(new Label("Сфера\nдеятельности: "), fieldOfActivityComboBox);
        activity.setAlignment(Pos.CENTER);
        activity.setSpacing(5);


        CheckBox died = new CheckBox("Умер");
        CheckBox married = new CheckBox("Женат");
        CheckBox haveChildren = new CheckBox("Есть дети");
        VBox factCheckbox = new VBox(died, married, haveChildren);
        factCheckbox.setPrefSize(150, 50);
        HBox facts = new HBox(new Label("Факты: "), factCheckbox);
        facts.setAlignment(Pos.CENTER);
        facts.setSpacing(5);

        ListView<String> tegList = new ListView<>(FXCollections.observableArrayList("#тяжёлое детство", "#ранняя смерть", "#безответная любовь"));
        tegList.setPrefSize(150,80);
        Label label = new Label("Теги: ");
        label.setAlignment(Pos.CENTER_RIGHT);
        HBox tegHBox = new HBox(label, tegList);
        tegHBox.setAlignment(Pos.CENTER);
        tegHBox.setSpacing(5);

        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 150);
        HBox textHBox = new HBox(new Label("Автобиография"), textArea);
        textHBox.setAlignment(Pos.CENTER);
        textHBox.setSpacing(5);

        Button button = new Button("Сохранить");
        button.setAlignment(Pos.CENTER);
        button.setOnMouseClicked(mouseEvent -> {
            try(OutputStream fileStream = new BufferedOutputStream(new FileOutputStream("D:/Учёба/ПСП/Лабы/Laba5/PSP 5.2/src/main/resources/data.txt", true))) {
                fileStream.write(("ФИО: " + nameField.getText() + "\n").getBytes());

                String sGender = "Пол: ";
                if(maleRadioButton.isSelected()) sGender+="Мужской\n";
                else sGender+="Женский\n";
                fileStream.write(sGender.getBytes());

                fileStream.write(("Сфера деятельности: " + fieldOfActivityComboBox.getValue() + "\n").getBytes());

                String s = "";
                if(died.isSelected()) { s+= died.getText() + ";"; }
                if(married.isSelected()) { s+= married.getText() + ";"; }
                if(haveChildren.isSelected()) { s+= haveChildren.getText() + ";"; }
                fileStream.write(("Факты: " + s + "\n").getBytes());

                MultipleSelectionModel<String> selectionModel = tegList.getSelectionModel();
                fileStream.write(("Теги: " + (selectionModel.getSelectedItem() == null ? "" : selectionModel.getSelectedItem()) + "\n").getBytes());

                fileStream.write((textArea.getText() + "\n").getBytes());
                fileStream.write("//////////////////////".getBytes());

            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Ошибка");
                e.printStackTrace();
            }
        });


        vBox.getChildren().addAll(nameHBox, gender, activity, facts, tegHBox, textHBox, button);

        addScene = new Scene(vBox, 500, 700);
    }


}


