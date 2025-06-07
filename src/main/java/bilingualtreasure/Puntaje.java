package bilingualtreasure;

/**
 * Gestiona y almacena la puntuación de un jugador en el juego "The Bilingual Treasure".
 * Registra el número de respuestas correctas e incorrectas, así como el nombre del jugador.
 * Provee métodos para actualizar y reiniciar la puntuación, y para obtener los valores actuales.
 *
 * @author Grupo13_B04
 * @version 1.0
 * @since 2025-06-05
 */
public class Puntaje {

    /** Almacena el número de respuestas correctas obtenidas por el jugador. */
    private int buenas;
    /** Almacena el número de respuestas incorrectas obtenidas por el jugador. */
    private int malas;
    /** Almacena el nombre del jugador actual. */
    private String jugador;

    /**
     * Crea una nueva instancia de {@code Puntaje}.
     * Inicializa los contadores de respuestas correctas e incorrectas a cero
     * y el nombre del jugador a una cadena vacía.
     */
    public Puntaje() {
        buenas = 0;
        malas = 0;
        jugador = "";
    }

    /**
     * Incrementa el contador de respuestas correctas en uno.
     * Este método se llama cuando el jugador proporciona una traducción correcta.
     */
    public void Buena() {
        buenas++;
    }

    /**
     * Incrementa el contador de respuestas incorrectas en uno.
     * Este método se llama cuando el jugador proporciona una traducción incorrecta
     * o cuando el tiempo para responder se agota.
     */
    public void Mala() {
        malas++;
    }

    /**
     * Reinicia los contadores de respuestas correctas e incorrectas a cero.
     * Este método se utiliza para preparar una nueva partida, borrando los resultados anteriores.
     */
    public void Reset() {
        buenas = 0;
        malas = 0;
    }

    /**
     * Obtiene el número actual de respuestas correctas.
     *
     * @return El valor entero que representa la cantidad de respuestas correctas.
     */
    public int getBuenas() {
        return buenas;
    }

    /**
     * Obtiene el número actual de respuestas incorrectas.
     *
     * @return El valor entero que representa la cantidad de respuestas incorrectas.
     */
    public int getMalas() {
        return malas;
    }

    /**
     * Establece o actualiza el nombre del jugador actual.
     *
     * @param nombre La cadena de texto que representa el nombre del jugador.
     * Este valor se asigna al atributo {@link #jugador}.
     */
    public void setJugador(String nombre) {
        this.jugador = nombre;
    }
}