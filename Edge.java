package test;

/**
 * Path between two nodes
 * @author robing
 *
 */
public class Edge implements Comparable<Edge>{

	private double length;
	private Node fromNode;
	private Node toNode;

	/**
	 * @param n1 Node
	 * @param n2 Node on the other Side of the Edge
	 * @param l length of Edge from Node to Node
	 */
	public Edge( Node n1, Node n2, double l) {
		fromNode = n1;
		toNode = n2;
		length = l;
//		x.addEdge(this);
//		y.addEdge(this);
	}

	/**
	 * @return the length of the edge as double
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param n Node with Edge
	 * @return Node on the other side of the Edge
	 */
	public Node getOtherNode(Node n) {
		if (n.equals(fromNode)) {
			return toNode;
		} else {
			return fromNode;
		}
	}

	public String toString() {
		return fromNode + "_" + toNode + "_" + length;
	}
	

	@Override
	public int compareTo(Edge o) {
		if(this.length > o.length) {
			return 1;
		}else if(this.length < o.length) {
			return -1;
		}else {
			return 0;
		}
	}

}
