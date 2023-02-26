//Assume an electric vehicle must go from source city s to destination city d. You can locate
// many service centers along the journey that allow for the replacement of batteries; however,
// each service center provides batteries with a specific capacity. You are given a 2D array in which
// serviceCenter[i]=[xi,yj] indicates that the ith service center is xi miles from the source city and
// offers yj miles after the automobile can travel after replacing batteries at specific service centers.
// Return the number of times the car's batteries need to be replaced before arriving at the destination.

//Answer:
//First the array of service centers is sorted using 'sort' method of Arrays class (time complexity: O(n log(n))).
//
//Then the service centers is put through loop and following conditions were checked:
// 1) If the current distance plus the current battery charge is greater than or equal to the target distance,
//    the method returns the number of replacements so far.
// 2) If the distance to the service center is greater than the current distance plus the current battery charge,
//     it means that the vehicle cannot reach the service center or the destination, so the method returns -1.
// 3) Otherwise, it searches for the service center with the largest battery capacity that can be reached from
//     the current position. If there is no such service center, the method returns -1. Otherwise, it replaces
//     the battery, updates the current charge and distance, and increments the replacements counter.
//
//Once the loop terminates, and the current distance plus the current charge is greater than or equal to the
//target distance, the method returns the number of replacements. Otherwise, it returns -1.



import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FiveB {
    int[][] serviceCenters;
    int startCapacity;
    int targetMiles;

    public FiveB(int startCapacity, int targetMiles) {
        this.serviceCenters = new int[][]{{10,60},{20,30},{30,30},{60,40}};
        this.startCapacity = startCapacity;
        this.targetMiles = targetMiles;
    }

    //this function returns the number of times the battery needs to be replaced
    //if the destination cannot be reached, -1 is returned
    public int timesReplaced() {

        //first the array of service centers is sorted according to the distance from starting point (0)
        Arrays.sort(serviceCenters, Comparator.comparingInt(arr -> arr[0])); //(time complexity: O(n log(n)))

        //initialize number of replacements, current distance and current charge
        int replacements = 0;
        int currentDistance = 0;
        int currentCharge = startCapacity;

        //loop to iterate through service centers
        for (int i = 0; i < serviceCenters.length; i++) {

            //if current distance and available charge is enough to reach the distance, return no. of replacements
            if (currentDistance + currentCharge >= targetMiles) {
                return replacements;
            }

            //if nearest service center or destination can't be reached, return -1
            if (serviceCenters[i][0] > currentDistance + currentCharge) {
                return -1;
            }

            int maxCapacity = 0;
            int maxIndex = -1;

            //iterator i represents visiting service center. iterator j checks for the next available
            //service center.

            for (int j = i; j < serviceCenters.length; j++) {
                //if it can't be reached, maxIndex remains -1 and loop is terminated returning -1.
                if (serviceCenters[j][0] > currentDistance + currentCharge) {
                    break;
                }

                //else, loop continues to check for the service center that is reachable and can provide
                //maximum charge.
                if (serviceCenters[j][1] > maxCapacity) {
                    maxCapacity = serviceCenters[j][1];
                    maxIndex = j;
                }
            }

            //if no suitable is battery found -1 is returned
            if (maxIndex == -1) {
                return -1;
            }

            //add replacement, update charge, increase distance to the service center with maximum capacity
            replacements++;
            currentCharge = maxCapacity;
            currentDistance = serviceCenters[maxIndex][0];
        }

        //if after replacement, target can be reached, we can return the times the battery is replaced
        if (currentDistance + currentCharge >= targetMiles) {
            return replacements;
        }

        //cannot reach the destination
        return -1;
    }


    public static void main(String[] args) {

        //taking input from the user
        Scanner input = new Scanner(System.in);
        System.out.print("Enter target miles: ");
        int target = input.nextInt();
        System.out.print("Enter starting capacity: ");
        int start = input.nextInt();

        //calling the function
        FiveB batteryReplacement = new FiveB(start, target);
        System.out.println(batteryReplacement.timesReplaced());
    }
}
