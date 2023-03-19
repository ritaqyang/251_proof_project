import java.util.*;
//the following code was taken from:https://www.javatpoint.com/avl-tree-program-in-java. By JavaTpoint 2021
//Comments and code marked with --- are my own

public class Dijkstra {
    private int distance[]; //fields of the class include distance
    private Set<Integer> settld; //a set of integers
    private PriorityQueue<G_Node> pQue; //a priority queue for holding the vertices in the graph
    private int totalNodes; //a collection of all the nodes
    List<List<G_Node>> adjacent; //adjacency list to store the vertex/edge relationships

    public Dijkstra(int totalNodes) { //class constructor

        this.totalNodes = totalNodes; //assigns total number of nodes to the field
        distance = new int[totalNodes]; //array of distances
        settld = new HashSet<Integer>(); //A hash set of nodes that have already been looked at by the algorithm
        pQue = new PriorityQueue<G_Node>(totalNodes, new G_Node()); //The priority queue of nodes to look at
    }
    public void dijkstra(List<List<G_Node>> adjacent, int s) {
        this.adjacent = adjacent; //assigns the adjacency list to the graph

        for (int j = 0; j < totalNodes; j++) { //Initialization step where distances are set to infinity
            distance[j] = Integer.MAX_VALUE; //infinity or max value
        }
        pQue.add(new G_Node(s, 0)); //The first node to add to the queue is the source node
        distance[s] = 0; //Source node distance is initialized to zero

        while (settld.size() != totalNodes) { //priority queue code to find the shortest distances and build the path
            if (pQue.isEmpty()) { //The algorithm terminates when the queue is empty or if all nodes have been traversed
                return;
            }
            int ux = pQue.remove().n; //removes the node from the queue with the minimum distance
            if (settld.contains(ux)) { //continue if node has already been evaluated
                continue;
            }
            settld.add(ux); //add the node to the evaluated nodes list
            eNeighbours(ux); //call the neighbors function to update edge relationships
        }
    }
    private void eNeighbours(int ux) {

        int edgeDist = -1; //initializes variables to update
        int newDist = -1;

        for (int j = 0; j < adjacent.get(ux).size(); j++) { //goes through the neighbors adjacent to ux
            G_Node vx = adjacent.get(ux).get(j); //gets the adjacent node
            if (!settld.contains(vx.n)) { //if the node isn't already part of the evaluated nodes
                edgeDist = vx.price; //edge distance is equal to the weight of the neighbor vx
                newDist = distance[ux] + edgeDist; //computes distance of ux + the neighbor weight
                if (newDist < distance[vx.n]) { //updates the distance with the lower weight
                    distance[vx.n] = newDist;
                }
                pQue.add(new G_Node(vx.n, distance[vx.n])); //node is added to queue
            }
        }
    }
    public static void main(String argvs[]) {
        //The example below for the general case of Dijkstra's algorithm was taken from:
        // was taken from:https://www.javatpoint.com/avl-tree-program-in-java. By JavaTpoint 2021
        //The comments and edge cases are my own
        int totalNodes = 9; //9 nodes for the general case graph
        int s = 0; //source is initialized to zero
        List<List<G_Node>> adjacent = new ArrayList<List<G_Node>>(); //adjacency list for node/edge relationships
        for (int i = 0; i < totalNodes; i++) { //each node has its own adjacency list
            List<G_Node> itm = new ArrayList<G_Node>();
            adjacent.add(itm);
        }
        adjacent.get(0).add(new G_Node(1, 3)); //building the adjacency list by adding nodes, edges, and weights
        adjacent.get(0).add(new G_Node(7, 7)); // Example: .get(0) means to travel from 0 to 1 at a cost of 3.
        adjacent.get(1).add(new G_Node(0, 3));
        adjacent.get(1).add(new G_Node(2, 7));
        adjacent.get(1).add(new G_Node(7, 10));
        adjacent.get(1).add(new G_Node(8, 4));
        adjacent.get(2).add(new G_Node(1, 7));
        adjacent.get(2).add(new G_Node(3, 6));
        adjacent.get(2).add(new G_Node(5, 2));
        adjacent.get(2).add(new G_Node(8, 1));
        adjacent.get(3).add(new G_Node(2, 6));
        adjacent.get(3).add(new G_Node(4, 8));
        adjacent.get(3).add(new G_Node(5, 13));
        adjacent.get(3).add(new G_Node(8, 3));
        adjacent.get(4).add(new G_Node(3, 8));
        adjacent.get(4).add(new G_Node(5, 9));
        adjacent.get(5).add(new G_Node(2, 2));
        adjacent.get(5).add(new G_Node(3, 13));
        adjacent.get(5).add(new G_Node(4, 9));
        adjacent.get(5).add(new G_Node(6, 4));
        adjacent.get(5).add(new G_Node(8, 5));
        adjacent.get(6).add(new G_Node(5, 4));
        adjacent.get(6).add(new G_Node(7, 2));
        adjacent.get(6).add(new G_Node(8, 5));
        adjacent.get(7).add(new G_Node(0, 7));
        adjacent.get(7).add(new G_Node(1, 10));
        adjacent.get(7).add(new G_Node(6, 2));
        adjacent.get(7).add(new G_Node(8, 6));
        adjacent.get(8).add(new G_Node(1, 4));
        adjacent.get(8).add(new G_Node(2, 1));
        adjacent.get(8).add(new G_Node(3, 3));
        adjacent.get(8).add(new G_Node(5, 5));
        adjacent.get(8).add(new G_Node(6, 5));
        adjacent.get(8).add(new G_Node(7, 6));


        Dijkstra obj = new Dijkstra(totalNodes); //building the graph
        obj.dijkstra(adjacent, s); //assigning the adjacency list to the graph

        System.out.println("The shortest path from the node :"); //prints the shortest path from source to destination

        for (int j = 0; j < obj.distance.length; j++) {
            System.out.println(s + " to " + j + " is " + obj.distance[j]);
        }

        //Graph edge case 2 when there is no path from the source to the destination node.

        List<List<G_Node>> adjacent_edge_case2 = new ArrayList<List<G_Node>>(); //adjacency list for node/edge relationships

        for (int i = 0; i < totalNodes; i++) { //each node has its own adjacency list
            List<G_Node> itm = new ArrayList<G_Node>();
            adjacent_edge_case2.add(itm);
        }

        adjacent_edge_case2.get(0).add(new G_Node(1, 3)); //builds some of the same edge relationships as
        //the general method above
        adjacent_edge_case2.get(0).add(new G_Node(1, 3)); //building the adjacency list by adding nodes, edges, and weights
        adjacent_edge_case2.get(0).add(new G_Node(4, 7)); // Example: .get(0) means to travel from 0 to 1 at a cost of 3.
        adjacent_edge_case2.get(1).add(new G_Node(4, 3)); //Changed the example so many nodes only point to node 4.
        adjacent_edge_case2.get(1).add(new G_Node(4, 7)); //Now there is no path from 0 to 2, 3, 5, 6, 7, or 8.
        adjacent_edge_case2.get(1).add(new G_Node(4, 10));
        adjacent_edge_case2.get(1).add(new G_Node(4, 4));
        adjacent_edge_case2.get(2).add(new G_Node(4, 7));
        adjacent_edge_case2.get(2).add(new G_Node(4, 6));
        adjacent_edge_case2.get(2).add(new G_Node(4, 2));
        adjacent_edge_case2.get(2).add(new G_Node(4, 1));
        adjacent_edge_case2.get(3).add(new G_Node(4, 6));
        adjacent_edge_case2.get(3).add(new G_Node(4, 8));
        adjacent_edge_case2.get(3).add(new G_Node(4, 13));
        adjacent_edge_case2.get(3).add(new G_Node(4, 3));
        adjacent_edge_case2.get(4).add(new G_Node(4, 8));
        adjacent_edge_case2.get(4).add(new G_Node(4, 9));
        adjacent_edge_case2.get(5).add(new G_Node(4, 2));
        adjacent_edge_case2.get(5).add(new G_Node(4, 13));
        adjacent_edge_case2.get(5).add(new G_Node(4, 9));
        adjacent_edge_case2.get(5).add(new G_Node(4, 4));
        adjacent_edge_case2.get(5).add(new G_Node(4, 5));
        adjacent_edge_case2.get(6).add(new G_Node(4, 4));
        adjacent_edge_case2.get(6).add(new G_Node(4, 2));
        adjacent_edge_case2.get(6).add(new G_Node(4, 5));
        adjacent_edge_case2.get(7).add(new G_Node(4, 7));
        adjacent_edge_case2.get(7).add(new G_Node(4, 10));
        adjacent_edge_case2.get(7).add(new G_Node(4, 2));
        adjacent_edge_case2.get(7).add(new G_Node(4, 6));
        adjacent_edge_case2.get(8).add(new G_Node(4, 4));
        adjacent_edge_case2.get(8).add(new G_Node(4, 1));
        adjacent_edge_case2.get(8).add(new G_Node(4, 3));
        adjacent_edge_case2.get(8).add(new G_Node(4, 5));
        adjacent_edge_case2.get(8).add(new G_Node(4, 5));
        adjacent_edge_case2.get(8).add(new G_Node(4, 6));

        Dijkstra obj2 = new Dijkstra(totalNodes);
        obj2.dijkstra(adjacent_edge_case2,s);

        System.out.println("The following is Edge Case 2");

        System.out.println("The shortest path from the node :"); //prints the shortest path from source to destination

        for (int j = 0; j < obj2.distance.length; j++) {
            System.out.println(s + " to " + j + " is " + obj2.distance[j]);
        }
    }

}
class G_Node implements Comparator<G_Node> { //comparator interface for comparing nodes

    public int n; //each node has integer n and associated price
    public int price;

    public G_Node() { //node constructor
    }

    public G_Node(int n, int price) { //node constructor with parameters
        this.n = n;
        this.price = price;
    }

    public int compare(G_Node n1, G_Node n2) { //to compare two nodes

        if (n1.price < n2.price) { //compares the weights between nodes
            return -1;
        }
        if (n1.price > n2.price) {
            return 1;
        }
        return 0;
    }
}










