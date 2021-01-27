import java.io.*;
import java.util.*;

public class Main {
    private static int[][] pSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] field = new int[1001][1001];
        pSum = new int[N + 1][N + 1];
        int[][] cows = new int[N][2];
        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();
        HashMap<Integer, Integer> xMap = new HashMap<>();
        HashMap<Integer, Integer> yMap = new HashMap<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
            xSet.add(cows[i][0]);
            ySet.add(cows[i][1]);
        }
        int X = 0;
        int Y = 0;

        for (int x : xSet) {
            xMap.put(x, X++);
        }

        for (int y : ySet) {
            yMap.put(y, Y++);
        }

        for (int i = 0; i < N; i++) {
            field[yMap.get(cows[i][1])][xMap.get(cows[i][0])]++;
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                pSum[i + 1][j + 1] = pSum[i + 1][j] + pSum[i][j + 1] - pSum[i][j] + field[i][j];
            }
        }

        int result = N;

        for (int i = 0; i <= X; i++) {
            for (int j = 0; j <= Y; j++) {
                int temp = Math.max(sum(0, 0, i, j), sum(i, j, Y, X));
                temp = Math.max(temp, sum(0, j, i, X));
                temp = Math.max(temp, sum(i, 0, Y, j));

                result = Math.min(result, temp);
            }
        }
        System.out.println(result);
    }

    private static int sum(int startY, int startX, int endY, int endX) {
        return pSum[endY][endX] - pSum[endY][startX] - pSum[startY][endX] + pSum[startY][startX];
    }
}

