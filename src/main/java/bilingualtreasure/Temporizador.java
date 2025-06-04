package bilingualtreasure;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Temporizador {

    private Timeline timeline;  // Variable que almacena el temporizador.

     public void iniciar(Label tiempoLabel, Runnable onTimeUp) {
        // Aplica estilo al número del temporizador
        tiempoLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: black; -fx-font-weight: bold;");

        // Configura el tiempo inicial (30 segundos).
        int tiempoInicial = 30;
        tiempoLabel.setText(String.valueOf(tiempoInicial));  // Muestra el tiempo inicial en el Label.

        // Crea el Timeline que controla el temporizador.
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    // Actualiza el tiempo restante cada segundo.
                    int tiempoRestante = Integer.parseInt(tiempoLabel.getText()) - 1;
                    tiempoLabel.setText(String.valueOf(tiempoRestante));  // Actualiza el Label con el nuevo tiempo.

                    // Si el tiempo llega a 0, detiene el temporizador y ejecuta la acción de tiempo agotado.
                    if (tiempoRestante == 0) {
                        timeline.stop();  // Detiene el temporizador.
                        onTimeUp.run();   // Ejecuta la acción proporcionada
                    }
                })
        );

        // Establece que el temporizador se ejecute durante 30 segundos.
        timeline.setCycleCount(tiempoInicial);
        timeline.play();  // Inicia el temporizador.
    }
    public void detener() {
        // Verifica si el temporizador está en ejecución y lo detiene.
        if (timeline != null) {
            timeline.stop();
        }
    }
}
