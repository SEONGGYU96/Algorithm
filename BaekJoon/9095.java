import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[][] dist = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            dist[i][i] = true;
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            dist[a][b] = true;
        }
        print(dist);

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] && dist[k][j]) {
                        dist[i][j] = true;
                    }
                }
            }
        }

        print(dist);

        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (!dist[i][j]) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    private static void print(boolean[][] dist) {
        for (int i = 0; i < dist.length; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }
        System.out.println();
    }
}