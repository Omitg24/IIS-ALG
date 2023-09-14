package Tema_3.Session_4_5.Tromino;

/**
 * Titulo: Clase TrominoPruebas
 *
 * @author Omar Teixeira, UO281847
 * @version 15 mar 2022
 */
public class TrominoPruebas {

	/**
	 * Método Main
	 * @param arg
	 */
	public static void main(String[] arg) {
		//Prueba base
		Tromino tr = new Tromino(8, 3, 5);
		
		System.out.println("Trominó: \n");
		tr.showBoard();
		
//		Prueba para todos los tamaños hasta el pasado por parámetro
//		int maxSize = Integer.parseInt(arg[0]);
//		for (int i=2; i<=maxSize;i*=2) {
//			System.out.println("Trominó con tamaño = (" + i + "): \n"  );
//			new Tromino(i, 1, 1).showBoard();
//		}
	} 
}
