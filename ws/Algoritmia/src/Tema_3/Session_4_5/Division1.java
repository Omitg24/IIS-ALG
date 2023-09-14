package Tema_3.Session_4_5;

/**
 * Titulo: Clase Division1
 *
 * @author Omar Teixeira, UO281847
 * @version 3 mar 2022
 */
public class Division1
{
	/**
	 * cont
	 */
	static long cont;

	/**
	 * Método rec1
	 * @param n
	 * @return
	 */
	public static boolean rec1 (int n)
	{ 
		if (n<=0) 
			cont++;
		else
		{ 
			for (int i=1;i<n;i++) cont++ ;  //O(n)    
			rec1 (n/3);
		}
		return true;   
	}

	/**
	 * Método Main
	 * @param arg
	 */
	@SuppressWarnings("unused")
	public static void main (String arg []) 
	{
		long t1,t2,cont;
		int nVeces= Integer.parseInt (arg [0]);
		boolean b=true;

		for (int n=1;n<=10_000_000;n*=2)
		{
			t1 = System.currentTimeMillis ();

			for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
			{ 
				cont=0;
				b=rec1 (n);
			} 

			t2 = System.currentTimeMillis ();

			System.out.println (b+" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);
		}  // for
	} // main
} //class