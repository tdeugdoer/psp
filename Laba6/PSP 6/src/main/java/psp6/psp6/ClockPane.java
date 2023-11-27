package psp6.psp6;

import java.util.*;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class ClockPane extends Pane {
    private final Font font = Font.font("Arial Rounded MT Bold", 20);

    private int hour, minute, second;

    public ClockPane() {
        setCurrent();
        writeClock();
    }

    public void setCurrent() {
        Calendar cr = new GregorianCalendar();
        this.hour = cr.get(Calendar.HOUR_OF_DAY);
        this.minute = cr.get(Calendar.MINUTE);
        this.second = cr.get(Calendar.SECOND);
        writeClock();
    }

    public Map<String, Integer> getTime() {
        return Map.of(
                "hour", hour,
                "minute", minute,
                "second", second
        );
    }

    private void writeClock() {
        double radius = 100;
        double coorX = 200.0;
        double coorY = 200.0;

        Circle circ = new Circle(coorX, coorY, radius);
        circ.setFill(Color.BEIGE);
        circ.setStroke(Color.BLACK);
        circ.setStrokeWidth(3);

        Text t1 = new Text(coorX - 12, coorY - radius + 24, "12");
        Text t2 = new Text(coorX - radius + 15, coorY + 5, "9");
        Text t3 = new Text(coorX - 6, coorY + radius - 15, "6");
        Text t4 = new Text(coorX + radius - 22, coorY + 6, "3");

        t1.setFont(font); t2.setFont(font); t3.setFont(font); t4.setFont(font);

        double sl = radius * 0.65;
        double ml = radius * 0.5;
        double hl = radius * 0.35;

        double sx = coorX + sl * Math.sin(second * (2 * Math.PI / 60));
        double mx = coorX + ml * Math.sin(minute * (2 * Math.PI / 60));
        double hx = coorX + hl * Math.sin((hour % 12 + minute / 60) * (2 * Math.PI / 12));

        double sy = coorY - sl * Math.cos(second * (2 * Math.PI / 60));
        double my = coorY - ml * Math.cos(minute * (2 * Math.PI / 60));
        double hy = coorY - hl * Math.cos((hour % 12 + minute / 60) * (2 * Math.PI / 12));

        Line sli = new Line(coorX, coorY, sx, sy);
        Line mli = new Line(coorX, coorY, mx, my);
        Line hli = new Line(coorX, coorY, hx, hy);

        sli.setStroke(Color.BLACK);
        mli.setStroke(Color.BLUE);
        hli.setStroke(Color.RED);

        getChildren().clear();
        getChildren().addAll(circ, t1,t2,t3,t4, sli,mli,hli);
    }

}