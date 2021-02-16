import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] parent;
    private static int[] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> minCost = new HashMap<>();

        parent = new int[N];
        cost = new int[N];
        Arrays.fill(parent, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;
            merge(v, w);
        }

        for (int i = 0; i < N; i++) {
            int root = find(i);
            if (minCost.containsKey(root)) {
                minCost.put(root, Math.min(minCost.get(root), cost[i]));
            } else {
                minCost.put(root, cost[i]);
            }
        }

        int sum = minCost.values().stream().mapToInt(Integer::intValue).sum();

        System.out.println(sum <= k ? sum : "Oh no");
    }

    private static int find(int n) {
        if (parent[n] < 0) {
            return n;
        }
        parent[n] = find(parent[n]);
        return parent[n];
    }

    private static void merge(int a, int b) {
        int parentOfA = find(a);
        int parentOfB = find(b);
        if (parentOfA == parentOfB) {
            return;
        }
        parent[parentOfB] = parentOfA;
    }
}

