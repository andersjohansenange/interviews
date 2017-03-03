package algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class DirectedGraph {
	
	public interface Vertice {
		int getId();
		Collection<Edge> getEdges();
		Vertice addEdge(int destination, int weight);
	}
	
	public interface Edge {
		int getWeight();	
		Vertice getDestination();
		Vertice getSource();
	}
	
	private Map<Integer, Vertice> vertices = new HashMap<>();
		
	public Collection<Vertice> getVertices() {
		return vertices.values();
	}
	
	public Collection<Edge> getEdges() {
		final Set<Edge> res = new HashSet<Edge>();
		for (final Vertice v : getVertices()) {
			res.addAll(v.getEdges());
		}
		return res;
	}
	
	public Vertice getVertice(final int id) {
		return vertices.get(id);
	}
	
	private class ConcreteVertice implements Vertice {
		final private int id;
		
		private final Set<Edge> edges = new HashSet<>();

		public ConcreteVertice(final int id) {
			this.id = id;
		}
		
		@Override
		public int getId() {
			return id;
		}

		@Override
		public Collection<Edge> getEdges() {
			return edges;
		}

		@Override
		public Vertice addEdge(final int destination, final int weight) {
			final Edge edge = new Edge() {

				@Override
				public int getWeight() {
					return weight;
				}

				@Override
				public Vertice getDestination() {
					return getVertice(destination);
				}

				@Override
				public Vertice getSource() {
					return ConcreteVertice.this;
				}
				
				@Override
				public String toString() {
					return super.toString() + " from " + getSource().getId() + ", to " + getDestination().getId() + ", weight " + getWeight();
				}
				
			};
			edges.add(edge);
			return this;
		}

	}

	public Vertice addVertice(final int id) {
		final ConcreteVertice vertice = new ConcreteVertice(id);
		vertices.put(id, vertice);
		return vertice;			
	}

}
