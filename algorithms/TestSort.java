package algorithms;

import java.util.Arrays;

public class TestSort {

	public final static void main(String... args) {
		final int[] data = {7, 2, 1, -42, 1000, 8, -10000};
//		final Sorter s = new InsertionSort();
//		final Sorter s = new HeapSort();
//
		final Sorter s = new MergeSort();
		System.out.println(Arrays.toString(data));
		System.out.println(Arrays.toString(s.sort(data)));
	}
}
