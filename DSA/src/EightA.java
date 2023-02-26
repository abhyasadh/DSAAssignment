//Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.

//Solution:
//The code starts by defining a maxAreaOfSquare method that takes in a 2D integer array called matrix.
//This method returns an integer, which is the maximum area of a square made by 0s in the matrix. The
//method starts by initializing a variable called maxArea to 0, which will eventually hold the maximum
//area of the square.
//
//The matrix variable is used to get the number of rows and columns in the matrix. Then, a histogram
//array of integers is created with the same size as the number of columns in the matrix. This histogram
//array will hold the height of each bar in the histogram.
//
//The code then loops through each row in the matrix. For each row, the code loops through each column
//and increments the value of the corresponding index in the histogram array if the matrix value is 0.
//If the matrix value is not 0, then the value in the histogram array is set to 0.
//
//After the histogram is constructed for each row, the code calls the maxAreaOfSquareInHistogram method,
//passing in the histogram array. This method computes the maximum area of a rectangle that can be
//formed using the bars in the histogram. The method returns the maximum area of a rectangle.
//
//Finally, the code checks whether the area of the square obtained from the histogram is greater than
//the current maximum area of the square, and if so, updates the maxArea variable.


import java.util.Stack;

public class EightA {

    public static void main(String[] args) {

        //matrix to find the area
        int[][] matrix = {
                {1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}
        };
        int maxArea = maxAreaOfSquare(matrix);
        System.out.println("Maximum area of square made by 0s: " + maxArea);
    }

    //this function returns the maximum area of square made by 0s in the given matrix
    public static int maxAreaOfSquare(int[][] matrix) {

        //initiate max area to 0 which will later hold the maximum area of the square made by 0s
        int maxArea = 0;

        //rows and columns of the matrix
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Calculate histogram for each row and column and find max area of square in histogram
        int[] histogram = new int[cols];
        for (int[] ints : matrix) { //time complexity: O(n^2)
            // Build histogram for current row
            for (int j = 0; j < cols; j++) {
                if (ints[j] == 0) {
                    //this gives the maximum number of consecutive 0s in a column
                    histogram[j]++;
                } else {
                    //if value is not 0, number of consecutive 0s is reset to 0
                    histogram[j] = 0;
                }
            }
            // Find max area of square in histogram
            int area = maxAreaOfSquareInHistogram(histogram);

            //update the maximum area
            if (area > maxArea) {
                maxArea = area;
            }
        }

        for (int j = 0; j < cols; j++) {
            if (histogram[j] > 0) {
                histogram[j] = 1;
            }
        }

        // Find max area of square in histogram of columns
        int area = maxAreaOfSquareInHistogram(histogram);
        if (area > maxArea) {
            maxArea = area;
        }
        return maxArea;
    }


    //This method computes the maximum area of a rectangle that can be formed using the bars in the
    //histogram and returns the maximum area of a rectangle.
    public static int maxAreaOfSquareInHistogram(int[] histogram) {
        int maxArea = 0;
        int n = histogram.length;
        Stack<Integer> stack = new Stack<>();

        // Traverse histogram
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || histogram[i] < histogram[stack.peek()])) {
                int height = histogram[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int side = Math.min(height, width);
                int area = side * side;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
            stack.push(i);
        }
        return maxArea;
    }
}