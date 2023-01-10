import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class project5 {

	/**
	 * @author Basak Tepe, Student ID:2020400117
	 * @since Date: 06.01.2023 submission
	 */

	/**
	 * My program works Using Ford-Fulkerson Algorithm and BFS.
	 * It can be counted as Edmonds-Karp implementation since I preferred BFS instead of DFS.
	 * I used an adjacency list.
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {

		try {
			ArrayList<String> indexedVerticeNames = new ArrayList<String>();
			
			String inputFile = args[0];
			String outputFile = args[1];
			File input = new File(inputFile);
			File output = new File(outputFile);
			Scanner sc = new Scanner(input);
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));

			int regionCount = 6; // fixed
			HashSet<Vertex> regions = new HashSet<Vertex>();
			// reading input
			String firstLine = sc.nextLine();
			int cityCount = Integer.parseInt(firstLine);
			String[] secondLine = sc.nextLine().split(" ");

			Graph graph = new Graph(); // a weighted graph of the whole map
			
		
			
			//before generating regions, I created a source node.
			//All regions will be connected to this node and troop count will be the capacity of the respective roads.
			
			
			Vertex source = new Vertex("start");
			graph.addVertex(source);
			graph.setSource(source);
			
			// generate the regions with their troop numbers
			for (int i = 0; i < regionCount; i++) {
				int troopCount = Integer.parseInt(secondLine[i]);
				Vertex region = new Vertex("r" + String.valueOf(i)); // r0,r1,r2 etc.
				regions.add(region);
				
				//making regions neighbor to our start node
				graph.addVertex(region);
				source.addAdjacentVertex(region,troopCount);
				
				Edge edge = new Edge(source,troopCount,region);
				edge.setCapacity(troopCount);
				graph.addEdge(edge);
			}

			// reading other distances
			while (sc.hasNextLine()) {

				
				try {
				String[] nextLine = sc.nextLine().split(" ");
				int nextLineLength = nextLine.length;
				
				//first vertex
				String firstPoint = nextLine[0];
				Vertex first = new Vertex(firstPoint);
				graph.addVertex(first); //checks for duplicates 
				
				//first element, nL[0] is node to be connected to others.
				//1,2 contains first neighbors info.
				//3,4 the next.
				
				//making neighbors for a node
				for(int i = 1; i<nextLineLength-1; i+=2) {
					String nextPoint = nextLine[i];
					Vertex next = new Vertex(nextPoint);
					int capacity=  Integer.parseInt(nextLine[i+1]); //the capacity
					graph.addVertex(next);
					
					Vertex v1 = graph.getVertices().get(firstPoint);
					Vertex v2 = graph.getVertices().get(nextPoint);
					//I wanted to keep all vertices in an indexed list.
					if(!indexedVerticeNames.contains(next.getName())) {
						indexedVerticeNames.add(next.getName());
					}
					v1.addAdjacentVertex(v2, capacity); //directed graph. 		
				
					//Creating the edge and adding it
					Edge edge = new Edge(v1,capacity,v2);
					edge.setCapacity(capacity);
					graph.addEdge(edge);
				}
				
				//adding the sink to the graph info
				graph.setSink(graph.getVertices().get("KL"));
				
				//putting reverse edges with capacities into the graph
				//graph.addReverseEdges();
				}

				catch (Exception e) { // except we are looking at the final node
					// well do nothing, last node is already added during try block.
				}

			}
			
			
			/**
			 * I need indexes for every vertex to keep track of their parents during bfs and ford fulkerson
			 * So I hash everyone into an array.
			 * Now everyone has index 0,1,2... etc in their data field
			 */
			
			/**
			 * hashing
			 */
			
			int vertexCount = graph.getVertices().size();
			Vertex[] indexes = new Vertex[vertexCount];
			
			for (Map.Entry<String, Vertex> v : graph.getVertices().entrySet()) {
				int k = 0; // used for linear probing
				String name = v.getKey();
				boolean placed = false;
				while (!placed) {
					int hashVal = 0;
					for (int h = 0; h < name.length(); h++) {
						hashVal = hashVal + ((int) (Math.pow(31, h)) * ((int) (name.charAt(h))));
					}
					hashVal = (hashVal + k) % vertexCount;
					if (indexes[hashVal] == null) {
						indexes[hashVal] = v.getValue();
						placed = true;
						v.getValue().setParentArrayIndex(hashVal);
					} else { // linear probing
						k++;
						continue;
					}
				}
			}
		
				
			//for checking the hashing function
			/**	for(Vertex v:indexes) {
				System.out.println("at " + v.getParentArrayIndex()+" we have "+v.getName());
			}
			
			*/
			
			int[] parent = new int[vertexCount];
			 BFS bfs = new BFS(graph,indexes);
			 bfs.BreadthFirstSearch(graph.getSource(), graph.getSink(), parent);
			
			
			FordFulkerson ff = new FordFulkerson(graph,indexes);
			int maxFlow = ff.computeMaxFlow();
			//System.out.println(maxFlow);
			writer.write(""+maxFlow);
			
			
			sc.close();
			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
