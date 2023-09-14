package Tema_1.Session_2;

/**
 * Titulo: Clase Incognita
 *
 * @author Omar Teixeira, UO281847
 * @version 18 feb 2022
 */
public class Incognita {

	/**
	 * Bucle6
	 * @param n
	 * @return
	 */
	public static long bucle6(int n)
	{
		long cont = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= i; j++)
				for (int k = 1; k <= j; k++)
					cont++;
		return cont;

	}

	/**
	 * Método Main
	 * @param arg
	 */
	public static void main(String arg[]) {
		long c = 0;
		long t1, t2;
		int nVeces = Integer.parseInt(arg[0]);

		for (int n = 8; n <= 100_000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle6(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(c + "**n=" + n + "**TIEMPO=" + (t2 - t1)
					+ "**nVeces=" + nVeces);

		} // for

	} // main
} // class