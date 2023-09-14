Echo off
Echo "Actividad 2. Tarea 2: Ejecutar este script en el ordenador de practicas"

Echo "Compilar Benchmarking1"
javac labs\benchmarking\Benchmarking1.java
Echo "Ejecutar Benchmarking1 varias veces"
for /l %%x in (1, 1, 15) do java labs.benchmarking.Benchmarking1
