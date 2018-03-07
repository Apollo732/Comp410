package A6_Dijkstra;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DiGraph implements DiGraph_Interface {

	HashMap<String, Vertex> vertices;
	HashSet<Long> edgeIDs;
	HashSet<Long> vertexIDs;

	public DiGraph() { // default constructor

		this.vertices = new HashMap<String, Vertex>();
		this.edgeIDs = new HashSet<Long>();
		this.vertexIDs = new HashSet<Long>();

	}

	@Override
	public boolean addNode(long idNum, String label) {
		if ((idNum < 0L) || (this.vertexIDs.contains(Long.valueOf(idNum))) || (label == null)
				|| (this.vertices.containsKey(label))) {
			return false;
		}

		Vertex NEW = new Vertex(idNum, label);
		this.vertices.put(label, NEW);
		this.vertexIDs.add(Long.valueOf(idNum));

		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String label) {

		Vertex origin = (Vertex) this.vertices.get(sLabel);
		Vertex dest = (Vertex) this.vertices.get(dLabel);

		if ((idNum < 0L) || (this.edgeIDs.contains(Long.valueOf(idNum)))) {
			return false;
		}
		Edge e = new Edge(idNum, origin, dest, weight, label);
		if ((!this.vertices.containsKey(sLabel)) || (!this.vertices.containsKey(dLabel))
				|| ((origin.OutEdge.containsKey(dLabel)) && (dest.InEdge.containsKey(sLabel)))) {

			return false;
		}

		dest.InEdge.put(sLabel, e);
		// dest.inEdges += 1;
		origin.OutEdge.put(dLabel, e);
		this.edgeIDs.add(Long.valueOf(idNum));

		return true;
	}

	@Override
	public boolean delNode(String label) {
		if (!this.vertices.containsKey(label)) {
			return false;
		}
		if (this.vertices.containsKey(label)) {
			Vertex n = (Vertex) this.vertices.get(label);
			for (Vertex c : this.vertices.values()) {
				if (c.InEdge.containsKey(label)) {
					c.InEdge.remove(label, c.InEdge.get(label));
					// c.inEdges -= 1;
				}
				if (c.OutEdge.containsKey(label)) {
					c.OutEdge.remove(label, c.OutEdge.get(label));
				}
			}
			this.vertexIDs.remove(Long.valueOf(n.ID));
			n.InEdge.clear();

			n.OutEdge.clear();
			this.vertices.remove(label, n);
		}
		return true;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		Vertex source = (Vertex) this.vertices.get(sLabel);
		Vertex dest = (Vertex) this.vertices.get(dLabel);
		if ((!this.vertices.containsKey(sLabel)) || (!this.vertices.containsKey(dLabel))) {
			return false;
		}
		if ((!source.OutEdge.containsKey(dLabel)) && (!dest.InEdge.containsKey(sLabel))) {
			return false;
		}
		Edge e = (Edge) source.OutEdge.get(dLabel);
		if (e == null) {
			return false;
		}
		this.edgeIDs.remove(Long.valueOf(e.idNum));
		source.OutEdge.remove(dLabel, e);
		dest.InEdge.remove(sLabel, e);

		return true;
	}

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return this.vertexIDs.size();
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return this.edgeIDs.size();
	}

	@Override
	public String[] topoSort() {
		int size = this.vertexIDs.size(); // Variable for size/ total number of
											// vertices

		String[] toposort = new String[size]; // The string array we need to
												// return

		Queue<Vertex> nodes = new LinkedList<Vertex>(); // My Queue that needs
														// to be initialized as
														// a LinkedList
														// because a Queue is an
														// ADT that implements a
														// LinkedList in the
														// Java Util library
		int count = -1;

		for (Vertex a : this.vertices.values()) { // I use this for loop to
													// assign the indegree to
													// each vertex
			a.initEd(); //
			if (a.getEd() == 0) { // and put all with indegree ==0 into my queue
				nodes.add(a);
				a.setQueued();

			}
		}
		while (!nodes.isEmpty()) {
			count++;

			Vertex temp = nodes.poll(); // Everytime the while-loop goes around,
										// i poll off the
										// top of the queue, and save the label
										// in the count location in the string[
										// ]

			toposort[count] = temp.Label;
			Edge[] tempAr = temp.OutEdge.values().toArray(new Edge[temp.OutEdge.size()]);
			// I collect all the edges that my vertex points to and then convert
			// it into an array

			for (int i = 0; i < tempAr.length; i++) {
				Vertex des = tempAr[i].dest;
				des.subEd();
				;
				if ((des.getEd() == 0)) { // I go to all of those vertices, and
											// decrement their indegree by
											// 1, if any of them hit 0, i throw
											// them into the queue, then I rinse
											// and repeat

					nodes.add(des);
					des.setQueued();

				}
			}
			if (toposort[count].equals(null)) {
				return null;
			}
		}
		if ((toposort.equals(null)) || (toposort.toString() == null)) {
			return null;
		}
		for (int i = 0; i < size; i++) {

			String a = toposort[i];
			if (a == null) {
				return null;
			}

		}
		return toposort;

	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		Vertex source = this.vertices.get(label); // The source for all the
													// paths to start from

		ShortestPathInfo[] paths = new ShortestPathInfo[vertexIDs.size()]; // our
																			// returned
																			// array
		Vertex prev;
		Vertex[] dist = new Vertex[vertexIDs.size()];
		ArrayList<ShortestPathInfo> list = new ArrayList<ShortestPathInfo>();

		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(10, new Comparator<Vertex>() { // this
																								// is
																								// out
																								// priority
																								// queue,
																								// compares
																								// vertices
																								// and
																								// places
																								// them
																								// based
																								// off
																								// of
																								// that
																								// order
			public int compare(Vertex v1, Vertex v2) {
				if (v1.getDistance() < v2.getDistance())
					return -1;
				if (v1.getDistance() > v2.getDistance())
					return 1;
				return 0;
			}
		});

		int counter = 0;
		for (Vertex a : this.vertices.values()) {

			if (source.Label.equals(a.Label)) {
				a.setDistance(0);
			} else {
				a.setDistance(Integer.MAX_VALUE); // used max value because we
													// need to make sure they
													// are all "as far as
													// possible before we know
													// their distance"
			}
			dist[counter] = a;

			queue.add(a);

			counter++;
		}

		counter = 0;

		while (!queue.isEmpty()) {

			counter++;

			Vertex min = queue.poll();
			list.add(new ShortestPathInfo(min.Label, min.getDistance()));

			for (Edge e : min.OutEdge.values()) {

				long weight = e.getWeight() + min.getDistance();
				if (weight < e.dest.getDistance()) {

					e.dest.setDistance(weight);
					queue.add(e.dest);
				}
			}

		}

		for (int i = 0; i < vertexIDs.size(); i++) {
			if (dist[i].distance == Integer.MAX_VALUE) {
				dist[i].setDistance(-1);

			}
			paths[i] = new ShortestPathInfo(dist[i].Label, dist[i].getDistance());

		}

		return paths;
	}

}