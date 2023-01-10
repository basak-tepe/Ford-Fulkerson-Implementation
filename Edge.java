import java.util.HashSet;

public class Edge {

	//data fields
	private Vertex v1;
	private Vertex v2;
	private int capacity; //the capacity of the edge
	private int residualFlow; //residual.
	private int reverseResidualFlow;
	private String name; //v1v2

	//constructors
	public Edge(Vertex v1, int capacity, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.capacity = capacity;
		this.name = ""+v1.getName()+v2.getName();
		this.residualFlow = capacity;  //forward remaining flow that we will reduce 
		this.reverseResidualFlow = capacity*(-1); //backward remaining flow that we will increase
	}
	//methods
	
	public int compareTo(Edge other) {
		return Integer.compare(this.capacity, other.getCapacity());
	    
	}

	
	//getters and setters

	
	//getters and setters
	//auto generated with eclipse.
	
	public Vertex getV1() {
		return v1;
	}

	public void setV1(Vertex v1) {
		this.v1 = v1;
	}

	public Vertex getV2() {
		return v2;
	}

	public void setV2(Vertex v2) {
		this.v2 = v2;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getResidualFlow() {
		return residualFlow;
	}

	public void setResidualFlow(int residualFlow) {
		this.residualFlow = residualFlow;
	}

	public void setReverseResidualFlow(int reverseResidualFlow) {
		this.reverseResidualFlow = reverseResidualFlow;
		
	}

	public int getReverseResidualFlow() {
		return this.reverseResidualFlow;
	}
	

}
