import java.util.*;

class Graph {
    private int V; // number of vertices in the graph
    LinkedList<Integer>[] adj; // adjacency list representing the graph

    // Constructor to initialize the graph with V vertices
    @SuppressWarnings("unchecked")
	Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>(); // initialize each vertex's adjacency list
        }
    }
    

    // Method to add an edge between two vertices v and w
    void addEdge(int v, int w) {
        adj[v].add(w); // add w to v's adjacency list
    }

    // Recursive function to perform DFS starting from vertex v
    void DFS(int v, boolean[] visited) {
        visited[v] = true; // mark v as visited
        System.out.print(v + " "); // print the visited vertex

        // Recursively visit all unvisited neighbors of vertex v
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFS(n, visited);
            }
        }
    }

    // Method to perform DFS traversal of the entire graph
    void DFS() {
        boolean[] visited = new boolean[V]; // initialize all vertices as unvisited
        for (int i = 0; i < V; i++) {
            if (!visited[i]) { // if vertex i is unvisited, perform DFS starting from i
                DFS(i, visited);
            }
        }
    }

    // Main method to create a graph and perform DFS traversal
    public static void main(String[] args) {
        Graph g = new Graph(5); // create a graph with 5 vertices
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is DFS from vertex 2:");

        g.DFS(2, new boolean[5]); // perform DFS starting from vertex 2
    }
}
