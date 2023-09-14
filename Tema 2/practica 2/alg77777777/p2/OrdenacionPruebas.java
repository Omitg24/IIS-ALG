package alg77777777.p2;

public class OrdenacionPruebas {

	/** Este programa sirve para probar todos los algoritmos de ordenacion
	 * */
	public static void main (String arg []){
		int n= Integer.parseInt(arg[0]);  //tamaño del problema
		
		
		// Prueba ordenación: Insercion
		pruebaAlgoritmoOrdenacion(new Insercion(n));
		
		// Prueba ordenación: Seleccion
		pruebaAlgoritmoOrdenacion(new Seleccion(n));
		
		// Prueba ordenación: Burbuja
		pruebaAlgoritmoOrdenacion(new Burbuja(n));
		
		// Prueba ordenación: RapidoFatal
		pruebaAlgoritmoOrdenacion(new RapidoFatal(n));
		
		// Prueba ordenación: RapidoCentral
		pruebaAlgoritmoOrdenacion(new RapidoCentral(n));
		
		// Prueba ordenación: RapidoMediana
		pruebaAlgoritmoOrdenacion(new RapidoMediana(n));
	}
	
	public static void pruebaAlgoritmoOrdenacion(Vector v) {
		System.out.println(" \n\nPrueba ordenación: "+v.getNombre());
		System.out.println ("Ordenar Vector ya ordenado");
		v.ordenDirecto();
		System.out.println ("Vector a ordenar es:");
		v.escribe(System.out);	
		v.ordenar();
		System.out.println ("Vector después de ordenar");
		v.escribe(System.out);

		System.out.println ("Ordenar Vector inverso");
		v.ordenInverso();
		System.out.println ("Vector a ordenar es:");
		v.escribe(System.out);	
		v.ordenar();
		System.out.println ("Vector después de ordenar");
		v.escribe(System.out);

		System.out.println ("Ordenar Vector aleatorio");
		v.ordenAleatorio();
		System.out.println ("Vector a ordenar es:");
		v.escribe(System.out);	
		v.ordenar();
		System.out.println ("Vector después de ordenar");
		v.escribe(System.out);
	}
	
}
