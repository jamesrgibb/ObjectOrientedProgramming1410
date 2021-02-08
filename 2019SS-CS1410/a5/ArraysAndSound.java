package a5;

import java.util.Arrays;

public class ArraysAndSound {

	public static void main(String args[]) {
		int[] myArray = { 1, 2, 3 };
		System.out.println(Arrays.toString(clearArray(myArray)));
		int[] myArray0 = { 1, 2, 3, 5, 67, 80 };
		System.out.println(Arrays.toString(clearArray(myArray0)));
		int[] myArray01 = { 12, 24, 36, 54, 67, 80 };
		System.out.println(Arrays.toString(clearArray(myArray01)));
		int[] myArray1 = { 1, 2, 3 };
		System.out.println(arrayToString(myArray1));
		int[] myArray10 = { 13, 24, 37, 67, 89, 90 };
		System.out.println(arrayToString(myArray10));
		int[] myArray11 = { 137, 242, 372, 674, 898, 990 };
		System.out.println(arrayToString(myArray11));
		String[] myArray2 = { "David", "Joe" };
		System.out.println(containsDuplicate(myArray2));
		String[] myArray22 = { "a", "b", "a" };
		System.out.println(containsDuplicate(myArray22));
		String[] myArray21 = { "David", "David" };
		String[] myArray23 = { "David", "Joe", "David" };
		System.out.println(containsDuplicate(myArray23));
		System.out.println(containsDuplicate(myArray21));
		System.out.println(averageArrayValues(myArray1));
		int[] myArray3 = { 0, 0, 1, 1, 1, 7 };
		System.out.println(Arrays.toString(frequencyCount(myArray3)));
		int[] myArray30 = { 4, 6, 2, 2, 5, 9, 3, 4, 7, 6, 8 };
		System.out.println(Arrays.toString(frequencyCount(myArray30)));
		int[] myArray31 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		System.out.println(Arrays.toString(frequencyCount(myArray31)));
		double[] myArray4 = { 0.5, 0.1, 0.2 };
		System.out.println(Arrays.toString(reverseSound(myArray4)));
		double[] myArray40 = { 0.3, 0.2, 0.1 };
		System.out.println(Arrays.toString(reverseSound(myArray40)));
		double[] myArray41 = { 0.0, 0.1, 0.3 };
		System.out.println(Arrays.toString(reverseSound(myArray41)));
		double[] myArray5 = { 0.0, -0.1, 0.3 };
		System.out.println(Arrays.toString(scaleSound(myArray5, 2.0)));
		double[] myArray50 = { 0.4, -0.2, 0.5 };
		System.out.println(Arrays.toString(scaleSound(myArray50, 3.0)));
		double[] myArray51 = { 0.7, -0.7, 0.4 };
		System.out.println(Arrays.toString(scaleSound(myArray51, 2.5)));
		double[] myArray6 = { 0.1, 0.2, 0.3, 0.4 };
		System.out.println(Arrays.toString(echoSound(myArray6, 1, 0.5)));
		double[] myArray60 = { 0.2, 0.4, 0.4, 0.6 };
		System.out.println(Arrays.toString(echoSound(myArray60, 1, 0.75)));
		double[] myArray61 = { 0.15, 0.24, 0.36, 0.45 };
		System.out.println(Arrays.toString(echoSound(myArray61, 1, 0.25)));
		double[] myArray7 = { 0.0, 0.2, 0.7, 0.2 };
		System.out.println(Arrays.toString(smoothSound(myArray7)));
		double[] myArray70 = { 0.0, 0.35, 0.75, 0.25 };
		System.out.println(Arrays.toString(smoothSound(myArray70)));
		double[] myArray71 = { 0.0, 0.2, 0.7, 0.2, 0.3, 0.6, };
		System.out.println(Arrays.toString(smoothSound(myArray71)));
	}

	/**
	 * clearArray- makes a clear Array by replacing all the values with 0's. Takes a
	 * param of an int array and returns an int array
	 * 
	 * @param ar - is an int array that contains a variety of int's
	 * @return - an int array with the same number of indexes however, the values
	 *         are replaced with 0's
	 */
	public static int[] clearArray(int ar[]) {
		int[] zeroA = new int[ar.length];
		for (int i = 0; i < ar.length; i++) {
			ar[i] = 0;

		}
		return zeroA;
	}

