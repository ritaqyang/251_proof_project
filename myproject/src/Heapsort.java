public class Heapsort{
    public int[] startingArray;
    public int size;
    Heapsort(int[] startingArray) {
        this.size = startingArray.length;
        this.startingArray = startingArray;
    }
    public static void main(String[] args){
        int[] h1 = {16,14,10,8,7,9,3,2,15,1};
        Heapsort heap1 = new Heapsort(h1);
        heap1.sort(h1);
    }
    public static void print(int[] A){
        String s = "";
        for (int E : A){
            s += E + ", ";
        }
        System.out.println(s);
    }
    public static void sort(int[] A) {
        buildMaxHeap(A);
        int size = A.length;
        //iterate (size - 1 ) times
        for (int i = size; i > 1; i--) {
            int lastnode = A[size - 1];int firstnode = A[0];
            A[size - 1] = firstnode; A[0] = lastnode; //store head to last spot in the array
            print(A);
            size -= 1;
            maxHeapify(A, 1,size);
        }
    }
    public static void buildMaxHeap(int[] A) {
        for (int i = A.length / 2; i >= 1; i--) {
            System.out.println("building heap at node = " +i);
            maxHeapify(A, i, A.length);
        }
        print(A);
    }

    public static void maxHeapify(int[] A, int node, int size) {
        System.out.println("calling maxHeapify(A," + node + ")");

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
            print(A);
            maxHeapify(A, largest,size); //call again to see if large child has bigger children

        }
    }
}
