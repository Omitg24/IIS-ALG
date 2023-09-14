package alg77777777.p2;

import java.io.PrintStream;
import java.util.Random ;

/**
	Clase de utilidad, que genera para un vector los 3 órdenes 
	iniciales posibles: ordenado, inverso y aleatorio.
	También escribe el contenido de un vector
 */
public abstract class Vector
{
	protected int[] elements;
	
	public Vector(int nElements) {
		inicializa(nElements);
	}
	
	/**	Este método inicializa el Vector con valores.
	 *  También se puede utilizar para reinicializar
	 *  con un nuevo tamaño de vector.
	 */
	public void inicializa(int n) {
		this.elements = new int[n];
		this.ordenAleatorio();
	}

	/**	Este método da valores ordenados
	 */
	public void ordenDirecto()
	{
		int n = this.elements.length;
		for(int i=0;i<n;i++)
			this.elements[i]=i;
	}

	/** Este método da valores ordenados pero de manera inversa
	 */
	public void ordenInverso()
	{
		int n = this.elements.length;
		for(int i=0;i<n;i++)
			this.elements[i]=n-i-1;
	}     

	/**
	 * Este método da valores aleatorios a un vector de enteros, 
	 * utiliza para ello la clase Random del paquete java.util
	 */
	public void ordenAleatorio()
	{
		ordenAleatorio(1000000);
	}
	
	/**
	 * Este método da valores aleatorios a un vector de enteros, 
	 * utiliza para ello la clase Random del paquete java.util
	 * @param maxAlea - máximo número aleatorio para rellenar
	 */
	public void ordenAleatorio(int maxAlea)
	{
		Random r= new Random ();
		int n= this.elements.length;
		for(int i=0;i<n;i++)
			this.elements[i]=r.nextInt (maxAlea);//valores entre 0 y maxAlea
	}     

	/**	Este método escribe los componentes del vector
	 */
	public void escribe(PrintStream outStream)
	{
		int n= this.elements.length;
		System.out.print("(");
		for (int i=0; i<n; i++ )
			outStream.print(String.format("%s%s", this.elements[i], ", "));
		outStream.println(")");
	}
	
	/**
	 * Intercambia los elementos de las posiciones i, j en el array a
	 * es O(1)
	 */
	protected void intercambiar(int i, int j)
	{
		int t;
		t = this.elements[i];
		this.elements[i] = this.elements[j];
		this.elements[j] = t;
	}
	
	/**
	 * Método que ordena los elementos
	 * Se implementará un método específico de ordenación en cada subclase
	 */
	public abstract void ordenar();
	
	/**
	 * Devuelve el nombre del método de ordenación
	 * Se implementará en cada subclase
	 * @return
	 */
	public abstract String getNombre();


	

}
