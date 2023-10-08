package examen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Cine {

	/* personalmente prefiero utilizar variables static para el ArrayList de salas y para el Scanner
		así podrán utilizarse desde cualquier método sin necesidad de pasarlas por parámetro */
	private static ArrayList<Sala> salas = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		leerFichero();
		menu();
		
	}

	private static void menu() {
		int opcion = 0;
		while (opcion != 4) {
			System.out.println("\nMENU:\n\t1. Crear sala\n\t2. Cambiar película\n\t3. Comprar entradas\n\t4. Salir");
			opcion = Teclado.leerInt();
			switch (opcion) {
				case 1: crearSala(); break;
				case 2: cambiarPelicula(); break;
				case 3: comprarEntradas(); break;
				case 4: System.out.println("Ha elegido salir. Guardando..."); escribirFichero(); break;
				default: System.out.println("La opción introducida no es válida, vuelva a intentarlo...");
			}
		}
		
	}
	
	private static void crearSala() {
		System.out.println("Título de la película a proyectar");
		String titulo = Teclado.leerLinea();
		System.out.println("Es una sala numerada (1) o no numerada (2)?");
		int opcion = Teclado.leerInt();
		while (opcion < 1 || opcion > 2) {
			System.out.println("La opción introducida no es correcta, inténtelo de nuevo");
			Teclado.leerInt();
		}
		
		Sala s = null;
		switch (opcion) {
			case 1: 
				System.out.println("Indica el número de filas");
				int filas = Teclado.leerInt();
				System.out.println("Ahora indica el número de butacas");
				int butacas = Teclado.leerInt();
				try {
					s = new Numerada(titulo, filas, butacas);
				} catch (Exception e) {
					System.out.println("ERROR. No ha sido posible crear la sala numerada");
				}
				break;
			case 2:
				System.out.println("Indica el número total de butacas");
				int totalButacas = Teclado.leerInt();	
				try {
					s = new NoNumerada(titulo, totalButacas);
				} catch (Exception e) {
					System.out.println("ERROR. No ha sido posible crear la sala no numerada");
				}
				break;
		}
		salas.add(s);
	}
	
	private static void cambiarPelicula() {
		System.out.println("Indica el título de la película a cambiar");
		String titulo = Teclado.leerLinea();
		for (Sala s : salas) {
			if (s.getTitulo().equalsIgnoreCase(titulo)) {
				System.out.println("En la sala " + s.getNumero() + " se proyecta esa película. Vamos a cambiarla. Indica la nueva película a proyectar");
				String nuevoTitulo = Teclado.leerLinea();
				s.setTitulo(nuevoTitulo);
				s.reiniciarButacas();
				return;
			}
		}
		System.out.println("No se ha encontrado ninguna película con el título " + titulo);
	}
	
	private static void comprarEntradas() {
		for (Sala s: salas) {
			System.out.print("Sala " + s.getNumero() + ". Película: " + s.getTitulo() + ". Butacas disponibles: ");
			s.mostrarButacas();
		}
		System.out.println("\nIndica el número de sala para el que quieres comprar entradas");
		int numero = Teclado.leerInt();
		if (buscarPeliculaPorNumero(numero) != null)
			buscarPeliculaPorNumero(numero).venderEntradas();
		else 
			System.out.println("El número de sala indicado no existe. Volviendo al menú...");
	}
	
	private static Sala buscarPeliculaPorNumero(int numero) {
		for (Sala s : salas) {
			if (s.getNumero() == numero)
				return s;
		}
		return null;
	}
	
	private static void escribirFichero() {
		ObjectOutputStream oos;
		oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("laEstrella.bin"));
			oos.writeObject(salas);
			oos.writeObject(Sala.contador);
		} catch (IOException e) {
			System.out.println("Error escrbiendo los datos de las salas de LaEstrella");
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				System.out.println("Error cerrando el fichero de salas de LaEstrella");
			}
		}
	}
	
	private static void leerFichero() {
		ObjectInputStream ois;
		ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("laEstrella.bin"));
			 salas = (ArrayList<Sala>) ois.readObject();
			Sala.contador = (int) ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo de salas de LaEstrella");
		} catch (ClassNotFoundException e) {
			System.out.println("Error leyendo los datos de salas");
		} catch (IOException e) {
			System.out.println("Error leyendo el fichero laEstrella.bin");
		} catch (Exception e) {
			System.out.println("Error en los datos de laEstrella.bin");
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
				System.out.println("Error cerrando el fichero laEstrella.bin");
			}
		}
	}

}
