package Tema_3.Session_4_5;

/* This program serves to measure times automatically increasing the size of 
 * the problem (n) and also using a time scale determined by nTimes, 
 * which is taken from the argument arg[1] 
 * It also gets as an execution argument (arg[0]) the Fibonacci method 
 * on which we will focus the measurement (opcions 1,2,3,4 respectively) 
 */
/**
 * Titulo: Clase Fibonacci2
 *
 * @author Omar Teixeira, UO281847
 * @version 3 mar 2022
 */
public class Fibonacci2 {
	  
	/**
	 * Método Main
	 * @param arg
	 */
	public static void main (String[] arg) { 
		int option = Integer.parseInt(arg[0]);
		int nTimes = Integer.parseInt(arg[1]);
		    
		long t1,t2;
		int solution=0;
		
		for (int n=1; n<100_000; n*=2) {  //from n = 10 to n = 60   
			  if (option==1) { //fib1    
				    t1= System.currentTimeMillis();
				  
				    for (int repeticiones=1; repeticiones<=nTimes; repeticiones++) 
				    	solution=Fibonacci1.fib1(n); 
				
				    t2= System.currentTimeMillis();
					System.out.println ("ORDER = "+n+"**"+"TIME = "+(t2-t1)+"**"+" nTimes = "+ nTimes + " SOL = " + solution); 
			  }    
			  else if (option==2) { //fib2
					int[]v = new int [100_000];  //for the second method 
				    t1= System.currentTimeMillis();
				  
				    for (int repeticiones=1; repeticiones<=nTimes; repeticiones++) 
				    	solution=Fibonacci1.fib2(n,v); 
				
				    t2= System.currentTimeMillis();
					System.out.println ("ORDER = "+n+"**"+"TIME = "+(t2-t1)+"**"+" nTimes = "+ nTimes + " SOL = " + solution); 
			  }
			  else if (option==3) { //fib3
				    t1= System.currentTimeMillis();
				  
				    for (int repeticiones=1; repeticiones<=nTimes; repeticiones++) 
				    	solution=Fibonacci1.fib3(n);
				
				    t2= System.currentTimeMillis();
					System.out.println ("ORDER = "+n+"**"+"TIME = "+(t2-t1)+"**"+" nTimes = "+ nTimes + " SOL = " + solution); 
			  }    
			  else if (option==4) { //fib4
			    t1= System.currentTimeMillis();
			
			    for (int repeticiones=1; repeticiones<=nTimes; repeticiones++) 
			    	solution=Fibonacci1.fib4(n);  
			
			    t2= System.currentTimeMillis();
				System.out.println ("ORDER = "+n+"**"+"TIME = "+(t2-t1)+"**"+" nTimes = "+ nTimes + " SOL = " + solution); 
			  }
			  else System.out.println("INCORRECT OPTION");
			    
		 } //for
		
	} //main
} 
