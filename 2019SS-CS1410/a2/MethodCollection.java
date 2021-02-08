package a2;

import java.util.Scanner;

/**
 * A collection of methods for the second assignment of CS 1410.
 * 
 * @author jamesgibb
 *
 */
public class MethodCollection {

	/**
	 * The main contains a variety of methods. They are described below.
	 * 
	 * countTokens: The method counts the number of tokens in each string. It takes
	 * the parameter of a string. A scanner should be used to analyze the string.
	 * The method will return an int representing the number of tokens in the
	 * string.
	 * 
	 * countTokensThatAreNotInt: The method will count all the tokens that are not
	 * ints and skip those that are. It takes the parameter of a string. This is the
	 * sentence that will be scanned for the number of tokens that are not ints. The
	 * method should use a scanner to analyze the string.
	 * 
	 * describeSign: The method will determine if a int is non-negative or negative.
	 * If the number is less than 0 then the method will return negative. Otherwise,
	 * the method will return non-negative. The method takes the parameter of an
	 * int.
	 * 
	 * isEvenlyDivisibleBySeven: The method will test if a int is divisible by 7. It
	 * will return true if it is and false otherwise. It takes a parameter of an
	 * int, this is the number being tested.
	 * 
	 * makeLine: Produces a horizontal line and will be used by the makeSquare
	 * method. The method takes the parameters of char, char, int. The char "+"
	 * should begin each line and the char "-" should be in between. Producing the
	 * line below. "+------+".
	 * 
	 * makeSquare: The method produces a square with the parameter int width. Width
	 * determines the size of the square being produced. Each side should consist of
	 * the character "|". The method will also call the makeLine method in order to
	 * print a line at the top and bottom of the square.
	 * 
	 * capitalizeLastCharacter: The method takes a parameter of a string and
	 * capitalizes the last character in the word.
	 * 
	 * capitalizeLastCharacterInSentence: The method returns a string that has the
	 * last character in each word in the sentence capitalized. For instance, the
	 * string "Hello World" will become "HellO WorlD".
	 */
	public static void main(String[] args) {
		System.out.println("The countTokens method counts the tokens in each string. ");
		System.out.println("The amount of the tokens in this string is : " + countTokens("this is a test for tokens"));
		System.out.println("The method should return 0 tokens because this is is an empty string. The result is: "
				+ countTokens(" "));
		System.out.println();
		System.out.println("The method countTokensThatAreNotInt counts all the tokens that are not ints.");
		System.out.println("The amount of tokens that are not ints are: "
				+ countTokensThatAreNotInt("hello world of 34 universe"));
		System.out.println("The method should return 0 because it is an empty string: " + countTokensThatAreNotInt(""));
		System.out.println(
				"The method should return 0 because it only contains ints: " + countTokensThatAreNotInt("1234"));
		System.out.println("The method should return 3 because it contains 3 non-ints: "
				+ countTokensThatAreNotInt("1234 is my address"));
		System.out.println();
		System.out.println("The method describeSign shows if a sign is negative or non-negative");
		System.out.println("The method should return non-negative as the number is greater than 0:" + describeSign(7));
		System.out.println("The method should return non-negative as the number is equal to 0: " + describeSign(0));
		System.out.println("The method should return negative since the number is less than 0: " + describeSign(-4));
		System.out.println("The method should return negative since the number is less than 0: " + describeSign(-44));
		System.out.println();
		System.out.println("The isEvenlyDivisibleBySeven method shows if a number is divisble by 7.");
		System.out.println("The method should return true as 14 is a multipleof 7: " + isEvenlyDivisibleBySeven(14));
		System.out
				.println("The method should return false as 25 is not a multipleof 7: " + isEvenlyDivisibleBySeven(25));
		System.out.println("The method should return false as 4 is not a multipleof 7: " + isEvenlyDivisibleBySeven(4));
		System.out.println();
		System.out.println("The method makeLine implements a line: " + makeLine('+', '-', 6));
		System.out.println("The method makeSquare implements a box:\n" + makeSquare(4));
		System.out.println("The method makeSquare implements a box:\n" + makeSquare(8));
		System.out.println("The method makeSquare implements a box:\n" + makeSquare(2));
		System.out.println();
		System.out.println("The method capitalizeLastCharacter capitalizes the last character in the string.");
		System.out.println("The input for this method is: Hello. The result of the method should equal HellO: "
				+ capitalizeLastCharacter("Hello"));
		System.out.println("The input for this method is: HELLO. The result of the method should equal HELLO: "
				+ capitalizeLastCharacter("HELLO"));
		System.out.println(
				"The input for this method is: Capitalization. The result of the method should equal CapitalizatioN: "
						+ capitalizeLastCharacter("Capitalization"));
		System.out.println();
		System.out.println(
				"The method capitalizeLastCharacterInSentence capitalizes the last character in the sentence.");
		System.out.println("The method input is: Hello World. The result of the method should equal Hello WorlD:"
				+ capitalizeLastCharactersInSentence("Hello world"));
		System.out.println(
				"The method input is: Hello planet EArth. The result of the method should equal HellO planeT eartH:"
						+ capitalizeLastCharactersInSentence("Hello planet earth"));

	}

	/**
	 * This method counts the number of tokens in each string. It takes the
	 * parameter of a string. A scanner should be used to analyze the string. The
	 * method will return an int representing the number of tokens in the string.
	 * 
	 * @param sentence - this is the string being analyzed for the number of tokens
	 *                 it contains.
	 * @return - will return an int that represents the number of tokens in the
	 *         string.
	 */
	public static int countTokens(String sentence) {
		Scanner s = new Scanner(sentence);
		int count = 0;
		while (s.hasNext()) {
			count++;
			s.next();
		}
		s.close();
		return count;

	}

