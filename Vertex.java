import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Vertex {

	// data fields
	private int parentArrayIndex; //the fixed index obtained by hashing for bfs and ford fulkerson steps
	private String name;
	private HashMap<Vertex, Integer> adjacentVertices; // this is a map(dictionary to store all adjacent vertices with their
													// capacities to a node.

	// constructor
	public Vertex(String name) {
		this.name = name;
		adjacentVertices = new HashMap<>();
		//capacity = Integer.MAX_VALUE; // edge property
	}
	
	//methods
	
    public void addAdjacentVertex(Vertex neighbor, int capacity) {
    	adjacentVertices.put(neighbor, capacity); //put the neighbor vertex to hashMap with edge as capacity
    }
    

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	


	//getters and setters
	//that I automatically generated with eclipse

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<Vertex, Integer> getAdjacentVertices() {
		return adjacentVertices;
	}

	public void setAdjacentVertices(HashMap<Vertex, Integer> adjacentVertices) {
		this.adjacentVertices = adjacentVertices;
	}

	public int getParentArrayIndex() {
		return parentArrayIndex;
	}

	public void setParentArrayIndex(int parentArrayIndex) {
		this.parentArrayIndex = parentArrayIndex;
	}

	
	
}
