import java.io.*;
import java.util.*;

public class Main {

    private static Node[] tree;
    private static Node root;
    private static int[] callCount;
    private static int N;
    private static int[] maxValueInLevel;
    private static int[] minValueInLevel;
    private static int headOfColOfNodes = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new Node[N];
        callCount = new int[N];
        maxValueInLevel = new int[N + 1];
        minValueInLevel = new int[N + 1];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()) - 1;
            int leftChild = Integer.parseInt(st.nextToken()) - 1;
            int rightChild = Integer.parseInt(st.nextToken()) - 1;
            callCount[parent]++;

            if (tree[parent] == null) {
                tree[parent] = new Node();
            }
            if (leftChild >= 0) {
                if (tree[leftChild] == null) {
                    tree[leftChild] = new Node();
                }
                tree[parent].setLeftChild(tree[leftChild]);
                callCount[leftChild]++;
            }
            if (rightChild >= 0) {
                if (tree[rightChild] == null) {
                    tree[rightChild] = new Node();
                }
                tree[parent].setRightChild(tree[rightChild]);
                callCount[rightChild]++;
            }
        }

        findRoot();

        findMinAndMaxInLevel(root, 1);

        printMaxWidth();
    }

    private static void findMinAndMaxInLevel(Node root, int level) {
        if (root.getLeftChild() != null) {
            findMinAndMaxInLevel(root.getLeftChild(), level + 1);
        }

        maxValueInLevel[level] = Math.max(maxValueInLevel[level], headOfColOfNodes);
        if (minValueInLevel[level] == 0) {
            minValueInLevel[level] = headOfColOfNodes;
        } else {
            minValueInLevel[level] = Math.min(minValueInLevel[level], headOfColOfNodes);
        }
        headOfColOfNodes++;

        if (root.getRightChild() != null) {
            findMinAndMaxInLevel(root.getRightChild(), level + 1);
        }
    }

    private static void findRoot() {
        for (int i = 0; i < callCount.length; i++) {
            if (callCount[i] == 1) {
                root = tree[i];
                return;
            }
        }
    }

    private static void printMaxWidth() {
        int maxWidth = 0;
        int maxLevel = 0;
        for (int i = 1; i <= N; i++) {
            int current = maxValueInLevel[i] - minValueInLevel[i] + 1;
            if (current > maxWidth) {
                maxWidth = current;
                maxLevel = i;
            }
        }
        System.out.println(maxLevel + " " + maxWidth);
    }

    private static class Node {
        private Node leftChild;
        private Node rightChild;

        public Node() {
            this.leftChild = null;
            this.rightChild = null;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }
    }
}

