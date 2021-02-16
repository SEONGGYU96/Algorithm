import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                boolean isConnected = st.nextToken().equals("1");
                if (isConnected) {
                    merge(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int root = -1;
        boolean isPossible = true;
        for (int i = 0; i < m; i++) {
            int city = Integer.parseInt(st.nextToken()) - 1;
            if (root == -1) {
                root = find(city);
            } else {
                if (root != find(city)) {
                    isPossible = false;
                    break;
                }
            }
        }
        System.out.println(isPossible ? "YES" : "NO");
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

