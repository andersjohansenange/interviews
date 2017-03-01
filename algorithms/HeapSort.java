package algorithms;

import java.util.Arrays;

public class HeapSort implements Sorter {

	@Override
	public int[] sort(int[] data) {
		final int[] res = data.clone();
		buildHeap(res);
		System.out.println("   " + Arrays.toString(res));
		for (int placeBiggest = res.length - 1; placeBiggest >= 1; --placeBiggest) {
			System.out.println(placeBiggest);
			// Note that we don't lose the value in the last leaf, as we use swap
			// After this the biggest value (top of heap) is where the last leaf was
			// and we consider that leaf gone from the heap.
			swap(res, 0, placeBiggest);
			// Make a heap in array that ends right before placeBiggest (we just used the leaf)
			// This will put the biggest element in 0..placeBiggest (not inclusive) at 
			// root of the heap, so in pos 0
			// It was ALWAYS a heap, so we don't need to sift everything - we just need
			// to recursively ensure that heap property is upheld for the damaged heap.
			// Basically we fix the root, and then we fix the subtree that we damaged to 
			// fix the root.
			// The biggest values keep floating to the top, and we then grab it and store it
			// at the end of the array
			System.out.println("   " + Arrays.toString(res));
			heapify(res, 0, placeBiggest);
			System.out.println("   " + Arrays.toString(res));
		}
		return res;
	}
	
	private void swap(final int[] data, final int pos1, final int pos2) {
		final int tmp = data[pos1];
		data[pos1] = data[pos2];
		data[pos2] = tmp;
	}

	private void buildHeap(final int[] res) {
		// Organize res into a heap starting from the first layer of parent nodes
		// and moving right to left
		// Why length / 2 -1? We know all layers except the last one are filled...
		// Showing the depth here...
		// 0 1 1 2 2 2 2 3
		// So the fist non-leaf node of these 8 nodes should be node at idx 3...
		// and it is, as the leaves in 3rd layer are flushed left. Adding two more...
		//
		// 0 1 1 2 2 2 2 3 3 3
		// ... the first non-leaf node should be at idx 4 (10 / 2 - 1)
		
		// Intuitively to make the node to the right of the last parent a parent, you
		// need to add 1 or 2 nodes. If you only add 1, the count of the nodes is unequal, 
		// and the integer division by 2 floors the result (9 / 2 = 4).
		
		// SO! Starting at res.length / 2 - 1 and counting downwards, EVERY node has children
		// and needs to be recursively sifted by heapify to move up any larger children.
		
		for (int i = res.length / 2 - 1; i >= 0; --i) {
			heapify(res, i, res.length);
		}
		
	}

	private void heapify(final int[] res, final int first, final int lastPlusOne) {
		int idxOfLargest = first;
		int idxOfLeftChild = 2 * first + 1;
		int idxOfRightChild = idxOfLeftChild + 1;
		
		// Find the idx of the largest element, so we can make sure the parent at
		// position first is larger than either child node (if present)
		if (idxOfLeftChild < lastPlusOne) {
			if (res[idxOfLeftChild]> res[idxOfLargest]) {
				idxOfLargest = idxOfLeftChild;
			}
		}
		if (idxOfRightChild < lastPlusOne) {
			if (res[idxOfRightChild]> res[idxOfLargest]) {
				idxOfLargest = idxOfRightChild;
			}
		}
		if (first != idxOfLargest) {
			// Sift larger value up to root
			swap(res, first, idxOfLargest);
			System.out.println("   " + "swap performed for item at " + first);
			System.out.println("   " + Arrays.toString(res));
			// Fix the subtree we just swapped the root out of
			heapify(res, idxOfLargest, lastPlusOne);

		}
		
		
	}

}
