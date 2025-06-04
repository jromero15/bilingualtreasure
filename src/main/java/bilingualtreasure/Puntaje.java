package bilingualtreasure;

public class Puntaje {

	private int buenas;  // Almacena el número de respuestas correctas.
	private int malas;   // Almacena el número de respuestas incorrectas.
	private String jugador;  // Almacena el nombre del jugador.

	// Constructor que inicializa el puntaje en 0 y el nombre del jugador en una cadena vacía.
	public Puntaje() {
		buenas = 0;
		malas = 0;
		jugador = "";
	}

	public void Buena() {
		buenas++;  // Aumenta el contador de respuestas correctas.
	}

	public void Mala() {
		malas++;  // Aumenta el contador de respuestas incorrectas.
	}

	public void Reset() {
		// Reinicia las variables a sus valores iniciales (0).
		buenas = 0;
		malas = 0;
	}

	public int getBuenas() {
		return buenas;  // Retorna el número de respuestas correctas.
	}

	public int getMalas() {
		return malas;  // Retorna el número de respuestas incorrectas.
	}

	public void setJugador(String nombre) {
		this.jugador = nombre;  // Asigna el nombre del jugador a la variable 'jugador'.
	}
}
