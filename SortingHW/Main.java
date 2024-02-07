package sortingHW;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int userChoice;
        
        do {
            System.out.println("Enter 1 if you want hard coded grades. Enter 2 if you want to enter yourself ");
            userChoice = scanner.nextInt();        	
        }while (userChoice != 1 && userChoice != 2);


        if (userChoice == 1) {
        	System.out.println("If the user enters in 1 then they will go straight to the hard coded method");
            automaticSort();
        }
        else{
        	System.out.println("If the user enters in 2 then they will go to the method where they can enter in their own data");
            userSort(scanner);
        }
        scanner.close();
    }

    private static void userSort(Scanner scanner) {
        System.out.println("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        Student[] studentsArray = new Student[numStudents];
        
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter a grade for student " + (i + 1) + ":");
            int grade = scanner.nextInt();
            studentsArray[i] = new Student("Student" + (i + 1), grade); // Adding students to the array
        }

        System.out.println("The users input will be sorted with quick sort the quicksort method will take "+
        		"the students grades and will pass in the first number and the last number");
        QuickSort<Student> quickSortedStudents = new QuickSort();
        System.out.println("Students sorted using QuickSort: ");
        quickSortedStudents.quickSort(studentsArray, 0, studentsArray.length-1);

        System.out.println("The users input will be sorted with merge sort the mergesort method will "+
        		"take the first and last numbers of the array as peramiters to help sort the list");
        MergeSort<Student> mergeSortedStudents = new MergeSort<>(studentsArray);
        System.out.println("Students sorted using merge sort:");
        mergeSortedStudents.mergeSortSplit(studentsArray, 0, studentsArray.length-1);
        mergeSortedStudents.display();
    }

    private static void automaticSort() {
        Integer[] grades = {99, 75, 67, 88, 98, 78};
        int gradeSize = grades.length;

        System.out.println("Original Grades:");
        for(int grade: grades)
            System.out.print(grade + " ");
        System.out.println();

        System.out.println("sort the array with quick sort by passing in the grades list and the first and last elements");
        QuickSort<Integer> qs = new QuickSort();
        qs.quickSort(grades, 0, gradeSize-1);

        System.out.println("QuickSort:");
        for (int grade : grades)
            System.out.print(grade + " ");
        System.out.println();

        System.out.println("sort the array with merge sort by passing in the first and last elements");
        MergeSort<Integer> ms = new MergeSort(grades);
        ms.mergeSortSplit(grades, 0, gradeSize-1);
        System.out.println("MergeSort");
        ms.display();
    }

}
