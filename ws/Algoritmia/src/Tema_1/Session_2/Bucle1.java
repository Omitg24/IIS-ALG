package Tema_1.Session_2;

/**
 * Titulo: Clase Bucle1
 *
 * @author Omar Teixeira, UO281847
 * @version 18 feb 2022
 */
public class Bucle1 {

	/**
	 * Bucle1
	 * @param n
	 * @return cont
	 */
	public static long bucle1(int n)
	{
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j *= 2)
				cont++;
		return cont;
	}

	/**
	 * M�todo Main
	 * @param arg
	 */
	public static void main(String arg[]) {
		long t1, t2;
		int nVeces = Integer.parseInt(arg[0]);

		System.out.println("n\ttiempo\tcontador");
		for (int n = 8; n <= 100_000; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle1(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(n+"\t"+(t2-t1)+"\t"+c);

		} // for

	} // main
} // class