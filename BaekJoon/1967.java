import java.io.*;
import java.util.*;

public class Main {

    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node[] tree = new Node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new Node(i);
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()) - 1;
            int child = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            tree[parent].edges.add(new Edge(weight, tree[child]));
            tree[child].edges.add(new Edge(weight, tree[parent]));
        }

        isVisited = new boolean[n];
        Node deepestNodeFromRoot = findDeepestNode(tree[0]).node;
        isVisited = new boolean[n];
        System.out.println(findDeepestNode(deepestNodeFromRoot).maxWeight);
    }

    private static DeepestNode findDeepestNode(Node parent) {
        isVisited[parent.value] = true;

        DeepestNode tempDeepestNode = new DeepestNode(null, 0);

        for (Edge edge : parent.edges) {
            if (isVisited[edge.target.value]) {
                continue;
            }
            DeepestNode deepestNode;
            if (edge.deepestNode != null) {
                deepestNode = edge.deepestNode;
            } else {
                deepestNode = findDeepestNode(edge.target);
                deepestNode.maxWeight += edge.weight;
                edge.deepestNode = deepestNode;
            }
            if (tempDeepestNode.maxWeight < deepestNode.maxWeight) {
                tempDeepestNode = deepestNode;
            }
        }
        return tempDeepestNode.node == null ? new DeepestNode(parent, 0) : tempDeepestNode;
    }

    private static class DeepestNode {
        Node node;
        int maxWeight;

        public DeepestNode(Node node, int maxWeight) {
            this.node = node;
            this.maxWeight = maxWeight;
        }
    }

    private static class Node {
        int value;
        ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }
    }

    private static class Edge {
        int weight;
        Node target;
        DeepestNode deepestNode;

        public Edge(int weight, Node target) {
            this.weight = weight;
            this.target = target;
        }
    }
}

