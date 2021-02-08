package a6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author james gibb
 * @author john gibb
 *
 *         CS1410-001 - Assignment 6
 */
public class ReviewAnalysis {
	/**
	 * reads the file of movie reviews then gets a score for each word finding the
	 * best scoring word with a count greater than some threshold. Then scores a few
	 * reviews and comparing the computer-generated score with the actual rating.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String[] lines = null;

		try {
			lines = convertFileToStringArray("src/a6/MovieReviews.txt");
		} catch (FileNotFoundException e) {

			System.out.println("File was not found");
			return;
		}

		String[] words = new String[16444];
		double[] wordScore = new double[16444];
		int[] wordCount = new int[16444];

		int numberOfSpacesUsedInWords = 0;

		for (int i = 0; i < lines.length; i++) {
			Scanner s = new Scanner(lines[i]);

			int lineScore = s.nextInt();

			numberOfSpacesUsedInWords = processWords(s, lineScore, words, wordScore, wordCount,
					numberOfSpacesUsedInWords);
		}

		int wordCountFilter = 15;

		int bestIndex = indexOfBestWord(wordScore, wordCount, wordCountFilter, numberOfSpacesUsedInWords);
		if (bestIndex != -1) {
			System.out.println("The best scoring word is " + words[bestIndex] + " with a count of "
					+ wordCount[bestIndex] + " and average score of: " + wordScore[bestIndex] / wordCount[bestIndex]);
		} else {
			System.out.println("No word found with a word count above " + wordCountFilter);
		}

		for (int index = 360; index < 380; index++) {
			String testReview = lines[index];
			Scanner testScanner = new Scanner(testReview);

			int actualScore = testScanner.nextInt();
			String reviewText = testScanner.nextLine();
			double estimatedScore = scoreReview(reviewText, words, wordScore, wordCount, numberOfSpacesUsedInWords);
			String formattedEstimate = String.format("%.1f", estimatedScore);
			String sentimentPrediction = "Wrong";
			if ((actualScore >= 2.0 && estimatedScore >= 2.0) || (actualScore < 2.0 && estimatedScore < 2.0))
				sentimentPrediction = "Correct";
			System.out.println("estimated score: " + formattedEstimate + " actual score: " + actualScore
					+ " Sentiment Predication: " + sentimentPrediction + " | Review: " + reviewText);
			testScanner.close();
		}
	}

	/**
	 * convertFileToStringArray - opens the file and reads the lines from the file
	 * (a line is defined as what a scanner nextLine() method produces). Converts
	 * each line to lower-case. (Use the toLowerCase() method). Store the lines in a
	 * String[]
	 *
	 * @param filename - the file being used to analyze the movies
	 * @return An array of strings with each line of the file an element of the
	 *         array.
	 * @throws FileNotFoundException
	 */
	public static String[] convertFileToStringArray(String filename) throws FileNotFoundException {
		int numLines = countLines(filename);
		String[] reviews = new String[numLines];
		reviews = fillArrayWithLines(filename, numLines);
		return reviews; // Implement this method
	}

	/**
	 * countLines - Count the number of lines in a file.
	 *
	 * @param filename - the file being analyzed
	 * @return the number of lines in the file. A line is defined as what a Scanner
	 *         nextLine() provides.
	 * @throws FileNotFoundException
	 */
	public static int countLines(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner s = new Scanner(file);
		int count = 0;
		if (!s.hasNextLine()) {
			s.close();
			return count;
		}
		while (s.hasNextLine()) {
			count++;
			s.nextLine();
		}
		s.close();
		return count;
	}

	/**
	 * fillArrayWithLines - Count of the number of lines in the file, fills in a
	 * String array with those lines. Each line is converted to lower-case. and
	 * leaves the punctuation.
	 *
	 * @param filename  - the file being analyzed
	 * @param lineCount - the line number being analyzed
	 * @return a String[] with each line in the file an element in the array.
	 * @throws FileNotFoundException
	 */
	public static String[] fillArrayWithLines(String filename, int lineCount) throws FileNotFoundException {
		File file = new File(filename);
		Scanner s = new Scanner(file);
		String[] reviews = new String[lineCount];
		int count = 0;
		while (s.hasNextLine()) {
			String lowerLine = s.nextLine().toLowerCase();
			reviews[count] = lowerLine;
			count++;
		}
		s.close();
		return reviews; // Implement this method
	}

	/**
	 * indexOfWordInArray - looks for word in the words array in the first
	 * numberOfSpacesUsedInWords elements.
	 *
	 * @param words: An array of String values. The first numberOfSpacesUsedInWords
	 *        are filled.
	 * @param word: The search word.
	 * @param numberOfSpacesUsedInWords: the number of elements currently used in
	 *        the words array.
	 * @return the index of the search word in words, or -1 if not found.
	 */
	public static int indexOfWordInArray(String[] words, String word, int numberOfSpacesUsedInWords) {
		int numIndex = numberOfSpacesUsedInWords;
		for (int wordVal = 0; wordVal < numIndex; wordVal++) {
			if (word.equals(words[wordVal])) {
				return wordVal;
			}
		}
		return -1;
	}

