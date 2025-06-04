package bilingualtreasure;

//Clase que representa una palabra en dos idiomas: inglés y español.
public class Palabra {

	// Atributo que guarda la palabra en inglés
	private String ingles;

	// Atributo que guarda la palabra en español
	private String espanol;

	///Constructor de la clase Palabra
	public Palabra(String ingles, String espanol) {
		this.ingles = ingles;
		this.espanol = espanol;
	}

	//Devuelve la palabra en inglés.
	public String getIngles() {
		return ingles;
	}

    //Devuelve la palabra en español
	public String getEspanol() {
		return espanol;
	}
}


