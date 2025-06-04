package bilingualtreasure;

import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class

PantallaFinal {

	void iniciar(Stage stage, Puntaje puntaje, List<Palabra> palabras, Timeline timeline, Temporizador mainTemporizador) {

		Label titulo = new Label("¡Juego terminado!");
		titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

		Label resumen = new Label("✅ Buenas: " + puntaje.buenas + "\n❌ Malas: " + puntaje.malas);
		resumen.setStyle("-fx-font-size: 18px;");

		Button reiniciarBtn = new Button("Jugar de nuevo");
		Button inicioBtn = new Button("Volver al inicio");

		reiniciarBtn.setOnAction(e -> {
			puntaje.Reset();
			// Reinicia el juego llamando a la pantalla del juego
			PantallaJuego pantallaJuego = new PantallaJuego();
			pantallaJuego.iniciar(stage, palabras, timeline, mainTemporizador, puntaje, this);  // Llamada correcta
		});

		inicioBtn.setOnAction(e -> {
			puntaje.Reset();
			try {
				// Aquí se puede redirigir a la pantalla de inicio
				BilingualTreasure pantallaInicio = new BilingualTreasure();
				pantallaInicio.start(stage);  // Redirigir al inicio
			} catch (Exception ex) {
				ex.printStackTrace();  // Manejo de la excepción
			}
		});

		VBox layout = new VBox(20, titulo, resumen, reiniciarBtn, inicioBtn);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-padding: 50; -fx-background-color: #fbbf24;");

		stage.setScene(new Scene(layout, 500, 400));
	}
}
