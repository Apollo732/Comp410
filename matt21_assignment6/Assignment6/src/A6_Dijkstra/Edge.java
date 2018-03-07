package A6_Dijkstra;

public class Edge {

	Vertex dest;
	String label;
	Vertex origin;

	long idNum;
	long weight;

	public Edge(long idNum, Vertex origin, Vertex dest, long weight, String label) {
		this.idNum = idNum;

		this.origin = origin;
		this.weight = weight;

		this.dest = dest;
		this.label = label;

	}

	public Vertex getOrigin() {
		return this.origin;
	}

	

	public Vertex getDest() {
		return this.dest;
	}

	

	public String getLabel() {
		return this.label;
	}

	

	public long getId() {
		return this.idNum;
	}

	public long getWeight() {
		return this.weight;
	}

	
}