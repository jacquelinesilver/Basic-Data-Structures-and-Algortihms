import java.util.*;
/* Jacqueline Silver
this project is adapted from a class assignment in COMP 251 at McGill
 This project finds the longest sequence of unique items */
public class max_unique_sequence {
	public static int elements(int[] sizes) {
		Map<Integer, Integer> map = new HashMap<>(); //creates hash map
		int maximum = 0;
		int first = 0; //initalizes variables for maximum length and start of sequence

		for (int i = 0; i < sizes.length; i++) { //iterates thru given array
			if (map.containsKey(sizes[i])) { //if the element at the current index is in the map
				if (map.get(sizes[i]) >= first) { //if the value there is greater than starting index
					first = map.get(sizes[i]) + 1; //the first index of ongoing sequence becomes the next value
				}
			}
			//whether or not the map contains element at index i
			map.put(sizes[i], i); //put index i in slot at key of the current element
			int length = (i - first + 1); //find difference between first index # and the current index # to get length
			if (length > maximum) {
				maximum = length; //update maximum length if the length is bigger
			}
		}
		return maximum;
	}
}


