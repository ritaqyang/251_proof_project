public class Heapsort{

    public static void main(String[] args){

        int[]edge1= {16,16,15,15,14,10,8,8,7,9,3,2,4,1,1,1};
        int[]edge2 = {16,16,16,16,16,16,16,16,16,8};
        int[]general = {16,14,10,8,7,9,3,2,4,1};
        heapSort(edge2);


    }
    public static void print(int[] A){
        String s = "";
        for (int E : A){
            s += E + " ";
        }

        System.out.println(s);
    }
    public static void heapSort(int[] A) {
        buildMaxHeap(A); //first build a max heap
        int size = A.length;
        //iterate (size - 1 ) times
        for (int i = size; i >= 2; i--) {
            int i2 = i + 1;
            if (i == A.length) {
                System.out.println("At initiation, maxheap is A[1..." + i + "] and the second subarray is empty.");
            } else if (i == A.length - 1) {
                System.out.println("At beginning of an interation during maintainence, " +
                        "maxheap is A[1..." + i + "] and the second subarray is A[" + i2 + "].");
            } else if (i == 2) {
                System.out.println("At termination, before execution, maxheap is now of size 2");
            } else {
                System.out.println("At beginning of an interation during maintainence, " +
                        "maxheap is A[1..." + i + "] and the second subarray is A[" + i2 + "..." + A.length + "].");
            }

            print(A);
            int lastnode = A[size - 1];
            int firstnode = A[0];
            A[size - 1] = firstnode;
            A[0] = lastnode; //store head to last spot in the array
            size -= 1;
            maxHeapify(A, 1, size); //call on the new root
            if (i == 2) {
                System.out.println("At termination, after execution, the array is now sorted.");
                print(A);
            }
        }
    }
    public static void buildMaxHeap(int[] A) {
        for (int i = A.length / 2; i >= 1; i--) {
            maxHeapify(A, i, A.length);
        }
    }

    public static void maxHeapify(int[] A, int node, int size) {
        //System.out.println("calling maxHeapify(A," + node + ")");

        int left = 2 * node;
        int right = 2 * node + 1;
        int largest = node;

        //check left child
        if (left <= size){
            if (A[left - 1] > A[node - 1]) {
                largest = left;
            }
        }
        //then check right child
        if (right <= size){
            if (A[right - 1] > A[largest - 1]) {
                largest = right;
            }
        }
        //switch the bigger child to the parent node
        if (largest != node) {
            int parent = A[node - 1]; int largechild = A[largest-1];
            A[node - 1] = largechild; A[largest - 1] = parent;
            maxHeapify(A, largest,size); //call again to see if large child has bigger children

        }
    }
    public static void increaseKey(int[] A, int i, int key){
        if (key < A[i]){
            return;
        }
        A[i] = key;
        while (i>1 && A[i] > A[(i/2)]){
            //exchange A[i] with parent
            int temp = A[i/2];
            System.out.println("temp is now " + temp);
            A[i/2] = key;
            A[i] = temp;
            i = i/2;
        }

    }

    public static int[] insert(int[] A, int key){
        int[] expandedA  = new int[A.length+1];

        for(int i =0; i < A.length; i++) {
            expandedA[i] = A[i];
        }
        expandedA[A.length] = -1000;
        increaseKey(expandedA,expandedA.length-1,key);
        return expandedA;

    }
}
