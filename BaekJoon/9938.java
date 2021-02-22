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

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        parent = new int[L];
        boolean[] isUsing = new boolean[L];
        Arrays.fill(parent, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            if (!isUsing[find(a)]) {
                isUsing[find(a)] = true;
                merge(b, a);
                answer.append("LADICA").append("\n");
            } else if (!isUsing[find(b)]) {
                isUsing[find(b)] = true;
                answer.append("LADICA").append("\n");
            } else {
                answer.append("SMECE").append("\n");
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

    private static void merge(int n, int m) {
        int parentOfN = find(n);
        int parentOfM = find(m);
        if (parentOfN == parentOfM) {
            return;
        }
        parent[parentOfM] = parentOfN;
    }
}

