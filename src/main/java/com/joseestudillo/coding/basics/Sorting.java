package com.joseestudillo.coding.basics;

public class Sorting {

    public static void bubbleSort(int array[]) {
        int temp;
        for(int i = array.length - 1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                if(array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int array[]) {
        int i, j, temp, pos_greatest;
        for(i = array.length - 1; i > 0; i--) {
            pos_greatest = 0;
            for(j = 0; j <= i; j++) {
                if(array[j] > array[pos_greatest]) {
                    pos_greatest = j;
                }
            }
            temp = array[i];
            array[i] = array[pos_greatest];
            array[pos_greatest] = temp;
        }
    }

    public static void insertionSort(int arr[]) {
        int i, j, temp;
        for(j = 1; j < arr.length - 1; j++) {
            temp = arr[j];
            i = j; // range 0 to j-1 is sorted
            while(i > 0 && arr[i - 1] >= temp) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[i] = temp;
        }
    }

}
