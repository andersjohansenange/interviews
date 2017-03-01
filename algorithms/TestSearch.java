package algorithms;

public class TestSearch {

	
	public static final void main(String... args) {
		final int[] values = {1, 2, 3, 5, 7, 8, 100, 101, 2000, 2048};
		final Searcher s = new BinSearch();
//		final Searcher s = new BinSearchBook();
		for (int i : values) {
			System.out.println(s.search(i, values));
		}
		final int min = values[0] - 1;
		final int max = values[values.length -1] + 1;
		for (int i = min; i < max; ++i) {
			if (s.search(i, values) != -1) {
				System.out.println(i);
			}
		}
	}
}
