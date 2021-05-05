import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];

        Node[] tree = new Node[nodeinfo.length];
        int maxX = 0;

        for (int i = 0; i < tree.length; i++) {
            int[] node = nodeinfo[i];
            tree[i] = new Node(node[0], node[1], i + 1);
            maxX = Math.max(maxX, node[0]);
        }

        Arrays.sort(tree);

        tree[0].leftChild = findSubTree(tree, -1, tree[0].x, tree[0].y);
        tree[0].rightChild = findSubTree(tree, tree[0].x, maxX + 1, tree[0].y);


        preorder(tree[0], new AtomicInteger(0), answer[0]);
        postorder(tree[0], new AtomicInteger(0), answer[1]);

        return answer;
    }

    private void preorder(Node parent, AtomicInteger depth, int[] result) {
        if (parent == null) {
            return;
        }
        result[depth.intValue()] = parent.value;
        depth.addAndGet(1);
        preorder(parent.leftChild, depth, result);
        preorder(parent.rightChild, depth, result);
    }

    private void postorder(Node parent, AtomicInteger depth, int[] result) {
        if (parent == null) {
            return;
        }
        postorder(parent.leftChild, depth, result);
        postorder(parent.rightChild, depth, result);
        result[depth.intValue()] = parent.value;
        depth.addAndGet(1);
    }

    private Node findSubTree(Node[] tree, int start, int end, int base) {
        for (Node node : tree) {
            if (node.y >= base) {
                continue;
            }
            if (start < node.x && node.x < end) {
                node.leftChild = findSubTree(tree, start, node.x, node.y);
                node.rightChild = findSubTree(tree, node.x, end, node.y);
                return node;
            }
        }
        return null;
    }

    private static class Node implements Comparable<Node> {
        private final int x;
        private final int y;
        private final int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }

        @Override
        public int compareTo(Node o) {
            if (y > o.y) {
                return -1;
            } else if (y < o.y) {
                return 1;
            } else {
                return Integer.compare(x, o.x);
            }
        }
    }
}
