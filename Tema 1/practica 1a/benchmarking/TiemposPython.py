from time import *

def lineal(n):
    """ lineal O(n)"""
    cont=0
    for i in range (n):
        cont=cont+1
    print ("CONTADOR",cont,end=" ")

def cuadratico(n):
    """ cuadrático O(n**2)"""
    cont=0
    for i in range (n):
        for j in range (n):
            cont=cont+1
    print ("CONTADOR",cont,end=" ")

print("TIEMPOS LINEALES (MILISEG.)")
t1=0
t2=0
n=1000000
while t2-t1<5:  #5 segundos
    t1=time()  # da el tiempo en seg.
    lineal(n)
    t2=time()
    print("n=",n,"***","tiempo",int(1000*(t2-t1)))
    n=n*2

print("TIEMPOS CUADRÁTICOS (MILISEG.")
t1=0
t2=0
n=100
while t2-t1<5:  #5 segundos
    t1=time()
    cuadratico(n)
    t2=time()
    print("n=",n,"***","tiempo",int(1000*(t2-t1)))
    n=n*2