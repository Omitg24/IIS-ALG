package Tema_2.Session_3;

import java.io.PrintStream;
import java.util.Random ;

/**
	Clase de utilidad, que genera para un vector los 3 órdenes 
	iniciales posibles: ordenado, inverso y aleatorio.
	También escribe el contenido de un vector
 */
/**
 * Titulo: Clase Vector
 *
 * @author Omar Teixeira, UO281847
 * @version 25 feb 2022
 */
public abstract class Vector
{
	/**
	 * Elements
	 */
	protected int[] elements;
	
	/**
	 * Constructor Vector
	 * @param nElements
	 */
	public Vector(int nElements) {
		inicializa(nElements);
	}
	
	/**	
	 * Este m�todo inicializa el Vector con valores.
	 * Tambi�n se puede utilizar para reinicializar
	 * con un nuevo tama�o de vector.
	 */
	public void inicializa(int n) {
		this.elements = new int[n];
		this.ordenAleatorio();
	}

	/**	
	 * Este m�todo da valores ordenados
	 */
	public void ordenDirecto()
	{
		int n = this.elements.length;
		for(int i=0;i<n;i++)
			this.elements[i]=i;
	}

	/** 
	 * Este m�todo da valores ordenados pero de manera inversa
	 */
	public void ordenInverso()
	{
		int n = this.elements.length;
		for(int i=0;i<n;i++)
			this.elements[i]=n-i-1;
	}     

	/**
	 * Este m�todo da valores aleatorios a un vector de enteros, 
	 * utiliza para ello la clase Random del paquete java.util
	 */
	public void ordenAleatorio()
	{
		ordenAleatorio(1000000);
	}
	
	/**
	 * Este m�todo da valores aleatorios a un vector de enteros, 
	 * utiliza para ello la clase Random del paquete java.util
	 * @param maxAlea - m�ximo n�mero aleatorio para rellenar
	 */
	public void ordenAleatorio(int maxAlea)
	{
		Random r= new Random ();
		int n= this.elements.length;
		for(int i=0;i<n;i++)
			this.elements[i]=r.nextInt (maxAlea);//valores entre 0 y maxAlea
	}     

	/**	
	 * Este m�todo escribe los componentes del vector
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
	 * M�todo que ordena los elementos
	 * Se implementar� un m�todo espec�fico de ordenaci�n en cada subclase
	 */
	public abstract void ordenar();
	
	/**
	 * Devuelve el nombre del m�todo de ordenaci�n
	 * Se implementar� en cada subclase
	 * @return
	 */
	public abstract String getNombre();


	

}
