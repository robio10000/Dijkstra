package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
//import javax.swing.JOptionPane;


/**
 * Shortest Path Algorithm. <br>
 * Main Class
 * 
 * @author robing
 *
 */
public class Main {

	public static void main(String[] args) {
		
		
		String start = "L";
		String end = "Q";

		CsvReader.chooseFile();
//		CsvReader.chooseFile("C:\\Users\\robing\\Desktop\\graph.csv");

		// Nodes in a Set
		Set<DijkstraNode> graph = CsvReader.graph;

		List<String> out = new LinkedList<String>();
		for (DijkstraNode n : graph) {
			out.add("\nNode: " + n + "   ");
			Set<Edge> test = n.getEdges();

			for (Edge edge : test) {
				out.add(edge.toString() + "|");
			}
		}
//		JOptionPane.showConfirmDialog(null, "Nodes:\n" + CsvReader.graph + "\nEdges:\n" + out, "Read nodes and edges", -1,
//				JOptionPane.INFORMATION_MESSAGE);
		
		//Init the algorithm
		DijkstraNode s = null;
		DijkstraNode t = null;
		for (DijkstraNode n : graph) {
			if (n.toString().equals(start)) {
				s = n;
			}
			if (n.toString().equals(end)) {
				t = n;
			}
		}
		//Start the algorithm
		dijstra(graph, s, t);
	}

	public static String dijstra(Set<DijkstraNode> graph, DijkstraNode start, DijkstraNode target) {
		
		for(DijkstraNode n : graph) {
			n.setDistance(Double.POSITIVE_INFINITY);
		}

		List<DijkstraNode> prev = new ArrayList<DijkstraNode>();
		DijkstraNode current;
		start.setDistance(0);
		start.setPred(null);

		PriorityQueue<DijkstraNode> pQ = new PriorityQueue<DijkstraNode>();
		pQ.addAll(graph);// put all the nodes in the queue
		while (!pQ.isEmpty()) {
			current = pQ.poll();// get the node with the lowest distance
//			System.err.println("Current: " + current);
			if (current.equals(target)) {// Check if current is target
				break;
			}
			for (Edge e : current.getEdges()) {// Do it for all neighbors of current
				DijkstraNode cN = (DijkstraNode) e.getOtherNode(current);// Save the neighbor of current
				double newDist = current.getDistance() + e.getLength();
				if (pQ.contains(cN) && cN.getDistance() > newDist) {// is the neighbor in pQ?
					pQ.remove(cN);// remove the neighbor from pQ
					cN.setDistance(newDist);// calculate the distance
					cN.setPred(current);
					pQ.add(cN);// add the neighbor in pQ with the new distance
//					System.out.println(
//							"dist: From " + current + " To " + e.getOtherNode(current) + " = " + cN.getDistance());
				}
				

			}
			prev.add(current);// add the current in previous because this is the last step
//			System.out.println("prev: " + prev);
		}
		
		List<DijkstraNode> deWey = new ArrayList<DijkstraNode>();
		deWey.add(target);
		
		current = target;
		while(current.getPred() != null) {
			current = current.getPred();
			deWey.add(0,current);
		}
//		JOptionPane.showConfirmDialog(null, "The shortest way: " + deWey, "The shortest way", -1,
//				JOptionPane.INFORMATION_MESSAGE);
		System.out.println(deWey + " " + target.getDistance());
		return deWey + " distance: " + target.getDistance();
		
	}

}
