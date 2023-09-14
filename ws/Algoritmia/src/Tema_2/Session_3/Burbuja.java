package Tema_2.Session_3;

/**
 * Titulo: Clase Burbuja
 *
 * @author Omar Teixeira, UO281847
 * @version 25 feb 2022
 */
public class Burbuja extends Vector
{
	/**
	 * Constructor Burbuja
	 * @param nElementos
	 */
	public Burbuja(int nElementos) {
		super(nElementos);
	}

	/**
	 * Ordenaci�n por el m�todo de Burbuja
	 */
	@Override
	public void ordenar() {
		int n = elements.length;
		
		for (int i = 0; i <= n - 2; i++) {
			for (int j = n - 1; j > i; j--) {
				if (elements[j - 1] > elements[j]) {
					intercambiar(j - 1, j);
				}
			}
		}
	}  

	/**
	 * M�todo que devuelve el nombre
	 */
	@Override
	public String getNombre() {
		return "Burbuja";
	}  
} 