package modelo;
/**
 * Clase que nos pemitira guardar piezas de 100x100 de nuestra matriz de 400x400 siendo cada pieza
 * finalmente un "bloque"
 */


public class Bloque {
	/**
	 * Atributos de la clase Bloque
	 * Matriz de int que representara una pieza de la matriz total de 400x400
	 */
	private int[][] pieza;
	/**
	 * Constructor de la clase Bloqeue en el que iniciamos nuestra matriz de pieza en 100x100
	 */
	public Bloque() {
		pieza = new int[100][100];
	}
	/**
	 * Metodo para obterner la pieza del bloque 
	 * @return una matriz de int con todos los pixeles a mover 
	 */
	public int[][] getPieza() {
		return pieza;
	}
	/**
	 * Metodo para establece la pieza con todos los pixeles a mover
	 * @param pieza con los pixeles a mover
	 */
	public void setPieza(int[][] pieza) {
		this.pieza = pieza;
	};
	/**
	 * Metodo para extraer el bloque del total de la matriz
	 * @param fila de inicio de donde se quiere extraer el bloque
	 * @param columna de inicio de donde se quiere extraer el bloque
	 * @param matrizImagen Mattriz de donde se quiere extraer el bloque
	 */
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
