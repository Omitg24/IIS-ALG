package labs.benchmarking;

import java.util.Random;

public class Benchmarking1 {	
	public static void loop1(int n){
		/* O(n logn) Algorithm */
		Random rn = new Random();
		@SuppressWarnings("unused")
		int cont=0;
		for (int i=1; i<=n; i++)
			for (int j=1; j<=n; j*=2)
				cont += rn.nextInt();
	}
	
	public static void main(String arg[]){
		long t1, t2;
		int n = 1024 * 1024;
		t1 = System.currentTimeMillis ();
		loop1(n);
		t2 = System.currentTimeMillis ();
		System.out.println("n="+n+ "**TIME="+(t2-t1));
	
	}//main
} //class