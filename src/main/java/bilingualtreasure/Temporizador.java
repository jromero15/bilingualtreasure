package bilingualtreasure;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Temporizador {
    private Timeline timeline;
    private int segundosRestantes;

    public void iniciar(Label temporizadorLabel, Runnable onTiempoTerminado) {
        if (timeline != null) {
            timeline.stop();
        }

        segundosRestantes = 30;
        temporizadorLabel.setText(String.valueOf(segundosRestantes));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            segundosRestantes--;
            temporizadorLabel.setText(String.valueOf(segundosRestantes));

            if (segundosRestantes <= 0) {
                timeline.stop();
                onTiempoTerminado.run();
            }
        }));

        timeline.setCycleCount(30);
        timeline.play();
    }

    public void detener() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}
