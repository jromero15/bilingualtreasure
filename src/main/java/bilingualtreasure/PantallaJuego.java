package bilingualtreasure;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

/**
 * Gestiona y muestra la pantalla principal del juego "The Bilingual Treasure".
 * Se encarga de mostrar las palabras a traducir, procesar las respuestas del jugador,
 * controlar el temporizador por pregunta y manejar la transición entre palabras
 * y a la pantalla final.
 *
 * @author Grupo13_B04
 * @version 1.0
 * @since 2025-06-05
 * @see Palabra
 * @see Puntaje
 * @see Temporizador
 * @see PantallaFinal
 */
public class PantallaJuego {

    /**
     * Índice que controla la {@link Palabra} actual que se está mostrando
     * de la {@link #palabras lista de palabras}.
     * Se incrementa con cada pregunta respondida o al agotarse el tiempo.
     */
    private int index = 0;
    /**
     * Almacena el idioma de juego seleccionado por el usuario ("espanol" o "ingles").
     * Define si la pregunta se muestra en inglés (pidiendo español) o viceversa.
     */
    private String idioma;
    /**
     * La lista de objetos {@link Palabra} que se utilizarán durante esta sesión de juego.
     * Contiene las palabras en inglés y español.
     */
    private List<Palabra> palabras;

