package Tema_1.Session_2;

/**
 * Titulo: Clase Bucle4
 *
 * @author Omar Teixeira, UO281847
 * @version 22 feb 2022
 */
public class Bucle4 {
	/**
	 * Bucle4
	 * @param n
	 * @return cont
	 */
	public static long bucle4(int n)
	{
		long cont = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					for (int z = 1; z <= n; z++) {
						cont++;
					}
				}
			}
		}
		return cont;
	}

	/**
	 * Método Main
	 * @param arg
	 */
	public static void main(String arg[]) {
		long t1, t2;
		int nVeces = Integer.parseInt(arg[0]);

		for (int n = 8; n <= 100_000; n *= 2) {
			long c = 0;
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				c += bucle4(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(c + "**n=" + n + "**TIEMPO=" + (t2 - t1)
					+ "**nVeces=" + nVeces);
		} // for

	} // main
} // class