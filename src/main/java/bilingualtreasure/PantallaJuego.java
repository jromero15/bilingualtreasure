package bilingualtreasure;

import java.util.List;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaJuego {

	private Runnable cargarSiguientePalabra;

	private void finalizarJuego(Stage stage, Temporizador mainTemporizador, Puntaje puntaje, PantallaFinal pantallaFinal, Timeline timeline) {
		if (timeline != null) {
			timeline.stop();
		}
		mainTemporizador.detener();
		pantallaFinal.iniciar(stage, puntaje);
	}

	void iniciar(
			Stage stage,
			List<Palabra> palabras,
			Timeline timeline,
			Temporizador mainTemporizador,
			Puntaje puntaje,
			PantallaFinal pantallaFinal
	) {

		Label palabraIngles = new Label();
		TextField inputRespuesta = new TextField();
		Label temporizador = new Label();
		Label puntos = new Label("✅ 0 ❌ 0");

		Button verificarBtn = new Button("Verificar");
		Button siguienteBtn = new Button("Siguiente");
		Button salirBtn = new Button("Salir");

		cargarSiguientePalabra = () -> {
			if (puntaje.indexPalabra >= palabras.size()) {
				finalizarJuego(stage, mainTemporizador, puntaje, pantallaFinal, timeline);
				return;
			}

			Palabra p = palabras.get(puntaje.indexPalabra);
			palabraIngles.setText(p.ingles);
			inputRespuesta.clear();

			mainTemporizador.iniciar(temporizador, () -> {
				puntaje.Mala();
				puntaje.cambiarPalabra();
				puntos.setText("✅ " + puntaje.buenas + " ❌ " + puntaje.malas);
				if (puntaje.indexPalabra >= palabras.size()) {
					finalizarJuego(stage, mainTemporizador, puntaje, pantallaFinal, timeline);
				} else {
					cargarSiguientePalabra.run();
				}
			});
		};

		verificarBtn.setOnAction(e -> {
			Palabra actual = palabras.get(puntaje.indexPalabra);
			String respuesta = inputRespuesta.getText().trim().toLowerCase();
			if (respuesta.equals(actual.espanol.toLowerCase())) {
				puntaje.Buena();
			} else {
				puntaje.Mala();
			}
			puntos.setText("✅ " + puntaje.buenas + " ❌ " + puntaje.malas);
			puntaje.cambiarPalabra();

			if (puntaje.indexPalabra >= palabras.size()) {
				finalizarJuego(stage, mainTemporizador, puntaje, pantallaFinal, timeline);
			} else {
				cargarSiguientePalabra.run();
			}
		});

		siguienteBtn.setOnAction(e -> {
			puntaje.Mala();
			puntaje.cambiarPalabra();
			puntos.setText("✅ " + puntaje.buenas + " ❌ " + puntaje.malas);
			cargarSiguientePalabra.run();
		});

		salirBtn.setOnAction(e -> {
			finalizarJuego(stage, mainTemporizador, puntaje, pantallaFinal, timeline);
		});

		VBox layout = new VBox(20, new Label("Jugador: " + puntaje.nombreJugador), palabraIngles, temporizador, inputRespuesta,
				new HBox(10, verificarBtn, siguienteBtn, salirBtn), puntos);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-padding: 40; -fx-background-color: #f59e0b;");

		stage.setScene(new Scene(layout, 500, 400));

		cargarSiguientePalabra.run();
	}
}
