// I am using the Comparator for the priority queue so taht the vertex with the min distance is popped from the queue
// Basically comparator is used to determine how the vertices will be compared when they are added to the queue.
import java.util.Comparator;
import java.util.PriorityQueue;

public class main
{
	// This is the distance we take as infinity (not reachable)
	static int infinity = 10000;

	/**
	 * This is the main driver function
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Here I initialized the graph as an adjacency matrix
		Dijkstras graph = new Dijkstras();
		graph.adjacencyArray[0][1] = 5;
		graph.adjacencyArray[0][2] = 7;
		graph.adjacencyArray[0][3] = 3;
		graph.adjacencyArray[1][0] = 5;
		graph.adjacencyArray[2][0] = 7;
		graph.adjacencyArray[3][0] = 3;
		graph.adjacencyArray[1][4] = 2;
		graph.adjacencyArray[1][5] = 10;
		graph.adjacencyArray[4][1] = 2;
		graph.adjacencyArray[5][1] = 10;
		graph.adjacencyArray[2][6] = 1;
		graph.adjacencyArray[6][2] = 1;
		graph.adjacencyArray[3][7] = 11;
		graph.adjacencyArray[4][7] = 9;
		graph.adjacencyArray[5][7] = 4;
		graph.adjacencyArray[6][7] = 6;
		graph.adjacencyArray[7][3] = 11;
		graph.adjacencyArray[7][4] = 9;
		graph.adjacencyArray[7][5] = 4;
		graph.adjacencyArray[7][6] = 6;
		graph.adjacencyArray[7][8] = 5;
		graph.adjacencyArray[8][7] = 5;

		// These are the vertices
		graph.vertices = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		// I printed the graph before applying the algorithm
		// The list of vertices and edges(pair of vertices) are printed
		graph.printGraphBefore();
		// This is the start vertex from which we start the algorithm
		int startVertex = 1;
		// I printed the graph after applying the algorithm
		// The list of vertices and edges of the tree(pair of vertices) are printed
		System.out.println();
		System.out.println("----------------After applying Dijkstra's------------------");
		graph.shortestPath(startVertex);
		graph.printGraphAfter(startVertex);
	}

	static class Dijkstras
	{
		// This is the adjacency array for the graph
		int[][] adjacencyArray = new int[10][10];
		// This is the list of vertices for the graph
		int[] vertices = new int[10];
		// These are list in which we store the vertex number and its distance from the start
		Vertices[] verticesWithDistance = new Vertices[10];

		/**
		 * This is the constructor for the algorithm in which we initialize each edge with infinite distance
		 */
		Dijkstras()
		{
			for (int j = 0; j < 10; j++)
			{
				for (int i = 0; i < 10; i++)
				{
					// Took the infinity value as 10000
					adjacencyArray[i][j] = infinity;
				}
			}
		}

		/**
		 * This is the function used to print the graph before applying the algorithm
		 */
		void printGraphBefore()
		{
			System.out.println("The edges are :-");
			for (int i = 0; i < 10; i++)
			{
				for (int j = 0; j < 10; j++)
				{
					if (adjacencyArray[i][j] != infinity)
					{
						// I print the value of each edge between the vertices
						System.out.println("Edge from vertex " + (i) + " to vertex " + (j) + " with weight " + adjacencyArray[i][j]);
					}
				}
			}
			// Now I printed the vertices
			System.out.print("The vertices are ");
			for (int j = 0; j < 10; j++)
			{
				System.out.print("Vertex " + vertices[j] + " ");
				verticesWithDistance[j] = new Vertices(j);
			}
			System.out.println();
		}

		/**
		 * This is the function whihc is used to print the tree after applying the algorithm
		 *
		 * @param start This is the index of the vertex on which the algorithm is applied
		 */
		void printGraphAfter(int start)
		{
			for (int i = 0; i < 10; i++)
			{
				if (verticesWithDistance[i].distance != infinity)
				{
					System.out.println("Distance from vertex " + start + " to vertex " + (i) + " is " + verticesWithDistance[i].distance);
					if(verticesWithDistance[i].parent != -1)
					{
						System.out.println("There is an edge between vertex " + (i) + " and vertex " + verticesWithDistance[i].parent + " in the tree.");
					}
					else{
						System.out.println("Vertex "+(i)+ " is the start point.");
					}
				}
				else
				{
					// Here if the vertex is not reachable from the start then we print that it is at infinity distance
					System.out.println("Distance from vertex " + start + " to vertex " + (i) + " is infinity (the point is not reachable from the starting point).");

				}
			}
			// Here I printed the vertices
			System.out.println("The vertices in the tree when we start from " + start + " are");
			for (int j = 0; j < 10; j++)
			{
				if (verticesWithDistance[j].distance != infinity)
				{
					System.out.println("Vertex " + vertices[j] + " ");}
				else{
					System.out.println("Vertex " + vertices[j] + " is not in the tree (Do not have a path from "+start+" to "+vertices[j] + ")");
				}
			}
			System.out.println();
		}

		/**
		 * This is the main function to find the shortest path from a start vertex to all the other vertices
		 * @param start This is the start vertex
		 */
		void shortestPath(int start)
		{
			Comparator<Vertices> distanceComparator = new Comparator<Vertices>()
			{
				//Here I implemented the comparator for the prioriy queue
				@Override
				public int compare(Vertices v1, Vertices v2)
				{
					return v1.distance - v2.distance;
				}
			};
			// This is the priority queue that we will use
			PriorityQueue<Vertices> queue = new PriorityQueue<Vertices>(distanceComparator);
			for (int j = 0; j < 10; j++)
			{
				// This is the first step in which the start vertex have distance 0 and other vertices have distance infinity
				if (verticesWithDistance[j].id == start)
				{
					verticesWithDistance[j].distance = 0;
				}
				else
				{
					verticesWithDistance[j].distance = infinity;
				}
				queue.add(verticesWithDistance[j]);
			}
			while (!queue.isEmpty())
			{
				// We remove the topmost from the heap/queue
				Vertices current = queue.remove();
				// We store the values for the number and distance of the current vetrex from the start
				int distanceOfCurrent = current.distance;
				int valueOfCurrent = current.id;
				for (int count1 = 0; count1 < 10; count1++)
				{
					// Here we loop for each vertex
					if (count1 == valueOfCurrent || adjacencyArray[valueOfCurrent][count1] == infinity)
					{
						// The current vertices or the vertices which are not connected with the start are not used
						continue;
					}
					// This is the opposite vertex from the current vertex
					Vertices opposite = verticesWithDistance[count1];
					// We now compute the distance of the opposite vertex to the current vetrex
					int currentDistance = distanceOfCurrent + adjacencyArray[valueOfCurrent][count1];
					if (currentDistance < opposite.distance)
					{
						verticesWithDistance[count1].distance = currentDistance;
						verticesWithDistance[count1].parent = valueOfCurrent;

						// If the new value is lesser then we change the value in the queue
						queue.remove(verticesWithDistance[count1]);
						queue.add(verticesWithDistance[count1]);
					}
				}
			}
		}

		static class Vertices
		{
			// This is the class which stores the number and distance from start for a given vertex
			int id;
			int distance;
			int parent;

			Vertices(int id)
			{
				this.id = id;
				// For initialization I used the max distance
				this.distance = infinity;
				this.parent = -1;
			}
		}
	}
}


