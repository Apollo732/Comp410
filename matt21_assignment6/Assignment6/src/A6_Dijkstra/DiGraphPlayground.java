package A6_Dijkstra;

import java.io.PrintStream;

public class DiGraphPlayground
{
  public static void main(String[] args) {
	  
	  exTest();
	  System.out.println();
	  testDij();
	  
	  
  }
  
  public static void exTest()
  {
    DiGraph d = new DiGraph();
    d.addNode(1L, "f");
    d.addNode(3L, "s");
    d.addNode(7L, "t");
    d.addNode(0L, "fo");
    d.addNode(4L, "fi");
    d.addNode(6L, "si");
    d.addEdge(0L, "f", "s", 0L, null);
    d.addEdge(1L, "f", "si", 0L, null);
    d.addEdge(2L, "s", "t", 0L, null);
    d.addEdge(3L, "fo", "fi", 0L, null);
    d.addEdge(4L, "fi", "si", 0L, null);
    System.out.println("numEdges: " + d.numEdges());
    System.out.println("numNodes: " + d.numNodes());
    printTOPO(d.topoSort());
  }
  
  public static void testDij()
  {
	  DiGraph d = new DiGraph();
      d.addNode(1, "Raleigh");
      d.addNode(3, "Durham");
      d.addNode(7, "Pittsboro");
      d.addNode(0, "Los_Angeles");
      d.addNode(4, "Graham");
      d.addNode(6, "Cary");
      d.addNode(8, "Chapel_Hill");
      d.addNode(9, "Hillsborough");
      d.addNode(5, "Carrboro");
      d.addNode(2, "Sanford");
      d.addEdge(0, "Raleigh", "Durham", 14, null);
      d.addEdge(1, "Durham", "Hillsborough", 9, null);
      d.addEdge(2, "Chapel_Hill", "Graham", 25, null);
      d.addEdge(3, "Chapel_Hill", "Carrboro", 1, null);
      d.addEdge(4, "Carrboro", "Cary", 32, null);
      d.addEdge(5, "Cary", "Raleigh", 3, null);
      d.addEdge(6, "Pittsboro", "Cary", 17, null);
      d.addEdge(7, "Pittsboro", "Sanford", 15, null);
      d.addEdge(8, "Sanford", "Los_Angeles", 3012, null);
    printShortestPath(d.shortestPath("Pittsboro"));
  }
  
  public static void printTOPO(String[] toPrint)
  {
    System.out.print("TOPO Sort: ");
    String[] arrayOfString = toPrint;int j = toPrint.length;
    for (int i = 0; i < j; i++)
    {
      String string = arrayOfString[i];
      System.out.print(string + " ");
    }
    System.out.println();
  }
  
  public static void printShortestPath(ShortestPathInfo[] list)
  {
    ShortestPathInfo[] arrayOfShortestPathInfo = list;
    int j = list.length;
    for (int i = 0; i < j; i++)
    {
      ShortestPathInfo s = arrayOfShortestPathInfo[i];
      System.out.println(s);
    }
  }
}