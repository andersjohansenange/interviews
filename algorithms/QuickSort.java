package algorithms;

public class QuickSort implements Sorter {

	@Override
	public int[] sort(int[] data) {
		final int[] res = data.clone();
		qsort(res, 0, res.length - 1);
		return res;
	}

	private void qsort(final int[] res, final int first, int last) {
		// Step 0 - are we done? If length of segment is 0 or less we are
		if (last < first) return;
		// Step 1, pick a pivot. We will just use halfway-through-array for idx of pivot
		int idxOfPivot = partition(res, (first + last) / 2, first, last);
		// Step 2, sort the two halves
		qsort(res, first, idxOfPivot - 1);
		qsort(res, idxOfPivot + 1, last);
	}

	private int partition(final int[] res, final int pivotIdx, final int first, final int last) {
		// Stash the pivot in the last position, we swap it with the value there
		int pivotValue = res[pivotIdx];
		
		swap(res, pivotIdx, last);
		
		int store = first;
		for (int i = first; i < last; ++i) {
			if (res[i] < pivotValue) {
				swap(res, store, i);
				++store;
			}
 		}

		// We knos what store points to a value that is >= pivot value, so we can just
		// swap them. Even if store is == last (identity operation)
		
		// Put the pivot in the proper place
		swap(res, store, last);
		
		return store;
	}

	private void swap(final int[] data, final int pos1, final int pos2) {
		final int tmp = data[pos1];
		data[pos1] = data[pos2];
		data[pos2] = tmp;
	}

}
