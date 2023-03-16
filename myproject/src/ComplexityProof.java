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
    public static void buildMaxHeap(int[] A) {
        for (int i = A.length / 2; i >= 1; i--) {
            System.out.println("building heap at node = " +i);
            maxHeapify(A, i, A.length);
        }
        //print(A);
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
            print(A);
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

    public static int[] generateRandomArray(int n){
        Random rand = new Random();
        int largest = rand.nextInt(100,1000);
        int heapsize =  n;
        int[] A = new int[heapsize];
        for (int i = 0; i < heapsize; i++){
            A[i] = rand.nextInt(0,largest-1);

        }
        return A;

    }


    public static void increaseKey(int[] A, int i, int key){
        if (key < A[i]){
            return;
        }
        A[i] = key;
        while (i>1 && A[i] > A[(i/2)]){
            //exchange A[i] with parent
            int temp = A[i/2];
            //System.out.println("temp is now " + temp);
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

    // generates a random nonzero integer in range (-100, 100)


    // runs the Bellman-Ford algoritm on a random graph
    // returns the time of execution in microseconds

    public static double runInsertion(int n) {

        int[] A  =generateRandomArray(n);
        buildMaxHeap(A);
        Random rand = new Random();
        int key = rand.nextInt(1500);

        double start = System.nanoTime();
        insert(A,key);
        double end = System.nanoTime();

        // execution time in microseconds
        double duration = (end - start) / 1000;
        return duration;
    }


    // runs the Bellman-Ford algorithm on a series of samples and outputs a chart of the runtime
    public static void main(String[] args) {
        // number of sample executions
        int samples = 100;

        double[] execution_times = new double[samples];
        double[] ns = new double[samples];
        int n = 10;
        for (int i=0; i<samples; i++) {
            // run bellman ford on a random graph with n vertices and 2n edges
            execution_times[i] = runInsertion(n);
            ns[i] += n;
            n+=1;

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
