package homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphSolver {

    public static class Edge implements Comparable<Edge> {
        public final int u;
        public final int v;
        public final double weight;

        public Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    public final int width;
    public final int height;
    public final int numVertices;
    public final double[] radiation;
    public final double[][] fullAdjacencyMatrix;

    public GraphSolver(int width, int height, double[] radiation) {
        this.width = width;
        this.height = height;
        this.numVertices = width * height;
        this.radiation = radiation;
        this.fullAdjacencyMatrix = new double[numVertices][numVertices];
        buildFullGraph();
    }

    private int getIndex(int x, int y) {
        return y * width + x;
    }

    private void buildFullGraph() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int u = getIndex(x, y);
                
                if (x + 1 < width) {
                    int v = getIndex(x + 1, y);
                    double weight = radiation[u] + radiation[v];
                    fullAdjacencyMatrix[u][v] = weight;
                    fullAdjacencyMatrix[v][u] = weight;
                }
                
                if (y + 1 < height) {
                    int v = getIndex(x, y + 1);
                    double weight = radiation[u] + radiation[v];
                    fullAdjacencyMatrix[u][v] = weight;
                    fullAdjacencyMatrix[v][u] = weight;
                }
            }
        }
    }

    public double[][] findMST() {
        double[][] mstAdjacencyMatrix = new double[numVertices][numVertices];
        boolean[] inMST = new boolean[numVertices];
        double[] minEdgeWeight = new double[numVertices];
        int[] parent = new int[numVertices];
        
        Arrays.fill(minEdgeWeight, Double.MAX_VALUE);
        Arrays.fill(parent, -1);
        
        minEdgeWeight[0] = 0;
        
        for (int i = 0; i < numVertices; i++) {
            int u = -1;
            double minW = Double.MAX_VALUE;
            for (int j = 0; j < numVertices; j++) {
                if (!inMST[j] && minEdgeWeight[j] < minW) {
                    minW = minEdgeWeight[j];
                    u = j;
                }
            }
            
            if (u == -1) break;
            
            inMST[u] = true;
            
            if (parent[u] != -1) {
                double weight = fullAdjacencyMatrix[u][parent[u]];
                mstAdjacencyMatrix[u][parent[u]] = weight;
                mstAdjacencyMatrix[parent[u]][u] = weight;
            }
            
            for (int v = 0; v < numVertices; v++) {
                double weight = fullAdjacencyMatrix[u][v];
                if (weight > 0 && !inMST[v] && weight < minEdgeWeight[v]) {
                    minEdgeWeight[v] = weight;
                    parent[v] = u;
                }
            }
        }
        
        return mstAdjacencyMatrix;
    }

    public List<Integer> runDFS(double[][] mstMatrix, int startNode) {
        List<Integer> visitedOrder = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];
        dfsHelper(mstMatrix, startNode, visited, visitedOrder);
        return visitedOrder;
    }

    private void dfsHelper(double[][] mstMatrix, int u, boolean[] visited, List<Integer> visitedOrder) {
        visited[u] = true;
        visitedOrder.add(u);
        
        for (int v = 0; v < numVertices; v++) {
            if (mstMatrix[u][v] > 0 && !visited[v]) {
                dfsHelper(mstMatrix, v, visited, visitedOrder);
            }
        }
    }
}
