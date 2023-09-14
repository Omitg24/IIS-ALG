package Tema_1.Session_1;

/**
 * Titulo: Clase Vector3
 * 
 * @author Omar Teixeira, UO281847
 * @version 14 feb 2022
 */
public class Vector3 {

	/**
	 * Vector
	 */
	static int[] v;
	
	/**
	 * Método Main
	 * @param args
	 */
	public static void main(String[] args) {				
		int n = Integer.parseInt(args[0]);
		int max = Integer.parseInt(args[1]);
		int multi = Integer.parseInt(args[2]); 
		
		for (int i = n; i < max; i=i*multi) {
			System.out.println("El tamaño del vector es: " + i);
			
			v = new int[i];
			
			Vector1.rellena(v);			
			
			long initialTimeS = System.currentTimeMillis();
			
			int s = Vector1.suma(v);
			System.out.println ("\tSuma de los elementos del vector = "+ s);
			
			long finalTimeS = System.currentTimeMillis();
			
			long initialTimeM = System.currentTimeMillis();
			
			int [] m = new int [2];
			Vector1.maximo(v,m);
			System.out.println ("\tValor del máximo del vector = "+ m[1]);
			System.out.println ("\tPosición del máximo del vector = "+ m[0]);
			
			long finalTimeM = System.currentTimeMillis();
			
			int timeS = (int) (finalTimeS - initialTimeS);
			int timeM = (int) (finalTimeM - initialTimeM);
			
			System.out.println("El tiempo de ejecución de la suma de los elementos del vector es: " + timeS);
			System.out.println("El tiempo de ejecución de obtener el máximo del vector es: " + timeM + "\n\n");			
		}
	}
}
