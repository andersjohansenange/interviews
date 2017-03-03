package algorithms;

import java.util.List;

public class TestGraph {
	
	
	public static final void main(final String... args) {
		Graph g = buildGraph();
		PathFinder pf = new BFS();
		List<Graph.Vertice> path = pf.findPath(g.getVertice(0), g.getVertice(100), g);
		System.out.println(path.toString());
	}

	private static Graph buildGraph() {
		final Graph res = new Graph();
		res.addVertice(0, 1, 6, 8);
		res.addVertice(1, 0, 2, 3);
		res.addVertice(2, 1, 10, 11);
		res.addVertice(3, 1, 4, 12);
		res.addVertice(4, 3, 5, 13);
		res.addVertice(5, 4, 6, 9);
		res.addVertice(6, 5, 7, 0);
		res.addVertice(7, 6, 8, 9);
		res.addVertice(8, 0, 7, 14);
		res.addVertice(9, 5, 7, 100);
		res.addVertice(10, 2);
		res.addVertice(11, 2);
		res.addVertice(12, 3);
		res.addVertice(13, 4);
		res.addVertice(14, 8);
		res.addVertice(100, 9);
		return res;
	}

}
