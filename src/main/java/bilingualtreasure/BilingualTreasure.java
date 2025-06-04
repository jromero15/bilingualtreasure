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
 * Clase principal del juego "The Bilingual Treasure".
 * Esta clase muestra la pantalla de inicio y permite comenzar el juego.
 */
public class BilingualTreasure extends Application {

	// Lista de palabras usadas en el juego (inglés-español)
	List<Palabra> palabras = Arrays.asList(
			new Palabra("Apple", "Manzana"),
			new Palabra("Dog", "Perro"),
			new Palabra("Sun", "Sol"),
			new Palabra("House", "Casa"),
			new Palabra("Book", "Libro")
	);

	// Instancias de clases necesarias para el juego
	private Puntaje puntaje = new Puntaje();
	private Temporizador mainTemporizador = new Temporizador();
	private PantallaJuego pantallaJuego = new PantallaJuego();
	private PantallaFinal pantallaFinal = new PantallaFinal();

	/**
	 * Método que se ejecuta al iniciar la aplicación JavaFX.
	 */
	@Override
	public void start(Stage stage) {
// Cargar imagen de fondo desde recursos
		Image fondoImage = new Image(getClass().getResourceAsStream("/images/fondo.png"));
		ImageView fondoImageView = new ImageView(fondoImage);
		fondoImageView.setFitWidth(500);
		fondoImageView.setFitHeight(400);

		// Título del juego
		Label titulo = new Label("🎯 The Bilingual Treasure");
		titulo.setStyle("-fx-font-size: 24px; -fx-text-fill: black; -fx-font-weight: bold;");

		// Campo de texto para ingresar el nombre del jugador
		TextField inputNombre = new TextField();
		inputNombre.setPromptText("Ingresa tu nombre");

		// ComboBox para seleccionar el idioma del juego
		ComboBox<String> idiomaSelect = new ComboBox<>();
		idiomaSelect.getItems().addAll("Español", "Inglés");
		idiomaSelect.setValue("Inglés"); // Selección por defecto

		// Variable para almacenar la selección del idioma
		AtomicReference<String> idiomaSeleccionado = new AtomicReference<>("ingles");

		// Evento para actualizar el idioma seleccionado
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

		// Botón para comenzar el juego
		Button comenzar = new Button("Comenzar");
		comenzar.setOnAction(e -> {
			String nombre = inputNombre.getText().trim(); // Obtener nombre del jugador
			puntaje.setJugador(nombre); // Asignar el nombre al objeto puntaje

			// Solo inicia el juego si el nombre no está vacío
			if (!nombre.isEmpty()) {
				pantallaJuego.iniciar(stage, palabras, mainTemporizador, puntaje, pantallaFinal, idiomaSeleccionado.get());
			}
		});

		// Layout que contiene todos los controles del menú de inicio
		VBox layout = new VBox(15, titulo, inputNombre, idiomaSelect, comenzar);
		layout.setStyle("-fx-padding: 30; -fx-alignment: center;");

		// Contenedor que pone el fondo detrás y el contenido encima
		StackPane root = new StackPane(fondoImageView, layout);

		// Crear y mostrar la escena principal
		Scene escenaInicial = new Scene(root, 500, 400);
		stage.setScene(escenaInicial);
		stage.setTitle("The Bilingual Treasure");
		stage.show();
	}

	//Método principal para lanzar la aplicación.
	public static void main(String[] args) {
		launch(args);
	}
}