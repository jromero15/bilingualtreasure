package bilingualtreasure;

public class Puntaje {
	
	int buenas =0;
	int malas =0;
	int indexPalabra=0;
	String nombreJugador="";
	
	
	
	public void Buena() {
		buenas=buenas+1;
	}
	
	public void Mala() {
	  malas = malas+1;
	}
	
	public void Reset() {
		buenas=0;
		malas=0;
		indexPalabra=0;
	}
	public void cambiarPalabra() {
		indexPalabra = indexPalabra+1;
	}
	public void setJugador(String nombre) {
		nombreJugador=nombre;
	}

}
