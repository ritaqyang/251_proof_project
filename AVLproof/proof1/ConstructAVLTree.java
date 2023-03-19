package AVLproof.proof1;


public class ConstructAVLTree {

    //class for constructing AVL tree
    //the following code was taken from:https://www.javatpoint.com/avl-tree-program-in-java. By JavaTpoint 2021
    //Comments and code indicated with --- is mine
    private Node rootNode; //private node class field
    public int steps = 0;
    boolean insert_success = true;

    public ConstructAVLTree() //sets root node to null (constructor)
    {
        rootNode = null; //begins the tree with the root node
    }
    public void insertElement(int element) //recursively builds the AVL tree
    {
        rootNode = insertElement(element, rootNode); //recursive call to construct the tree node by node
        //calls the overloaded insertElement function
    }
    private int getHeight(Node node )
    {
        return node == null ? -1 : node.h; //returns the height of the tree. -1 if the tree is empty, otherwise
        //uses the node's height field to return the height
    }
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) //
    {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
        //compares the height of the left node and the height of the right node
        //returns the int value of the greater height, the max height
        //important for rotations to maintain AVL tree properties
    }
    private Node insertElement(int element, Node node) //recursive insert element function to build the tree
    {
        if (node == null) //first call node is null so a new node is created and added to the tree
            node = new Node(element);
        //if the node is less than the root node, left subtree is built and the node is added to the left
        else if (element < node.element)
        {
            node.leftChild = insertElement( element, node.leftChild ); //node is assigned to the left child
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 ) //AVL property is broken and so
                //a rotation must be done to restore the AVL properties
                if( element < node.leftChild.element ) //calls to rotate the left child (no children)
                    node = rotateWithLeftChild( node );
                else
                    node = doubleWithLeftChild( node ); //rotates left child with children
        }
        else if( element > node.element )
        {
            node.rightChild = insertElement( element, node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 ) //the same as above, however
                //rotating the right tree
                if( element > node.rightChild.element)
                    node = rotateWithRightChild( node );
                else
                    node = doubleWithRightChild( node );
        }
        else{ //node is already in the tree
            this.insert_success = false;

        }
        node.h = getMaxHeight( getHeight( node.leftChild ), getHeight( node.rightChild ) ) + 1; //updates the node
        //height
        return node;
    }
    // rotate the left tree to maintain AVL properties
    private Node rotateWithLeftChild(Node node2)
    {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild; //reassign children
        node1.rightChild = node2;
        node2.h = getMaxHeight( getHeight( node2.leftChild ), getHeight( node2.rightChild ) ) + 1; //update heights
        node1.h = getMaxHeight( getHeight( node1.leftChild ), node2.h ) + 1;
        return node1;
    }
    // rotate the right tree to maintain AVL properties
    private Node rotateWithRightChild(Node node1)
    {
        Node node2 = node1.rightChild; //same as above: reassign children and update heights
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), getHeight( node1.rightChild ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.rightChild ), node1.h ) + 1;
        return node2;
    }
    //Rotation methods for case where the node to rotate has children
    private Node doubleWithLeftChild(Node node3)
    {
        node3.leftChild = rotateWithRightChild( node3.leftChild ); //rotate the child
        return rotateWithLeftChild( node3 );
    }
    //Same as above but for the right tree
    private Node doubleWithRightChild(Node node1)
    {
        node1.rightChild = rotateWithLeftChild( node1.rightChild );
        return rotateWithRightChild( node1 );
    }

    //AVL search function
    public boolean searchElement(int element) //returns boolean: node found or not
    {
        return searchElement(rootNode, element); //calls the recursive search function
    }
    private boolean searchElement(Node head, int element)
    {
        int step_counter = 1; //step counter for graph ---
        boolean check = false; //boolean to break the loop
        while ((head != null) && !check) //starts at given node and advances
        {
            int headElement = head.element;
            if (element < headElement) { //searches the left tree if element in question is smaller than head.element
                head = head.leftChild;
            }//updates the head to advance down the tree
            else if (element > headElement) { //searches the right tree if element in question is larger than head.element
                head = head.rightChild; //updates the head to advance
            }
            else
            {
                check = true; //has found the element so loop is broken
                //step_counter += 1;
                break;
            }
            check = searchElement(head, element); //recursively calls the function until loop breaks or height of tree
            //is searched
            step_counter += 1; //step counter increased with each recursive call to search ---
        }
        this.steps = step_counter; //assigns the number of steps to the step counter for the tree ---
        return check;
    }

}
