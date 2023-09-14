package Tema_1.Session_0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Titulo: Clase MatrizOperaciones
 *
 * @author Omar Teixeira, UO281847
 * @version 8 feb 2022
 */
public class MatrizOperaciones {
	/**
	 * Atributo matrix
	 */
	private int[][] matrix;
	
	/**
	 * Constructor MatrizOperaciones
	 * 
	 * @param n, tamaño
	 * @param min, valor minimo
	 * @param max, valor máximo
	 */
	public MatrizOperaciones(int n, int min, int max) {
		matrix = new int[n][n];
		Random r = new Random();
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				matrix[i][j] = r.nextInt(max - min) + min;
			}
		}
	}
	
	/**
	 * Constructor que lee los datos de la matriz del nombre de un fichero
	 * 
	 * @param nomFich, nombre del fichero
	 */
	public MatrizOperaciones (String nomFich) {
		int tam;
		int counter = 0;
	    String linea;	    
	    String[] filaMatriz= null;	   
	    
	    try {
	    	   BufferedReader fichero = new BufferedReader(new FileReader(nomFich));
	    		while (fichero.ready()) {
	    			linea = fichero.readLine();
	    			if (!linea.contains("\t")) {
	    				tam = Integer.valueOf(linea);
	    				matrix = new int[tam][tam];
	    			} else {
	    				filaMatriz = linea.split("\t");
	    				for (int i=0; i<filaMatriz.length; i++) {
	    					matrix[counter][i]=Integer.valueOf(filaMatriz[i]);
	    				}
	    				counter++;
	    			}	    			
	    		}
	    		fichero.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("El archivo no se ha encontrado.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("Error de entrada/salida.");
	    } 
	  }
	
	/**
	 * Método que devuelve el tamaño de la matriz
	 * 
	 * @return tam, tamaño
	 */
	public int getTam() {
		return matrix.length;
	}
	
	/**
	 * Método que imprime la matriz
	 */
	public void escribir() {
		int tam = getTam();
		for (int i=0; i<tam; i++) {
			for (int j=0; j<tam; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Método que suma la diagonal de la primera forma
	 * 
	 * @return suma, suma diagonal
	 */
	public int sumarDiagonal1() {
		int suma = 0;
		int tam = getTam();
		for (int i=0; i<tam; i++) {
			for (int j=0; j<tam; j++) {
				if (i==j) {
					suma = suma + matrix[i][j];
				}
			}			
		}
		return suma;
	}
	
	/**
	 * Método que suma la diagonal de la primera forma
	 * 
	 * @return suma, suma diagonal
	 */
	public int sumarDiagonal2() {
		int suma = 0;
		int tam = getTam();
		for (int i=0; i<tam; i++) {
			suma = suma + matrix[i][i];
		}
		return suma;
	}
	
	/**
	 * Método que recorre el camino de la matriz
	 * 
	 * @param i, posicion i
	 * @param j, posicion j
	 * @return
	 */
	public void recorrerCamino(int i, int j) {
		int value = matrix[i][j];
		
		while (i<matrix.length && j<matrix.length) {
			if (value == 1) {
				matrix[i][j]=-1;
				i--;
			} else if (value == 2) {
				matrix[i][j]=-1;
				j++;
			} else if (value == 3) {
				matrix[i][j]=-1;
				i++;
			} else if (value == 4) {
				matrix[i][j]=-1;
				j--;
			} else {
				break;
			}
		}
	}
}