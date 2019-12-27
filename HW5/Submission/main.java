public class main
{
	// This is the value we use if the distance is infinity or the vertex is not visited or not done
	private static int infinity = 10000;
	// This variable is used to determine if there is a cycle in the DAG
	private static boolean cycle = false;

	/**
	 * This is the main function
	 * @param args
	 */
	public static void main(String[] args)
	{

		// This is the case where the graph does not have a cycle
		System.out.println("\nCase 1 - Where DAG does not have a cycle \nThis is the case where we don't have a cycle and thus the algorithm generates the correct topological order");
		topologicalDFS graph = new topologicalDFS();
		// The nodes and vertices are added in the following lines
		graph.adjacencyArray[0][4] = 1;
		graph.adjacencyArray[0][3] = 1;
		graph.adjacencyArray[1][4] = 1;
		graph.adjacencyArray[2][0] = 1;
		graph.adjacencyArray[2][1] = 1;
		graph.adjacencyArray[2][5] = 1;
		graph.adjacencyArray[2][6] = 1;
		graph.adjacencyArray[3][5] = 1;
		graph.adjacencyArray[5][7] = 1;
		graph.adjacencyArray[7][8] = 1;
		graph.adjacencyArray[8][9] = 1;
		graph.adjacencyArray[7][9] = 1;
		graph.adjacencyArray[4][9] = 1;
		graph.adjacencyArray[6][9] = 1;
		graph.adjacencyArray[1][9] = 1;
		// After adding all the vertices and edges the graph is printed
		graph.printGraph();
		// DFS algorithm is applied to the graph to find the topological ordering
		graph.DFS();
		// Now the nodes with their ordering are printed
		System.out.println("Following is the list of nodes with their respective order value");
		for (int i = 0; i < graph.vertices.length; i++)
		{
			if (graph.vertices[i].getTopologicalOrder() != infinity)
			{
				System.out.println("The vertex " + i + " is ordered at " + graph.vertices[i].getTopologicalOrder());
			}
		}

		// This is the second case where the DAG have a cycle
		System.out.println("\nCase 2 - Where DAG have a cycle \nThis is the case where we have a cycle and thus the algorithm exits");
		graph = new topologicalDFS();
		graph.adjacencyArray[0][4] = 1;
		graph.adjacencyArray[0][3] = 1;
		graph.adjacencyArray[1][4] = 1;
		graph.adjacencyArray[2][0] = 1;
		graph.adjacencyArray[2][1] = 1;
		graph.adjacencyArray[2][5] = 1;
		graph.adjacencyArray[2][6] = 1;
		graph.adjacencyArray[3][5] = 1;
		graph.adjacencyArray[5][0] = 1;
		graph.adjacencyArray[5][7] = 1;
		graph.adjacencyArray[7][8] = 1;
		graph.adjacencyArray[8][9] = 1;
		graph.adjacencyArray[7][9] = 1;
		graph.adjacencyArray[4][9] = 1;
		graph.adjacencyArray[6][9] = 1;
		graph.adjacencyArray[1][9] = 1;
		graph.printGraph();
		graph.DFS();
		System.out.println("Following is the list of nodes with their respective order value");
		for (int i = 0; i < graph.vertices.length; i++)
		{
			if (graph.vertices[i].getTopologicalOrder() != infinity)
			{
				System.out.println("The vertex " + i + " is ordered at " + graph.vertices[i].getTopologicalOrder());
			}
			else{
				System.out.println("Cannot find the order for vertex " + i + " since a cycle was detected in the graph.");
			}
		}
	}

	static class topologicalDFS
	{
		// This is the adjacency array for the graph
		int[][] adjacencyArray = new int[10][10];
		// This value is used for the ordering
		int numberOfNodes = 10;
		// This is the list of vertices for the graph
		Vertices[] vertices = new Vertices[10];

		/**
		 * This is the constructor for the topologicalDFS class
		 */
		topologicalDFS()
		{
			for (int j = 0; j < 10; j++)
			{
				for (int i = 0; i < 10; i++)
				{
					// Took the infinity value as 10000
					adjacencyArray[i][j] = infinity;
				}
				// Here all the vertices are defined
				vertices[j] = new Vertices(j);
				vertices[j].setDone(infinity);
				vertices[j].setVisited(infinity);
				vertices[j].setId(j);
				vertices[j].setTopologicalOrder(infinity);
			}
		}

		/**
		 * This function is used to print the graph before applying the algorithm
		 */
		void printGraph()
		{
			System.out.println("The adjacency matrix for the graph :-");
			System.out.print("  ");
			for (int i = 0; i < 10; i++)
			{
				System.out.print(" " + i + " ");
			}
			System.out.println();
			for (int i = 0; i < 10; i++)
			{
				System.out.print(i + " ");
				for (int j = 0; j < 10; j++)
				{
					// If the nodes do not have an edge then we print ∞
					if(adjacencyArray[i][j] != infinity)
						System.out.print(" " + adjacencyArray[i][j] + " ");
					else
						System.out.print(" ∞ ");
				}
				System.out.println();
			}
		}

		/**
		 * This is the topologicalDFS function mentioned in the slides
		 */
		void DFS()
		{
			for (Vertices vertex : vertices)
			{
				// Initialize the values of done and visited
				vertex.setDone(infinity);
				vertex.setVisited(infinity);
			}
			int count = 0;
			// In the following steps the algorithm finds the node with no in edges and start the DFS with that node
			while (count < vertices.length)
			{
				boolean startPoint = true;
				for (int count1 = 0; count1 < vertices.length; count1++)
				{
					if (adjacencyArray[count1][count] != infinity || !startPoint)
					{
						startPoint = false;
						continue;
					}
				}
				count++;
				if (!startPoint)
				{
					continue;
				}
				else
				{
					// the vertex at count - 1 is the node with zero in degree.
					DFS(vertices[count - 1]);
					return;
				}
			}
		}

		/**
		 * This the main DFS function which takes the start node and finds the ordering for nodes in the graph
		 * @param vertex This is the start node
		 */
		void DFS(Vertices vertex)
		{
			// The current node is marked as visited
			vertex.setVisited(1);
			int currentId = vertex.getId();
			for (int count1 = 0; count1 < vertices.length; count1++)
			{
				if (adjacencyArray[currentId][count1] == infinity)
				{
					// The current vertices or the vertices which are not connected with the start are not used
					continue;
				}
				// Store the value of the opposite vertex in the var
				Vertices opposite = vertices[count1];
				// If opposite is not done and is visited then we have a cycle
				if (opposite.getDone() == infinity && opposite.getVisited() == 1)
				{
					if (!cycle)
					{
						System.out.println("There is a cycle in the DAG");
						cycle = true;
					}
					return;
				}
				else
				{
					if (opposite.getVisited() == infinity)
					{
						// Recursively call DFS on the opposite vertex
						DFS(opposite);
					}
				}
			}
			// The current vertex is marked as done and it is ranked according to the value of number of nodes
			vertex.setDone(1);
			vertex.setTopologicalOrder(numberOfNodes--);
		}
	}

	static class Vertices
	{
		// This is the class which stores the various properties of the vertices
		int id;
		int visited;
		int done;
		int topologicalOrder;

		Vertices(int id)
		{
			this.id = id;
			// For initialization a max distance of 10000 is used
			this.visited = infinity;
			this.topologicalOrder = -1;
		}

		// Following are the getter and setter methods for the vertex class
		int getVisited()
		{
			return visited;
		}

		void setVisited(int visited)
		{
			this.visited = visited;
		}

		int getDone()
		{
			return done;
		}

		void setDone(int done)
		{
			this.done = done;
		}

		int getId()
		{
			return id;
		}

		void setId(int id)
		{
			this.id = id;
		}

		int getTopologicalOrder()
		{
			return topologicalOrder;
		}

		void setTopologicalOrder(int parent)
		{
			this.topologicalOrder = parent;
		}
	}
}
