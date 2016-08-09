package myProj;

import java.util.Arrays;

public class LargestSum {

	private static int currentMax;
	private static int saveStart;
	private static int saveEnd;
	private static int straightSum;
	
	static int largestSum(int[] a) {

		// the trick is that if sumSoFar is < a[i], drop sumSoFar and start with a[i].
		int sumSoFar = a[0];
		int startSoFar = 0;
		currentMax = sumSoFar;
		saveStart = 0;
		saveEnd = 0;
		straightSum = 0;
		for (int i = 0; i < a.length; i++) {
			straightSum += (a[i] > 0 ? a[i] : 0);
			if (sumSoFar + a[i] < a[i]) { // start over at i
				startSoFar = i;
				sumSoFar = a[i];
			} else {
				sumSoFar += a[i];
			}
			if (sumSoFar >= currentMax) {
				currentMax = sumSoFar;
				saveStart = startSoFar;
				saveEnd = i;
			}
		}
		return currentMax;
	}
	
	public static void main(String[] args) {
		int[][] tests =  {
				{1, 2, 3, 4},
			{1, 1, -2, 1},
			{3, -1, 2, 0, -4, 0},
			{0, -4, -2, 12, 8, -34, 9},
			{1,2,3,4,5,6,7,8,9},
			{-1,2,5,-5,6}
		};

		for (int[] test : tests) {
			largestSum(test);
			System.out.println(Arrays.toString(test));
			System.out.println("Largest Sum : " + currentMax + " straightSum " + straightSum + " start : " + saveStart + " end : " + saveEnd);
			System.out.println(Arrays.toString(Arrays.copyOfRange(test, saveStart, saveEnd+1)));
			
		}
	}

}
