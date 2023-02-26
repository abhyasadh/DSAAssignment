// Given a linked list containing an integer value, return the number of steps required to sort
// an array in ascending order by eliminating elements at each step.
// Note: at each step remove element a[i] where a[i-1]> a[i]


//Solution:
//Bubble sort algorithm is used to sort the array, variable 'steps' is used to keep track of the steps
//and sorted boolean variable is used to run the while loop until there is no more iterations in the
//loop.

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class FourB {

    //this function returns the steps required to sort the array using bubble sort technique.
    public static int stepsToSortLinkedList(int[] array) {

        //this variable is used to keep track of steps required
        int steps = 0;

        //this boolean is used to run while loop until the array is sorted
        boolean sorted = false;

        //loop to sort array
        while (!sorted) {

            //set sorted variable to true
            //if the condition inside the for loop is not met, the value for sorted is not updated to false
            //and the while loop terminates
            sorted = true;

            //loop to check if the previous value is greater than current and swap elements if true.
            for (int i = 1; i < array.length; i++) {
                if (array[i - 1] > array[i]) {

                    //if the condition is met, we have to swap elements so we increase the step counter.
                    steps++;

                    //swapping technique
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;

                    //since the condition is met, the array is still not sorted
                    sorted = false;
                }
            }
        }

        //return the steps required once the while loop terminated and array is sorted
        return steps;
    }

    public static void main(String[] args) {
        //create an array of 10 elements and assign 10 random values
        int[] array = new int[10];
        Random random = new Random();
        for (int i=0; i<10; i++){
            array[i]=random.nextInt(0,20);
        }

        //print the array and steps required to sort
        System.out.println(Arrays.toString(array));
        System.out.println(stepsToSortLinkedList(array));
    }
}
