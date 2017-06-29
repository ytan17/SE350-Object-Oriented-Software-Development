package hw1;


import java.util.*; // for comparing arrays in main() tests only

class AssertExp1 {

	/*
	 * minValue returns the minimum value in an array of doubles. You can assume
	 * the array is nonempty and has no duplicates. Your solution must go
	 * through the array exactly once. Your solution must not call any other
	 * functions. Here are some examples (using "==" informally):
	 * 
	 * -7 == minValue(new double[] { -7 }) -7 == minValue(new double[] { 1, -4,
	 * -7, 7, 8, 11 }) -13 == minValue(new double[] { -13, -4, -7, 7, 8, 11 })
	 * -9 == minValue(new double[] { 1, -4, -7, 7, 8, 11, -9 })
	 */
	public static double minValue(double[] list) {
		double min = list[0];
		for (int i = 0; i < list.length; ++i) {
			if (list[i] < min) {
				min = list[i];
			}
		}
		return min;
	}

	/*
	 * minPosition returns the position of the minimum value in an array of
	 * doubles. The first position in an array is 0 and the last is the
	 * array.length-1. You can assume the array is nonempty and has no
	 * duplicates. Your solution must go through the array exactly once. Your
	 * solution must not call any other functions. Here are some examples (using
	 * "==" informally):
	 * 
	 * 0 == minPosition(new double[] { -7 }) 2 == minPosition(new double[] { 1,
	 * -4, -7, 7, 8, 11 }) 0 == minPosition(new double[] { -13, -4, -7, 7, 8, 11
	 * }) 6 == minPosition(new double[] { 1, -4, -7, 7, 8, 11, -9 })
	 */
	public static boolean Duplicates(double[] list){
		Set dups = new HashSet ();
		for(double num : list){
			if(dups.add(num) == false){
				return true;
			}
		}
		return false;
	}
	public static int minPosition(double[] list) {
		 // TODO
		//preCondition: input a nonempty double array, list[0,n] of n values. And it has no duplicates
		assert(list != null);
		assert(Duplicates(list) != true);
		
		//loop-invariant: m is a index of min in list[0,i]
		int m = 0;
		for(int i = 0; i < list.length; i++){
			if(list[m] > list[i]){
				m = i;
			}
			assert(list[m] == Math.min(list[m], list[i]));
		}
		//postCondition: m is a index of min in list[0,n]
		assert(list[m] == minValue(list));
		return m;
	}


	/*
	 * numUnique returns the number of unique values in an array of doubles.
	 * Unlike the previous questions, the array may be empty and it may contain
	 * duplicate values. Also unlike the previous questions, you can assume the
	 * array is sorted.
	 * 
	 * Your solution must go through the array exactly once. Your solution must
	 * not call any other functions. Here are some examples (using "=="
	 * informally):
	 * 
	 * 0 == numUnique(new double[] { }) 1 == numUnique(new double[] { 11 }) 1 ==
	 * numUnique(new double[] { 11, 11, 11, 11 }) 8 == numUnique(new double[] {
	 * 11, 11, 11, 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88, 88 }) 8
	 * == numUnique(new double[] { 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66,
	 * 77, 88 })
	 */
	public static boolean sorted(double[] list){
		for(int i = 0; i<list.length-1; i++){
			if(list[i] > list[i+1]) return false;
		}
		return true;
	}
	
	public static int numUnique(double[] list) {
		// TODO
		//preCondition: input array is sorted
		assert(sorted(list) == true);
		int counter = 1;
		
		if(list.length == 0) return 0;
		else if(list.length == 1) return 1;
		else{
			//loop-invariant: counter is greater or equal to 1
			for(int i = 0; i < list.length-1; i++){
				assert(!(counter < 1));
				if(list[i] != list[i+1]){
					counter++;
				}
			}
		}
		
		//postCondition: counter is non-negative number
		assert(counter >= 0);
		return counter;
	}

	/*
	 * removeDuplicates returns the number of unique values in an array of
	 * doubles. You may assume that the list is sorted, as you did for
	 * numUnique.
	 * 
	 * Your solution may call numUnique, but should not call any other
	 * functions. After the call to numUnique, you must go through the array
	 * exactly one more time. Here are some examples (using "==" informally):
	 * 
	 * new double[] { } == removeDuplicates(new double[] { }) new double[] { 11
	 * } == removeDuplicates(new double[] { 11 }) == removeDuplicates(new
	 * double[] { 11, 11, 11, 11 }) new double[] { 11, 22, 33, 44, 55, 66, 77,
	 * 88 } == removeDuplicates(new double[] { 11, 11, 11, 11, 22, 33, 44, 44,
	 * 44, 44, 44, 55, 55, 66, 77, 88, 88 }) == removeDuplicates(new double[] {
	 * 11, 22, 33, 44, 44, 44, 44, 44, 55, 55, 66, 77, 88 })
	 */
	public static double[] removeDuplicates(double[] list) {
		//TODO
		//preCondition: list is sorted
		assert(sorted(list) == true);
		
		int change = numUnique(list);
		if(change == 0 || list.length == 0){
			return list;
		}
		//loop-invariant: i is greater or equal to 0 and less or equal to length of list
		assert(change >= 1);
		double[] unique = new double[change];
		int curr = 0;
		unique[curr] = list[0];
		for(int i = 0; i != list.length; i++){
			assert(i>= 0 && i <= list.length);
			if(unique[curr] != list[i]){
				unique[curr + 1] = list[i];
				curr++;
			}
			
		}
		//postCondition: length of unique is no greater than length of list
		assert(unique.length <= list.length);
		return unique;
	}

}

