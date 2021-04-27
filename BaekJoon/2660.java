import java.io.*;
import java.util.*;

class Main {

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final int max = 100;
        
        int[][] weights = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(weights[i], max);
            weights[i][i] = 0;
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (a < 0) {
                break;
            }
            weights[a][b] = 1;
            weights[b][a] = 1;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    weights[i][j] = Math.min(weights[i][j], weights[i][k] + weights[k][j]);
                }
            }
        }

        int min = max;
        ArrayList<Integer> candidates = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int localMax = 0;
            for (int j = 0; j < N; j++) {
                localMax = Math.max(localMax, weights[i][j]);
            }
            if (localMax <= min) {
                if (localMax < min) {
                    min = localMax;
                    candidates.clear();
                }
                candidates.add(i + 1);
            }
        }

        Collections.sort(candidates);

        System.out.printf("%d %d\n", min, candidates.size());
        for (int candidate : candidates) {
            System.out.print(candidate + " ");
        }
    }
}



