package algorithms;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import algorithms.DirectedGraph.Vertice;

public class Djikstra implements ShortestPathFinder {

	@Override
	public Result findShortestPath(final DirectedGraph g, final Vertice startVertice) {
		final Map<DirectedGraph.Vertice, Integer> dist = new HashMap<>();
		final Map<DirectedGraph.Vertice, DirectedGraph.Vertice> pred = new HashMap<>();
		
		final PriorityQueue<PrioritizedVertice> pq = new PriorityQueue<PrioritizedVertice>(g.getVertices().size(), new PrioritizedVertice(null, 0));
		for (final DirectedGraph.Vertice v : g.getVertices()) {
			final int distToVertice;
			if (v == startVertice) {
				distToVertice = 0;
			} else {
				distToVertice = Integer.MAX_VALUE;
			}
			dist.put(v, distToVertice);
			pq.add(new PrioritizedVertice(v, distToVertice));
		}
		while (!pq.isEmpty()) {
			final PrioritizedVertice smallest = pq.remove();
			// NB We will insert duplicates rather than trying to update the priority of
			// enqueued vertices. So we need to discard old values that have higher costs.
			System.out.println("Investigating connections from:" + smallest.getVertice().getId());
			if (dist.get(smallest.getVertice()) < smallest.getDistance()) {
				// This is a dupe and we have a better way to get to this vertice already
				System.out.println("We already have a better solution, bailing");
				continue;
			}
			for (final DirectedGraph.Edge connection : smallest.getVertice().getEdges()) {
				final int distanceFromHere = smallest.getDistance() + connection.getWeight();
				final DirectedGraph.Vertice destination = connection.getDestination();
				System.out.println("Looking at edge to: " + destination.getId() + " (weight " + connection.getWeight() + ")");
				final int smallestPreviousDistance = dist.get(destination);
				System.out.println("Smallest previous distance is: " + smallestPreviousDistance + ", and distance from here is " + distanceFromHere);
				if (distanceFromHere < smallestPreviousDistance) {
					System.out.println("The new solution is cheaper, adding to priority queue");
					dist.put(destination, distanceFromHere);
					pred.put(destination, smallest.getVertice());
					pq.add(new PrioritizedVertice(connection.getDestination(), distanceFromHere));
				}
			}
		}
		
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
	
	private class PrioritizedVertice implements Comparator<PrioritizedVertice>{
		final private DirectedGraph.Vertice vertice;
		final private int distance;
		
		public PrioritizedVertice(final DirectedGraph.Vertice vertice, final int distance) {
			this.vertice = vertice;
			this.distance = distance;
		}
		
		public int getDistance() {
			return distance;
		}
		
		public DirectedGraph.Vertice getVertice() {
			return vertice;
		}

		@Override
		public int compare(PrioritizedVertice o1, PrioritizedVertice o2) {
			if (o1.getDistance() < o2.getDistance()) {
				return -1;
			} else if (o1.getDistance() > o2.getDistance()) {
				return 1;
			}
			return 0;
		}
	}
	
}
