import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        Arrays.fill(parent, -1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int commend = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (commend == 0) {
                merge(a, b);
            } else {
                answer.append(find(a) == find(b) ? "YES" : "NO").append("\n");
            }
        }
        System.out.println(answer);
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

