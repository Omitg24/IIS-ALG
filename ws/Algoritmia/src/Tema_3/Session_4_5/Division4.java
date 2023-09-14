package Tema_3.Session_4_5;

/**
 * Titulo: Clase Division4
 *
 * @author Omar Teixeira, UO281847
 * @version 3 mar 2022
 */
public class Division4
{
	/**
	 * cont
	 */
	static long cont;
	
	/**
	 * Método rec4
	 * @param n
	 * @return true o false
	 */
	public static boolean rec4 (int n)
	{
		if (n<=0) 
			cont++;
		else
		{ 
			cont++ ; // O(1)    
			rec4 (n/2);
			rec4 (n/2);
			rec4 (n/2);
			rec4 (n/2);
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
				b=rec4 (n);
			} 

			t2 = System.currentTimeMillis ();

			System.out.println (b+" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);

		}  // for

	} // main
} //class