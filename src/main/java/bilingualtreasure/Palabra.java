package bilingualtreasure;

public class Palabra {
	private String ingles;
	private String espanol;

	public Palabra(String ingles, String espanol) {
		this.ingles = ingles;
		this.espanol = espanol;
	}

	public String getIngles() {
		return ingles;
	}

	public String getEspanol() {
		return espanol;
	}
}

