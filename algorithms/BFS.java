package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import algorithms.Graph.Vertice;

public class BFS implements PathFinder {
	
	private enum Color {
		WHITE, GREY, BLACK
	}
	
	@Override
	public List<Graph.Vertice> findPath(final Graph.Vertice start, final Graph.Vertice goal, final Graph graph) {
		final Map<Integer, Integer> pred = new HashMap<>();
		final Map<Integer, Color> colors = new HashMap<>();
		for (final Graph.Vertice v : graph.getVertices()) {
			colors.put(v.getId(), Color.WHITE);
		}
		final Map<Integer, Integer> dist = new HashMap<>();
		
		dist.put(start.getId(), 0);
		colors.put(start.getId(), Color.GREY);
		final Queue<Graph.Vertice> work = new LinkedList<>();
		work.add(start);
		while (!work.isEmpty()) {
			// Dequeue the next item
			final Graph.Vertice current = work.remove();
			final int currentDist = dist.get(current.getId());
			for (int i : current.getConnections()) {
				final Vertice v = graph.getVertice(i);
				if (Color.WHITE == colors.get(i)) {
					dist.put(v.getId(), currentDist + 1);
					pred.put(v.getId(), current.getId());
					colors.put(v.getId(), Color.GREY);
					work.add(v);
				}
			}
			colors.put(current.getId(), Color.BLACK);
		}
		return makePath(goal, pred, graph);
	}
	
	

	private List<Graph.Vertice> makePath(final Vertice goal, final Map<Integer, Integer> pred, final Graph g) {
		
		final List<Graph.Vertice> res = new ArrayList<>();
		Vertice current = goal;
		while (true) {
			res.add(current);
			final Integer next = pred.get(current.getId());
			if (null == next) {
				break;
			}
			current = g.getVertice(next);
		}
		Collections.reverse(res);
		return res;
	}

}
