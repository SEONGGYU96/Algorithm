import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] parent;
    private static int[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T > 0) {
            int N = Integer.parseInt(br.readLine());
            parent = new int[N];
            distances = new int[N];
            Arrays.fill(parent, -1);

            StringTokenizer st;
            String nextLine = br.readLine();
            while (!nextLine.equals("O")) {
                st = new StringTokenizer(nextLine);
                char command = st.nextToken().charAt(0);
                int args1 = Integer.parseInt(st.nextToken()) - 1;
                if (command == 'I') {
                    int args2 = Integer.parseInt(st.nextToken()) - 1;
                    connect(args1, args2);
                } else {
                    answer.append(getDistanceFromCenter(args1)).append("\n");
                }
                nextLine = br.readLine();
            }
            T--;
        }
        System.out.println(answer);
    }

    private static int getDistanceFromCenter(int i) {
        find(i);
        return distances[i];
    }

    private static void connect(int i, int j) {
        merge(i, j);
    }

    private static int find(int n) {
        if (parent[n] < 0) {
            return n;
        }
        int temp = find(parent[n]);
        distances[n] += distances[parent[n]];
        parent[n] = temp;
        return parent[n];
    }

    private static void merge(int n, int m) {
        distances[n] = Math.abs(n - m) % 1000;
        parent[n] = m;
    }
}

