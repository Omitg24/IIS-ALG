package Tema_3.Session_4_5.Tromino;

/**
 * Titulo: Clase TrominoTiempos
 *
 * @author Omar Teixeira, UO281847
 * @version 16 mar 2022
 */
public class TrominoTiempos {
	/**
	 * Método Main
	 * @param arg
	 */
	public static void main(String[] args) {		
		long t1, t2, tFinal;
		int nVeces = Integer.parseInt(args[0]);
		int posX = Integer.parseInt(args[1]);
		int posY = Integer.parseInt(args[2]);
		
		for (int n=16; n<10_000_000; n*=2) {
			t1 = System.currentTimeMillis();
			
			for (int j=0; j<=nVeces; j++) {
				new Tromino(n, posX, posY);
			}			
			
			t2 = System.currentTimeMillis();
			tFinal = t2 - t1;
			
			System.out.println ("Trominó: n="+n+ "**TIEMPO="+ tFinal +"**nVeces="+nVeces);
		}
	}
}
