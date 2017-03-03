package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.Graph.Vertice;


public class DFS implements PathFinder {

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
		
		dfsSearch(start, goal, graph, pred, colors);
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



	private void dfsSearch(final Vertice current, final Vertice goal, final Graph graph, final Map<Integer, Integer> pred,
			Map<Integer, Color> colors) {
		if (goal.getId() == current.getId()) {
			return;
		}
		// We can only enter with a white node...
		// ...but now we start processing it.
		colors.put(current.getId(), Color.GREY);
		
		for (int i : current.getConnections()) {
			final Vertice v = graph.getVertice(i);
			if (Color.WHITE == colors.get(i)) {
				pred.put(i, current.getId());
				dfsSearch(v, goal, graph, pred, colors);
			}
		}
	}

}
