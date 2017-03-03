package algorithms;

public class TestDirectedGraph {

	
	public static void main(String[] args) {
		final DirectedGraph g = new DirectedGraph();
		final DirectedGraph.Vertice start = g.addVertice(0).addEdge(1, 6).addEdge(2, 8).addEdge(3, 18);
		g.addVertice(1).addEdge(4, 11);
		g.addVertice(2).addEdge(3, 9);
		g.addVertice(3);
		g.addVertice(4).addEdge(5, 3);
		g.addVertice(5).addEdge(3, 4);
		
		ShortestPathFinder dpf = new Djikstra();
		
		final ShortestPathFinder.Result res = dpf.findShortestPath(g, start);
		printResult(g, start, res);

		ShortestPathFinder fwpf = new FloydWarshall();

		printResult(g, start, fwpf.findShortestPath(g, start));

		/*
		ShortestPathFinder spf = new BellmanFord();

		printResult(g, start, spf.findShortestPath(g, start));
		// Create a negative cycle
		g.getVertice(1).addEdge(0, -7);
		printResult(g, start, spf.findShortestPath(g, start));
	*/
	}

	public static void printResult(final DirectedGraph g, final DirectedGraph.Vertice start,
			final ShortestPathFinder.Result res) {
		System.out.println("ID\tPred\tDistance");
		for (final DirectedGraph.Vertice v : g.getVertices()) {
			if (v == start) {
				System.out.println(v.getId() + "\t0\t0");
			} else {
				System.out.println(v.getId() + "\t" + res.getPred().get(v).getId() + "\t" + res.getDist().get(v));
			}
		}
	}

}
