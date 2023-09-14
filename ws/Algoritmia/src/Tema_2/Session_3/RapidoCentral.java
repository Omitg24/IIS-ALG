package Tema_2.Session_3;

/** Este programa sirve para ordenar n elementos
	con el algoritmo mejor. Es el QUICKSORT
 */
/**
 * Titulo: Clase RapidoCentral
 *
 * @author Omar Teixeira, UO281847
 * @version 25 feb 2022
 */
public class RapidoCentral extends Vector
{
	/**
	 * Constructor RapidoCentral
	 * @param n
	 */
	public RapidoCentral(int n) {
		super(n);
	}	

	/** 
	 * Deja el	pivote en una posicion tal que a su izquierda no 
	 * hay ning�n mayor, ni a la derecha ningun menor.
	 * Es un proceso lineal O(n).  
	 */
	private int particion(int iz, int de) 
	{
		int i, pivote;
		intercambiar ((iz+de)/2,iz);
		//el pivote es el de centro y se cambia con el primero
		pivote= this.elements[iz]; 
		i= iz;
		for (int s= iz+1; s <= de; s++) 
			if (this.elements[s] <= pivote) 
			{
				i++;
				intercambiar(i,s);
			}
		intercambiar(iz,i);//se restituye el pivote donde debe estar
		return i; // retorna la posicion en que queda el pivote 
	}

	/**
	 * Ordenaci�n por el m�todo r�pido (quicksort)
	 * M�todo divide y vencer�s de complejidad estudiada en clase
	 */  
	private void rapirec (int iz, int de) 
	{
		int m;
		if (de>iz) 
		{
			m=particion(iz,de);
			rapirec(iz,m-1);
			rapirec(m+1,de);
		}
	}
	
	/**
	 * M�todo que ordena
	 */
	@Override
	public void ordenar() {
		rapirec(0, this.elements.length-1);		
	}

	/**
	 * M�todo que devuelve el nombre
	 */
	@Override
	public String getNombre() {
		return "R�pido pivote central";
	}
} 
