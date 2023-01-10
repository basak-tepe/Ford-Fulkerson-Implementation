import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class FordFulkerson {

	/**
	 * I used BFS. So this method is actually an Edmonds-Karp Algorithm, a variation of Ford-Fulkerson. 
	 */
	//data fields
		Graph graph;
		Vertex source;
		Vertex sink;
		private HashMap<String,Vertex> vertices; // all vertices of a graph
		Vertex[] indexes;
 		
		//constructor
		public FordFulkerson(Graph graph, Vertex[] indexes) {
			this.graph = graph;
			this.vertices = graph.getVertices();
			this.source = graph.getSource();
			this.sink = graph.getSink();
			this.indexes = indexes;
		}
		
		//methods
		
		/**
		 * in each iteration
		 * we first find an augmenting path with a modified BFS
		 * then compute the smallest road capacity, i.e the bottleneck
		 * and we augment these edges and add to the total flow.
		 * 
		 * repeat until we can't find a augmenting path.
		 * 
		 * 
		 * augmenting path: not-full in forward and non-empty in backward.
		 * @return 
		 */
		
		public int computeMaxFlow() {
 
				Vertex u;
				Vertex v;
				
			    int vertexCount = vertices.size();
	
		        int parent[] = new int[vertexCount]; //our parent array. Filled during bfs

		        int max_flow = 0;
		 
		        BFS bfs = new BFS(graph,indexes);
		        
		        //while there is a path, augment flow
		        while (bfs.BreadthFirstSearch(source, sink, parent)) {
		        	
		            int bottleneckFlow = Integer.MAX_VALUE;
		           
		            //parent index of v
		            int index = sink.getParentArrayIndex();
		            int parentsIndex = parent[index]; //getting the parent's index from the parent array
		            Vertex parentOfv = indexes[parentsIndex]; //getting the parent object. indexes list stores the vertices at corresponding spaces.
		            		
		            for (v = this.sink; v != this.source; v = parentOfv) {
		            	index = v.getParentArrayIndex();
				        parentsIndex = parent[index];
				        parentOfv = indexes[parentsIndex];
		            	u = parentOfv;
		                bottleneckFlow = Math.min(bottleneckFlow, graph.getEdge(u, v).getResidualFlow()); //this line obtains the bottleneck value
		           
		                
		                /**
		                 * in the next iteration, v becomes the previous node(u) and u is taken a step backward.
		                 * i.e, the edge before current one is examined until we reach the source.
		                 */
		            }
		 
		            // update flows 
		  
		            index = sink.getParentArrayIndex();
			        parentsIndex = parent[index];
			        parentOfv = indexes[parentsIndex];
	                
		            for (v = this.sink; v != this.source;  v = parentOfv) {
		            	index = v.getParentArrayIndex();
				        parentsIndex = parent[index];
				        parentOfv = indexes[parentsIndex];
		            	u = parentOfv;
		                
		                //edge between a vertex and its parent
		                int residualFlow = graph.getEdge(u, v).getResidualFlow();
		                int reverseResidualFlow = graph.getEdge(u, v).getReverseResidualFlow();
		                graph.getEdge(u, v).setResidualFlow(residualFlow-bottleneckFlow); //decrease remaining flow capacity forward
		                graph.getEdge(u, v).setReverseResidualFlow(reverseResidualFlow+bottleneckFlow);  //increase possible flow capacity backward

		            }
		 
		            // Add path flow to overall flow
		            max_flow += bottleneckFlow;
		        }
		 
		        // Return the overall flow
		        return max_flow;
		}
		
		
		
		
}
