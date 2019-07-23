package test;

import java.util.HashSet;
import java.util.Set;

/**
 * Node is a point of a Map for<br>
 * for real life example Munich is a node<br>
 * and the path between Munich and Berlin is a Edge
 * @author robing
 *
 */
public class Node {

	private Set<Edge> edges = new HashSet<Edge>();
	private String name;

	/**
	 * @param n is the name of the node
	 */
	public Node(String n) {
		name = n;
	}

	/**
	 * {@code public Set<Edge> getEdges()}
	 * 
	 * @return {@code Set<Edge>}
	 */
	public Set<Edge> getEdges() {
		return edges;
	}

	/**
	 * {@code public void addEdge(Edge edge)}
	 * 
	 * @param
	 */
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	
	
	public void test() {
		System.out.println(edges);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
