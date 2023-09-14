package Tema_6.Session_8_9;

import java.nio.file.Paths;

/**
 * Titulo: Clase PromediadorImagenBench
 * 
 * @author Omar Teixeira, UO281847
 * @version 1 abr 2022
 */
public class PromediadorImagenBench {
	
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
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "/img/out_bt";
	/**
	 * Directorio Backtracking con Poda
	 */
	private static String OUT_DIR_BP = Paths.get("").toAbsolutePath().toString() + "/img/out_btp";
	/**
	 * Numero de imagenes
	 */
	private static int N_IMGS = 6;
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
		PromediadorImagen img_avger;
		
		// Generación y testeo de un conjunto de imágenes
		n_bad = (int) ((PORCENTAJE_BAD/100.)*N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
				
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

		// Medidas
		long t1, t2, tFinal;
		int nVeces = Integer.valueOf(args[0]);
		
		System.out.println("MEDICION TIEMPOS:");
		for (int n = 1; n<=16384; n++) {		
			n_bad = (int) ((PORCENTAJE_BAD/100.)*n);
			n_real = n - n_bad;
			img_avger = new PromediadorImagen(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
			//VORAZ				
			t1 = System.currentTimeMillis();			
			for (int j = 0; j <= nVeces; j++) {
				img_avger.splitSubsetsGreedy(n);
			}			
			t2 = System.currentTimeMillis();			
			tFinal = t2 - t1;
			
			System.out.println ("VORAZ: PromediadorImagen n=" + n + " **TIEMPO="+ tFinal +" **nVeces=" + nVeces + " **ZNCC=" + img_avger.zncc() + " **Contador=" + img_avger.getCounter());
			
			if (n<=12) {
				//BACKTRACKING SIN PODA
				t1 = System.currentTimeMillis();				
				for (int j = 0; j <= nVeces; j++) {
					img_avger.splitSubsetsBacktracking();
				}				
				t2 = System.currentTimeMillis();			
				tFinal = t2 - t1;
				
				System.out.println ("BACKTRACKING SIN PODA: PromediadorImagen n=" + n + " **TIEMPO="+ tFinal/nVeces +" **nVeces=" + nVeces + " **ZNCC=" + img_avger.zncc() + " **Contador=" + img_avger.getCounter());
				
				//BACKTRACKING CON PODA
				t1 = System.currentTimeMillis();				
				for (int j = 0; j <= nVeces; j++) {
					img_avger.splitSubsetsBacktracking(1);
				}				
				t2 = System.currentTimeMillis();			
				tFinal = t2 - t1;
				
				System.out.println ("BACKTRACKING CON PODA: PromediadorImagen n=" + n + " **TIEMPO="+ tFinal/nVeces +" **nVeces=" + nVeces + " **ZNCC=" + img_avger.zncc() + " **Contador=" + img_avger.getCounter());
			}			
		}
	}
}

