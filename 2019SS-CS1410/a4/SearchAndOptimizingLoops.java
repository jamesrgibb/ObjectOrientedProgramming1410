package a4;

import java.awt.Color;
import java.util.Scanner;

/**
 * @author jamesgibb
 *
 */
public class SearchAndOptimizingLoops {

	/**
	 * This is the main method that holds a collection of all the methods in the
	 * class
	 * 
	 * @param args - the arguments passed in the main method
	 */
	public static void main(String[] args) {
		System.out.println(findSmallestPositiveNumber("4 6 9 -6"));
		System.out.println(lowestAlphabetically(("bug car ant")));
		System.out.println(lowestAlphabetically(("ant bug car")));
		System.out.println(findSmallestNumberInTwoStrings("4 2 8 10", "4 5 6 1 9"));
		System.out.println(curveScores("70 80 90"));
		System.out.println(containsThisColor(new Picture("Arches.jpg"), Color.orange));

	}

	/**
	 * findSmallestPositiveNumbers - takes a param of a String. Analyzes the numbers then returns the smallest one
	 * @param numbers - string full of numbers both negative and positive. 
	 * @return - the smallest positive number in the string.
	 */
	public static int findSmallestPositiveNumber(String numbers) {
		int smallestInt = 10000000;
		Scanner sc = new Scanner(numbers);
		while (sc.hasNext()) {
			int nextNum = sc.nextInt();
			if (nextNum < smallestInt && nextNum > 0) {
				smallestInt = nextNum;
			}
		}
		sc.close();
		return smallestInt;
	}

	/**
	 * lowestAlphabetically - takes a param of a String. Analyzes the string for the lowest leixographic value. Then 
	 * returns the string with the lowest value.  
	 * @param words - a string of words separated by spaces.
	 * @return - the word/token with the lowest lexiographic value. 
	 */
	public static String lowestAlphabetically(String words) {
		Scanner sc = new Scanner(words);
		String lowString = sc.next();
		String comp = "";
		while(sc.hasNext()) {
			comp = sc.next();
			if(lowString.compareTo(comp+1) > 0) {
				lowString = comp;
			}
		}
		sc.close();
		return lowString;
	}

	/**
	 * findSmallestNumberInTwoStrings - takes two String param's. Analyzes the two string and returns the 
	 * smallest number out of the numbers in both strings
	 * @param numbers1 - first set of numbers being analyzed. Formatted in a string
	 * @param numbers2 - second set of numbers being analyzed. Formatted in a string
	 * @return - the smallest number out of both strings. 
	 */
	public static int findSmallestNumberInTwoStrings(String numbers1, String numbers2) {
		Scanner s1 = new Scanner(numbers1);
		Scanner s2 = new Scanner(numbers2);
		int smallestNum = 10000000;
		while (s1.hasNextInt()) {
			int numToken = s1.nextInt();
			if (numToken < smallestNum) {
				smallestNum = numToken;
			}

		}
		s1.close();
		while (s2.hasNext()) {
			int numToken2 = s2.nextInt();
			if (numToken2 < smallestNum) {
				smallestNum = numToken2;
			}
		}
		s2.close();
		return smallestNum;

	}

	/**
	 * curvScores - takes a string of numbers that represent the test scores. Then takes the highest score and subtracts
	 *  from 100 then adds the difference to all the scores. Returns a string of numbers with curved scores.
	 * @param scores - the string of scores
	 * @return - returns an adjusted string of scores that have been curved. 
	 */
	public static String curveScores(String scores) {
		Scanner sc = new Scanner(scores);
		int highScore = sc.nextInt();
		while (sc.hasNextInt()) {
			int num = sc.nextInt();
			if (num > highScore) {
				highScore = num;
			}
		}
		sc.close();
		int curve = 100 - highScore;
		Scanner sc2 = new Scanner(scores);
		while (sc2.hasNextInt()) {
			int num = sc2.nextInt();
			if (num > highScore) {
				highScore = num;
			}
		}
		sc2.close();
		String newNum = "";
		Scanner sc3 = new Scanner(scores);
		int num = 0;
		while (sc3.hasNextInt()) {
			num = sc3.nextInt();
			newNum += (num + curve)+" ";
		}
		newNum = newNum.replace("100 ", "100");
		sc.close();
		return newNum;
	}

	/**
	 * containsThisColor - takes a param of a picture as well as a color object. Then tests each pixel to see if
	 * any of them are the same. Will return true if the color exists in the photo, otherwise will return false
	 * @param picture - the picture being compared with the color object
	 * @param color - the color that is being compared to the colors in the photo
	 * @return - a boolean value of true if the color is in the photo. Otherwise will return false. 
	 */
	public static boolean containsThisColor(Picture picture, Color color) {
		 Picture picCopy = new Picture(picture);
		 for (int row = 0; row < picCopy.height(); row++) {
				for (int col = 0; col < picCopy.width(); col++) {
					Color c = picCopy.get(col,row);
					c.getRGB();
					if((color.equals(c))){
						return true;
					}
					}
		 		}
	return false;
	}
}