package DiGraph_A5;

import java.util.HashMap;


public class Vertex
{
  HashMap<String, Edge> InEdge;
  HashMap<String, Edge> OutEdge;
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
  
  
}
