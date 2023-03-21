import java.util.*;
import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class TimeComplexityDFS {

    public static void main(String[] args) {
        @SuppressWarnings("unused")
		int numGraphs = 100; // number of graphs to create
        int stepSize = 10; // increase n by 10 for each new graph
        int maxVertices = 1000; // maximum number of vertices

        // Create XChart objects for graphing the results
        XYChart chart = new XYChartBuilder().title("DFS Time Complexity").xAxisTitle("Number of Vertices").yAxisTitle("Time (ms)").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line).setLegendPosition(LegendPosition.InsideNE);
        chart.getStyler().setMarkerSize(4);
        chart.getStyler().setPlotMargin(0);

        // Add a series for the expected O(V+E) time complexity
        double[] n = new double[maxVertices / stepSize];
        double[] oVE = new double[maxVertices / stepSize];
        for (int i = 0; i < n.length; i++) {
            n[i] = (i + 1) * stepSize;
            oVE[i] = n[i] + 2 * (i + 1) * stepSize;
        }
        chart.addSeries("Expected O(V+E)", n, oVE);

        // Display the chart
        new SwingWrapper<XYChart>(chart).displayChart();
    }

    // Method to create a random graph with n vertices and 2n edges
    static Graph createRandomGraph(int n) {
        Graph g = new Graph(n);
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                int neighbor = rand.nextInt(n);
                while (neighbor == i) {
                    neighbor = rand.nextInt(n);
                }
                g.addEdge(i, neighbor);
            }
        }
        return g;
    }
}