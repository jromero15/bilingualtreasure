package bilingualtreasure;

import java.util.List;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
public class BilingualTreasure extends Application {

	List<Palabra> palabras = Arrays.asList(
		new Palabra("Apple", "Manzana"), 
		new Palabra("Dog", "Perro"),
		new Palabra("Sun", "Sol"), 
		new Palabra("House", "Casa"), 
		new Palabra("Book", "Libro")
	);

	private Puntaje puntaje= new Puntaje();
	private Timeline timeline;
	Temporizador mainTemporizador = new Temporizador();
	Label temporizador = new Label();
	PantallaJuego pantallaJuego = new PantallaJuego();
	PantallaFinal pantallaFinal = new PantallaFinal();

	@Override
	public void start(Stage stage) throws Exception {
		Label title = new Label("BILINGUAL TREASURE");
		TextField inputNombre = new TextField();
		inputNombre.setPromptText("Ingresa tu nombre");
		Button comenzar = new Button("Comenzar");

		comenzar.setOnAction(e -> {
			String nombre = inputNombre.getText().trim();
			puntaje.setJugador(nombre);
			if (!nombre.isEmpty()) {
				pantallaJuego.iniciar(stage, palabras, timeline, mainTemporizador, puntaje, pantallaFinal);
			}
		});

		VBox layout = new VBox(10, title, inputNombre, comenzar);
		layout.setStyle("-fx-padding: 30; -fx-alignment: center; -fx-background-color: orange;");

		Scene escenaInicial = new Scene(layout, 400, 300);
		stage.setScene(escenaInicial);
		stage.setTitle("Juego de Traducción");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
