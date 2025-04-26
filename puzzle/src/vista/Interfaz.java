package vista;

import java.util.Scanner;

/**
 * Clase interfaz que se dedica a la comunicaion con el usuario tanto para
 * mostarle la pantalla de juego coomo para pedirle por terminar los inputs
 * donde quiere mover
 */
public class Interfaz {
	/**
	 * Atributos de la clase Interfaz Imagen: Clase Imagen que nos permitira
	 * establecer la pantalla de juego y mostrar esta POSIBILIDADES: Son todas los
	 * inputs correctos que puede introducir el jugador para continuar la partida
	 * IMAGENES: Son todas las images con las que el jugador puede jugar
	 */
	private Imagen imagen;
	private static final String[] POSIBILIDADES = { "i", "d", "b", "a", "t", "c" };
	private static final String[] IMAGENES = { "barcas.jpg", "caballero.jpg", "luna.jpg", "pelayo.jpg", "rosa.jpg",
			"tulipanes.jpg" };
	private Scanner sc;

	/**
	 * Constructor de la clase interfaz solo con el scanner para recoger los inputs
	 * del usuario
	 */
	public Interfaz() {
		sc = new Scanner(System.in);

	}

	/**
	 * Metodo para pedir con que imagen quiere jugar al usuario de todas las
	 * posiblidades que tiene el array IMAGENES
	 * 
	 * @return el la matriz en 2d de las imagenes, en este caso son siempre matrices
	 *         de pixeles de 400x400
	 */
	public int[][] pedirImagen() {
		boolean inputCorrecto = false;
		String input;
		do {
			System.out.println(
					"Pulsa 1 para jugar con la imagen de las barcas\nPulsa 2 para jugar con la imagen del caballero\n"
							+ "Pulsa 3 para jugar con la imagen de la Luna\nPulsa 4 para jugar con la imagen de pelayo\n"
							+ "Pulsa 5 para jugar con la imagen de la rosa\nPulsa 6 para jugar con la imagen de los tulipanes");
			input = sc.nextLine();
			if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
					|| input.equals("6"))
				inputCorrecto = true;
		} while (!inputCorrecto);

		establecerImagen(Integer.parseInt(input) - 1);
		return imagen.getArray2D();
	}

	/**
	 * Establecer la imagen con la que se jugara la partida
	 * 
	 * @param indice del array de IMAGENS que ha seleccionado el usuario
	 */
	private void establecerImagen(int indice) {

		imagen = new Imagen("./img/" + IMAGENES[indice]);
	}

	/**
	 * Metodo para mostrar la imagen en pantalla
	 */
	public void mostrarImagen() {
		imagen.ver();

	}

	/**
	 * Metodo para que el usuario introduzca su proximo movimiento
	 * 
	 * @return el movimiento que quiere hacer el usuario siempre dentro de los
	 *         limetes marcados
	 */
	public String pedirMovimiento() {
		String input = null;
		boolean buena = false;
		System.out.println("Seleciona un movimiento:\r\n‘i’ se mueve la ficha a la izquierda si es posible\r\n"
				+ "‘d’ se mueve la ficha a la derecha si es posible\r\n"
				+ "‘b’ se mueve la ficha hacia abajo si es posible\r\n"
				+ "‘a’ Se mueve la ficha hacia arriba si es posible\r\n" + "‘c’ para mezclar el puzzle\r\n"
				+ "‘t’ termina el programa solicitando confirmación");
		while (!buena) {
			buena = false;
			input = sc.nextLine();
			for (int i = 0; i < POSIBILIDADES.length; i++) {
				if (input.equals(POSIBILIDADES[i]))
					buena = true;
			}
		}
		return input;

	}

	/**
	 * Metodo que se llama siemrpre que el jugador termina la partida sin haber
	 * ganado o cuando se gana la partida
	 */
	public void finalizar() {
		System.out.println("Gracias por jugar...");
		imagen.cerrar();

	}

	/**
	 * Metodo para establecer la imagen con las fichas cambiadas despues del
	 * realizar el movimiento que quiere el usuario
	 * 
	 * @param matrizImagen matriz de todos los pixeles que representa la imagen
	 */
	public void insertarNuevaImagenVer(int[][] matrizImagen) {
		imagen.setArray2D(matrizImagen);
		imagen.ver();

	}

	/**
	 * Metodo que se llama la primera vez para que el usuario mezcle siempre al
	 * principio del juego la imagen
	 */
	public void pedirRandomizar() {
		boolean buena = false;
		do {
			System.out.println("pulse c para mezclar el puzzle");
			String input = sc.nextLine();
			if (input.equals("c") || input.equals("C"))
				buena = true;
		} while (!buena);

	}

	/**
	 * Mensaje de victoria para el usuario
	 * 
	 * @param turnos cantidad de turnos que le ha llevado completar el juego
	 */
	public void mensajeVictoria(int turnos) {
		System.out.println("Felicidades has ganado en " + turnos + " turnos");

	}

	public void mostrarMezclas(int[][] mezclas) {
		System.out.println("Estas son las mezclas realizadas");
		for (int i = 0; i < mezclas.length; i++) {
			System.out.println();
			for (int j = 0; j < mezclas[0].length-1; j++) {
				if (j == 0)
					System.out.print("Casilla Inicio " + mezclas[i][j] + " ");
					
				else if(j==2)
					System.out.print("Casilla Destino " + mezclas[i][j] + " " + mezclas[i][j+1]);
				
				else
					System.out.print(mezclas[i][j]+ " ");
			}
		}
		System.out.println();
	}
}
