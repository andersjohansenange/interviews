package algorithms;

import java.util.Arrays;

public class InsertionSort implements Sorter {

	@Override
	public int[] sort(int[] data) {
		final int[] res = data.clone();
		// Why start at pos 1? Because starting at pos 1 is a no-op and make things
		// more complicated too (risk of underflow for flushPos)
		for (int pos = 1; pos < res.length; pos++) {
			insert(res, pos, res[pos]);
			System.out.println(Arrays.toString(res));
		}
		return res;
	}

	private void insert(final int[] res, final int pos, final int value) {
		int flushPos = pos - 1;
		System.out.println("   " + "pos: " + pos + "   value: " + value);
		while (flushPos >= 0 && res[flushPos] > value) {
			res[flushPos + 1] = res[flushPos];
			--flushPos;
			System.out.println("   " + Arrays.toString(res));
		}
		System.out.println("   " + "replacing value " + value + " at position " + (flushPos + 1));
		res[flushPos + 1] = value;
		System.out.println("   " + Arrays.toString(res));
		System.out.println("   " + "done");
	}

}
