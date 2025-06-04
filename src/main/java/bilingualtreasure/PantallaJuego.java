package bilingualtreasure;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;
import java.util.Random;

public class PantallaJuego {

	private int index = 0; // Para llevar el control de la palabra actual

	void iniciar(Stage stage, List<Palabra> palabras, Timeline timeline, Temporizador mainTemporizador, Puntaje puntaje, PantallaFinal pantallaFinal) {
		// Mostrar la primera pregunta
		Label pregunta = new Label("Traduce esta palabra: " + palabras.get(index).getIngles());
		TextField respuesta = new TextField();
		Button verificarBtn = new Button("Verificar");

		verificarBtn.setOnAction(e -> {
			String respuestaUsuario = respuesta.getText().trim();
			if (respuestaUsuario.equalsIgnoreCase(palabras.get(index).getEspanol())) {
				puntaje.Buena();
			} else {
				puntaje.Mala();
			}
			index++; // Avanzar a la siguiente pregunta

			if (index < palabras.size()) {
				pregunta.setText("Traduce esta palabra: " + palabras.get(index).getIngles());
				respuesta.clear();
			} else {
				pantallaFinal.iniciar(stage, puntaje, palabras, timeline, mainTemporizador);
			}
		});

		VBox layout = new VBox(10, pregunta, respuesta, verificarBtn);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-padding: 30; -fx-background-color: lightblue;");

		stage.setScene(new Scene(layout, 400, 300));
	}
}
