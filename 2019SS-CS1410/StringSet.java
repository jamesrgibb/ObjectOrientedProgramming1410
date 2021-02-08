package a8;

/**
 * @author James Gibb and John Gibb
 *
 */
public class StringSet {

	/**
	 * Dynamic Array Instance Variable
	 */
	private DynamicArray d;

	/**
	 * Creates an empty StringSet object
	 */
	public StringSet() {
		d = new DynamicArray();
	}

	/**
	 * Throws an IllegalArgumentException if e is null, otherwise adds e to the set
	 * if there is not already an element in the set equal to e
	 * 
	 * @param e - the string being put in the set
	 * 
	 */
	public void insert(String e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		int count3 = 0;
		int count2 = 0;
		int index = 0;
		for (int count = 0; count < d.size(); count++) {
			if (d.get(count).equals(e)) {
				count2++;
			}
			if (d.get(count).equals(null) && count3 == 0)
				index = count;
			count3++;
		}
		if (count2 == 0) {
			d.add(index, e);
		}
	}

	/**
	 * Throws an IllegalArgumentException if e is null, otherwise indicates whether
	 * the set contains e
	 * 
	 * @param e - the string being implemented in the set
	 * @return - boolean value indicating whether the set contains e
	 */
	public boolean contains(String e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < d.size(); i++) {
			if (d.get(i).equals(e)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Throws an IllegalArgumentException if e is null, otherwise removes e from the
	 * set
	 * 
	 * @param e - string being removed from the set
	 */
	public void remove(String e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < d.size(); i++) {
			if (d.get(i).equals(e)) {
				d.remove(i);
			}
		}
	}

	/**
	 * Returns the number of strings in the set
	 * 
	 * @return - the number of strings in the set
	 */
	public int size() {

		return d.size();
	}

	/**
	 * Computes and returns the union of the StringSet that calls this method and
	 * the StringSet argument to the method. The original StringSets should not be
	 * changed. The union set contains every element of each of the original
	 * StringSets. Throws an IllegalArgumentException if other is null.
	 * 
	 * @param other - stringset being passed
	 * @return - the unified stringsets
	 */
	public StringSet union(StringSet other) {
		if (other == null) {
			throw new IllegalArgumentException();
		}

		StringSet unified = new StringSet();
		unified = other;
		int count;
		for (count = 0; count < d.size(); count++) {
			if (other.contains(d.get(count)) == false) {
				unified.insert(d.get(count));
			}

		}

		return unified;
	}

	/**
	 * Computes and returns the intersection of the StringSet that calls this method
	 * and the StringSet argument to the method. The original StringSets should not
	 * be changed. The intersection set contains only the elements that are in both
	 * of the StringSets. Throws an IllegalArgumentException if other is null.
	 * 
	 * @param other - StringSet being passed
	 * @return - the common points in each StringSet
	 */
	public StringSet intersection(StringSet other) {
		if (other == null) {
			throw new IllegalArgumentException();
		}
		StringSet intersection = new StringSet();
		for (int count = 0; count < d.size(); count++) {
			if (other.contains(d.get(count)) == true) {
				intersection.insert(d.get(count));
			}
		}

		return intersection;
	}

	/*
	 * Returns a formatted string version of this set Examples: If set contains "a"
	 * and "b", this method should return the string "{a, b}".
	 * 
	 * @return String of the StringSet
	 */
	public String toString() {
		String result = "{";
		if (d.size() > 0)
			result += d.get(0);

		for (int i = 1; i < d.size(); i++)
			result += ", " + d.get(i);

		return result + "}";

	}

}