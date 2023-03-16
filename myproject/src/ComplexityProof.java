import java.io.IOException;
import java.util.*;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import org.knowm.xchart.*;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.style.markers.SeriesMarkers;
public class ComplexityProof {

    // THE FOLLOWING ARE HELPER METHODS TO GENERATE RANDOM INSTANCES OF THE PROBLEM:
    public static void buildMaxHeap(ArrayList<Integer> A) {
        for (int i = A.size() / 2; i >= 1; i--) {
            //System.out.println("building heap at node = " +i);
            maxHeapify(A, i, A.size());
        }
        //print(A);
    }

    public static void maxHeapify(ArrayList<Integer> A, int node, int size) {
        //System.out.println("calling maxHeapify(A," + node + ")");

        int left = 2 * node;
        int right = 2 * node + 1;
        int largest = node;

        //check left child
        if (left <= size){
            if (A.get(left - 1) > A.get(node - 1) ){
                largest = left;
            }
        }
        //then check right child
        if (right <= size){
            if (A.get(right - 1) > A.get(largest - 1)) {
                largest = right;
            }
        }
        //switch the bigger child to the parent node
        if (largest != node) {
            int parent = A.get(node - 1);
            int largechild = A.get(largest-1);
            A.remove(node-1);
            A.add(node-1,largechild);
            A.remove(largest-1);
            A.add(largest-1,parent);
            maxHeapify(A, largest,size); //call again to see if large child has bigger children

        }
    }

    public static void print(int[] A){
        String s = "";
        for (int E : A){
            s += E + ", ";
        }
        System.out.println(s);
    }

    public static ArrayList<Integer> generateArrayList(int n){
        Random rand = new Random();
        int largest = n;
        int heapsize =  n;
        ArrayList<Integer> A = new ArrayList<>();

        for (int i = 0; i < heapsize; i++){
            A.add(i+1);

        }
        return A;

    }


    public static int increaseKey(ArrayList<Integer> A, int i, int key){
        if (key < A.get(i)){
            return 0;
        }
        A.remove(i);
        A.add(key);
        int count = 0;
        while (i>1 && A.get(i) > A.get(i/2)){
            //exchange A[i] with parent
            int temp = A.get(i/2);
            //System.out.println("temp is now " + temp);
            A.remove(i/2);
            A.add((i/2), key);
            A.remove(i);
            A.add(i,temp);
            i = i/2;
            count += 1;
        }
        return count;

    }

    public static void insert(ArrayList<Integer> A, int key){

        A.add(-10000);
        increaseKey(A,A.size()-1,key);

    }

    // generates a random nonzero integer in range (-100, 100)


    // runs the Bellman-Ford algoritm on a random graph
    // returns the time of execution in microseconds

    public static double runInsertion(int n) {

        ArrayList<Integer> A  =generateArrayList(n);
        buildMaxHeap(A);
        Random rand = new Random();

       int key = n+1;

        double start = System.nanoTime();
        insert(A,key);
        double end = System.nanoTime();

        // execution time in microseconds
        double duration = (end - start) / 10000;
        return duration;
    }


    // runs the Bellman-Ford algorithm on a series of samples and outputs a chart of the runtime
    public static void main(String[] args) {
        // number of sample executions

        ArrayList<Integer> array = new ArrayList<>();
        array = generateArrayList(10);
        System.out.println(array);
        buildMaxHeap(array);
        System.out.println(array);

        int samples = 200;

        double[] execution_times = new double[samples];
        double[] ns = new double[samples];
        int n = 10000;
        for (int i=0; i<samples; i++) {
            n = 100 * (i+2);
            // run bellman ford on a random graph with n vertices and 2n edges
            execution_times[i] = runInsertion(n);
            ns[i] = n;


        }

        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of Insertion of a node into a maxHeap", "Heap Size", "Execution Time (us)", "Runtime", ns, execution_times);
        double[] n2s = new double[samples];
        // add reference quadratic
        for (int i=0; i<samples; i++) {
            n2s[i] = (Math.log(ns[i]));
        }
        chart.addSeries("logn", ns, n2s).setMarker(SeriesMarkers.NONE);;
        // display chart
        //new SwingWrapper<>(chart).displayChart();

        // save chart
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapFormat.PNG, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
