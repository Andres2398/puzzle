package modelo;

import java.util.Random;

/**
 * Clase Logica en la que hacemos los movimientos de las "fichas" de la imagen
 */
public class Logica {
	/**
	 * Atributos de la clase logica Puzzle: matriz que representara el puzzle de la
	 * imagen y sobre la que moveremos puzzleResuelto: matriz que representa el
	 * puzzle resuelto para asi compararla con puzzle para saber si se ha ganado
	 * matrizImagen: matriz que contendra todos los pixeles del puzzle turnos
	 * variable que aumentara en 1 cada vez que se haga el intercambio de casillas
	 * representando un turno
	 */
	private int[][] puzzle;
	private int[][] puzzleResuelto;
	private int[][] matrizImagen;
	private int turnos;
	private Random r;
	private int mezclas[][];
	private int indiceMezclas;

	/**
	 * Constructor de la clase logica en el que iniciamos cada casilla del puzzle y
	 * puzzle resuelto con los mismos valores tanto puzzle como puzzle resuelto son
	 * de 4x4 porque son las fichas que tiene la imagen
	 */
	public Logica() {
		indiceMezclas=0;
		mezclas=new int[7][4];
		turnos = 0;
		r = new Random();
		int k = 0;
		puzzle = new int[4][4];
		puzzleResuelto = new int[4][4];
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				puzzle[i][j] = k;
				puzzleResuelto[i][j] = k;
				k++;

			}

		}
	}

	/**
	 * Metodo para obtner los turnos jugados en la partida
	 * 
	 * @return int cantidad de turnos jugados
	 */
	public int getTurnos() {
		return turnos;
	}

	/**
	 * Metodos para restar turnos cuando se mezcla el puzzle para que esos
	 * movimientos no cuenten
	 * 
	 * @param turnos cantidad de turnos a restar
	 */

	public void setTurnos(int turnos) {
		this.turnos -= turnos;
	}

	/**
	 * metodo para aumentar los turnos en 1 por cada movimiento
	 */
	public void setTurnos() {
		this.turnos++;
	}

	/**
	 * metodo que te devuelve la representación del puzzle
	 * @return
	 */
	public int[][] getPuzzle() {
		return puzzle;
	}
	
	
	/**
	 * Metodo para obtener la matriz del puzzle
	 * @return
	 */
	public int[][] getMatrizImagen() {
		return matrizImagen;
	}
	
	/**
	 * Metodo para establecer la matriz que representa toda la imagen del puzzle 400x400
	 * @param matrizImagen matriz completa del puzzle
	 */
	public void setMatrizImagen(int[][] matrizImagen) {
		this.matrizImagen = matrizImagen;
	}
	/**
	 * Metodo para elegir a que direccion mover segun el input del usuario
	 * @param input la direccion a la que se quiere mover el usuario
	 * @return la imagen con las fichas cambiadas si el movimiento era posible
	 */
	public int[][] moverFicha(String input) {
		indiceMezclas=0;
		if (input.equals("i")) {
			ComprobarmoverIzquierda();
		} else if (input.equals("d")) {
			ComprobarmoverDerecha();
		} else if (input.equals("b")) {
			ComprobarmoverAbajo();
		} else if (input.equals("a")) {
			ComprobarmoverArriba();
		} else if (input.equals("c")) {
			mezclarPuzzle();
		}
		return getMatrizImagen();
	}
	
	/**
	 * Metodo para comprobar si se puede mover hacia arriba una ficha
	 * @return true si se puede, false si no
	 */
	private boolean ComprobarmoverArriba() {
		for (int i = 0; i < puzzle.length - 1; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				if (puzzle[i][j] == 0) {
					moverFila(i + 1, j, -1);
					int ayuda = puzzle[i][j];
					mezclas[indiceMezclas][0]= i+1;
					mezclas[indiceMezclas][1]= j;
					mezclas[indiceMezclas][2]= i;
					mezclas[indiceMezclas][3]= j;
					indiceMezclas++;
					puzzle[i][j] = puzzle[i + 1][j];
					puzzle[i + 1][j] = ayuda;
					setTurnos();
					
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Metodo para comprobar si se puede mover hacia abajo una ficha
	 * @return true si se puede, false si no
	 */
	private boolean ComprobarmoverAbajo() {
		for (int i = 1; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[0].length; j++) {
				if (puzzle[i][j] == 0) {
					moverFila(i - 1, j, 1);
					int ayuda = puzzle[i][j];
					mezclas[indiceMezclas][0]= i-1;
					mezclas[indiceMezclas][1]= j;
					mezclas[indiceMezclas][2]= i;
					mezclas[indiceMezclas][3]= j;
					indiceMezclas++;
					puzzle[i][j] = puzzle[i - 1][j];
					puzzle[i - 1][j] = ayuda;
					setTurnos();
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Metodo para mover intercambiar la ficha en la columna 
	 * @param fila de la ficha que se quiere intercambiar
	 * @param columna de la ficha que se quiere intercambiar
	 * @param direccion de la ficha por la que se quiere intercambiar +1 para una fila superior, -1 para una fila inferior
	 */
	private void moverFila(int fila, int columna, int direccion) {

		int filaMatrizImagenBlanco = fila * 100;
		int columnaMatrizImagenBlanco = columna * 100;
		int filaIntercambiar = fila + direccion;
		int filaMatrizImagenIntercambiar = filaIntercambiar * 100;
		int columnaMatrizImagenIntercambiar = columna * 100;
		Bloque bloqueBlanco = new Bloque();
		bloqueBlanco.extraerBloque(filaMatrizImagenBlanco, columnaMatrizImagenBlanco, matrizImagen);
		Bloque bloqueIntercambiar = new Bloque();
		bloqueIntercambiar.extraerBloque(filaMatrizImagenIntercambiar, columnaMatrizImagenIntercambiar, matrizImagen);

		insertarBloque(bloqueBlanco.getPieza(), filaMatrizImagenIntercambiar, columnaMatrizImagenIntercambiar);
		insertarBloque(bloqueIntercambiar.getPieza(), filaMatrizImagenBlanco, columnaMatrizImagenBlanco);

	}
	
	/**
	 * Metodo para comprobar si se puede mover hacia la derecha una ficha
	 * @return true si se puede, false si no
	 */
	private boolean ComprobarmoverDerecha() {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 1; j < puzzle.length; j++) {
				if (puzzle[i][j] == 0) {
					moverColumna(i, j - 1, 1);
					int ayuda = puzzle[i][j];
					mezclas[indiceMezclas][0]= i;
					mezclas[indiceMezclas][1]= j-1;
					mezclas[indiceMezclas][2]= i;
					mezclas[indiceMezclas][3]= j;
					indiceMezclas++;
					puzzle[i][j] = puzzle[i][j - 1];
					puzzle[i][j - 1] = ayuda;
					setTurnos();
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Metodo para comprobar si se puede mover hacia la izquierda una ficha
	 * @return true si se puede, false si no
	 */
	private boolean ComprobarmoverIzquierda() {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length - 1; j++) {
				if (puzzle[i][j] == 0) {
					moverColumna(i, j + 1, -1);
					int ayuda = puzzle[i][j];
					mezclas[indiceMezclas][0]= i;
					mezclas[indiceMezclas][1]= j+1;
					mezclas[indiceMezclas][2]= i;
					mezclas[indiceMezclas][3]= j;
					indiceMezclas++;
					puzzle[i][j] = puzzle[i][j + 1];
					puzzle[i][j + 1] = ayuda;
					setTurnos();
					return true;
				}
			}
		}

		return false;

	}
	
	public int[][] getMezclas() {
		return mezclas;
	}

	public void setMezclas(int[][] mezclas) {
		this.mezclas = mezclas;
	}

	/**
	 * Metodo para mover intercambiar la ficha en la columna 
	 * @param fila de la ficha que se quiere intercambiar
	 * @param columna de la ficha que se quiere intercambiar
	 * @param direccion de la ficha por la que se quiere intercambiar +1 para una columna a la derecha,
	 *  -1 para una columna a la izquierda
	 */
	
	private void moverColumna(int fila, int columna, int direccion) {
		int filaMatrizImagenBlanco = fila * 100;
		int columnaMatrizImagenBlanco = columna * 100;
		int columnaIntercambiar = columna + direccion;
		int filaMatrizImagenIntercambiar = fila * 100;
		int columnaMatrizImagenIntercambiar = columnaIntercambiar * 100;
		Bloque bloqueBlanco = new Bloque();
		bloqueBlanco.extraerBloque(filaMatrizImagenBlanco, columnaMatrizImagenBlanco, matrizImagen);
		Bloque bloqueIntercambiar = new Bloque();
		bloqueIntercambiar.extraerBloque(filaMatrizImagenIntercambiar, columnaMatrizImagenIntercambiar, matrizImagen);

		insertarBloque(bloqueBlanco.getPieza(), filaMatrizImagenIntercambiar, columnaMatrizImagenIntercambiar);
		insertarBloque(bloqueIntercambiar.getPieza(), filaMatrizImagenBlanco, columnaMatrizImagenBlanco);

	}

	public void insertarBloque(int[][] pieza, int filaInicio, int columnaInicio) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				matrizImagen[filaInicio + i][columnaInicio + j] = pieza[i][j];

			}
		}
	}

	public int[][] mezclarPuzzle() {
		int vecesMezcla = -1;
		
		while (vecesMezcla != 6) {
			int random = r.nextInt(4);
			if (random == 0) {
				if (ComprobarmoverDerecha()) {
					vecesMezcla++;
				
				}
			} else if (random == 1) {
				if (ComprobarmoverIzquierda()) {
					vecesMezcla++;
				
			}
			}
			else if (random == 2) {
				if (ComprobarmoverAbajo()) {
					vecesMezcla++;
				
			}
			}
			else {
				if (ComprobarmoverArriba()) {
					vecesMezcla++;
				
			}}
			
			
			
		}
		setTurnos(vecesMezcla);
		return getMatrizImagen();
	}

	public boolean comprobarGanar() {
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				if (puzzle[i][j] != puzzleResuelto[i][j]) {
					return false;
				}
			}

		}
		return true;
	}

}
