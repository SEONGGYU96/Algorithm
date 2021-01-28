import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<Character, Node> index = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            char parentName = st.nextToken().charAt(0);

            Node parent;
            if (index.containsKey(parentName)) {
                parent = index.get(parentName);
            } else {
                parent = new Node(parentName);
                index.put(parentName, parent);
            }

            for (int j = 0; j < 2; j++) {
                char childName = st.nextToken().charAt(0);
                if (childName == '.') {
                    continue;
                }
                Node child = new Node(childName);
                index.put(child.getName(), child);
                parent.attachChildren(j, child);
            }
        }
        Node parent = index.get('A');

        preorder(parent);
        System.out.println();
        inorder(parent);
        System.out.println();
        postorder(parent);
    }

    private static void preorder(Node parent) {
        System.out.print(parent.name);
        for (int i = 0; i < 2; i++) {
            if (parent.getChild(i) != null) {
                preorder(parent.getChild(i));
            }
        }
    }

    private static void inorder(Node parent) {
        if (parent.getChild(0) != null) {
            inorder(parent.getChild(0));
        }
        System.out.print(parent.name);
        if (parent.getChild(1) != null) {
            inorder(parent.getChild(1));
        }
    }

    private static void postorder(Node parent) {
        for (int i = 0; i < 2; i++) {
            if (parent.getChild(i) != null) {
                postorder(parent.getChild(i));
            }
        }
        System.out.print(parent.name);
    }

    private static class Node {
        private final char name;
        private Node parent;
        private Node[] children;

        public Node(char value) {
            this.name = value;
            children = new Node[2];
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }

        public char getName() {
            return name;
        }

        public Node getChild(int index) {
            return this.children[index];
        }

        public void attachChildren(int index, Node children) {
            this.children[index] = children;
            children.setParent(this);
        }
    }
}

