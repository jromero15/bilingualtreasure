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

public class PantallaFinal {

	// Método que muestra la pantalla final cuando termina el juego
	void iniciar(Stage stage, Puntaje puntaje, List<Palabra> palabras, Temporizador mainTemporizador, String idioma) {

		// Cargar una imagen de fondo para la pantalla final desde los recursos
		Image fondoImage = new Image(getClass().getResourceAsStream("/images/fondo.png"));
		ImageView fondoImageView = new ImageView(fondoImage);
		fondoImageView.setFitWidth(500);
		fondoImageView.setFitHeight(400);

		// Crear una etiqueta con el título de la pantalla
		Label titulo = new Label("¡Juego terminado!");
		titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

		// Mostrar la cantidad de respuestas correctas (buenas) en verde
		Label buenasLabel = new Label("✅ Buenas: " + puntaje.getBuenas());
		buenasLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: green;");

		// Mostrar la cantidad de respuestas incorrectas (malas) en rojo
		Label malasLabel = new Label("❌ Malas: " + puntaje.getMalas());
		malasLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");

		// Crear el botón para volver a jugar
		Button reiniciarBtn = new Button("Jugar de nuevo");
		reiniciarBtn.setOnAction(e -> {
			puntaje.Reset();  // Reiniciar el puntaje
			PantallaJuego pantallaJuego = new PantallaJuego();  // Crear nueva pantalla de juego
			pantallaJuego.iniciar(stage, palabras, mainTemporizador, puntaje, this, idioma);  // Volver a iniciar el juego
		});

		// Crear el botón para volver al inicio
		Button inicioBtn = new Button("Volver al inicio");
		inicioBtn.setOnAction(e -> {
			puntaje.Reset();  // Reiniciar el puntaje
			try {
				BilingualTreasure pantallaInicio = new BilingualTreasure();  // Volver a la pantalla principal
				pantallaInicio.start(stage);  // Iniciar desde cero
			} catch (Exception ex) {
				ex.printStackTrace();  // Imprimir error en consola si ocurre
			}
		});

		// Crear el diseño vertical con los elementos
		VBox layout = new VBox(15, titulo, buenasLabel, malasLabel, reiniciarBtn, inicioBtn);
		layout.setAlignment(Pos.CENTER); // Centra los elementos
		layout.setStyle("-fx-padding: 50;"); // Agrega espacio alrededor

		// StackPane permite poner la imagen de fondo debajo del contenido
		StackPane root = new StackPane(fondoImageView, layout);

		// Crear la escena final con tamaño fijo
		Scene escenaFinal = new Scene(root, 500, 400);
		stage.setScene(escenaFinal); // Mostrar escena en la ventana
		stage.show(); // Mostrar ventana
	}
}
