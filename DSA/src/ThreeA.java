import java.util.Arrays;

//You are given an even length array; divide it in half and return possible minimum product difference of any two
//        subarrays.
//        Note that the minimal product difference is the smallest absolute difference between any two arrays a and b, which
//        is computed by calculating the difference after multiplying each element of the arrays a and b.
//        Input: {5,2,4,11}
//        Output: 2
//        {5,4} {2,11} result into minimum product difference

//Answer:
//In order to calculate the minimum product difference,
// 1) we have to first sort the array in ascending order. We can use sort method inside Arrays class
//    to do so which has the time complexity of O(n log(n)).
// 2) then we have to assign a variable with infinite (Integer.MAX_VALUE) so that we can compare the
//    product difference and update it.
// 3) after that, we can initiate a loop upto (length of array / 2) that can be used to divide array
//    into two halves and can be accessed through first index and last index.
// 4) Then we can multiply each element of first half to each element of second half using the initiated
//    loop and update the minimum difference variable if the resulting difference is less than the
//    present value.
public class ThreeA {

    //function to find the minimum product difference
    public int minProductDifference(int[] arr) {
        //this function multiplies the elements in two halves of the array after soring it and returns
        //the minimum product difference between the two elements

        //sorting the array (time complexity: O(n log(n))
        Arrays.sort(arr);
        int n = arr.length;

        //this value is updated if the resulting difference after each loop is less than present value
        int minDiff = Integer.MAX_VALUE;

        // loop to divide the array into two halves and calculate product difference
        // we can use i<n/2 to iterate through the array from the front and the back and multiply the respective elements
        for (int i = 0; i < n / 2; i++) {
            int product1 = arr[i] * arr[n - i - 1];
            int product2 = arr[n / 2 - i - 1] * arr[n / 2 + i];
            int diff = Math.abs(product1 - product2);
            minDiff = Math.min(minDiff, diff);
        }

        //after loop is complete, return the minimum difference
        return minDiff;
    }

    //driver method
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 11};
        ThreeA minProduct = new ThreeA();
        System.out.println(minProduct.minProductDifference(arr));
    }
}