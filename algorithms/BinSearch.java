package algorithms;

public class BinSearch implements Searcher {
	
	/**
	 * @param value Value to search for
	 * @param numbers Numbers to search in, assumed sorted
	 * @return pos of occurence if found, -1 if not found
	 */
	public int search(final int forValue, final int... numbers) {
		return binSearch(forValue, numbers, 0, numbers.length - 1);
	}

	private int binSearch(int value, int[] numbers, int bottomRangeInclusive, int topRangeInclusive) {
		// Terminate recursion if range is negative. This can happen if we had a range length 1 that
		// did not contain the number. We could check before invoking recursion, as that would save
		// a stack push, but given how few recursions we will get in binSearch that's really not relevant
		// and we should just go for the cleanest algorithm in our opinion.
		if (bottomRangeInclusive > topRangeInclusive) {
			return -1;
		}
		// Find middle idx and value
		// If range length is 1 (both ends inclusive) this will test the one element
		// If range length is 2, it doesn't matter how this is rounded - we will either check
		// the first or second element, and act accordingly (terminate OR search other end)
		// Same argument holds for all even length ranges.
		// For all odd length ranges > 1 we will truly hit the middle and either match or bisect 
		// symmetrically
		final int middleIdx = (bottomRangeInclusive + topRangeInclusive) / 2;
		final int middleValue = numbers[middleIdx];
		// Did we match?
		if (value == middleValue) {
			return middleIdx;
		}
		// Should we bisect lower half or upper half? Do NOT include middle element...
		// As we do not include the middle element, we will be creating a negative length
		// range if the current range is length 1, or if we have this situation in a two item range:
		// middleIdx points to top element, top element is less than target value (search for range outside top)
		// middleIdx points to bottom element, bottom element is bigger than target value (search for range below bottom)
		if (value < middleValue) {
			return binSearch(value, numbers, bottomRangeInclusive, middleIdx -1);
		} else {
			return binSearch(value, numbers, middleIdx + 1, topRangeInclusive);
		}
	}
	
	

}
