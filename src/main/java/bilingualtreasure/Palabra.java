package bilingualtreasure;

/**
 * Representa una palabra con su traducción en dos idiomas: inglés y español.
 * Esta clase es inmutable una vez creada, ya que sus atributos son privados
 * y solo se accede a ellos mediante métodos 'getter'.
 *
 * @author Grupo13_B04
 * @version 1.0
 * @since 2025-06-05
 */
public class Palabra {

    /** El atributo que guarda la palabra en inglés. */
    private String ingles;

    /** El atributo que guarda la palabra en español. */
    private String espanol;

    /**
     * Crea una nueva instancia de {@code Palabra}.
     * Inicializa una nueva instancia de {@code Palabra} con las versiones
     * en inglés y español de una palabra.
     *
     * @param ingles La cadena de texto de la palabra en inglés. No debe ser nula.
     * @param espanol La cadena de texto de la palabra en español. No debe ser nula.
     */
    public Palabra(String ingles, String espanol) {
        this.ingles = ingles;
        this.espanol = espanol;
    }

    /**
     * Devuelve la palabra en inglés.
     *
     * @return La cadena de texto que representa la palabra en inglés.
     */
    public String getIngles() {
        return ingles;
    }

    /**
     * Devuelve la palabra en español.
     *
     * @return La cadena de texto que representa la palabra en español.
     */
    public String getEspanol() {
        return espanol;
    }
}