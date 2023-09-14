package s6_es;

import java.nio.file.Paths;

import s6.ImageAverager;

public class PromediadorImagenBench {
	
	// Ajustes del banco de pruebas
	private static String REAL_IMG = Paths.get("").toAbsolutePath().toString() + "/algprofesor/s6_es/einstein_1_256.png";
	private static String BAD_IMG = Paths.get("").toAbsolutePath().toString() + "/algprofesor/s6_es/einstein_1_256.png";
	private static String OUT_DIR_G = Paths.get("").toAbsolutePath().toString() + "/algprofesor/s6_es/out_g/";
	private static String OUT_DIR_B = Paths.get("").toAbsolutePath().toString() + "/algprofesor/s6_es/out_bt";
	private static int N_IMGS = 13; 
	private static double PORCENTAJE_BAD = 25; // %
	private static double S_NOISE = 5.0; // Nivel de ruido - desvición estándar de una distrubución Gaussiana
		
	public static void main(String[] args) {
		
		int n_real, n_bad;
		ImageAverager img_avger;
		
		// Generación y testeo de un conjunto de imágenes
		n_bad = (int) ((PORCENTAJE_BAD/100.)*N_IMGS);
		n_real = N_IMGS - n_bad;
		img_avger = new ImageAverager(REAL_IMG, BAD_IMG, n_real, n_bad, S_NOISE);
				
		System.out.print("TESTING VORAZ:\n");
		img_avger.splitSubsetsGreedy(N_IMGS);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_G);
			
		System.out.print("TESTING BACKTRACKING CON BALANCEO:\n");
		img_avger.splitSubsetsBacktracking(1);
		System.out.printf("  -ZNCC: %f\n",  img_avger.zncc());
		System.out.printf("  -Contador: %d\n",  img_avger.getCounter());
		img_avger.saveResults(OUT_DIR_B);

		// Medidas
		// TODO
	}

}

