import java.io.*;
import java.util.*;

public class Main {

    private static int[] populations;
    private static List<Integer>[] villages;
    private static int[][] dp;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        populations = new int[n];
        isVisited = new boolean[n];
        villages = new ArrayList[n];
        int[] parentCount = new int[n];
        int root = 0;
        dp = new int[n][];

        for (int i = 0; i < n; i++) {
            dp[i] = new int[2];
        }

        for (int i = 0; i < n; i++) {
            villages[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            populations[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            villages[start].add(end);
            villages[end].add(start);
            parentCount[start]++;
            parentCount[end]++;
        }

        for (int i = 0; i < n; i++) {
            if (parentCount[i] == 1) {
                root = i;
                break;
            }
        }
        isVisited[root] = true;
        System.out.println(Math.max(solution(root, true), solution(root, false)));
    }

    private static int solution(int currentVillages, boolean isBest) {
        if (dp[currentVillages][isBest ? 1 : 0] != 0) {
            return dp[currentVillages][isBest ? 1 : 0];
        }
        int population = isBest ? populations[currentVillages] : 0;

        for (int village : villages[currentVillages]) {
            if (isVisited[village]) {
                continue;
            }
            isVisited[village] = true;
            population += isBest ? solution(village, false)
                    : Math.max(solution(village, true), solution(village, false));
            isVisited[village] = false;
        }
        dp[currentVillages][isBest ? 1 : 0] = population;
        return population;
    }
}

