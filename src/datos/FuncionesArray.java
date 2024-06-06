package datos;

import java.util.Random;

public class FuncionesArray {
	
	public static int[] generarArrayAleatorio(int n, int min, int max) {
        // Declaración del array
        int[] arr = new int[n];
        
        //Generación de números aleatorios
        Random random = new Random();
        //Recorre el array y lo llena con numeros aleatorios 
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
        }
        
        return arr;
    }
	
	//Metodo para mostrar el array
	public static void mostrarArray(int[] arr) {
	        System.out.println("\n-----------------------:\n");
	        for (int num : arr) {
	            System.out.print(num +" / ");
	        }
	    }
}
