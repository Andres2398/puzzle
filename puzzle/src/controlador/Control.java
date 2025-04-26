package controlador;

import modelo.Logica;
import vista.Interfaz;
/**
 * Clase control que se dedica a controlar el flujo del juego e ir conecatando la parte logica de este con la interfaz que vera
 * el usuario
 */
public class Control {
	/**
	 * Atributos de la clase control
	 */
	
	private Logica logica;
	private Interfaz interfaz;
/**
 * Constructor de la clase control con los objetos lógica e interfaz 
 */
	public Control() {
		logica = new Logica();
		interfaz = new Interfaz();
	}
/**
 * Metodo que se encarga del bucle del juego de ir llamando a las diferentes clases para que el juego funcione
 */
	public void start() {
	
		int[][] matrizImagen = interfaz.pedirImagen();
		logica.setMatrizImagen(matrizImagen);
		interfaz.mostrarImagen();
		interfaz.pedirRandomizar();
		matrizImagen=logica.mezclarPuzzle();
		interfaz.mostrarMezclas(logica.getMezclas());
		interfaz.insertarNuevaImagenVer(matrizImagen);
		boolean fin = false;
		boolean ganar = false;
		while (!fin && !ganar) {
			String input = interfaz.pedirMovimiento();
			if (input.compareTo("t") != 0) {
				matrizImagen=logica.moverFicha(input);
				interfaz.insertarNuevaImagenVer(matrizImagen);
				ganar=logica.comprobarGanar();
				if(input.equals("c")) {
					interfaz.mostrarMezclas(logica.getMezclas());
				}
			}
			else {
				fin=true;
				interfaz.finalizar();
			}
		}
		if(ganar) {
			interfaz.mensajeVictoria(logica.getTurnos());
			interfaz.finalizar();
		}
	}
}
