package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * <b>Csv reader for dijkstra</b><br>
 * <br>
 * Read information from a file:<br>
 * *.csv or *.txt<br>
 * Syntax: Node1;Node2;length<br>
 * Example: A;B;23,1
 * 
 * @author robing
 *
 */
public class CsvReader {

	public static Set<Edge> edges = new HashSet<Edge>();
	public static Set<DijkstraNode> graph = new HashSet<DijkstraNode>();

	/**
	 * Choose a file with the JFileChooser.<br>
	 * When calling Method the chooser is opened automatically.
	 */
	public static void chooseFile() {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//Other design
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV & TXT FILES", "csv", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			readFile(chooser.getSelectedFile().getAbsolutePath());
			System.out.println(chooser.getSelectedFile().getAbsolutePath());
		} else
			JOptionPane.showConfirmDialog(null, "Cancel!", "Cancel", -1, JOptionPane.CANCEL_OPTION);

	}

	/**
	 * You can choose a file without the JFileChooser
	 * @param absolutePath as String 
	 */
	public static void chooseFile(String absolutePath) {
		readFile(absolutePath);
	}

	private static void readFile(String absolutePath) {
		File f = new File(absolutePath);
		FileReader fR = null;
		try {
			fR = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "File Not Found!", "ERROR: File not Found!", -1,
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			BufferedReader bR = new BufferedReader(fR);
			String zeile;
			while ((zeile = bR.readLine()) != null) {
				String[] splitted = zeile.split(";");// Split in 3 Strings node1,node2,length
				DijkstraNode n1 = newNode(splitted[0]);
				DijkstraNode n2 = newNode(splitted[1]);
				double l = Double.parseDouble(splitted[2]);

				Edge e1 = new Edge(n1, n2, l);
				n1.addEdge(e1);
				edges.add(e1);

				Edge e2 = new Edge(n2, n1, l);
				n2.addEdge(e2);
				edges.add(e2);

			}
			bR.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

	}

	private static DijkstraNode newNode(String name) {

		DijkstraNode test = new DijkstraNode(name);

		if (graph.add(test)) {
			return test;
		} else {
			for (DijkstraNode n : graph) {
				if (n.equals(test)) {
					test = n;
					break;
				}
			}
			return test;
		}
	}
}
