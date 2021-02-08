package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Implements a kNN classifier on Face samples.
 * Assignment 9 CS 1410
 * @author John Gibb and James Gibb
 *
 */
public class KNNClassifier {

	private int k; // The number of nearest neighbors used
	private ArrayList<Face> samples; // The List of known samples
	private ArrayList<Face> kClosestSet; // The sublist of the nearest k samples
	private Face unknown; // The face that is being tested as an unknown sample.

	// Some getters to allow the GUI to draw these elements
	public ArrayList<Face> getSamples() {
		return samples;
	}

	public ArrayList<Face> getkClosestSet() {
		return kClosestSet;
	}

	public Face getUnknown() {
		return unknown;
	}
	
	/**
	 * Swap out the old unknown for the new one.
	 * 
	 * @param newUnknown
	 */
	public void setUnknown(Face newUnknown) {
		// To test the classifier, pull one sample out of the known samples.
		// Pretend we do not know its classification and classify it.
		// Compare the kNN classification with what it really is.
		// If there already was an unknown, add it back into samples
		if (unknown != null)
			samples.add(unknown);

		// Now find the unknown, save it, and remove it from the samples
		int unknownIndex = samples.indexOf(newUnknown);
		if (unknownIndex != -1) {
			unknown = samples.get(unknownIndex);
			samples.remove(unknownIndex);
		}
		kClosestSet = null;
	}

	/**
	 * Construct the classifier object with a k value set.
	 * 
	 * @param kInit
	 */
	public KNNClassifier(int kInit) {
		k = kInit;
		samples = new ArrayList<Face>(); // Make the List to store samples in
		kClosestSet = null; // The nearest set is not yet known
		unknown = null; // no unknown face yet.
	}

	/**
	 * Add a known sample to the classifier.
	 * 
	 * @param sample
	 */
	public void addSample(Face sample) {
		samples.add(sample);
	}

	/**
	 * Get rid of the existing samples in preparation for adding new ones.
	 */
	public void clearSamples() {
		samples.clear();
	}


	/**
	 * Find the distance from every known sample to the unknown and store each
	 * distance in each known sample. At the end of this method, each Face in
	 * samples should have a correct distance to the unknown sample assigned to its
	 * distance instance variable. Call the methods in Face to accomplish this.
	 * 
	 * @param unknown
	 */
	public void computeDistanceFromUnknownToSamples(Face unknown) {
		for(Face face: samples)
		{
			face.distance = Math.sqrt(Math.pow(unknown.getX()-face.getX(), 2) + Math.pow((unknown.getY()-face.getY()), 2));
		}
	}

	/**
	 * Set the internal kClosestSet instance variable to the nearest k Faces in
	 * samples. Assumes Face distances have been calculated. At the end of this
	 * method the kClosestSet should contain the k smallest Faces (as defined by
	 * Face.compareTo). You can assume there are at least k items in samples.
	 */
	public void findKClosest() {
		kClosestSet = new ArrayList<Face>(k);
		Collections.sort(samples);
		int j =0;
		Face transfer;
		while(j < k) {
			if(samples.get(j) != null) {
			transfer = samples.get(j);
			kClosestSet.add(transfer);
			j++;
			}
		}
		}

	

	/**
	 * Given a HashMap of classifications and their votes, pick the classification
	 * with the most votes. For a tie, one of the tied classifications can be picked
	 * but it is not specified which.
	 * 
	 * @param map with entries of classifications as keys and number of votes as
	 *            values.
	 * @return the classification with the most votes.
	 */
	public String getHighestVotedClassification(HashMap<String, Integer> map) {
		Integer vote = 0;
		String ret = "";
		for(String key : map.keySet()) {
			if(vote<map.get(key)) {
				vote = map.get(key);
				
				ret = key;
				
			}
			
		}
		
		return ret;
	}

	/**
	 * Use the stored kClosestSet instance variable to vote on the classification.
	 * Each Face in kClosestSet has a classification. This method uses a Map to
	 * store classifications and their vote count. It returns the classification
	 * with the highest vote. Use the getHighestVotedClassification to pick the
	 * winner.
	 * 
	 * @return the String classification.
	 */
	public String voteOnClassification() {
		HashMap<String, Integer> map = new HashMap<>();
		int value = 0;
		for(int count = 0; count < kClosestSet.size(); count++) {
			if(!map.containsKey(kClosestSet.get(count).classification)){
			map.put(kClosestSet.get(count).classification, 1);
		}
			if(map.containsKey(kClosestSet.get(count).classification)){
				map.replace(kClosestSet.get(count).classification, map.get(kClosestSet.get(count).classification)+1);
			}
		}

		return getHighestVotedClassification(map);
	}

	/**
	 * The main method to find a classification. The code calls methods to enact
	 * each of the needed steps.
	 * 
	 * @param unknown sample
	 * @return the classification
	 */
	public String classifyUnknownSample(Face unknown) {
		computeDistanceFromUnknownToSamples(unknown);
		findKClosest();
		return voteOnClassification();
	}
	
}
