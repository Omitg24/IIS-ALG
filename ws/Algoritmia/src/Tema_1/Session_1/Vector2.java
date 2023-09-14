package Tema_1.Session_1;

/**
 * Titulo: Clase Vector2
 * 
 * @author Omar Teixeira, UO281847
 * @version 14 feb 2022
 */
public class Vector2 {

	/**
	 * Vector
	 */
	static int[] v;
	
	/**
	 * M�todo Main
	 * @param args
	 */
	public static void main(String[] args) {			
		int n = Integer.parseInt(args[0]);
		
		System.out.println("El tama�o del vector es: " + n);
		
		v = new int[n];
		
		Vector1.rellena(v);
		
		long initialTimeS = System.currentTimeMillis();
		
		int s = Vector1.suma(v);
		System.out.println ("\tSuma de los elementos del vector = "+ s);
		
		long finalTimeS = System.currentTimeMillis();

		long initialTimeM = System.currentTimeMillis();
		
		int [] m = new int [2];
		Vector1.maximo(v,m);
		System.out.println ("\tValor del m�ximo del vector = "+ m[1]);
		System.out.println ("\tPosici�n del m�ximo del vector = "+ m[0]);
		
		long finalTimeM = System.currentTimeMillis();
		
		int timeS = (int) (finalTimeS - initialTimeS);
		int timeM = (int) (finalTimeM - initialTimeM);
		
		System.out.println("El tiempo de ejecuci�n de la suma de los elementos del vector es: " + timeS);
		System.out.println("El tiempo de ejecuci�n de obtener el m�ximo del vector es: " + timeM + "\n\n");
	}

}
