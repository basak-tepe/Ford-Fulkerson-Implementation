import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFS {

	// data fields
	Graph graph;
	Vertex source;
	Vertex sink;
	Vertex[] indexes;
	
	// constructor
	public BFS(Graph graph, Vertex[] indexes) {
		this.source = graph.getSource();
		this.sink = graph.getSink();
		this.graph = graph;
		this.indexes = indexes;
	}
	
	
	/**
	 * Starting from the source, we add it to our queue.
	 * We dequeue the source(visit), and add all its children to the back.
	 * First children is dequeued(visited), and all its children are added to the back.
	 * By following these steps, we perform a level-by-level search.
	 */
	
	/**
	 * In addition, BFS is modified to find an augmenting path. 
	 * 	 * augmenting path: not-full in forward and non-empty in backward.
	 * It will be called in our  while loop for the main algorithm.
	 */
	
	/**
	 * The parent array: each index stores the index of the parent. 
	 * These indexes are obtained with hashing.
	 * 
	 * method returns true if there is an augmenting path.
	 */
	
	public boolean BreadthFirstSearch(Vertex source, Vertex sink, int[] parent) {
		//System.out.println(sink.getName());
		Set<Vertex> visited = new HashSet<Vertex>();
		Queue<Vertex> queue = new LinkedList<Vertex>();
		
		visited.add(source);
		queue.add(source);
		int index = source.getParentArrayIndex(); //index for parent array
		parent[index] = -1; // source has no parent
		 
		
		while(!queue.isEmpty()) {
			Vertex vertex = queue.poll();
			visited.add(vertex);
			//System.out.println(vertex.getName()+" ");
			 for(Map.Entry<Vertex, Integer> neighbor: vertex.getAdjacentVertices().entrySet()) {
				int residualFlow =  graph.getEdge(vertex, neighbor.getKey()).getResidualFlow();
				 int reverseResidualFlow = graph.getEdge(vertex, neighbor.getKey()).getReverseResidualFlow();
				 
				if(!visited.contains(neighbor.getKey()) && residualFlow>0 && reverseResidualFlow<0) { //if not visited and capacity is open 
					if(neighbor.getKey().getName().compareTo(sink.getName()) == 0) { //if we have reached the end, a path still exists so return true.
						parent[neighbor.getKey().getParentArrayIndex()] = vertex.getParentArrayIndex();
						//System.out.println("reached");
						return true;
					}
						
					visited.add(neighbor.getKey());
					parent[neighbor.getKey().getParentArrayIndex()] = vertex.getParentArrayIndex();
					//used for checking the parent-child relationships
					//System.out.println("parent of "+neighbor.getKey().getName()+ " is "+vertex.getName());
					//System.out.println("visiting "+ neighbor.getKey().getName());
					queue.add(neighbor.getKey());
				}	
			 }
			 
		}
		
		//at here no path exists 
		return false;
	}
}

