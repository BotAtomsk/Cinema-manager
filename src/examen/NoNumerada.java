package examen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class NoNumerada extends Sala implements Serializable {

	// atributos
	private int butacas;
	private int entradas;
	
	
	// constructor
	public NoNumerada(String titulo, int butacas) {
		super(titulo);
		this.butacas = butacas;
		this.entradas = butacas; // Al crear una sala el número de entradas disponible será el número de butacas, por definición
	}

	
	// setters y getters
	public int getButacas() {
		return butacas;
	}
	public void setButacas(int butacas) {
		this.butacas = butacas;
	}
	public int getEntradas() {
		return entradas;
	}
	public void setEntradas(int entradas) {
		this.entradas = entradas;
	}


	// desarrollamos el método abstracto de la clase padre venderEntradas()
	@Override
	public void venderEntradas() {
		System.out.println("¿Cuántas entradas quiere comprar?");
		int numEntradas = Teclado.leerInt();
		if (this.getEntradas() >= numEntradas) {
			System.out.println("Se han comprado " + numEntradas + " entradas");
			this.setEntradas(this.getEntradas() - numEntradas);
			imprimirEntradas(numEntradas);
		} else 
			System.out.println("No se ha podido realizar la venta, ya que quedan " + this.getEntradas() + " entradas"
					+ " y se quieren comprar " + numEntradas + " entradas");
	}
	
	
	public void imprimirEntradas(int numEntradas) {
		System.out.println("Indica el nombre del fichero donde guardaremos la información de la compra:");
		String nombreFichero = Teclado.leerString();
		BufferedWriter bw;
		bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(nombreFichero + ".txt"));
			bw.write("Número de sala: " + this.getNumero() + "\nTítulo de la película: " + this.getTitulo()
			+ "\n\tEntradas compradas " + numEntradas);
			bw.close();
		} catch (IOException e) {
			System.out.println("IOException error");
		}	
	}
	
	public void reiniciarButacas() {
		this.entradas = butacas;
	}
	
	public void mostrarButacas() {
		System.out.println(this.getEntradas());
	}
	
	@Override
	public String toString() {
		return "NoNumerada [butacas=" + butacas + ", entradas=" + entradas + ", numero=" + numero + ", titulo=" + titulo
				+ "]";
	}

}
