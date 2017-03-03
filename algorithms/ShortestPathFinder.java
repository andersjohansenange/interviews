package algorithms;

import java.util.Map;

public interface ShortestPathFinder {
	
	public interface Result {
		Map<DirectedGraph.Vertice, Integer> getDist();
		Map<DirectedGraph.Vertice, DirectedGraph.Vertice> getPred();
	}
	
	public Result findShortestPath(final DirectedGraph g, final DirectedGraph.Vertice startVertice);

}
