package algorithms;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Graph {
	
	public interface Vertice {
		int getId();
		Set<Integer> getConnections();
	}
	
	private Map<Integer, Vertice> vertices = new HashMap<>();
	
	public Collection<Vertice> getVertices() {
		return vertices.values();
	}
	
	public Vertice getVertice(final int id) {
		return vertices.get(id);
	}
	
	public void addVertice(final int id, final Integer... connections) {
		final Set<Integer> connSet = new TreeSet<Integer>(Arrays.asList(connections));
		final Vertice v = new Vertice() {
			@Override
			public int getId() {
				return id;
			}
			
			@Override
			public Set<Integer> getConnections() {
				return connSet;
			}
			
			public String toString() {
				return "Node with ID: "+ Integer.toString(id);
			}
		}; 
		vertices.put(id, v);
	}
	
	

}
