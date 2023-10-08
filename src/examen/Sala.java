package examen;

import java.io.Serializable;

public abstract class Sala implements Serializable {

	// atributos
	protected int numero;
	protected String titulo;
	
	protected static int contador = 1;	// contador estático

	
	// constructor
	public Sala(String titulo) {
		this.numero = contador++;
		this.titulo = titulo;
	}

	
	// getters y setters
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	// getters y setters del contador; no creo que nos hagan falta, pero no molestan
	public static int getContador() {
		return contador;
	}
	public static void setContador(int contador) {
		Sala.contador = contador;
	}

	
	// método abstracto; aquí meramente figura la cabecera; la implementación se produce en las clases hijas
	public abstract void venderEntradas();

	// este método nos permite poner todas las butacas como LIBRES cuando cambiemos una película
	public abstract void reiniciarButacas();
	
	// este método nos permite mostrar las butacas cuando vayamos a comprar entradas
	public abstract void mostrarButacas();
	
	
	@Override
	public String toString() {
		return "Sala [numero=" + numero + ", titulo=" + titulo + "]";
	}
}
