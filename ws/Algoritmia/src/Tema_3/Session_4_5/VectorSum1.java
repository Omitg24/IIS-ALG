package Tema_3.Session_4_5;

import java.util.Random; 

/* This program can solve a problem in three ways. 
 * The problem is to calculate the sum of the elements of a vector
 */
/**
 * Titulo: Clase VectorSum1
 *
 * @author Omar Teixeira, UO281847
 * @version 3 mar 2022
 */
public class VectorSum1{
	/**
	 * v
	 * @param a
	 */
	static int []v;
	
	/**
	 * Metodo Main
	 * @param arg
	 */
	//MAIN METHOD TO VERIFY PROPER OPERATION
	public static void main (String arg []) {
		int n = Integer.parseInt(arg[0]);
		
		v = new int[n];
		fillIn(v);
		write(v);
		 
		System.out.println("FIRST SOLUTION =" + sum1(v));
		System.out.println("SECOND SOLUTION =" + sum2(v));		
		System.out.println("THIRD SOLUTION =" + sum3(v));
	} 
	
	/* This method gives random values ​​to a vector of integers. 
	 * It uses the Random class, from the java.util package */
	/**
	 * M�todo fillIn
	 * @param a
	 */
	public static void fillIn(int[]a) {
	 Random r= new Random();
	 int n=a.length;
	 for(int i=0;i<n;i++)
	  a[i]= r.nextInt(19)-9; //values between -9 and 9
	}   
	
	/* This method writes the vector */
	/**
	 * M�todo write
	 * @param a
	 */
	public static void write(int[]a) {
	 int n= a.length;
	 for (int i=0; i<n; i++)
	   System.out.print(a[i]+"//");
	 System.out.println();
	}  
	
	/* This method iteratively calculates the sum of 
	 * the vector with linear complexity O(n) */
	/**
	 * M�todo sum1
	 * @param a
	 * @return int
	 */
	public static int sum1(int[]a) {
	 int n= a.length;
	 int s=0;
	 for(int i=0;i<n;i++)
	    s=s+a[i];
	 return s; 
	}    
	
	/* This method recursively calculates the sum of 
	 * the vector with a complexity O(n) */
	/**
	 * M�todo sum2
	 * @param a
	 * @return int
	 */
	public static int sum2(int[]a) {
	 return recSust(0,a);
	}    
	
	/**
	 * M�todo recSust
	 * @param i
	 * @param a
	 * @return int
	 */
	private static int recSust(int i, int[]a) {
	 if (i==a.length) 
		 return 0;
	 else 
		 return a[i] + recSust(i+1,a);
	} 
	
	/*  This method recursively calculates the sum of 
	 * the vector with a complexity O(n) */
	/**
	 * M�todo sum3
	 * @param a
	 * @return int
	 */
	public static int sum3(int[]a) {
	  return recDiv(0,a.length-1,a);
	}  
	
	/**
	 * M�todo recDiv
	 * @param iz
	 * @param de
	 * @param a
	 * @return int
	 */
	private static int recDiv(int iz,int de,int[]a) {
	 if (iz==de)
		 return a[iz];
	 else { 
		 int m= (iz+de)/2;
	     return recDiv(iz,m,a)+ recDiv(m+1,de,a);
	 }
	}  

} 
