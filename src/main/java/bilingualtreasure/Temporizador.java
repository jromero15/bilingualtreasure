package bilingualtreasure;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Gestiona un temporizador regresivo para el juego "The Bilingual Treasure".
 * Permite iniciar un conteo de tiempo para cada pregunta y ejecutar una acción
 * específica cuando el tiempo se agota. También ofrece la funcionalidad para detener
 * el temporizador en cualquier momento.
 *
 * @author Grupo13_B04
 * @version 1.0
 * @since 2025-06-05
 * @see javafx.animation.Timeline
 * @see javafx.scene.control.Label
 * @see javafx.util.Duration
 */
public class Temporizador {

    /** Almacena la instancia de {@link javafx.animation.Timeline} que controla el temporizador. */
    private Timeline timeline;

    /**
     * Inicia un temporizador regresivo de 30 segundos.
     * Actualiza un {@link javafx.scene.control.Label} cada segundo para mostrar el tiempo restante
     * y ejecuta una acción cuando el tiempo se agota.
     *
     * @param tiempoLabel El {@link javafx.scene.control.Label} de JavaFX donde se mostrará
     * el tiempo restante del temporizador. Su estilo se ajustará para que el número sea visible.
     * @param onTimeUp Un objeto {@link java.lang.Runnable} que contiene el código a ejecutar
     * cuando el temporizador llega a cero (tiempo agotado).
     */
    public void iniciar(Label tiempoLabel, Runnable onTimeUp) {
        // Aplica un estilo CSS al Label para el número del temporizador.
        tiempoLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: black; -fx-font-weight: bold;");

        // Configura el tiempo inicial del temporizador en segundos.
        final int tiempoInicial = 30;
        // Muestra el tiempo inicial en el Label proporcionado.
        tiempoLabel.setText(String.valueOf(tiempoInicial));

        // Crea una nueva instancia de Timeline para controlar la animación del temporizador.
        timeline = new Timeline(
                // Define un KeyFrame que se ejecuta cada segundo.
                new KeyFrame(Duration.seconds(1), e -> {
                    // Obtiene el tiempo restante actual, lo convierte a entero y lo decrementa.
                    int tiempoRestante = Integer.parseInt(tiempoLabel.getText()) - 1;
                    // Actualiza el texto del Label con el nuevo tiempo restante.
                    tiempoLabel.setText(String.valueOf(tiempoRestante));

                    // Verifica si el tiempo ha llegado a cero.
                    if (tiempoRestante == 0) {
                        timeline.stop(); // Detiene el temporizador para evitar más ejecuciones.
                        onTimeUp.run();  // Ejecuta la acción definida por el Runnable (ej. contar una respuesta mala).
                    }
                })
        );

        // Establece el número de ciclos que el Timeline debe ejecutar.
        // En este caso, igual al tiempo inicial para contar cada segundo hasta cero.
        timeline.setCycleCount(tiempoInicial);
        timeline.play(); // Inicia la ejecución del temporizador.
    }

    /**
     * Detiene el temporizador si está actualmente en ejecución.
     * Es crucial llamarlo cuando el jugador responde antes de que el tiempo se agote
     * o cuando el juego termina, para evitar ejecuciones no deseadas del {@code onTimeUp} Runnable.
     */
    public void detener() {
        // Verifica si el objeto Timeline ha sido inicializado antes de intentar detenerlo.
        if (timeline != null) {
            timeline.stop(); // Detiene la animación del temporizador.
        }
    }
}