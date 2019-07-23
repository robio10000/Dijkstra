package test;

/**
 * Special Node for Dijkstra
 * @author robing
 *
 */
public class DijkstraNode extends Node implements Comparable<DijkstraNode>{

	private double distance = Double.POSITIVE_INFINITY;

	/**
	 * @param n is the name of the node
	 */
	public DijkstraNode(String n) {
		super(n);
	}

	/**
	 * {@code public double getDistance()}
	 * 
	 * @return The distance as double
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * {@code public void setDistance(double dist)}
	 * 
	 * @param distance as Double
	 */
	public void setDistance(double dist) {
		distance = dist;
	}

	
	@Override
	public int compareTo(DijkstraNode o) {
		if(this.distance > o.distance) {
			return 1;
		}else if(this.distance < o.distance) {
			return -1;
		}else {
			return 0;
		}
	}

}
