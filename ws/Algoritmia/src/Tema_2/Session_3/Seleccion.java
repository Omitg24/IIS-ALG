package Tema_2.Session_3;
/** Este programa sirve para ordenar n elementos
	con un algoritmo de los "malos" (cuadrático)·
	es la SELECCION
 */
/**
 * Titulo: Clase Seleccion
 *
 * @author Omar Teixeira, UO281847
 * @version 25 feb 2022
 */
public class Seleccion extends Vector
{
	/**
	 * Constructor Seleccion
	 * @param nElementos
	 */
	public Seleccion(int nElementos) {
		super(nElementos);
	}
	
	/**
	 * Ordenaci�n por selecci�n
	 */
	@Override
	public void ordenar() {
		int n = elements.length;
		
		int minPos;
		for (int i = 0; i < n - 1; i++) {
			minPos = i;
			for (int j=i+1; j < n; j++) {
				if (elements[j] < elements[minPos]) {
					minPos = j;
				}
				intercambiar(i,j);
			}
			
		}
	}  

	/**
	 * M�todo que devuelve el nombre
	 */
	@Override
	public String getNombre() {		
		return "Selecci�n";
	}  
} 