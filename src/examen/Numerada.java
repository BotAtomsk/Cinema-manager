package examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class Numerada extends Sala implements Serializable {

	// atributos
		// creo que se podría hacer sin las variables filas y butacas, pero necesito conocer la longitud del array para el método venderEntradas()
	private boolean asientos[][];
	private int filas;
	private int butacas;
	
	
	// constructor
	public Numerada(String titulo, int filas, int butacas) {
		super(titulo);
		this.asientos = new boolean[filas][butacas];
		// bucle anidado para inicializar todos los asientos como LIBRES (false)
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < butacas; j++) {
				asientos[i][j] = false;
			}
		}
		this.filas = filas;
		this.butacas = butacas;
	}

	
	// setters y getters
	public boolean[][] getAsientos() {
		return asientos;
	}
	public void setAsientos(boolean[][] asientos) {
		this.asientos = asientos;
	}
	public int getFilas() {
		return filas;
	}
	public void setFilas(int filas) {
		this.filas = filas;
	}
	public int getButacas() {
		return butacas;
	}
	public void setButacas(int butacas) {
		this.butacas = butacas;
	}


	// desarrollamos el método abstracto de la clase padre venderEntradas()
	@Override
	public void venderEntradas() {
			mostrarButacas();
			System.out.println("¿Cuántas entradas quiere comprar?");
			int numEntradas = Teclado.leerInt();
			System.out.println("¿A partir de qué fila?");
			int fila = Teclado.leerInt() - 1;	// hay que meter -1 porque la fila 1 es para el ordenador el índice 0
			System.out.println("Valor de fila: " + fila);
			if (fila > this.getFilas()) {
				System.out.println("No existen tantas filas...");
				return;
			}
			System.out.println("¿Y a partir de qué butaca?");
			int butaca = Teclado.leerInt() - 1;
			System.out.println("Valor de butaca: " + butaca);
			if (butaca > this.getButacas()) {
				System.out.println("No existen tantas butacas...");
				return;
			}
			if (comprobarLibre(numEntradas, fila, butaca)) {
				System.out.println("Hay suficientes butacas disponibles. Procedemos a la compra...");
				String entradasCompradas = comprarButacas(numEntradas, fila, butaca);
				imprimirEntradas(numEntradas, entradasCompradas);
			} else
				System.out.println("No hay suficientes butacas disponibles");
				
			
	}

	// vamos a elegir, por convención, que muestre con una X las butacas OCUPADAS (true), y con un - las butacas LIBRES (false)
	public void mostrarButacas() {
		System.out.println("Película: " + this.getTitulo() + "\t- significa LIBRE; X significa OCUPADA");
		for (int i = 0; i < filas; i++) {
			System.out.print("\tFila: " + (i+1));
			for (int j = 0; j < butacas; j++) {
				if (asientos[i][j] == false)
					System.out.print(" - ");
				else if (asientos[i][j] == true)
					System.out.print(" X ");
				else
					System.out.println("Ha ocurrido un error iterando por el array de butacas de la sala");
			}
			System.out.println();
		}
	}
	
	public boolean comprobarLibre(int numEntradas, int fila, int butaca) {
		int contador = 0;
		
		// iteramos por asientos, DESDE fila y butaca, y como máximo hasta llegar a la última fila y la última butaca
		for (int i = fila; i < this.getFilas(); i++) {
			for (int j = butaca; j < this.getButacas(); j++) {
				if (asientos[i][j] == false) {
					contador++;
					if (contador == numEntradas) 
						return true;
				}
			}
		}
		return false;	// no hay suficientes butacas
	}
	
	// como éste método se ejecuta DESPUÉS de comprobarLibre, podemos asignar todas las butacas compradas a true sin comprobaciones 
	public String comprarButacas(int numEntradas, int fila, int butaca) {
		// inicializamos el array con la info de las entradas
		String entradasCompradas = "";
		int contador = 0;
		// de nuevoiteramos por asientos, DESDE fila y butaca, y salimos tras pasar a true (OCUPADA) tantas butacas como numEntradas
		for (int i = fila; i < this.getFilas(); i++) {
			for (int j = butaca; j < this.getButacas(); j++) {
				if (asientos[i][j] == false) {
					asientos[i][j] = true;
					entradasCompradas += "Entrada número " + contador + ". Fila: " + (i+1) + ". Butaca: " + (j+1) + "\n";
					contador++;
					if (contador == numEntradas)
						return entradasCompradas;
				}
			}
		}
		return entradasCompradas;
	}
	
	public void imprimirEntradas(int numEntradas, String entradasCompradas) {
		System.out.println("Indica el nombre del fichero donde guardaremos la información de la compra:");
		String nombreFichero = Teclado.leerString();
		BufferedWriter bw;
		bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(nombreFichero + ".txt"));
			bw.write("Número de sala: " + this.getNumero() + "\nTítulo de la película: " + this.getTitulo()
			+ "\n\tEntradas compradas " + numEntradas + "\n\nListado de entradas compradas:\n" + entradasCompradas + "\n");
			bw.close();
		} catch (IOException e) {
			System.out.println("IOException error");
		}	
	}
	
	public void reiniciarButacas() {
		for (int i = 0; i < this.getFilas(); i++) {
			for (int j = 0; j < this.getButacas(); j++) {
				asientos[i][j] = false;
			}
		}
	}
	
	@Override
	public String toString() {
		mostrarButacas();
		return "Numerada [numero=" + numero + ", titulo=" + titulo + "]";
	}

}
