package alg77777777.p2;


public class OrdenacionTiempos {
	private static int nTam= 20;
	private static int nVeces= 1;

	/** Este programa sirve para probar todos los algoritmos de ordenacion
	 * */
	public static void main (String arg []){
		String opcion= arg[0];
		
		int[] it = new int[nTam];
		int i= 0;
		for (int tam= 1_000; tam<=Integer.MAX_VALUE && i<it.length; tam*= 2) {
			it[i]= tam;
			i++;
		}
//		int[] it = new int[] { 1_000, 2_000, 4_000, 8_000, 16_000, 32_000, 64_000, 128_000, 256_000, 512_000, 1_024_000,
//				2_048_000, 4_096_000, 8_192_000, 16_384_000, 32_768_000, 65_536_000, 131_072_000, 262_144_000, 524_288_000 };
		int n = it[0];
		System.out.println("i= "+i);
		
		
		// Medir tiempo: Insercion
		medirTiempos(new Insercion(n), it, opcion);
		
		// Medir tiempo: Seleccion
		medirTiempos(new Seleccion(n), it, opcion);
		
		// Medir tiempo: Burbuja
		medirTiempos(new Burbuja(n), it, opcion);
		
		// Medir tiempo: RapidoFatal
		medirTiempos(new RapidoFatal(n), it, opcion);
		
		// Medir tiempo: RapidoCentral
		medirTiempos(new RapidoCentral(n), it, opcion);
		
		// Medir tiempo: RapidoMediana
		medirTiempos(new RapidoMediana(n), it, opcion);
		

	}
	
	public static void medirTiempos(Vector v, int[] iteraciones, String opcion) {
		long t1,t2;
		
		System.out.println(" \n\nMedir tiempo: "+v.getNombre());
		System.out.println ("n   \tTiempo");
		for (int n : iteraciones) {
			v.inicializa(n);
			if (opcion.compareTo("ordenado")==0)
				v.ordenDirecto();
			else if (opcion.compareTo("inverso")==0)
				v.ordenInverso();
			else
				v.ordenAleatorio();

			
			t1 = System.currentTimeMillis();
			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				v.ordenar();
			}
			t2 = System.currentTimeMillis();

			System.out.println (n+"\t"+(t2-t1));
		}
	}
	
}
