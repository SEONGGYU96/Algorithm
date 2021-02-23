import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        parent = new int[G];
        boolean[] isUsing = new boolean[G];
        Arrays.fill(parent, -1);

        int answer = 0;
        for (int i = 0; i < P; i++) {
            int range = Integer.parseInt(br.readLine()) - 1;
            int target = find(range);

            if (!isUsing[target]) {
                isUsing[target] = true;
                if (target - 1 >= 0) {
                    merge(target - 1, target);
                }
                answer++;
            } else {
                break;
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

