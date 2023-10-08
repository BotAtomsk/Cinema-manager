package examen;

import java.io.Serializable;

public abstract class Sala implements Serializable {

	// atributos
	protected int numero;
	protected String titulo;
	
	protected static int contador = 1;	// contador est�tico

	
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

	
	// m�todo abstracto; aqu� meramente figura la cabecera; la implementaci�n se produce en las clases hijas
	public abstract void venderEntradas();

	// este m�todo nos permite poner todas las butacas como LIBRES cuando cambiemos una pel�cula
	public abstract void reiniciarButacas();
	
	// este m�todo nos permite mostrar las butacas cuando vayamos a comprar entradas
	public abstract void mostrarButacas();
	
	
	@Override
	public String toString() {
		return "Sala [numero=" + numero + ", titulo=" + titulo + "]";
	}
}
