public class Node {
    //node class for the AVL tree
    //the following code was taken from: https://www.javatpoint.com/avl-tree-program-in-java. Developed by JavaTpoint
    //2021
    int element;
    int h;  //represents the height of a node in the AVL tree
    Node leftChild; //left and right child fields to build the subtrees
    Node rightChild;

    public Node() //node constructor
    {
        leftChild = null;
        rightChild = null;
        element = 0;
        h = 0;
    }
    public Node(int element) //custom constructor allowing for parameters
    {
        leftChild = null;
        rightChild = null;
        this.element = element; //assigns the node's element (integer)
        h = 0;
    }








}