	/**
	 * scoreReview - estimates the movie rating based on the words in the review.
	 * For each word, it find the index in the words list, then computes its average
	 * score (scores[index]/wordCount[index]) and adds it to a cumulative review
	 * score. Then counts up the number of words in the review and uses the count
	 * and the cumulative review score to get an averaged movie score.
	 *
	 * @param review: The text of the review.
	 * @param words: The array of words found in all reviews.
	 * @param scores: The cumulative score for each word in words.
	 * @param wordCount: The number of times each word in words appears in all the
	 *        reviews.
	 * @param numberOfSpacesUsedInWords: The number of elements in the arrays to be
	 *        used.
	 * @return the average score for the words in review.
	 */
	public static double scoreReview(String review, String[] words, double[] scores, int[] wordCount,
			int numberOfSpacesUsedInWords) {
		int index;
		double reviewScore = 0;
		double wordScore = 0;
		double numWords = 0;
		Scanner s = new Scanner(review);
		while (s.hasNext()) {
			String word1 = s.next();
			numWords++;
			index = indexOfWordInArray(words, word1, numberOfSpacesUsedInWords);
			double wordCount1 = wordCount[index];
			wordScore = scores[index] / wordCount1;
			reviewScore = reviewScore + wordScore;
		}
		reviewScore = reviewScore / numWords;
		s.close();
		return reviewScore; // Implement this method
	}

	/**
	 * indexOfBestWord - searches through numberOfSpacesUsedInWords elements of the
	 * scores array. Finds the index of the highest average scoring word. With this
	 * index the actual word can be found later. Ignores words whose counts are not
	 * greater than the countAbove value.
	 *
	 * @param scores: An array of cumulative scores for a word.
	 * @param counts: An array of times the word appeared in the reviews.
	 * @param countAbove: Words with counts below or equal to countAbove are
	 *        ignored.
	 * @param numberOfSpacesUsedInWords: Specifies the number of valid elements in
	 *        the arrays.
	 * @return the index of the best average score or -1 if none satisfy the
	 *         countAbove threshold.
	 */
	public static int indexOfBestWord(double[] scores, int[] counts, int countAbove, int numberOfSpacesUsedInWords) {
		double averageScore = 0.0;
		double oldAverageScore = 0.0;
		int index = -1;
		for (int count = 0; count < numberOfSpacesUsedInWords; count++) {
			if (counts[count] > countAbove) {
				averageScore = scores[count] / counts[count];
				if (oldAverageScore < averageScore) {
					oldAverageScore = averageScore;
					index = count;
				}
			}
		}
		return index;
	}

	/**
	 * processWords - goes through the words in the scanner s. If a token in s is
	 * already in words, then adds the lineScore to the word_score location for that
	 * word and it adds 1 to the word_count for that location. If the token is not
	 * is words, then it adds the token to the next available spot in words and add
	 * the lineScore to word_score at that location and put a count of 1 in
	 * word_count at that location. Adjust numberOfSpacesUsedInWords by adding 1
	 * when a new spot is used up. Does not change the numberOfSpacesUsedInWords
	 * variable if the word is already in the array.
	 *
	 * @param s: A Scanner with the text part of a movie review
	 * @param lineScore: the integer movie rating taken from the review
	 * @param words: an array to hold the words from the reviews
	 * @param wordScore: an array holding the cumulative (summed) score for that
	 *        word.
	 * @param wordCount: an array holding the number of times a word has been seen
	 *        in reviews
	 * @param numberOfSpacesUsedInWords: the number of elements used in the arrays
	 * @return the new numberOfSpacesUsedInWords. If no new words are found in s,
	 *         then it is the same value as the input numberOfWordSoFar.
	 */
	public static int processWords(Scanner s, int lineScore, String[] words, double[] wordScore, int[] wordCount,
			int numberOfSpacesUsedInWords) {

		double lineScore2 = lineScore;
		while (s.hasNext()) {

			if (numberOfSpacesUsedInWords < words.length) {
				String word1 = s.next();

				if (indexOfWordInArray(words, word1, numberOfSpacesUsedInWords) != -1) {
					wordScore[indexOfWordInArray(words, word1, numberOfSpacesUsedInWords)] = lineScore2
							+ wordScore[indexOfWordInArray(words, word1, numberOfSpacesUsedInWords)];

					wordCount[indexOfWordInArray(words, word1, numberOfSpacesUsedInWords)]++;

				} else {

					words[numberOfSpacesUsedInWords] = word1;
					wordScore[numberOfSpacesUsedInWords] = wordScore[numberOfSpacesUsedInWords] + lineScore2;
					wordCount[numberOfSpacesUsedInWords]++;
					numberOfSpacesUsedInWords = numberOfSpacesUsedInWords + 1;
				}
			} else {
				return numberOfSpacesUsedInWords;
			}
		}
		return numberOfSpacesUsedInWords;
	}
}

// We were able to conclude that the output is: Correct. If both the estimated score and the actual score are both 
// less than or greater than 2.0, except for in a very small number of cases. If either the actual score or estimated 
// score is less than 2.0 and the other is greater than 2.0 then the output is: Wrong. We could also see why this 
// happens in the main method as well because of the the conditional loop. This helps determine if the review
// was positive or negative as well. Especially if the review didn’t include the actual score initially.
