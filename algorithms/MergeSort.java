package algorithms;

import java.util.Arrays;

public class MergeSort implements Sorter {

	@Override
	public int[] sort(int[] data) {
		if (data.length <= 1) {
			return data;
		}
		if (data.length == 2) {
			if (data[0] <= data[1]) {
				return data;
			} else {
				swap(data, 0, 1);
				return data;
			}
		}
		final int halfPoint = data.length / 2;
		final int[] left = new int[halfPoint];
		System.arraycopy(data, 0, left, 0, halfPoint);
		final int[] right = new int[data.length - left.length];
		System.arraycopy(data, left.length, right, 0, right.length);
		
		System.out.println("Data: " + Arrays.toString(data));
		System.out.println("Left (unsorted): " + Arrays.toString(left));
		System.out.println("Right (unsorted): " + Arrays.toString(right));
		
		sort(left);
		sort(right);

		System.out.println("Left (sorted): " + Arrays.toString(left));
		System.out.println("Right (sorted): " + Arrays.toString(right));
		
		// Finally merge the two halves
		merge(left, right, data);
		
		return data;
	}
	
	private void merge(final int[] left, final int[] right, final int[] data) {
		
		int leftIdx = 0, rightIdx = 0, mergeIdx = 0;
		
		while (leftIdx < left.length && rightIdx < right.length) {
			final int leftVal = left[leftIdx];
			final int rightVal = right[rightIdx];
			if (leftVal < rightVal) {
				data[mergeIdx] = leftVal;
				leftIdx++;
			} else {
				data[mergeIdx] = rightVal;
				rightIdx++;
			}
			mergeIdx++;
		}
		// Now determine which array is already spent
		System.arraycopy(right, rightIdx, data, mergeIdx, right.length - rightIdx);
		System.arraycopy(left, leftIdx, data, mergeIdx, left.length - leftIdx);
		
	}

	private void swap(final int[] data, final int pos1, final int pos2) {
		final int tmp = data[pos1];
		data[pos1] = data[pos2];
		data[pos2] = tmp;
	}


}
