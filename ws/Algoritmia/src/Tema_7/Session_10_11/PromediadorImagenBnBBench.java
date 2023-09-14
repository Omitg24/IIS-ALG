package Tema_7.Session_10_11;

import java.nio.file.Paths;

/**
 * Titulo: Clase PromediadorImagenBench
 * 
 * @author Omar Teixeira, UO281847
 * @version 1 abr 2022
 */
public class PromediadorImagenBnBBench {
	
	// Ajustes del banco de pruebas
	/**
	 * Directorio Imagen Real
	 */
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "/img/einstein_1_256.png";
	/**
	 * Directorio Imagen Mala
	 */
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "/img/einstein_1_256.png";
	/**
	 * Directorio Greedy
	 */
	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString() + "/img/out_g/";
	/**
	 * Directorio Backtracking
	 */
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "/img/out_bt/";
	/**
	 * Directorio Backtracking con Poda
	 */
	private static String OUT_DIR_BP = Paths.get("").toAbsolutePath().toString() + "/img/out_btp/";
	/**
	 * Directorio Branch and Bounds
	 */
	private static String OUT_DIR_BNB = Paths.get("").toAbsolutePath().toString() + "/img/out_bnb/";
	/**
	 * Numero de imagenes
	 */
	private static int N_IMGS = 7;
	/**
	 * Porcentaje malo
	 */
	private static double PORCENTAJE_BAD = 25; // %
	/**
	 * Nivel de ruido
	 */
	private static double S_NOISE = 5.0; // Nivel de ruido - desviación estándar de una distrubución Gaussiana
		
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		int n_real, n_bad;
		PromediadorImagenBnB img_avger;
		
		// Generación y testeo de un conjunto de imágenes
		n_bad = (int) ((PORCENTAJE_BAD/100.)*N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new PromediadorImagenBnB(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
				
		System.out.print("TESTING VORAZ:\n");
		img_avger.splitSubsetsGreedy(N_IMGS);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_G);
			
		System.out.print("TESTING BACKTRACKING SIN BALANCEO:\n");
		img_avger.splitSubsetsBacktracking();
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);
		
		System.out.print("TESTING BACKTRACKING CON BALANCEO:\n");
		img_avger.splitSubsetsBacktracking(1);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_BP);
		
		System.out.print("TESTING BRANCH AND BOUND:\n");
		img_avger.branchAndBound();
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_BNB);
		
		// Medidas
		long t1, t2, tFinal;
		int nVeces = Integer.valueOf(args[0]);
		
		System.out.println("MEDICION TIEMPOS:");
		for (int n_imgs=1; n_imgs<12; n_imgs++) {
			n_bad = (int) ((PORCENTAJE_BAD/100.)*n_imgs);
			n_real = n_imgs - n_bad;
			img_avger = new PromediadorImagenBnB(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
						
			//BACKTRACKING CON BALANCEO
			t1 = System.currentTimeMillis();				
			for (int j = 0; j <= nVeces; j++) {
				img_avger.splitSubsetsBacktracking(1);
			}				
			t2 = System.currentTimeMillis();			
			tFinal = t2 - t1;
			
			System.out.println ("BACKTRACKING CON BALANCEO: PromediadorImagen n=" + n_imgs + " **TIEMPO="+ tFinal/nVeces +" **nVeces=" + nVeces + " **ZNCC=" + img_avger.zncc() + " **Contador=" + img_avger.getCounter());
			
			//BRANCH AND BOUND
			t1 = System.currentTimeMillis();				
			for (int j = 0; j <= nVeces; j++) {
				img_avger.branchAndBound();
			}				
			t2 = System.currentTimeMillis();			
			tFinal = t2 - t1;
			
			System.out.println ("BRANCH AND BOUND: PromediadorImagen n=" + n_imgs + " **TIEMPO="+ tFinal/nVeces +" **nVeces=" + nVeces + " **ZNCC=" + img_avger.zncc() + " **Contador=" + img_avger.getCounter());
		}
	}
}

