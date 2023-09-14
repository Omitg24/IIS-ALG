package alg77777777.p1;

public class TiemposJava
{	
public static void lineal(long n)
{ //* O(n ) 
long cont=0;
for (long i=1; i<=n; i++)
	cont++;
System.out.print("CONTADOR="+cont);
}

public static void cuadratico(long n)
{ //* O(n**2 ) 
long cont=0;
for (long i=1; i<=n; i++)
	for (long j=1; j<=n; j++)
		cont++;
System.out.print("CONTADOR="+cont);
}
	
public static void main(String arg[])
{
System.out.println("TIEMPOS LINEALES EN JAVA (MILISEG.)");
long t1=0;
long t2=0;
long n = 1000000;
while (t2-t1  <5000)  //5 seg.
   {
   t1 = System.currentTimeMillis ();
   lineal(n);
   t2 = System.currentTimeMillis ();
   System.out.println("  n="+n+ "  TIEMPO="+(t2-t1));
   n=n*2;
   }

System.out.println("TIEMPOS CUADRATICOS EN JAVA(MILISEG.)");
t1=0;
t2=0;
n = 100;
while (t2-t1  <5000)  //5 seg.
   {
   t1 = System.currentTimeMillis ();
   cuadratico (n);
   t2 = System.currentTimeMillis ();
   System.out.println("  n="+n+ "  TIEMPO="+(t2-t1));
   n=n*2;
   }

}//main
} //class