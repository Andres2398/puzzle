package modelo;

import java.util.Iterator;

public class Bloque {
	private int[][] pieza;

	public Bloque() {
		pieza = new int[100][100];
	}

	public int[][] getPieza() {
		return pieza;
	}

	public void setPieza(int[][] pieza) {
		this.pieza = pieza;
	};
	
	public void extraerBloque(int fila, int columna, int [][] matrizImagen) {
		
		
		int [][] piezaNueva = new int [100][100];
		for (int i = 0; i < pieza.length; i++) {
			for (int j = 0; j < pieza[0].length; j++) {
				piezaNueva[i][j]=matrizImagen[i+fila][j+columna];
				
			}
		}
		
		setPieza(piezaNueva);
		
		
	}

}