	/**
	 * arrayToString - takes a param of an int array, then returns a string. The
	 * method takes the int array the converts it to a string with the "{}" around
	 * the return value
	 * 
	 * @param a - the int array that is passed to the method
	 * @return - a string of the int array accompanied by the opening and closing
	 *         "{}"
	 */
	public static String arrayToString(int a[]) {
		String ret = "{";
		int i = 0;
		while (i < a.length) {
			if (i == a.length - 1) {
				ret += a[i] + "}";
				return ret;
			}
			ret += a[i] + ", ";
			i++;
		}
		return ret;
	}

	/**
	 * containsDuplicate - takes a param of the string array and returns a boolean
	 * value. It takes a string then compares the values to see if they equal each
	 * other and if they do it returns true, otherwise false.
	 * 
	 * @param a - String array of values being compared
	 * @return - a boolean value that indicates if there are duplicates.
	 */
	public static boolean containsDuplicate(String a[]) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 1; j < a.length; j++) {
				if (a[j].equals(a[i])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * averageArrayValues - takes a param of an int array and returns a double
	 * value. Takes the param and adds up all the numbers inside the int array then
	 * finds the average of the int Array.
	 * 
	 * @param a - an int array, the values of the array will be added up and
	 *          averaged
	 * @return - the double value of the average of the int array's numbers.
	 */
	public static double averageArrayValues(int a[]) {
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		sum = sum / a.length;
		return sum;
	}

	/**
	 * Takes the param of the int array with numbers from 0-9 and returns a value of
	 * each index and how many times it appears in the first array
	 * 
	 * @param a - the param being passed that contains numbers from 0-9
	 * @return - an int array that shows how many times each index number is shown
	 *         in the first array.
	 */
	public static int[] frequencyCount(int a[]) {
		int count[] = new int[10];
		for (int i = 0; i < a.length; i++) {
			// take the index val from a and go to that spot on the count and add 1
			count[a[i]] = count[a[i]] + 1;
		}
		return count;
	}

	/**
	 * reverseSound - takes a param of a double array then puts it in reverse order.
	 * Then will return it as a double array
	 * 
	 * @param a - double array being passed to have the elements reversed
	 * @return - a double array that has the elements reversed
	 */
	public static double[] reverseSound(double a[]) {
		double rev[] = new double[a.length];
		int i = 0;
		for (int j = a.length - 1; j >= 0; j--) {
			rev[i] = a[j];
			i++;
		}
		return rev;
	}

	/**
	 * scaleSound - takes a double variable and a double array as param's. Then
	 * scales the array by the variable, however, it does not change the values of
	 * the param array.
	 * 
	 * @param a     - the param double array being scaled
	 * @param scale - the variable being used to scale the array
	 * @return - the double array from the param being scaled by the variable in the
	 *         param
	 */
	public static double[] scaleSound(double a[], double scale) {
		double scaleA[] = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			scaleA[i] = a[i] * scale;
		}
		return scaleA;

	}

	/**
	 * echoSound- takes a double array and then makes another double array with as
	 * many spots as the offset variable contains. Each value is incremented by
	 * adding the multiple of the weight and previous value in the array to the
	 * current value. It returns the double array with the adjusted values.
	 * 
	 * @param a      - the double array being manipulated, has a length equal to the
	 *               offset
	 * @param offset - indicates how many offset samples the echo begins at
	 * @param weight - the overall weight of the echo's sound
	 * @return adjA - the double array adjusted by the method with a length of param
	 *         double array added to the offset.
	 */
	public static double[] echoSound(double a[], int offset, double weight) {
		double adjArr[] = new double[a.length + offset];
		int i = 0;
		adjArr[i] = a[i];
		for (i = 1; i < adjArr.length - 1; i++) {
			adjArr[i] = a[i] + (a[i - 1] * weight);
		}
		adjArr[i] = a[a.length - 1] * weight;
		return adjArr;
	}

	/**
	 * smoothSound - takes a param of a double array then averages out the values
	 * according to its own values and the ones before and after it. For example:
	 * for value i it takes i-1, i, i+1 and the average of those three values. Then
	 * it returns a new double array
	 * 
	 * @param a - the double array being analyzed
	 * @return - a double array with the values averaged out to make a smooth sound
	 */
	public static double[] smoothSound(double a[]) {
		double aSound[] = new double[a.length];
		aSound[0] = a[0];
		aSound[aSound.length - 1] = a[a.length - 1];
		for (int i = 1; i < aSound.length - 1; i++) {
			aSound[i] = (a[i - 1] + a[i + 1] + a[i]) / 3;
		}
		return aSound;
	}
}