    /**
     * Inicializa y configura la pantalla de juego.
     * Este método establece la interfaz de usuario, carga la primera palabra
     * y comienza el ciclo de juego, gestionando las dependencias necesarias.
     *
     * @param stage El {@link javafx.stage.Stage} principal de la aplicación JavaFX
     * donde se mostrará esta escena.
     * @param palabras La {@link List} de objetos {@link Palabra} que el jugador debe traducir.
     * @param mainTemporizador El objeto {@link Temporizador} principal del juego, usado para
     * controlar el tiempo por pregunta.
     * @param puntaje El objeto {@link Puntaje} para registrar las respuestas correctas e incorrectas.
     * @param pantallaFinal El objeto {@link PantallaFinal} al que se transicionará el juego
     * una vez que se agoten todas las palabras.
     * @param idioma La cadena que representa el idioma de juego seleccionado ("espanol" o "ingles").
     */
    public void iniciar(Stage stage, List<Palabra> palabras, Temporizador mainTemporizador, Puntaje puntaje, PantallaFinal pantallaFinal, String idioma) {
        this.idioma = idioma; // Asigna el idioma seleccionado
        this.palabras = palabras; // Asigna la lista de palabras para esta ronda de juego
        this.index = 0; // Reinicia el índice al iniciar una nueva partida

        // Carga la imagen de fondo específica para la pantalla de juego.
        // La imagen se espera en '/images/fondo_juego.png' dentro del JAR.
        Image fondoImage = new Image(getClass().getResourceAsStream("/images/fondo_juego.png"));
        ImageView fondoImageView = new ImageView(fondoImage);
        fondoImageView.setFitWidth(400);   // Ajusta el ancho de la imagen
        fondoImageView.setFitHeight(300);  // Ajusta el alto de la imagen

        /** Etiqueta que muestra la palabra a traducir o la pregunta. */
        Label pregunta = new Label();
        /** Campo de texto donde el jugador ingresa su respuesta. */
        TextField respuesta = new TextField();
        /** Botón para verificar la respuesta ingresada por el jugador. */
        Button verificarBtn = new Button("Verificar");
        /** Etiqueta que muestra el tiempo restante para responder la pregunta actual. */
        Label tiempoLabel = new Label("30"); // Inicializa el tiempo a 30 segundos

        // Inicia el ciclo de juego mostrando la primera palabra y configurando el temporizador.
        runSiguientePalabra(stage, palabras, pregunta, respuesta, verificarBtn, tiempoLabel, mainTemporizador, puntaje, pantallaFinal);

        /**
         * Define la acción a ejecutar cuando se presiona el botón "Verificar".
         * Detiene el temporizador, evalúa la respuesta del usuario (correcta/incorrecta),
         * actualiza el puntaje y avanza a la siguiente palabra o a la pantalla final.
         */
        verificarBtn.setOnAction(e -> {
            mainTemporizador.detener(); // Detiene el temporizador cuando el usuario responde
            String respuestaUsuario = respuesta.getText().trim(); // Obtiene la respuesta del jugador, eliminando espacios
            if (respuestaUsuario.equalsIgnoreCase(getRespuestaCorrecta())) {
                puntaje.Buena(); // Incrementa el contador de respuestas correctas
            } else {
                puntaje.Mala(); // Incrementa el contador de respuestas incorrectas
            }
            index++; // Pasa a la siguiente palabra en la lista
            // Llama recursivamente para procesar la siguiente palabra o finalizar el juego.
            runSiguientePalabra(stage, palabras, pregunta, respuesta, verificarBtn, tiempoLabel, mainTemporizador, puntaje, pantallaFinal);
        });

        /**
         * Layout vertical que organiza los elementos de la interfaz del juego:
         * la pregunta, el campo de respuesta, el botón de verificar y el temporizador.
         */
        VBox layout = new VBox(10, pregunta, respuesta, verificarBtn, tiempoLabel);
        layout.setAlignment(Pos.CENTER); // Centra los elementos dentro del VBox
        layout.setStyle("-fx-padding: 30; -fx-background-color: transparent;"); // Añade padding y hace el fondo transparente

        /**
         * Contenedor raíz que apila la imagen de fondo ({@code fondoImageView})
         * detrás del layout de controles ({@code layout}), creando la escena visual del juego.
         */
        StackPane root = new StackPane();
        root.getChildren().add(fondoImageView);  // Añade la imagen de fondo
        root.getChildren().add(layout);  // Añade los controles de la interfaz de usuario encima

        // Configura y muestra la escena del juego en el Stage.
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    /**
     * Gestiona el flujo de una pregunta individual en el juego.
     * Muestra la palabra actual, reinicia el temporizador y maneja la lógica
     * de transición a la siguiente palabra o a la pantalla final, incluyendo
     * el comportamiento al agotar el tiempo.
     *
     * @param stage El {@link javafx.stage.Stage} principal de la aplicación.
     * @param palabras La {@link List} de objetos {@link Palabra} para el juego.
     * @param pregunta La {@link Label} donde se muestra la palabra a traducir.
     * @param respuesta El {@link TextField} donde el jugador ingresa su respuesta.
     * @param verificarBtn El {@link Button} para verificar la respuesta.
     * @param tiempoLabel La {@link Label} que muestra el tiempo restante.
     * @param mainTemporizador El objeto {@link Temporizador} para controlar el tiempo.
     * @param puntaje El objeto {@link Puntaje} para actualizar la puntuación.
     * @param pantallaFinal El objeto {@link PantallaFinal} para transicionar al final del juego.
     */
    private void runSiguientePalabra(Stage stage, List<Palabra> palabras, Label pregunta, TextField respuesta, Button verificarBtn,
                                     Label tiempoLabel, Temporizador mainTemporizador, Puntaje puntaje, PantallaFinal pantallaFinal) {

        // Verifica si quedan más palabras en la lista.
        if (index < palabras.size()) {
            // Establece la pregunta basándose en el idioma seleccionado.
            if (idioma.equals("espanol")) {
                // Si el idioma de la interfaz es español, se pregunta la traducción al español
                // mostrando la palabra en inglés.
                pregunta.setText("Traduce esta palabra: " + palabras.get(index).getIngles());
            } else {
                // Si el idioma de la interfaz es inglés, se pregunta la traducción al inglés
                // mostrando la palabra en español.
                pregunta.setText("Translate this word: " + palabras.get(index).getEspanol());
            }
            pregunta.setStyle("-fx-font-size: 24px; -fx-text-fill: black; -fx-font-weight: bold;");

            respuesta.clear(); // Limpia el campo de respuesta para la nueva pregunta
            respuesta.requestFocus(); // Pone el foco en el campo de respuesta para que el usuario pueda escribir de inmediato

            // Inicia el temporizador para la pregunta actual.
            // Si el tiempo se agota, se ejecuta la acción definida en el lambda.
            mainTemporizador.iniciar(tiempoLabel, () -> {
                puntaje.Mala(); // Se cuenta como respuesta incorrecta si el tiempo se agota
                index++; // Avanza al siguiente índice de palabra
                // Llama recursivamente para la siguiente palabra o para finalizar el juego.
                runSiguientePalabra(stage, palabras, pregunta, respuesta, verificarBtn, tiempoLabel, mainTemporizador, puntaje, pantallaFinal);
            });
        } else {
            // Si todas las palabras han sido procesadas, detiene el temporizador y
            // transiciona a la pantalla final.
            mainTemporizador.detener(); // Asegura que el temporizador esté detenido
            // Pasa toda la información necesaria a la PantallaFinal para que muestre los resultados.
            pantallaFinal.iniciar(stage, puntaje, palabras, mainTemporizador, idioma);
        }
    }

    /**
     * Obtiene la traducción correcta de la palabra actual.
     * La respuesta correcta se determina basándose en el idioma de juego seleccionado
     * y el índice actual de la palabra en la lista.
     *
     * @return La cadena de texto que representa la respuesta correcta esperada
     * para la palabra actual, en el idioma configurado.
     */
    private String getRespuestaCorrecta() {
        Palabra palabra = palabras.get(index); // Obtiene el objeto Palabra actual
        if (idioma.equals("espanol")) {
            // Si el juego es en español, la respuesta correcta es la versión en español de la palabra inglesa mostrada.
            return palabra.getEspanol();
        } else {
            // Si el juego es en inglés, la respuesta correcta es la versión en inglés de la palabra española mostrada.
            return palabra.getIngles();
        }
    }
}