	/**
	 * The method will count all the tokens that are not ints and skip those that
	 * are. It takes the parameter of a string. This is the sentence that will be
	 * scanned for the number of tokens that are not ints. The method should use a
	 * scanner to analyze the string.
	 * 
	 * @param sentence - the string being analyzed for the number of tokens that are
	 *                 not ints
	 * @return - will return an int with the number of the non-ints in the string
	 */
	public static int countTokensThatAreNotInt(String sentence) {
		int count = 0;
		Scanner s = new Scanner(sentence);
		while (s.hasNext()) {
			if (s.hasNext() != s.hasNextInt()) {
				count++;
			}
			s.next();
		}
		s.close();
		return count;
	}

	/**
	 * The method will determine if a int is non-negative or negative. If the number
	 * is less than 0 then the method will return negative. Otherwise, the method
	 * will return non-negative. The method takes the parameter of an int.
	 * 
	 * @param value - this is the int being tested if it is a negative or
	 *              non-negative
	 * @return - will return a string that will describe the sign as negative or
	 *         non-negative.
	 */
	public static String describeSign(int value) {
		String sign = "negative";
		if (value >= 0) {
			sign = "non-negative";
		} else if (value < 0) {
			sign = "negative";
		}
		return sign;
	}

	/**
	 * The method will test if a int is divisible by 7. It will return true if it is
	 * and false otherwise. It takes a parameter of an int, this is the number being
	 * tested.
	 * 
	 * @param value - the int being tested
	 * @return - a boolean value determining whether a number is divisible by 7.
	 */
	public static boolean isEvenlyDivisibleBySeven(int value) {
		if (value % 7 != 0) {
			return false;
		}
		return true;
	}

	/**
	 * Produces a horizontal line and will be used by the makeSquare method. The
	 * method takes the parameters of char, char, int. The char "+" should begin
	 * each line and the char "-" should be in between. Producing the line below.
	 * "+------+".
	 * 
	 * @param edge  - the char used to represent each side. Should be "+"
	 * @param inner - the char used inside the edge. Should be "-"
	 * @param width - the int used to determine the size of the line
	 * @return - will produce a string with the size determined by the variable
	 *         width.
	 */
	public static String makeLine(char edge, char inner, int width) {
		String line = "";
		int i = 0;
		while (i < width - 2) {
			line = line + inner;
			i = i + 1;
		}
		return edge + line + edge;
	}

	/**
	 * The method produces a square as illustrated below with the paramter int
	 * width. Width determines the size of the square being produced. Each side
	 * should consist of the character "|". The method will also call the makeLine
	 * method in order to print a line at the top and bottom of the square.
	 * 
	 * <pre>
	 * +-----+
	 * |     |
	 * |     |
	 * |     |
	 * |     |
	 * |     |
	 * +-----+
	 * </pre>
	 * 
	 * @param width - determines the size of the square (int)
	 * @return - will return a square with the size determined by the variable
	 *         width.
	 */
	public static String makeSquare(int width) {
		int spaceCount = 0; // initialize count
		String square = ""; // initialize square
		square += makeLine('+', '-', width) + square + "\n";
		String side = "|";
		String space = " ";
		int i = 0;
		if (width == 2) {
			square += makeLine('+', '-', width) + "\n";
			return square;
		}
		while (i < width - 2) {
			square += side;
			spaceCount = 0;
			while (spaceCount < width - 2) {

				square += space;
				spaceCount++;
			}
			square += side + "\n";
			i++;
		}
		square += makeLine('+', '-', width) + "\n";
		return square;
	}

	/**
	 * The method takes a parameter of a string and capitalizes the last character
	 * in the word.
	 * 
	 * @param word - the string parameter being altered by the method.
	 * @return - will return a new version of the string word with the last
	 *         character capitalized.
	 */
	public static String capitalizeLastCharacter(String word) {
		String newWord = word.replace(word.charAt(word.length() - 1),
				Character.toUpperCase(word.charAt(word.length() - 1)));
		return newWord;
	}

	/**
	 * The method returns a string that has the last character in each word in the
	 * sentence capitalized. For instance, the string "Hello World" will become
	 * "HellO WorlD".
	 * 
	 * @param sentence = String parameter of sentence that needs to be altered
	 *                 newSentence = the new sentence after the last character in
	 *                 each of the words is capitalized newWord = each new word,
	 *                 that has the last character capitalized Scanner s = scanner
	 *                 used to move the string
	 * @return - the new string altered to figure
	 */
	public static String capitalizeLastCharactersInSentence(String sentence) {
		String newSentence = "";
		String newWord = "";
		Scanner s = new Scanner(sentence);
		
		while (s.hasNext()) {
			if(newSentence ==  "") {
				newWord = s.next();
				newWord = newWord.replace(newWord.charAt(newWord.length() - 1), 
						Character.toUpperCase(newWord.charAt(newWord.length()-1)));
				newSentence = newWord;
			}
			newWord = s.next();
			newWord = newWord.replace(newWord.charAt(newWord.length() - 1), 
					Character.toUpperCase(newWord.charAt(newWord.length()-1)));
			newSentence = newSentence + " " + newWord;
				
			}
		
		s.close();
		return newSentence;
	
	}
}
