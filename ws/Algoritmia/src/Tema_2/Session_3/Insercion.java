package Tema_2.Session_3;

/** Este programa sirve para ordenar n elementos
	con un algoritmo de los "malos" (cuadrático)· 
	Es la INSERCIÓN DIRECTA
 */
/**
 * Titulo: Clase Insercion
 *
 * @author Omar Teixeira, UO281847
 * @version 25 feb 2022
 */
public class Insercion extends Vector
{
	
	/**
	 * Constructor Insercion
	 * @param nElementos
	 */
	public Insercion(int nElementos) {
		super(nElementos);
	}

	/**
	 * Ordenaci�n por inserci�n directa
	 */
	@Override
	public void ordenar() {
		int n = elements.length;
		for (int i = 1; i < n; i++) {
			int x = elements[i];
			int j = i - 1;
			while (j>=0 && x<elements[j]) {
				elements[j+1]=elements[j];
				j--;
			}
			elements[j+1]=x;
		}
	} 

	/**
	 * M�todo que devuelve el nombre
	 */
	@Override
	public String getNombre() {
		return "Inserci�n directa";
	} 
} 
