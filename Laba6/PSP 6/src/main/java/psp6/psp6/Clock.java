package psp6.psp6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Clock extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Scene sc = new Scene(getVbox(), 400, 500);
        stage.setTitle("Часы");
        stage.setScene(sc);
        stage.show();
    }

    private VBox getVbox(){
        Button start = new Button("Start");
        start.setPrefSize(80, 20);


        Button stop = new Button("Stop");
        stop.setPrefSize(80, 20);

        HBox hBox1 = new HBox(start, stop);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(5);

        TextField textField = new TextField("12:44:32");
        textField.setPrefWidth(120);

        Button alarm = new Button("Установить будильник");
        alarm.setPrefSize(120, 20);

        HBox hBox2 = new HBox(new Label("Будильник: "), textField);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(5);

        Label alarmLabel = new Label();
        ClockPane clock = new ClockPane();

        Timeline timeline = getTimeLine(clock);
        timeline.play();

        start.setOnMouseClicked(mouseEvent -> timeline.play());
        stop.setOnMouseClicked(mouseEvent -> timeline.pause() );
        alarm.setOnMouseClicked(mouseEvent -> {
            List<String> textList = List.of(textField.getText().split(":"));
            new Alarmread(textList).start();
        });

        VBox vBox = new VBox(hBox1, hBox2, alarmLabel, alarm, clock);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        return vBox;
    }

    private Timeline getTimeLine(ClockPane clock){
        EventHandler<ActionEvent> eHandler = e -> clock.setCurrent();

        Timeline anime = new Timeline(new KeyFrame(Duration.millis(1000),eHandler));
        anime.setCycleCount(Timeline.INDEFINITE);
        return anime;
    }
}

