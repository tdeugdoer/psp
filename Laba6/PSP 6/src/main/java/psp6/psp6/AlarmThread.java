package psp6.psp6;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AlarmThread extends Thread{
    private int hour, min, sec;

    public AlarmThread(List<String> time){
        hour = Integer.parseInt(time.get(0));
        min = Integer.parseInt(time.get(1));
        sec = Integer.parseInt(time.get(2));
    }

    @Override
    public void run() {
        Media media = new Media(new File("D:/Учёба/ПСП/Лабы/Laba6/PSP 6/src/main/resources/Aljona_Apina_-_Den_rozhdeniya_54040505.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        while (true) {
            Calendar cr = new GregorianCalendar();
            if (hour == cr.get(Calendar.HOUR_OF_DAY) && min == cr.get(Calendar.MINUTE) && sec == cr.get(Calendar.SECOND)) {
                mediaPlayer.setAutoPlay(true);
                while (true){
                    mediaPlayer.setAutoPlay(true);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
