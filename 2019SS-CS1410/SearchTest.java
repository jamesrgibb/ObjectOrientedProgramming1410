package a7;

import java.util.Arrays;
import java.util.Random;

/**
 * This class has methods to compare the performance of sequential search and
 * binary search.
 *
 */
public class SearchTest {

	/**
	 * Call the test method with an array size and repetition count and output
	 * results. This method has been fully implemented for you. You should change
	 * values in it for your investigation.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int testArraySize = 1_000; // This is the size of the array to be searched.
		int numberOfRepetitions = 100; // This is the number of times the array is searched.
		int[] testData = randomSortedFill(testArraySize);
		int binaryCount = 0, sequentialCount = 0;
		Random generator = new Random();
		for (int rep = 0; rep < numberOfRepetitions; rep++) {
			int key = generator.nextInt(testArraySize);
			binaryCount += binarySearchForKeyWithCount(testData, key);
			sequentialCount += sequentialSearchForKeyWithCount(testData, key);
		}

		double binaryAvg = binaryCount / (double) numberOfRepetitions;
		double sequentialAvg = sequentialCount / (double) numberOfRepetitions;
		System.out.println("For an array of size " + testArraySize + " the average search costs are:");
		System.out.println("     binaryAvg    : " + binaryAvg + " equality tests");
		System.out.println("     sequentialAvg: " + sequentialAvg + " equality tests");
	}

	/**
	 * Given an array length, make and fill the array with random values from 0 to
	 * length-1 (inclusive) int values. Use the Random class to generate these
	 * values. The array can have duplicate values, this is not a shuffle of all
	 * values from 0 to length-1, but instead length values picked randomly from 0
	 * to length-1.
	 * 
	 * The values are then sorted in ascending order.
	 * 
	 * This method has been implemented for you. You do not need to change the
	 * documentation for this method.
	 * 
	 * @param arraySize: the length of the array to be filled with random values in
	 *        ascending order.
	 * @return the array of length arraySize filled with sorted random values.
	 */
	public static int[] randomSortedFill(int arraySize) {
		int[] vals = new int[arraySize];
		Random generator = new Random();

		for (int index = 0; index < vals.length; index++) {
			vals[index] = generator.nextInt(vals.length);
		}

		Arrays.sort(vals);
		return vals;
	}

	/**
	 * Search vals for the key using binary search. Assumes vals is sorted in
	 * ascending order. This code is provided as a reminder of how binary search
	 * works. You do not need to run it or modify it.
	 * 
	 * @param vals
	 * @param key
	 * @return the index where val is found, or -1 otherwise.
	 */
	public static int binarySearchForKey(int[] vals, int key) {
		int lo = 0;
		int hi = vals.length - 1;
		while (lo <= hi) {
			int arrayLength = hi + 1 - lo;
			int mid = arrayLength / 2 + lo;
			if (vals[mid] == key)
				return mid;
			if (vals[mid] < key)
				lo = mid + 1;
			else // if (vals[mid] > key) is the only case left, so we don't need to check it.
				hi = mid - 1;
		}
		return -1;
	}

	/**
	 * Search vals for the key using binary search. Assumes vals is sorted in
	 * ascending order.
	 * 
	 * @param vals
	 * @param key
	 * @return the number of == tests performed during the search.
	 */
	public static int binarySearchForKeyWithCount(int[] vals, int key) {
		return 0; // change this code
	}

	/**
	 * Search vals for the key using sequential search. This code is provided as a
	 * reminder of how sequential search works. You do not need to run it or modify
	 * it.
	 * 
	 * @param vals
	 * @param key
	 * @return the index where val is found, or -1 otherwise.
	 */
	public static int sequentialSearchForKey(int[] vals, int key) {
		for (int index = 0; index < vals.length; index++) {
			if (key == vals[index])
				return index;
		}
		return -1;
	}

	/**
	 * Search vals for the key using sequential search.
	 * 
	 * @param vals
	 * @param key
	 * @return the number of == tests performed during the search.
	 */
	public static int sequentialSearchForKeyWithCount(int[] vals, int key) {
		return 0; // change this code
	}

}
