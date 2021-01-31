import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] children = new ArrayList[N];
        int[] parents = new int[N];
        int root = 0;

        for (int i = 0; i < children.length; i++) {
            children[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int node = 0; node < N; node++) {
            int parent = Integer.parseInt(st.nextToken());
            parents[node] = parent;
            if (parent == -1) {
                root = node;
                continue;
            }
            children[parent].add(node);
        }

        int remove = Integer.parseInt(br.readLine());

        int parentOfRemove = parents[remove];

        if (parentOfRemove == -1) {
            System.out.println(0);
            return;
        }

        children[parentOfRemove].remove(Integer.valueOf(remove));

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        int answer = 0;

        while (!queue.isEmpty()) {
            int head = queue.poll();
            for (int child : children[head]) {
                if (children[child].isEmpty()) {
                    answer++;
                } else {
                    queue.add(child);
                }
            }
        }

        if (answer == 0) {
            answer++;
        }

        System.out.println(answer);
    }
}

