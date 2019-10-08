package com.base;

import java.util.Scanner;

public class Main {
	
	public static void main (String[] ar) {
		// Clase de pruebas para utilización en cursos y pruebas de código
		Scanner scan = new Scanner(System.in);
		float calificacion = 0;
		System.out.print("Ingrese un valor entres 0 y 10: ");
		calificacion = Float.parseFloat(scan.nextLine());
		
		if (calificacion <= 10 && calificacion  >= 9) {
			System.out.println("A");
		} else if (calificacion >= 8 && calificacion < 9) {
			System.out.println("B");
		} else if (calificacion >= 7 && calificacion < 8) {
			System.out.println("C");
		} else if (calificacion >= 6 && calificacion < 7) {
			System.out.println("D");
		} else if (calificacion < 6 && calificacion >= 0) {
			System.out.println("F");
		} else {
			System.out.println("Valor desconocido");
		}
    }
	
} 
