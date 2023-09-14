package Tema_5.Session_7;

import java.util.Random;

/**
 * Titulo: Clase LevenshteinTiempos
 *
 * @author Omar Teixeira, UO281847
 * @version 29 mar 2022
 */
public class LevenshteinTiempos {

	/**
	 * Método main
	 * @param args
	 */
	public static void main(String[] args) {
		long t1, t2, tFinal;
		int nVeces = Integer.valueOf(args[0]);
		
		for (int n = 100; n <= 10_000_000; n*=2) {
			t1 = System.currentTimeMillis();
			
			for (int j = 0; j <= nVeces; j++) {
				new Levenshtein(generateCad(n), generateCad(n));
			}
			
			t2 = System.currentTimeMillis();			
			tFinal = t2 - t1;
			
			System.out.println ("Levenshtein: n="+n+ "**TIEMPO="+ tFinal +"**nVeces="+nVeces);			
		}		
	}
	
	/**
	 * Método que genera una cadena aleatoria
	 * @param n
	 * @return randomCad
	 */
	public static String generateCad(int n) {
		Random r = new Random();
		String randomCad = "";
		for (int i=0; i<=n; i++) {
			randomCad += Character.toString(r.nextInt((90-65+1))+65);
		}
		return randomCad;
	}
}
