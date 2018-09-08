import java.io.*; //1
import java.util.*; //2
import java.text.*; //3
import java.math.*;  //4
import java.util.regex.*; //5

public class Main {

    /*
     1. removes duplicates
     2. compares by numbers of 1s
     3. if 1's are the same, compares by decimal value, not binary (5 < 6)
     4. constraints:    1 ≤ n ≤ 105
                        1 ≤ elementsi ≤ 109
     5. returns initial array sorted

     */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = 0;
		n = Integer.parseInt(in.nextLine().trim());
		int[] elements = new int[n];
		int element;
		for (int i = 0; i < n; i++) {
			element = Integer.parseInt(in.nextLine().trim());
			elements[i] = element;
		}
		// call rearrange function
		int[] results = rearrange(elements);
		for (int i = 0; i < results.length; i++) {
			System.out.println(String.valueOf(results[i]));
		}
	}

	static int[] rearrange(int[] elements) {
		int maxAllowedArraySize = (int) Math.pow(10, 5);
		int maxAllowedIntValue = (int)Math.pow(10, 9);

		// validate input for maxAllowedArraySize constraint
		if (elements.length > maxAllowedArraySize)
			try {
				throw new Exception("array length is out of max allowed bounds! Cutting it to length of " + maxAllowedArraySize);
			} catch (Exception e) {
				e.printStackTrace();
				elements = Arrays.copyOf(elements, maxAllowedArraySize);
			}

		// remove duplicates and sort array using TreeSet
		Set<Integer> treeSet = new TreeSet<>();
		for (int element : elements) {
			// validate input for  maxAllowedIntValue constraint
			if (element > maxAllowedIntValue) {
				try {
					throw new Exception("value is greater than max allowed (" + maxAllowedIntValue + ") Removing it from calculation...");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				treeSet.add(element);
			}
		}

		// convert decimal integers to binary, count number of occurences on '1'
		// and save that counts for each integer
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		for (int number : treeSet) {
			int key = number;
			int ones_count = 0;
			do {
				int remainder = number % 2;
				if (remainder == 1) ones_count++;
				number = number / 2;
			} while (number > 0);
			map.put(key, ones_count);
		}

		//sort map by values (by ones_count's)
		List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});
		LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<Integer, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		// convert set of Integers to int[]
		Set<Integer> sortedKeySet = sortedMap.keySet();
		int i = 0;
		int[] sorted_array = new int[sortedKeySet.size()];
		for (Integer integer : sortedKeySet) sorted_array[i++] = integer;
		return sorted_array;
	}

}