package Tema_1.Session_1;

import Tema_1.Session_0.MatrizOperaciones;

/**
 * Titulo: Clase MatrizOperacionesTiempos
 * 
 * @author Omar Teixeira, UO281847
 * @version 15 feb 2022
 */
public class MatrizOperacionesTiempos {

	/**
	 * Matriz
	 */
	static MatrizOperaciones matrix;
	
	/**
	 * Método Main
	 * @param args
	 */
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int max = Integer.parseInt(args[1]);
		int multi = Integer.parseInt(args[2]);
		int reps = Integer.parseInt(args[3]);
		
		for (int i = n; i < max; i=i*multi) {
			System.out.println("El tamaño de la matriz es: " + i);
			
			matrix = new MatrizOperaciones(i, 0, max);
			
			long initialTimeD1 = System.currentTimeMillis();
			
			for (int j = 0; j < reps; j++) {
				matrix.sumarDiagonal1();
			}			
			
			long finalTimeD1 = System.currentTimeMillis();
			
			long initialTimeD2 = System.currentTimeMillis();
			
			for (int j = 0; j < reps; j++) {
				matrix.sumarDiagonal2();
			}
			
			long finalTimeD2 = System.currentTimeMillis();
			
			int timeD1 = (int) (finalTimeD1 - initialTimeD1);
			int timeD2 = (int) (finalTimeD2 - initialTimeD2);
			
			System.out.println("El tiempo de ejecución de la suma de los elementos de la diagonal 1 es: " + timeD1);
			System.out.println("El tiempo de ejecución de la suma de los elementos de la diagonal 2 es: " + timeD2 + "\n\n");
		}		
	}
	
}
