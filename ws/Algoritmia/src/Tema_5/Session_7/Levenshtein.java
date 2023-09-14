package Tema_5.Session_7;

/**
 * Titulo: Clase Levenshtein
 *
 * @author Omar Teixeira, UO281847
 * @version 25 mar 2022
 */
public class Levenshtein {
	/**
	 * Matrix
	 */
	private int[][] matrix;
	/**
	 * Cadena 1
	 */
	private String cad1;
	/**
	 * Cadena 2
	 */
	private String cad2;
	/**
	 * Resultado
	 */
	private int result;
	
	/**
	 * Constructor
	 * @param cad1
	 * @param cad2
	 */
	public Levenshtein(String cad1, String cad2) {
		this.cad1=cad1;
		this.cad2=cad2;
		distanciaLevenshtein(cad1, cad2);
		showResult();
	}

	/**
	 * Método que calcula la distancia de Levenshtein
	 * @param cad1
	 * @param cad2
	 */
	private void distanciaLevenshtein(String cad1, String cad2) {
		matrix = new int[cad2.length()+1][cad1.length()+1];
		prepareMatrix();
		for (int i=1; i<matrix.length; i++) {
			for (int j=1; j<matrix[i].length; j++) {
				if (cad2.charAt(i-1) == cad1.charAt(j-1)) {
					matrix[i][j] = matrix[i-1][j-1];
				} else if (cad2.charAt(i-1) != cad1.charAt(j-1)) {
					matrix[i][j] = 1 + min(matrix[i-1][j-1], matrix[i][j-1], matrix[i-1][j]);
				}
			}
		}
		result = matrix[cad2.length()][cad1.length()];		
	}
	
	/**
	 * Método que calcula el minimo
	 * @param a
	 * @param b
	 * @param c
	 * @return minimo
	 */
	private int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
	
	/**
	 * Método que prepara la matriz
	 */
	private void prepareMatrix() {
		for (int i=0; i < matrix.length; i++) {
			matrix[i][0]=i;			
		}
		for (int j=0; j<matrix[0].length; j++) {
			matrix[0][j]=j;
		}
	}	
	
	/**
	 * Método que imprime el resultado
	 */
	private void showResult() {
		System.out.println("Distancia de Levenshtein:\n");
		for (int i = 0; i < cad1.length(); i++) {
			if (i==0) {
				System.out.print("        " + cad1.charAt(i) + "   ");
			} else if (i==cad1.length()-1) {
				System.out.print(cad1.charAt(i) + "\n");
			} else  {
				System.out.print(cad1.charAt(i) + "   ");
			}
			
		}
		for (int i = 0; i < matrix.length; i++) {		
			if (i==0) {
				System.out.print("    ");
			} else {
				System.out.print(cad2.charAt(i-1) + "   ");
			}
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j]>=100) {
					System.out.print(matrix[i][j] + " ");
				} else if (matrix[i][j]>=10) {
					System.out.print(matrix[i][j] + "  ");
				} else {
					System.out.print(matrix[i][j] + "   ");
				}			
			}
			System.out.println();
		}
		
		System.out.println("\nEl resultado es: " + result);
	}
}
