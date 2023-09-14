package alg77777777.p2;

import alg77777777.p2.Vector;

/** Este programa sirve para ordenar n elementos con un algoritmo QUICKSORT
	Pero como selecciona como pivote el elemento primero de la partición, si se
	le mete el array ordenado tiene un comportamiento fatal (cuadrático). 
 */
public class RapidoFatal extends Vector
{

	public RapidoFatal(int nElementos) {
		super(nElementos);
	}
	
	/** Deja el	pivote en una posición tal que a su izquierda no hay 
		ningún mayor, ni a la derecha ningún menor.
		es un proceso lineal O(n). 
	 */
	private int particion (int iz, int de) 
	{
		int i, pivote;
		pivote= this.elements[iz]; //el pivote es el primero
		i= iz;
		for (int s= iz+1; s <= de; s++) 
			if (this.elements[s] <= pivote) 
			{
				i++;
				intercambiar(i, s);
			}
		intercambiar(iz, i);
		//se restituye el pivote donde debe estar
		return i; // retorna la posicion en que queda el pivote 
	}

	private void rapirec (int iz, int de) 
	{
		int m;
		if (de>iz) 
		{
			m=particion(iz, de);
			rapirec(iz, m-1);
			rapirec(m+1, de);
		}
	}

	//Antes se llamaba 'rapido2'
	@Override
	public void ordenar() {
		rapirec(0,this.elements.length-1);		
	}

	@Override
	public String getNombre() {
		return "Rápido pivote inicial";
	}
} 

