import java.util.*;
class Edge implements Comparable<Edge> {
    int u, v, weight;
    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
    public int compareTo(Edge other) {
        return this.weight - other.weight;
}
}
public class KruskalNetwork {
    static int findParent(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return findParent(parent, parent[i]);
    }
    static void union(int[] parent, int[] rank, int x, int y) {
        int rootX = findParent(parent, x);
        int rootY = findParent(parent, y);
        if (rank[rootX] < rank[rootY])
            parent[rootX] = rootY;
        else if (rank[rootX] > rank[rootY])
            parent[rootY] = rootX;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        Edge[] edges = new Edge[e];
        System.out.println("Enter edges (u v cost):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges[i] = new Edge(u, v, w);
        }
        Arrays.sort(edges);
        int[] parent = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        int totalCost = 0;
        System.out.println("\nEdges in the Minimum Cost Network:");
        for (Edge edge : edges) {
            int rootU = findParent(parent, edge.u);
            int rootV = findParent(parent, edge.v);
            if (rootU != rootV) {
                System.out.println("Edge: " + edge.u + " - " + edge.v + "  Cost: " + edge.weight);
                totalCost += edge.weight;
                union(parent, rank, rootU, rootV);
            }
        }
        System.out.println("\nTotal Minimum Network Cost = " + totalCost);
    }
}
