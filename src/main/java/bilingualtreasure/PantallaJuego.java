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

public class PantallaJuego {

	private int index = 0; // Para controlar el índice de la palabra actual en la lista
	private String idioma; // Idioma seleccionado (Español o Inglés)
	private List<Palabra> palabras; // Lista de palabras que se usan en el juego

	// Constructor modificado para aceptar palabras y el idioma
	public void iniciar(Stage stage, List<Palabra> palabras, Temporizador mainTemporizador, Puntaje puntaje, PantallaFinal pantallaFinal, String idioma) {
		this.idioma = idioma; // Asigna el idioma seleccionado
		this.palabras = palabras; // Asigna la lista de palabras

		Image fondoImage = new Image(getClass().getResourceAsStream("/images/fondo_juego.png"));
		ImageView fondoImageView = new ImageView(fondoImage);
		fondoImageView.setFitWidth(400);  // Ajusta el tamaño de la imagen
		fondoImageView.setFitHeight(300);

		// Crear controles: pregunta, campo de respuesta y temporizador
		Label pregunta = new Label();
		TextField respuesta = new TextField();
		Button verificarBtn = new Button("Verificar");
		Label tiempoLabel = new Label("30");

		// Mostrar la primera palabra y preparar la transición a la siguiente
		runSiguientePalabra(stage, palabras, pregunta, respuesta, verificarBtn, tiempoLabel, mainTemporizador, puntaje, pantallaFinal);

		// Botón de verificación: al hacer clic, se detiene el temporizador y se verifica la respuesta
		verificarBtn.setOnAction(e -> {
			mainTemporizador.detener(); // Detiene el temporizador al responder
			String respuestaUsuario = respuesta.getText().trim(); // Obtiene la respuesta del jugador
			if (respuestaUsuario.equalsIgnoreCase(getRespuestaCorrecta())) {
				puntaje.Buena(); // Si es correcta, incrementa las buenas
			} else {
				puntaje.Mala(); // Si es incorrecta, incrementa las malas
			}
			index++; // Aumenta el índice para pasar a la siguiente palabra
			runSiguientePalabra(stage, palabras, pregunta, respuesta, verificarBtn, tiempoLabel, mainTemporizador, puntaje, pantallaFinal);
		});

		// Diseño de la interfaz del juego
		VBox layout = new VBox(10, pregunta, respuesta, verificarBtn, tiempoLabel);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-padding: 30; -fx-background-color: transparent;");

		// Usar StackPane para poner la imagen de fondo y los controles encima
		StackPane root = new StackPane();
		root.getChildren().add(fondoImageView);  // Añadir la imagen de fondo
		root.getChildren().add(layout);  // Añadir los controles encima de la imagen

		// Configura la escena del juego
		stage.setScene(new Scene(root, 400, 300));
		stage.show();
	}

	// Método que maneja la transición entre palabras, respuestas y tiempo
	private void runSiguientePalabra(Stage stage, List<Palabra> palabras, Label pregunta, TextField respuesta, Button verificarBtn,
									 Label tiempoLabel, Temporizador mainTemporizador, Puntaje puntaje, PantallaFinal pantallaFinal) {

		if (index < palabras.size()) {
			// Muestra la pregunta según el idioma seleccionado
			if (idioma.equals("espanol")) {
				pregunta.setText("Traduce esta palabra: " + palabras.get(index).getIngles());
			} else {
				pregunta.setText("Translate this word: " + palabras.get(index).getEspanol());
			}
			pregunta.setStyle("-fx-font-size: 24px; -fx-text-fill: black; -fx-font-weight: bold;");

			respuesta.clear(); // Limpia el campo de respuesta

			// Inicia el temporizador para esta pregunta
			mainTemporizador.iniciar(tiempoLabel, () -> {
				puntaje.Mala(); // Si el tiempo se agota, se cuenta como mala
				index++; // Aumenta el índice para la siguiente palabra
				runSiguientePalabra(stage, palabras, pregunta, respuesta, verificarBtn, tiempoLabel, mainTemporizador, puntaje, pantallaFinal);
			});
		} else {
			// Si no hay más palabras, muestra la pantalla final
			mainTemporizador.detener();
			pantallaFinal.iniciar(stage, puntaje, palabras, mainTemporizador, idioma); // Pasa la información necesaria para la pantalla final
		}
	}

	// Método que devuelve la respuesta correcta según el idioma
	private String getRespuestaCorrecta() {
		Palabra palabra = palabras.get(index);
		if (idioma.equals("espanol")) {
			return palabra.getEspanol(); // Si el idioma es español, la respuesta es en español
		} else {
			return palabra.getIngles(); // Si el idioma es inglés, la respuesta es en inglés
		}
	}
}