package algorithms;

import java.util.List;

public interface PathFinder {
	
	public List<Graph.Vertice> findPath(final Graph.Vertice start, final Graph.Vertice goal, final Graph graph);

}
