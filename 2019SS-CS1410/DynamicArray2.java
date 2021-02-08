package a8;

/**
 * @author James Gibb and John Gibb
 *
 */
public class DynamicArray2 {

	private String[] data;
	private int virtualArrayLength;

	/**
	 * Creates an empty dynamic array with room to grow.
	 * 
	 */
	public DynamicArray2() {

		data = new String[8];

		virtualArrayLength = 0;
	}

	/**
	 * Returns the number of elements in the dynamic array.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		virtualArrayLength = 0;
		for (int count = 0; count < data.length; count++) {
			if (data[count] != null) {
				virtualArrayLength++;
			}
		}
		return virtualArrayLength;
	}

	/**
	 * Throws an IndexOutOfBoundsException if i is not a valid index for adding to
	 * the dynamic array, otherwise inserts s at index i. Elements can be added from
	 * index 0 to this.size().
	 * 
	 * @param i - the index being referenced
	 * @param s - string being added
	 */
	public void add(int i, String s) {

		if (i < 0 || i > data.length) {
			throw new IndexOutOfBoundsException();
		}
		if (data[data.length - 1] == null) {

			for (int j = data.length - 1; j > i; j--) {
				data[j] = data[j - 1];
			}
			data[i] = s;

		} else if (data[data.length - 1] != null) {
			String[] newData = new String[data.length * 2];

			for (int j = 0; j < i; j++) { //
				newData[j] = data[j];
			}

			for (int j = i + 1; j < data.length; j++) {
				newData[j] = data[j - 1];
			}
			newData[i] = s;
			data = newData;
		}
	}

	/**
	 * Appends s to the end of the dynamic array at index this.size().
	 * 
	 * @param s - String being added to the array
	 */
	public void add(String s) {
		add(size(), s);

	}

	/**
	 * Throws an IndexOutOfBoundsException if i is not a valid index of the dynamic
	 * array, otherwise removes the element at index i and shifts the elements after
	 * i down one to fill in the gap.
	 * 
	 * @param i - index value being referenced
	 */
	public void remove(int i) {
		if (i < 0 || i >= data.length)
			throw new IndexOutOfBoundsException();
		if (data.length > 1) {
			for (int j = 0; j < data.length - 1; j++) {
				data[i + j] = data[i + j + 1];
			}
		} else {
			data[i] = null;
		}
	}

	/**
	 * Throws an IndexOutOfBoundsException if i is not a valid index of the dynamic
	 * array, otherwise returns the element at index i
	 * 
	 * @param i - index being referenced
	 * @return - String that corresponds with the index passed to the method
	 */
	public String get(int i) {
		if (i < 0 || i >= data.length)
			throw new IndexOutOfBoundsException();

		return data[i];
	}

	/**
	 * Throws an IndexOutOfBoundsException if i is not a valid index of the dynamic
	 * array, otherwise replaces the element at index i with s
	 * 
	 * @param i - index reference
	 * @param s - string corresponding with the index reference
	 */
	public void set(int i, String s) {
		if (i < 0 || i >= data.length) {
			throw new IndexOutOfBoundsException();
		}
		data[i] = s;
	}

	/**
	 * Returns a formatted string version of this dynamic array.
	 * 
	 * @return the formatted string
	 */
	public String toString() {
		String result = "[";
		for (int i = 0; i < size(); i++) {
			if (i == size() - 1) {
				result = result + get(i);
			} else {
				result = result + get(i) + ", ";
			}
		}
		return result + "] backing size: " + data.length;
	}

	public static void main(String[] args) {
		DynamicArray2 d = new DynamicArray2();
		d.add("David");
		d.add("Joe");
		System.out.println(d.toString());
		System.out.println(d.get(0));
		d.set(1, "Mary");
		System.out.println(d.get(1));

	}
}