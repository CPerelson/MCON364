package sortingHW;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> {

    private T[] arrayToSort;

    public MergeSort(T[] arrayToSort) {
        this.arrayToSort = arrayToSort;
        mergeSortSplit(arrayToSort, 0, arrayToSort.length - 1);
    }

    public void display() {
        for (int i = 0; i < this.arrayToSort.length; i++) {
            System.out.print(this.arrayToSort[i] + " ");
        }
        System.out.print("\n");
    }

    public void mergeSortSplit(T[] array, int firstIndex, int lastIndex) {
    	System.out.println("mergesortsplit method splits the list recursively until it is ready to be put back together");
    	if (firstIndex < lastIndex) {
    		// find the middle index in the list
        	System.out.println("the midpoint finds the middle index in the list ");
            int midPoint = (firstIndex + lastIndex) / 2;
            System.out.println("called recursivly");
            mergeSortSplit(array, firstIndex, midPoint); // left half of the list
            mergeSortSplit(array, midPoint + 1, lastIndex); // right half of the list
            mergeTogether(array, firstIndex, midPoint, lastIndex);
        }
    }

    private void mergeTogether(T[] array, int firstIndex, int midPoint, int lastIndex) {
    	System.out.println("mergetogether this method merges the list back together after they were recursivley taken apart.");
    	System.out.println("This method is called recursivly until all the little mini list that were created are put back together "+
    			"in the sorted order");
    	
    	T[] tempArray = Arrays.copyOf(array, array.length);

        int leftPointer = firstIndex;
        int rightPointer = midPoint + 1;
        int k = firstIndex;

        while (leftPointer <= midPoint && rightPointer <= lastIndex) {
        	System.out.println("comparing two values, which ever value is smaller place into the temp array");
        	if (tempArray[leftPointer].compareTo(tempArray[rightPointer]) <= 0) {
                array[k++] = tempArray[leftPointer++];
            } else {
                array[k++] = tempArray[rightPointer++];
            }
        }

        while (leftPointer <= midPoint) {
            array[k++] = tempArray[leftPointer++];
        }

        while (rightPointer <= lastIndex) {
            array[k++] = tempArray[rightPointer++];
        }
    }
}