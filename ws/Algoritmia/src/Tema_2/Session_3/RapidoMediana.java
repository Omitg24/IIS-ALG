package Tema_2.Session_3;

/** Este programa sirve para ordenar n elementos
	con el algoritmo mejor. Es el QUICKSORT
 */
/**
 * Titulo: Clase RapidoMediana
 *
 * @author Omar Teixeira, UO281847
 * @version 25 feb 2022
 */
public class RapidoMediana extends Vector
{
	/**
	 * Constructor RapidoMediana
	 * @param nElementos
	 */
	public RapidoMediana(int nElementos) {
		super(nElementos);
	}
	

	/**
	 * Calcula la mediana entre tres elementos en el vector:
	 * el primero, el √∫ltimo y el central
	 * Coloca estos tres elementos de tal forma que queden 
	 * el menor la la izquierda, la mediana en el centro y el mayor la la derecha
	 * @return la posici√≥n que ocupa la mediana entre estos tres elemenos
	 */
	private  int getMediana3(int iz, int de, int cen){
		// intercambiaremos los 3 elementos entre si para colocar la mediana en el medio
		if ( elements[iz] > elements[cen] )
			intercambiar(iz,cen);
		if ( elements[iz] > elements[de] )
			intercambiar(iz,de);
		if ( elements[cen] > elements[de] )
			intercambiar(cen,de);
		return cen;
	}


	/** 
	 * Deja el	pivote en una posicion tal que a su izquierda no 
	 * hay ning√∫n mayor, ni a la derecha ningun menor.
	 * Es un proceso lineal O(n).  
	 */
	private int particion(int iz, int de) 
	{
		int i, pivote;
		intercambiar(getMediana3(iz, de, (iz+de)/2), iz);
		pivote = this.elements[iz];
		i = iz;
		for (int s = iz + 1; s <= de; s++) {
			if (this.elements[s] <= pivote) {
				i++;
				intercambiar(i, s);
			}
		}
		intercambiar(iz, i);
		return i;
	}

	/**
	 * OrdenaciÛn por el m√©todo r√°pido (quicksort)
	 * MÈtodo divide y vencer·s de complejidad estudiada en clase
	 */  
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

	/**
	 * MÈtodo ordenar
	 */
	@Override
	public void ordenar() {
		rapirec(0,this.elements.length-1);	
	}

	/**
	 * MÈtodo que devuelve el nombre
	 */
	@Override
	public String getNombre() {
		return "R·pido mediana a tres";
	}

} 
