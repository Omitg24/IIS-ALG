package alg77777777.p2;


/** Este programa sirve para ordenar n elementos
	con el algoritmo mejor. Es el QUICKSORT
 */
public class RapidoMediana extends Vector
{
	
	public RapidoMediana(int nElementos) {
		super(nElementos);
	}
	

	/**
	 * Calcula la mediana entre tres elementos en el vector:
	 * el primero, el último y el central
	 * Coloca estos tres elementos de tal forma que queden 
	 * el menor la la izquierda, la mediana en el centro y el mayor la la derecha
	 * @return la posición que ocupa la mediana entre estos tres elemenos
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


	/** Deja el	pivote en una posicion tal que a su izquierda no 
		hay ningún mayor, ni a la derecha ningun menor.
		Es un proceso lineal O(n).  
	 */
	private int particion(int iz, int de) 
	{
		// TODO: Implementación del método de partición
		return -1;
	}

	/**
	 * Ordenación por el método rápido (quicksort)
	 * Método divide y vencerás de complejidad estudiada en clase
	 */  
	private void rapirec (int iz, int de) 
	{
		// TODO: Implementación del método de creación de la partición
	}


	@Override
	public void ordenar() {
		// TODO: Implementación del método de ordenación
		
	}

	@Override
	public String getNombre() {
		return "Rápido mediana a tres";
	}

} 
