package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.DirectedGraph.Vertice;

public class FloydWarshall implements ShortestPathFinder {

	@Override
	public Result findShortestPath(final DirectedGraph g, final Vertice startVertice) {
		final Map<DirectedGraph.Vertice, Map<DirectedGraph.Vertice, Integer>> dist = new HashMap<>();
		final Map<DirectedGraph.Vertice, Map<DirectedGraph.Vertice,DirectedGraph.Vertice>> next = new HashMap<>();
		
		// Prep the distance matrix
		
		for (final DirectedGraph.Vertice v : g.getVertices()) {
			next.put(v, new HashMap<DirectedGraph.Vertice,DirectedGraph.Vertice>());
			final Map<DirectedGraph.Vertice, Integer> fromV = new HashMap<>();
			for (final DirectedGraph.Vertice v2 : g.getVertices()) {
				if (v == v2) {
					fromV.put(v, 0); // Always zero from v to v					
				} else {
					fromV.put(v2, 100000);
				}
			}
			dist.put(v, fromV);
		}
		
		// Initialize to direct distances
		for (final DirectedGraph.Edge e : g.getEdges()) {
			dist.get(e.getSource()).put(e.getDestination(), e.getWeight());
			// Now the next information...
			// Naive: To get from source to dest we go to dest from source
			// If we find a detour later, we will add that
			System.out.println("Adding edge " + e);
			final Map<DirectedGraph.Vertice, DirectedGraph.Vertice> m;
			if (! next.containsKey(e.getSource())) {
				m = new HashMap<>();
				next.put(e.getSource(), m);
			} else {
				m = next.get(e.getSource());
			}
			m.put(e.getDestination(), e.getDestination());
		}
		
		Vertice test = next.get(startVertice).get(g.getVertice(2));
		
		// Now iteratively try out all detours for a shorter roude
		
		for (final DirectedGraph.Vertice source : g.getVertices()) {
			for (final DirectedGraph.Vertice destination : g.getVertices()) {
				for (final DirectedGraph.Vertice detour : g.getVertices()) {
					final int detourDistance = dist.get(source).get(detour) + dist.get(detour).get(destination);
					if (detourDistance < dist.get(source).get(destination)) {
						dist.get(source).put(destination, detourDistance);
						// Enter the detour in the pred matrix: When coming from Source, the cheapest path to dest
						// goes through the detour...
						Vertice v = next.get(source).get(detour);
						next.get(source).put(destination, next.get(source).get(detour));
					}
				}
			}
		}
				

		
		
		return new Result() {

			@Override
			public Map<Vertice, Integer> getDist() {
				return dist.get(startVertice);
			}

			@Override
			public Map<Vertice, Vertice> getPred() {
				return makePath(startVertice, g, next);
			}
		};

	}

	private Map<Vertice, Vertice> makePath(final Vertice start, final DirectedGraph g, Map<Vertice, Map<Vertice, Vertice>> next) {
		Map<Vertice, Vertice> res = new HashMap<>();
		for (final Vertice v : g.getVertices()) {
			if (v == start) {
				continue;
			};
			final List<Vertice> path = getPath(start, v, g, next);
			final Vertice pred = path.get(path.size() - 2);
			res.put(v, pred);
			
		}
		return res;
	}
		
	private  List<Vertice> getPath(final Vertice start, final Vertice dest, final DirectedGraph g, Map<Vertice, Map<Vertice, Vertice>> next) {
		final List<Vertice> res = new ArrayList<>();
		
		if (null == next.get(start).get(dest)) {
			return res; // No path
		}
		
		Vertice current = start;
		res.add(start);
		while (current != dest) {
			current = next.get(current).get(dest);
			res.add(current);
		}
		// TODO Auto-generated method stub
		return res;
	}

}
