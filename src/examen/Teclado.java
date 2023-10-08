package examen;

import java.util.Scanner;


/* esta clase nos permite recoger el input del usuario a través de Scanner de manera mejorada
	1) porque establece bucles de los que no podemos salir hasta que escribamos la variable correcta
	2) porque evita el problema del salto de línea que ocurre al usar sc.nextInt() y continuación sc.nextLine(); 
	3) porque establece un sistema de try/catch que supone el manejo de excepciones, de modo que el programa no deje de funcionar si el usuario se equivoca
	*/

public class Teclado {

	public static Scanner sc = new Scanner(System.in);

	public static int leerInt() {
		while (true)
			try {
				int n = sc.nextInt();
				sc.nextLine();
				return n;
			} catch (Exception e) {
				System.out.println("Debe escribir un número entero");
				sc.nextLine();
			}
	}

	public static double leerDouble() {
		while (true)
			try {
				double d = sc.nextDouble();
				sc.nextLine();
				return d;
			} catch (Exception e) {
				System.out.println("Debe escribir un número");
				sc.nextLine();
			}
	}

	public static String leerString() {
		while (true)
			try {
				String str = sc.next();
				return str;
			} catch (Exception e) {
				System.out.println("Debe escribir un conjunto de caracteres");
				sc.nextLine();
			}
	}

	public static String leerLinea() {
		String line = sc.nextLine();
		return line;
	}

}
