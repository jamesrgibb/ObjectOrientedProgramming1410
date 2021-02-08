package a3;

import java.awt.Color;
import java.util.Scanner;

/**
 * CS1410 -
 * 
 * @author jamesgibb
 *
 */
public class LoopsAndImages {

	/**
	 * This the main method and the other methods will be implemented through this
	 * method
	 * 
	 * @param
	 */
	public static void main(String[] args) {
		System.out.println("The expected output is: An animal just walked by");
		System.out.println("The actual output is: " + hideLetterA("An animal just walked by"));
		System.out.println(
				"The expected output is: An animal just walked by" + "My name is James and I like to drive cars");
		System.out.println("The actual output is: " + hideLetterA("My name is James and I like to drive cars"));
		System.out.println("The actual output is: " + hideLetterA(""));
		System.out
				.println("The output should result in false because there are an odd amount of numbers in the string: "
						+ hasMoreEvenThanOdd("1 2 3 4 6"));
		System.out
				.println("The output should result in true because there are an even amount of numbers in the string: "
						+ hasMoreEvenThanOdd("2 3 -9 7 4 9"));
		System.out.println(makeTextTriangle(3));
		Picture picture = new Picture("Arches.jpg");
		makeGrey(new Picture(picture)).show();
		makeNegative(new Picture(picture)).show();
		makeBrighter(new Picture(picture)).show();;
	}

	/**
	 * hideLetterA will change the sentence from containing the char 'a' to '*'. For
	 * example: "I ran all the way from Alabama" to "I r*n *ll the w*y from *l*b*m*"
	 * 
	 * @param sentence - the original sentence that needs to be changed
	 * @return - the altered sentence that has '*' instead of 'a' in the sentence.
	 */
	public static String hideLetterA(String sentence) {
		String newSentence = " ";
		Scanner sc = new Scanner(sentence);
		while (sc.hasNext()) {
			newSentence = sentence.replace('a', '*').replace('A', '*');
			sc.next();
		}
		sc.close();
		return newSentence;
	}

	/**
	 * hasMoreEvenThanOdd method counts the number of even and odd numbers in the
	 * string.
	 * 
	 * @param numbers - a string containing ints in a string with spaces between
	 *                each one
	 * @return - returns true or false depending on which occurs more in the string
	 */
	public static boolean hasMoreEvenThanOdd(String numbers) {
		Scanner sc = new Scanner(numbers);
		int evenNum = 0;
		int oddNum = 0;
		while (sc.hasNextInt()) {
			if (sc.nextInt() % 2 == 0) {
				evenNum++;
			} else {
				oddNum++;
			}
		}
		if (evenNum > oddNum) {
			sc.close();
			return true;
		}
		sc.close();
		return false;
	}

	/**
	 * makeTextTriangle - method draws out a triangle made of stars according to the
	 * int param that is received.
	 * 
	 * @param numRows - the number of rows that the equation will execute.
	 * @return - A triangle of stars/asterisks with the number of rows that are
	 *         inputed into numRows
	 */
	public static String makeTextTriangle(int numRows) {
		String tri = "";
		String newLine = "\n";
		int count = 0;
		for (count = 0; count < numRows; count++) {
			for (int i = 0; i <= count; i++) {
				tri += "*";
			}
			tri += newLine;
		}
		return tri;
	}

	/**
	 * makeGrey - will turn the picture to show all grey values in the pixels. All
	 * of the color values are added up and averaged.
	 * 
	 * @param picture - the original image being edited
	 * @return - returns the picture with all the pixels displaying the grey values.
	 */
	public static Picture makeGrey(Picture picture) {
		Picture pictureCopy = new Picture(picture);
		for (int row = 0; row < pictureCopy.height(); row++) {
			for (int col = 0; col < pictureCopy.width(); col++) {
				Color c = pictureCopy.get(col, row);
				int intensity = (c.getRed() + c.getBlue() + c.getGreen()) / 3;
				Color newColor = new Color(intensity, intensity, intensity);
				pictureCopy.set(col, row, newColor);
			}
		}
		return pictureCopy;
	}

	/**
	 * makeNegative - takes the original picture and inverts it to give it the
	 * negative effect. It takes a param of the original picture. Each color value
	 * is subtracted from 255.
	 * 
	 * @param picture - the original picture that is being altered
	 * @return - a inverted color of the original image
	 */
	public static Picture makeNegative(Picture picture) {
		Picture picCopy = new Picture(picture);
		for (int row = 0; row < picCopy.height(); row++) {
			for (int col = 0; col < picCopy.width(); col++) {
				Color c = picCopy.get(col, row);
				Color newColor = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
				picCopy.set(col, row, newColor);
			}
		}
		return picCopy;
	}

	/**
	 * safeColor - the method takes an int parameter for the color value and will
	 * make sure that the value is between the range of 0-255.
	 * 
	 * @param colorVal the value of each color passed through the method
	 * @return - a value between the range of 0-255.
	 */
	public static int safeColor(int colorVal) {
		if (colorVal < 0) {
			colorVal = 0;
		}
		if (colorVal > 255) {
			colorVal = 255;
		}
		return colorVal;
	}

	/**
	 * makeBrighter - makes the image brighter by doubling the color value while
	 * staying within the color limits that are described.
	 * 
	 * @param picture - the picture file used
	 * @return - a brighter picture with double the value of the original unless the
	 *         limits are outside the range of 0-255
	 */
	public static Picture makeBrighter(Picture picture) {
		Picture picCopy = new Picture(picture);
		for (int row = 0; row < picCopy.height(); row++) {
			for (int col = 0; col < picCopy.width(); col++) {
				Color c = picCopy.get(col, row);
				Color newColor = new Color(safeColor(c.getRed() * 2), safeColor(c.getGreen() * 2),
						safeColor(c.getBlue() * 2));
				picCopy.set(col, row, newColor);
			}
		}
		return picCopy;
	}

}