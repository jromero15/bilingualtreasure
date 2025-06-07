package bilingualtreasure;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

/**
 * Gestiona y muestra la pantalla final del juego "The Bilingual Treasure".
 * Esta pantalla se presenta al jugador una vez que el juego ha terminado,
 * mostrando su rendimiento (respuestas correctas e incorrectas)
 * y ofreciendo opciones para volver a jugar o regresar a la pantalla de inicio.
 *
 * @author Grupo13_B04
 * @version 1.0
 * @since 2025-06-05
 * @see Puntaje
 * @see Palabra
 * @see Temporizador
 * @see PantallaJuego
 * @see BilingualTreasure
 */
public class PantallaFinal {

    /**
     * Inicializa y muestra la pantalla final del juego.
     * Esta pantalla resume el rendimiento del jugador (respuestas correctas e incorrectas)
     * y proporciona opciones de navegación, como "Jugar de nuevo" o "Volver al inicio".
     *
     * @param stage El {@link javafx.stage.Stage} principal de la aplicación JavaFX
     * donde se mostrará esta escena.
     * @param puntaje El objeto {@link Puntaje} que contiene la puntuación final del jugador,
     * incluyendo el número de respuestas correctas e incorrectas.
     * @param palabras La lista de {@link Palabra}s que se utilizaron en el juego.
     * Se pasa para poder reiniciar el juego si el usuario decide "Jugar de nuevo".
     * @param mainTemporizador El objeto {@link Temporizador} principal del juego.
     * Se pasa para mantener la referencia si se reinicia el juego.
     * @param idioma La cadena de texto que indica el idioma de juego seleccionado
     * ("ingles" o "espanol"). Necesario para reiniciar el juego con el mismo idioma.
     */
    void iniciar(Stage stage, Puntaje puntaje, List<Palabra> palabras, Temporizador mainTemporizador, String idioma) {

        // Cargar una imagen de fondo para la pantalla final desde los recursos.
        // La imagen se espera en '/images/fondo.png' dentro del JAR.
        Image fondoImage = new Image(getClass().getResourceAsStream("/images/fondo.png"));
        ImageView fondoImageView = new ImageView(fondoImage);
        fondoImageView.setFitWidth(500);
        fondoImageView.setFitHeight(400);

        /** Etiqueta que muestra el título de la pantalla final. */
        Label titulo = new Label("¡Juego terminado!");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        /** Etiqueta que muestra la cantidad de respuestas correctas obtenidas por el jugador. */
        Label buenasLabel = new Label("✅ Buenas: " + puntaje.getBuenas());
        buenasLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: green;");

        /** Etiqueta que muestra la cantidad de respuestas incorrectas del jugador. */
        Label malasLabel = new Label("❌ Malas: " + puntaje.getMalas());
        malasLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");

        /** Botón para que el jugador elija volver a jugar. */
        Button reiniciarBtn = new Button("Jugar de nuevo");
        /**
         * Define la acción a ejecutar cuando se presiona el botón "Jugar de nuevo".
         * Reinicia el puntaje, crea una nueva instancia de {@link PantallaJuego}
         * y la inicia con los parámetros actuales del juego.
         */
        reiniciarBtn.setOnAction(e -> {
            puntaje.Reset();  // Reiniciar el puntaje a cero
            PantallaJuego pantallaJuego = new PantallaJuego();  // Crear una nueva instancia de la pantalla de juego
            // Reinicia el juego pasando todas las dependencias necesarias.
            pantallaJuego.iniciar(stage, palabras, mainTemporizador, puntaje, this, idioma);
        });

        /** Botón para que el jugador elija volver a la pantalla de inicio. */
        Button inicioBtn = new Button("Volver al inicio");
        /**
         * Define la acción a ejecutar cuando se presiona el botón "Volver al inicio".
         * Reinicia el puntaje y redirige a la pantalla de inicio principal del juego.
         * Envuelve la lógica de inicio en un bloque try-catch para manejar posibles excepciones.
         */
        inicioBtn.setOnAction(e -> {
            puntaje.Reset();  // Reiniciar el puntaje a cero
            try {
                BilingualTreasure pantallaInicio = new BilingualTreasure();  // Crear una nueva instancia de la aplicación principal
                pantallaInicio.start(stage);  // Iniciar la aplicación principal desde cero
            } catch (Exception ex) {
                ex.printStackTrace();  // Imprimir el rastro de la pila en caso de error
            }
        });

        /**
         * Layout vertical que organiza el título, los resultados y los botones de opción.
         * Los elementos se centran y se aplica un padding.
         */
        VBox layout = new VBox(15, titulo, buenasLabel, malasLabel, reiniciarBtn, inicioBtn);
        layout.setAlignment(Pos.CENTER); // Centra los elementos horizontal y verticalmente dentro del VBox
        layout.setStyle("-fx-padding: 50;"); // Agrega espacio de relleno alrededor del VBox

        /**
         * Contenedor que apila la imagen de fondo ({@code fondoImageView})
         * detrás del layout de controles ({@code layout}), creando la escena visual final.
         */
        StackPane root = new StackPane(fondoImageView, layout);

        /**
         * La {@link javafx.scene.Scene} que representa la pantalla final del juego.
         * Define las dimensiones de la ventana.
         */
        Scene escenaFinal = new Scene(root, 500, 400);
        stage.setScene(escenaFinal); // Establece la escena en el Stage
        stage.show(); // Muestra la ventana con la escena final
    }
}