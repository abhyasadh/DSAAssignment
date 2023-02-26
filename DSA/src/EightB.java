// Given an array of even numbers sorted in ascending order and an integer k,
// Find the k^th missing even number from provided array

import java.util.Scanner;

// Solution:
// To do this, we can create a counter that keeps track of missing even numbers in ascending order.
// Then, we can create a boolean array of length (Integer.MAX_VALUE) but it resulted in spacing error
// so boolean array of length 1000 was created and all values in the index of numbers present in provided
// array were set to true. Then, a loop was initiated to check for false values. If a false value is
// found, the counter increases and if the counter reaches the desired number 'k', the index
// of boolean array is returned.
public class EightB {

    //function to find the missing number in that index
    int findMissing(int[] a, int k){

        //initiate the counter to 0
        int counter=0;

        //boolean array of maximum length (Integer.MAX_VALUE is preferred but it resulted in no space available error).
        boolean[] status = new boolean[1000];

        //for each element in array, set the index of status to true (time complexity: O(n), n being size of array).
        for (int j : a) {
            status[j] = true;
        }

        //iterate through the array (time complexity: O(1000) since array if size 1000 is used)
        for (int i=0; i<status.length; i+=2){

            //if the status is false, increase the counter
            if (!status[i]){
                counter++;
            }

            //check if the counter meets the limit and if true, return the index
            if (counter>=k){
                return i;
            }
        }

        //if there is any error in the process, return -1.
        return -1;
    }

    //driver method
    public static void main(String[] args) {
        int[] a ={0, 2, 6, 18, 22};
        EightB missingNumber = new EightB();

        //taking user input
        Scanner input = new Scanner(System.in);
        System.out.println("Enter limit: ");

        //calling the function
        System.out.println(missingNumber.findMissing(a, input.nextInt()));
    }
}