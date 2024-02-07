package sortingHW;

public class QuickSort<T extends Comparable<T>> {

    private void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(T[] array, int low, int high) {
    	System.out.println("In this method it uses the last element in the array to pivot "+
    			"the element around what you want to partition the array with");
        T pivot = array[high];
        
        System.out.println("i is a pointer that starts position before the low index i is used to keep track of the last position "+
        		"where the lowest element was found then the pivot");
        System.out.println("j is the pointer that traverses the array from the low index to one "+
        			"position before the high index j is used to compare all the elements with the pivot");
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array[j].compareTo(pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        System.out.println("if the current element is less than the pivot increment i and swap the elements positions this is " +
        		"to make sure all elements less than the pivot are on the left side");
        System.out.println("after the loop swap the pivot with the element at position i+1");
        System.out.println("after this place the pivot at the correct position in the sorted array");
        swap(array, i + 1, high);
        System.out.println("return the index of the pivot after the swap");
        return (i + 1);
    }

    public void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }
}
