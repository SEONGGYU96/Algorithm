import java.io.*;
import java.util.*;

public class Main {

    private static List<Integer>[] peoples;
    private static int[][] dp;
    private static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        isVisited = new boolean[n];
        peoples = new ArrayList[n];
        int[] parentCount = new int[n];
        int root = 0;
        dp = new int[n][];

        for (int i = 0; i < n; i++) {
            dp[i] = new int[2];
        }

        for (int i = 0; i < n; i++) {
            peoples[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            peoples[start].add(end);
            peoples[end].add(start);
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
        System.out.println(Math.min(solution(root, true), solution(root, false)));
    }

    private static int solution(int currentPeople, boolean isEarlyAdaptor) {
        if (dp[currentPeople][isEarlyAdaptor ? 1 : 0] != 0) {
            return dp[currentPeople][isEarlyAdaptor ? 1 : 0];
        }
        int numberOfEarlyAdaptor = isEarlyAdaptor ? 1 : 0;

        for (int people : peoples[currentPeople]) {
            if (isVisited[people]) {
                continue;
            }
            isVisited[people] = true;
            if (isEarlyAdaptor) {
                numberOfEarlyAdaptor += Math.min(solution(people, true), solution(people, false));
            } else {
                numberOfEarlyAdaptor += solution(people, true);
            }
            isVisited[people] = false;
        }
        dp[currentPeople][isEarlyAdaptor ? 1 : 0] = numberOfEarlyAdaptor;
        return numberOfEarlyAdaptor;
    }
}

