package algorithms;

import java.util.HashMap;
import java.util.Map;

import algorithms.DirectedGraph.Vertice;

public class BellmanFord implements ShortestPathFinder {

	@Override
	public Result findShortestPath(final DirectedGraph g, final Vertice startVertice) {
		final Map<DirectedGraph.Vertice, Integer> dist = new HashMap<>();
		final Map<DirectedGraph.Vertice, DirectedGraph.Vertice> pred = new HashMap<>();
		for (final DirectedGraph.Vertice v : g.getVertices()) {
			dist.put(v, 100000);
		}
		dist.put(startVertice, 0);
		
		// Relax the edges N-1 times
		for (int i = 0; i < g.getVertices().size(); ++i) {
			final boolean cyclePlusOne = (i == (g.getVertices().size() -1));
			for (final DirectedGraph.Edge e : g.getEdges()) {
				System.out.println("Checking edge: " + e);
				final DirectedGraph.Vertice source = e.getSource();
				final DirectedGraph.Vertice destination = e.getDestination();
				
				// Beware overflow here
				int newDistance = dist.get(source) + e.getWeight();
				
				if (newDistance < dist.get(destination)) {
					if (cyclePlusOne) {
						// We should not be able to find ANY improvement after |V| - 1 iterations, so this indicates a 
						// negative cycle. Report!
						throw new RuntimeException("Negative cycle detected, bailing out!");
					}
					dist.put(destination, newDistance);
					pred.put(destination, source);
				}
				
			}
		}
		
		// Check for negative cycles - 
		return new Result() {

			@Override
			public Map<Vertice, Integer> getDist() {
				return dist;
			}

			@Override
			public Map<Vertice, Vertice> getPred() {
				return pred;
			}
		};

	}
}
