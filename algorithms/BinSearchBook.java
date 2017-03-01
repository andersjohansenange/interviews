package algorithms;

public class BinSearchBook implements Searcher {

	@Override
	public int search(int target, int... numbers) {
		int low = 0, high = numbers.length - 1;
		while (low <= high) {
			final int midIdx = (low + high) / 2;
			final int midValue = numbers[midIdx];
			if (target < midValue) {
				high = midIdx - 1;
			} else if (target > midValue) {
				low = midIdx + 1;
			} else {
				return midIdx;
			}
		}
		// TODO Auto-generated method stub
		return -1;
	}

}
