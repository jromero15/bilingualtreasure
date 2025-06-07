package bilingualtreasure;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Representa la clase principal del juego "The Bilingual Treasure".
 * Esta clase extiende {@link javafx.application.Application} y es el punto de entrada
 * de la aplicación JavaFX. Se encarga de configurar y mostrar la pantalla de inicio
 * del juego, donde el usuario puede ingresar su nombre, seleccionar el idioma
 * y comenzar a jugar.
 *
 * @author Grupo13_B04
 * @version 1.0
 * @since 2025-06-05
 * @see Palabra
 * @see Puntaje
 * @see Temporizador
 * @see PantallaJuego
 * @see PantallaFinal
 */
public class BilingualTreasure extends Application {

    /**
     * Lista predefinida de objetos {@link Palabra} que representan las palabras
     * a adivinar en el juego (inglés-español).
     * Esta lista define el vocabulario inicial del juego.
     */
    List<Palabra> palabras = Arrays.asList(
            new Palabra("Apple", "Manzana"),
            new Palabra("Dog", "Perro"),
            new Palabra("Sun", "Sol"),
            new Palabra("House", "Casa"),
            new Palabra("Book", "Libro")
    );

    /**
     * Instancia de la clase {@link Puntaje} para gestionar la puntuación del jugador.
     */
    private Puntaje puntaje = new Puntaje();
    /**
     * Instancia de la clase {@link Temporizador} para controlar el tiempo límite
     * de cada ronda del juego.
     */
    private Temporizador mainTemporizador = new Temporizador();
    /**
     * Instancia de la clase {@link PantallaJuego} que gestiona la lógica y la interfaz
     * de la pantalla principal del juego.
     */
    private PantallaJuego pantallaJuego = new PantallaJuego();
    /**
     * Instancia de la clase {@link PantallaFinal} que gestiona la visualización
     * de los resultados al finalizar el juego.
     */
    private PantallaFinal pantallaFinal = new PantallaFinal();

    /**
     * Método que inicializa y configura la aplicación JavaFX.
     * Configura la interfaz de usuario de la pantalla de inicio del juego,
     * incluyendo el fondo, el título, el campo de entrada de nombre,
     * el selector de idioma y el botón para iniciar el juego.
     *
     * @param stage El {@link javafx.stage.Stage} principal de la aplicación JavaFX.
     * Es el contenedor de la ventana principal donde se mostrará la escena.
     */
    @Override
    public void start(Stage stage) {
        // Cargar imagen de fondo desde recursos
        // La imagen se espera en 'src/main/resources/images/fondo.png' en el JAR final.
        Image fondoImage = new Image(getClass().getResourceAsStream("/images/fondo.png"));
        ImageView fondoImageView = new ImageView(fondoImage);
        fondoImageView.setFitWidth(500);
        fondoImageView.setFitHeight(400);

        /** Etiqueta para el título principal del juego. */
        Label titulo = new Label("🎯 The Bilingual Treasure");
        titulo.setStyle("-fx-font-size: 24px; -fx-text-fill: black; -fx-font-weight: bold;");

        /** Campo de texto para que el jugador ingrese su nombre. */
        TextField inputNombre = new TextField();
        inputNombre.setPromptText("Ingresa tu nombre");

        /** ComboBox para que el jugador seleccione el idioma de juego. */
        ComboBox<String> idiomaSelect = new ComboBox<>();
        idiomaSelect.getItems().addAll("Español", "Inglés");
        idiomaSelect.setValue("Inglés"); // Selección por defecto

        /**
         * Referencia atómica para almacenar el idioma seleccionado por el usuario.
         * Se usa AtomicReference para permitir su modificación dentro de una expresión lambda
         * que capture una variable efectivamente final. Por defecto, el idioma es "ingles".
         */
        AtomicReference<String> idiomaSeleccionado = new AtomicReference<>("ingles");

        /**
         * Define la acción a ejecutar cuando el valor del ComboBox de idioma cambia.
         * Actualiza la variable {@code idiomaSeleccionado} a "espanol" o "ingles"
         * según la selección del usuario y muestra la selección por consola.
         */
        idiomaSelect.setOnAction(e -> {
            String seleccion = idiomaSelect.getValue();
            if (seleccion.equals("Español")) {
                idiomaSeleccionado.set("espanol");
                System.out.println("Idioma seleccionado: Español");
            } else {
                idiomaSeleccionado.set("ingles");
                System.out.println("Idioma seleccionado: Inglés");
            }
        });

        /** Botón para comenzar el juego. */
        Button comenzar = new Button("Comenzar");
        /**
         * Define la acción a ejecutar cuando se presiona el botón "Comenzar".
         * Obtiene el nombre del jugador, lo asigna al objeto {@link Puntaje},
         * y si el nombre no está vacío, inicia la {@link PantallaJuego} principal.
         */
        comenzar.setOnAction(e -> {
            String nombre = inputNombre.getText().trim(); // Obtener nombre del jugador
            puntaje.setJugador(nombre); // Asignar el nombre al objeto puntaje

            // Solo inicia el juego si el nombre no está vacío
            if (!nombre.isEmpty()) {
                // Inicia la pantalla de juego con todas las dependencias necesarias.
                pantallaJuego.iniciar(stage, palabras, mainTemporizador, puntaje, pantallaFinal, idiomaSeleccionado.get());
            }
        });

        /**
         * Layout principal de la pantalla de inicio, organiza verticalmente
         * el título, el campo de nombre, el selector de idioma y el botón de comenzar.
         * Aplica un padding y centra los elementos.
         */
        VBox layout = new VBox(15, titulo, inputNombre, idiomaSelect, comenzar);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");

        /**
         * Contenedor raíz que apila la imagen de fondo detrás y el layout de controles
         * encima, creando la escena principal de la aplicación.
         */
        StackPane root = new StackPane(fondoImageView, layout);

        /**
         * La {@link javafx.scene.Scene} inicial que se muestra en el {@link Stage}.
         * Define las dimensiones de la ventana de inicio.
         */
        Scene escenaInicial = new Scene(root, 500, 400);
        stage.setScene(escenaInicial);
        stage.setTitle("The Bilingual Treasure"); // Título de la ventana de la aplicación
        stage.show(); // Muestra la ventana
    }

    /**
     * Método principal para lanzar la aplicación JavaFX.
     * Este método es el punto de entrada estándar para las aplicaciones Java,
     * el cual llama al método {@code launch()} de {@link javafx.application.Application}
     * para inicializar y comenzar la aplicación.
     *
     * @param args Argumentos de la línea de comandos pasados a la aplicación.
     */
    public static void main(String[] args) {
        launch(args);
    }
}