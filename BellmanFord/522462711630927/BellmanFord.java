// A class to represent a connected, directed and weighted BellmanFord graph
class BellmanFord {
	
	// A class to represent a weighted edge in graph
	class Edge {
		int src, dest, weight;
		Edge() { src = dest = weight = 0; }
	};

	int V, E;
	Edge edge[];

	// Creates a graph with V vertices and E edges
	BellmanFord(int v, int e)
	{
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}

	// The main function that finds shortest distances from
	// src to all other vertices using Bellman-Ford
	// algorithm. The function also detects negative weight
	// cycle
	boolean BellmanFordBool(BellmanFord graph, int src)
	{
		int V = graph.V, E = graph.E;
		int dist[] = new int[V];
		int pred[] = new int[V];

		// Step 1: Initialize distances from src to all
		// other vertices as INFINITE
		for (int i = 0; i < V; ++i) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}
		dist[src] = 0;

		// Step 2: Relax all edges |V| - 1 times. A simple
		// shortest path from src to any other vertex can
		// have at-most |V| - 1 edges
		for (int i = 1; i < V; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				if (dist[u] != Integer.MAX_VALUE
					&& dist[u] + weight < dist[v]) {
					
					dist[v] = dist[u] + weight;
					pred[v] = u;
				}
			}
		}

		// Step 3: check for negative-weight cycles. The
		// above step guarantees shortest distances if graph
		// doesn't contain negative weight cycle. If we get
		// a shorter path, then there is a cycle.
		for (int j = 0; j < E; ++j) {
			int u = graph.edge[j].src;
			int v = graph.edge[j].dest;
			int weight = graph.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE
				&& dist[u] + weight < dist[v]) {
				return false;
			}
		}
		// Step 4: construct predecessor subgraph Gpi
		for (int i = 0; i < V; i++) {
			if ( i != src && pred[i] != -1) {
				graph.edge[i - 1].src = pred[i];
				graph.edge[i - 1].dest = i;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args)
	{
		//General Case: positive and negative weights
		BellmanFord graph1 = new BellmanFord(5, 8);
		graph1.edge[0].src = 0;
		graph1.edge[0].dest = 1;
		graph1.edge[0].weight = -1;
		graph1.edge[1].src = 0;
		graph1.edge[1].dest = 2;
		graph1.edge[1].weight = 4;
		graph1.edge[2].src = 1;
		graph1.edge[2].dest = 2;
		graph1.edge[2].weight = 3;
		graph1.edge[3].src = 1;
		graph1.edge[3].dest = 3;
		graph1.edge[3].weight = 2;
		graph1.edge[4].src = 1;
		graph1.edge[4].dest = 4;
		graph1.edge[4].weight = 2;
		graph1.edge[5].src = 3;
		graph1.edge[5].dest = 1;
		graph1.edge[5].weight = 1;
		graph1.edge[6].src = 4;
		graph1.edge[6].dest = 3;
		graph1.edge[6].weight = -3;
		graph1.edge[7].src = 2;
		graph1.edge[7].dest = 4;
		graph1.edge[7].weight = 1;
		
		System.out.println(graph1.BellmanFordBool(graph1, 0));

		//Case 1: positive weights
		BellmanFord graph2 = new BellmanFord(4, 5);
		graph2.edge[0].src = 0;
		graph2.edge[0].dest = 1;
		graph2.edge[0].weight = 1;
		graph2.edge[1].src = 1;
		graph2.edge[1].dest = 2;
		graph2.edge[1].weight = 3;
		graph2.edge[2].src = 2;
		graph2.edge[2].dest = 3;
		graph2.edge[2].weight = 2;
		graph2.edge[3].src = 3;
		graph2.edge[3].dest = 1;
		graph2.edge[3].weight = 6;
		graph2.edge[4].src = 1;
		graph2.edge[4].dest = 3;
		graph2.edge[4].weight = 2;

		System.out.println(graph2.BellmanFordBool(graph2, 0));
		
		//Case 2: negative weight
		BellmanFord graph3 = new BellmanFord(4, 4);
		graph3.edge[0].src = 0;
		graph3.edge[0].dest = 1;
		graph3.edge[0].weight = -1;
		
		graph3.edge[1].src = 1;
		graph3.edge[1].dest = 2;
		graph3.edge[1].weight = -1;
		
		graph3.edge[2].src = 2;
		graph3.edge[2].dest = 3;
		graph3.edge[2].weight = -1;
		
		graph3.edge[3].src = 3;
		graph3.edge[3].dest = 0;
		graph3.edge[3].weight = -1;
		
		System.out.println(graph3.BellmanFordBool(graph3, 0));
		
		
		
	}
}