package AVLproof.proof1;
import java.util.Random;

//The following is code to generate the data for the graph (written by me)


public class ProofAVL {

    public double[][] create_graph(ConstructAVLTree tree){

        Random rand = new Random();
        double[] num_nodes_in_tree = new double[500]; //x axis for graph, number of nodes in tree
        double[] num_steps = new double[500]; //y axis for graph, number of steps taken by search
        double[][] nodes_and_search_times = new double[2][1]; //creates an array to store the run time of the searches
        int counter = 0;

        for (int i = 3; i < 500; i++) {
            //generates trees with nodes ranging from 3 to 500
            tree = new ConstructAVLTree(); //new tree every time
            int number_of_nodes = i;
            for (int j = 0; j < number_of_nodes; j++) {
                //inserts elements into the tree
                int random_element = rand.nextInt(1,5000); //adds random numbers to the tree from 1 to 5000
                tree.insertElement(random_element);
            }
            num_nodes_in_tree[counter] = i; //keeps track of nodes added to the tree
            int random_search_number = rand.nextInt(1,5000); //searches for a random node in the tree
            tree.searchElement(random_search_number); //performs the search
            int how_many_steps = tree.steps; //gets the number of steps the search took for each new tree
            num_steps[counter] = how_many_steps; //builds the data array for steps
            counter ++; //counter for building the data arrays

        }
        nodes_and_search_times[0] = num_nodes_in_tree; //number of nodes in the tree x axis
        nodes_and_search_times[1] = num_steps; //number of steps y axis

        return nodes_and_search_times; //returns a 2D array with both data arrays (number of nodes and steps)

    }


    public static void main(String[] args) {

        ConstructAVLTree my_tree = new ConstructAVLTree(); //Constructs an initial AVL tree object
        ProofAVL my_proof = new ProofAVL();

        double[][] result_times = my_proof.create_graph(my_tree); //calls the function to generate the data arrays

        for (int i = 0; i < result_times[0].length; i++) { //prints the array data for copy/paste into excel
            System.out.println(result_times[0][i]); //printing number of nodes in each of the 500 trees
        }
        System.out.println("----------------------------------------------------------------");
        for (int i = 0; i < result_times[1].length; i++) {
            System.out.println(result_times[1][i]);//printing the number of steps taken to execute a search on each tree
        }

    }




}
