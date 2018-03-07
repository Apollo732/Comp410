package A6_Dijkstra;

import java.util.Comparator;
import java.util.HashMap;


public class Vertex implements Comparable<Vertex>
{
  HashMap<String, Edge> InEdge;
  HashMap<String, Edge> OutEdge;
  HashMap<String, Vertex> inVert;
  HashMap<String, Vertex> outVert;
  long distance;
  long ID;
  String Label;
  int inEdges;
  boolean queued;
  
  public Vertex(long Id, String label)
  {
    this.ID = Id;
    this.Label = label;
    this.InEdge = new HashMap();
    this.OutEdge = new HashMap();
    this.inVert = new HashMap();
    this.outVert = new HashMap();

    this.inEdges = 0;
    this.queued = false;
  }
  
  public String getLabel()
  {
    return this.Label;
  }
  
  public long getID()
  {
    return this.ID;
  }
  public void setQueued(){
	  this.queued = true;
  }
  
  public void addEd(){
	  this.inEdges++;
  }
  public void subEd(){
	  this.inEdges--;
  }
  public int getEd(){
	  return inEdges;
  }
  
  
  public void initEd(){
	  this.inEdges = this.InEdge.size();
	  
  }
  
  
  
  /*public void initialize() {
		for (Edge a : this.OutEdge.values()) {
			outVert.put(a.dest.Label, a.dest);
		}
		for (Edge b : this.OutEdge.values()) {
			inVert.put(b.origin.Label, b.origin);
		}

	}*/
  
  public void setDistance(long weight){
	  this.distance = weight;
	  
  }
  
  
  public long getDistance(){
	  return this.distance;
	  
  }



@Override
public int compareTo(Vertex v1) {
	 if(this.distance>v1.distance){  
	        return 1;  
	    }else if(this.distance<v1.distance){  
	        return -1;  
	    }else{  
	    return 0;  
	    }  
}






  
  
}
