import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        parent = new int[N];
        Arrays.fill(parent, -1);
        Stack<int[]> disconnection = new Stack<>();

        int[][] connection = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                connection[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

        for (int i = 0; i < Q; i++) {
            int target = Integer.parseInt(br.readLine()) - 1;
            disconnection.push(new int[]{connection[target][0], connection[target][1]});
            connection[target][0] = -1;
        }

        for (int[] connect : connection) {
            if (connect[0] != -1) {
                merge(connect[0], connect[1]);
            }
        }

        long answer = 0;

        while (!disconnection.isEmpty()) {
            int[] disconnect = disconnection.pop();
            int parentOfStart = find(disconnect[0]);
            int parentOfEnd = find(disconnect[1]);
            if (parentOfStart != parentOfEnd) {
                answer += parent[parentOfStart] * parent[parentOfEnd];
            }
            merge(disconnect[0], disconnect[1]);
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
        long sumOfFlavorOfN = Math.abs(parent[parentOfN]);
        long sumOfFlavorOfM = Math.abs(parent[parentOfM]);
        if (sumOfFlavorOfN >= sumOfFlavorOfM) {
            parent[parentOfN] += parent[parentOfM];
            parent[parentOfM] = parentOfN;
        } else {
            parent[parentOfM] += parent[parentOfN];
            parent[parentOfN] = parentOfM;
        }
    }

}

