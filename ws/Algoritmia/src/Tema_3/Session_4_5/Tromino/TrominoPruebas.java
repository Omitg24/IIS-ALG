package Tema_3.Session_4_5.Tromino;

/**
 * Titulo: Clase TrominoPruebas
 *
 * @author Omar Teixeira, UO281847
 * @version 15 mar 2022
 */
public class TrominoPruebas {

	/**
	 * M�todo Main
	 * @param arg
	 */
	public static void main(String[] arg) {
		//Prueba base
		Tromino tr = new Tromino(8, 3, 5);
		
		System.out.println("Tromin�: \n");
		tr.showBoard();
		
//		Prueba para todos los tama�os hasta el pasado por par�metro
//		int maxSize = Integer.parseInt(arg[0]);
//		for (int i=2; i<=maxSize;i*=2) {
//			System.out.println("Tromin� con tama�o = (" + i + "): \n"  );
//			new Tromino(i, 1, 1).showBoard();
//		}
	} 
}
