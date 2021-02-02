import java.io.*;
import java.util.*;

public class Main {

    private static int[] rootOrder;
    private static int[] inorder;
    private static int MAX = 1001;
    private static Node tree;
    private static int n;
    private static StringBuilder answer = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());

            rootOrder = new int[n + 1];
            inorder = new int[n];
            int root;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int element = Integer.parseInt(st.nextToken());
                if (i == 0) {
                    root = element;
                }
                rootOrder[element] = i;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }

            solution(0, n, null, false);

            postOrder(tree);
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static void postOrder(Node parent) {
        if (parent.leftChild != null) {
            postOrder(parent.leftChild);
        }
        if (parent.rightChild != null) {
            postOrder(parent.rightChild);
        }
        answer.append(parent.value).append(" ");
    }

    private static void solution(int start, int end, Node parent, boolean isLeftChild) {
        if (start >= n || start == end) {
            return;
        }
        int minOrder = MAX;
        int root = -1;
        int rootIndex = -1;
        for (int i = start; i < end; i++) {
            int temp = rootOrder[inorder[i]];
            if (minOrder > temp) {
                minOrder = temp;
                root = inorder[i];
                rootIndex = i;
            }
        }
        Node currentNode = new Node(root);
        if (parent == null) {
            tree = currentNode;
        } else {
            if (isLeftChild) {
                parent.leftChild = currentNode;
            } else {
                parent.rightChild = currentNode;
            }
        }
        solution(start, rootIndex, currentNode, true);
        solution(rootIndex + 1, end, currentNode, false);
    }

    private static class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }
    }
}
