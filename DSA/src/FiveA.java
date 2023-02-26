// You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi],
// where xi the x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi
// represents the height of the peaks of each rectangle. If you want to construct a border line over the
// peaks of rectangle represented in bar chart, return the key coordinates required to build a border
// line that contacts the peaks of the given chart.
// Note: key points are the left coordinates of shape representing peaks where you need to draw boarder
// line.

import java.util.*;

public class FiveA {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> res=new ArrayList<>();

        List<int[]> heights=new ArrayList<>();

        transformBuilding(buildings,heights);

        //if heights of 2 points are same then place the point with smaller height first else place point
        //with smaller starting point

        heights.sort((a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);// Time Complexity = O(nlog n)

        PriorityQueue<Integer> pq=new PriorityQueue<>((a, b)->(b-a));

        //seeding the Priority Queue
        pq.add(0);
        int prevMax=0;

        for(int[] height:heights){ //O(n)

            if(height[1]<0){
                pq.add(-height[1]);
            }
            else{
                pq.remove(height[1]); //O(log n)
            }

            int CurrentMax=pq.peek();
            if(CurrentMax!=prevMax)
            {
                List<Integer> subResult=new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(CurrentMax);

                res.add(subResult);
                prevMax=CurrentMax;
            }
        }

        return res;
    }

    //this will separate the values of start point and end point with height
    //example-->[1,2,3]-->[1,-3] & [2,3]-->here -(minus) is just for convention for starting point
    private void transformBuilding(int[][] buildings,List<int[]> heights)
    {
        for(int[] building:buildings)
        {
            heights.add(new int[]{building[0],-building[2]});
            heights.add(new int[]{building[1],building[2]});
        }
    }

    public static void main(String[] args) {
        FiveA barChart = new FiveA();
        System.out.println(barChart.getSkyline(new int[][]{{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}));
    }
}

//-----------------------------------------------------------------------------------------------------

//public class FiveA {
//    public static int[][] constructBorderLine(int[][] height) {
//        List<int[]> allCoordinates = new ArrayList<>();
//
//        int n = height.length;
//
//        for (int i = 0; i < n; i++) {
//            int left = height[i][0];
//            int right = height[i][1];
//            int h = height[i][2];
//
//            if (i==0){
//                allCoordinates.add(new int[]{left, h});
//            } else if (left<=height[i-1][1]) {
//                allCoordinates.add(new int[]{height[i][0], height[i-1][2]});
//                allCoordinates.add(new int[]{left, h});
//            } else if (height[i-1][1]<left){
//                allCoordinates.add(new int[]{height[i-1][1], height[i-1][2]});
//                allCoordinates.add(new int[]{height[i-1][1], 0});
//                allCoordinates.add(new int[]{left, 0});
//                allCoordinates.add(new int[]{left, h});
//            }
//        }
//
//        allCoordinates.add(new int[]{height[n-1][1], height[n-1][2]});
//        allCoordinates.add(new int[]{height[n-1][1], 0});
//
//        for (int i=0; i < allCoordinates.size(); i++){
//            if (i+1<allCoordinates.size() && allCoordinates.get(i)[1]==allCoordinates.get(i+1)[1]){
//                allCoordinates.remove(i+1);
//                i--;
//            }
//        }
//
//        int[][] result = new int[allCoordinates.size()][2];
//        for (int i = 0; i < allCoordinates.size(); i++) {
//            result[i] = allCoordinates.get(i);
//        }
//        return result;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(constructBorderLine(new int[][]{{1, 4, 10}, {2, 5, 15}, {5, 8, 12}, {9, 11, 1}, {11, 13, 15}})));
//    }
//}