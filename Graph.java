import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph {

	// Data fields
	 
		private Vertex source;
		private Vertex sink;
		private HashMap<String,Vertex> vertices; // all vertices of a graph
		private HashMap<String,Edge> edges; //all edges of a graph. String name is the v1v2 form
		private HashMap<String,Edge> reverseEdges; //all edges of a graph. String name is the v1v2 form. Didn't end yp needing it.


		// constructors
		public Graph() {
			vertices = new HashMap<>();
			edges = new HashMap<>();
			reverseEdges =  new HashMap<>();
		}

		// methods
		
		/**
		 * create reverse connections.
		 */
		
		
		//no need
		public void addReverseEdges() {
	
			for(Map.Entry<String, Edge> pair : edges.entrySet()) {
				Edge e = pair.getValue(); 
				Vertex v1 = e.getV1();
				Vertex v2 = e.getV2();
				int capacity = e.getCapacity()*(-1);
				Edge reverseEdge = new Edge(v2,capacity,v1);
				this.reverseEdges.put(""+v2.getName()+v1.getName(), reverseEdge);
			}
		}
		
		
		public void addVertex(Vertex vertex) {
			 String name = vertex.getName();
			 //hashMap checks for any duplicates.
			 vertices.putIfAbsent(name, vertex);
		}
		
		public void addEdge(Edge edge) {
			Vertex v1 = edge.getV1();
			Vertex v2 = edge.getV2();
			String v1Name = v1.getName();
			String v2Name = v2.getName();
			String edgeName = ""+v1Name+v2Name;
			edges.putIfAbsent(edgeName, edge);
		}

		
		/**
		 * a method for obtaining an edge from x to y. 
		 * @param vertices
		 */
		
		public Edge getEdge(Vertex v1,Vertex v2) {
			String v1Name = v1.getName();
			String v2Name = v2.getName();
			String edgeName = ""+v1Name+v2Name;
			Edge edge = edges.get(edgeName);
			return edge;
			
		}
		
		
		//no need
		public Edge getReverseEdge(Vertex v1, Vertex v2) {
			String v1Name = v1.getName();
			String v2Name = v2.getName();
			String edgeName = ""+v1Name+v2Name;
			Edge edge = reverseEdges.get(edgeName);
			return edge;
		}

		
		// getters and setters
	
		public void setVertices(HashMap<String,Vertex> vertices) {
			this.vertices = vertices;
		}

		public HashMap<String,Edge> getEdges() {
			return edges;
		}

		public void setEdges(HashMap<String,Edge> edges) {
			this.edges = edges;
		}

		public HashMap<String, Vertex> getVertices() {
			// TODO Auto-generated method stub
			return this.vertices;
		}

		public Vertex getSource() {
			return source;
		}

		public void setSource(Vertex source) {
			this.source = source;
		}

		public Vertex getSink() {
			return sink;
		}

		public void setSink(Vertex sink) {
			this.sink = sink;
		}

	

}
