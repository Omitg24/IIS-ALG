package alg77777777.p2;

import alg77777777.p2.Vector;

/** Este programa sirve para ordenar n elementos
	con el algoritmo mejor. Es el QUICKSORT
 */
public class RapidoCentral extends Vector
{
	
	public RapidoCentral(int n) {
		super(n);
	}
	

	/** Deja el	pivote en una posicion tal que a su izquierda no 
		hay ningún mayor, ni a la derecha ningun menor.
		Es un proceso lineal O(n).  
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
	 * Ordenación por el método rápido (quicksort)
	 * Método divide y vencerás de complejidad estudiada en clase
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


	@Override
	public void ordenar() {
		rapirec(0, this.elements.length-1);		
	}

	@Override
	public String getNombre() {
		return "Rápido pivote central";
	}
} 
