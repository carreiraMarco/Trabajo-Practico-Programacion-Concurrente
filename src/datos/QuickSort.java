package datos;

import java.util.concurrent.RecursiveAction;

public class QuickSort extends RecursiveAction {
    private static final int THRESHOLD = 16; // Umbral para cambiar a ordenamiento secuencial, 
    										//establece el tamaño del subarreglo por debajo del cual
    										//se realizará el ordenamiento secuencial en lugar del concurrente.
   
    //Atributos que tendra la clase
    private int[] array; //Array de enteros que sera ordenado.
    private int low;	//Variable low representa el indice inferior del subarreglo a ordenar.
    private int high;	//Variable high representa el indice superior del subarreglo a ordenar.

    //Constructor.
    public QuickSort(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
    	//Verifica si el tamaño del subarreglo es menor que el umbral (THRESHOLD).
        if (high - low < THRESHOLD) { 
        	//Si el tamaño es menor al del ordenamiento secuencial, se llama al metodo de ordenamiento secuencial.
            quickSortSequential(array, low, high); 
        } else {
        	//Se segmenta el subarreglo y se establece el indice pivote.
            int pivotIndex = partition(array, low, high); 
            //Crea una nueva tarea QuickSort para el subarreglo izquierdo.
            QuickSort leftTask = new QuickSort(array, low, pivotIndex - 1);	
            //Crea una nueva tarea QuickSort para el subarreglo derecho.
            QuickSort rightTask = new QuickSort(array, pivotIndex + 1, high);
            //Invoca ambas tareas en paralelo.
            invokeAll(leftTask, rightTask);	
        }
    }

    public static void quickSortSequential(int[] array, int low, int high) {
    	//Verifica si el índice inferior es menor que el índice superior.
    	if (low < high) {	
    		//Particiona el subarreglo y obtiene el índice del pivote.
            int pi = partition(array, low, high);
            //Llama recursivamente al método para ordenar el subarreglo izquierdo.
            quickSortSequential(array, low, pi - 1);
            //Llama recursivamente al método para ordenar el subarreglo derecho.
            quickSortSequential(array, pi + 1, high);	
        }
    }
   
    private static int partition(int[] arr, int low, int high) {
    	//Elige el último elemento como pivote.
    	int pivot = arr[high];	
    	//Inicializa el índice i como low - 1.
		int i = low - 1;	
		
		//Itera sobre el subarreglo desde low hasta high - 1.
		for (int j = low; j < high; j++) {	
			//Verifica si el elemento actual es menor o igual al pivote.
			if (arr[j] <= pivot) {	
				//Incrementa el índice i.
				i++;	
				
				//Intercambia arr[i] y arr[j].
				int temp = arr[i];	
				arr[i] = arr[j];	
				arr[j] = temp;
			}
		}
		
		//Coloca el pivote en su posición correcta.
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		
		//Devuelve el índice del pivote.
		return i + 1;
	}
}