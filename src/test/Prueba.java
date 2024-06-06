package test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import datos.FuncionesArray;
import datos.QuickSort;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Diferentes casos de prueba
		//int caso1 = 10;
		//int caso2 = 100;
		//int caso3 = 1000;
		//int caso4 = 10000;
		//int caso5 = 100000;
		//int caso6 = 500000;
		int caso7 = 1000000;
		
		//Genera un array con numeros aleatorios del -1000 al 1000.
		int[] array = FuncionesArray.generarArrayAleatorio(caso7, (-1000), 1000);	
		
		//Define las variables para calcular el tiempo de ejecucion.
		double tiempoInicial, tiempoFinal;	
		
		//Ejecucion secuencial.
		System.out.println(" Manera Secuencial");
		
		//Genera una copia 1 del array original para la ejecucion secuencial.
		int[] arrayCopia1 = Arrays.copyOf(array, array.length); 
		
		//Calcula el tiempo inicial.
		tiempoInicial = System.nanoTime();
		
		//Llama al metodo de ordenamiento secuencial de QuickSort.
		QuickSort.quickSortSequential(arrayCopia1, 0, arrayCopia1.length-1);
		
		//Calcula el tiempo final.
		tiempoFinal = System.nanoTime() - tiempoInicial;
		
		//Imprime cuanto tardo la ejecucion secuencial.
		System.out.print("\n El programa secuencial, demoro: " + tiempoFinal/1000 + " microSegundos\n\n");
		
		//Ejecucion concurrente.
		System.out.println(" Manera Concurrente");
		
		//Genera una copia 1 del array original para la ejecucion secuencial.
		int[] arrayCopia2 = Arrays.copyOf(array, array.length);
		
		//Calcula el tiempo inicial.
		tiempoInicial = System.nanoTime();
		
		//Crea un pool de hilos ForkJoin para manejar la ejecución concurrente.
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
		//Inicia la tarea de ordenamiento concurrente invocando la tarea QuickSort.
        forkJoinPool.invoke(new QuickSort(arrayCopia2, 0, arrayCopia2.length - 1));
        
        //Calcula el tiempo final.
        tiempoFinal = System.nanoTime() - tiempoInicial;
		
		//Imprime cuanto tardo la ejecucion concurrente.
		System.out.print("\n El programa concurrente, demoro: " + tiempoFinal/1000 + " microSegundos\n\n");
	}

}
