package Opcionales;

import java.util.concurrent.ForkJoinPool;

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
		int n = 8;
		TrominoParalelo trParalelo = new TrominoParalelo(3, 5, 0, 0, n, new int[n][n]);
		ForkJoinPool fj= new ForkJoinPool();
		fj.invoke(trParalelo);
		trParalelo.showBoard();
	}
}
