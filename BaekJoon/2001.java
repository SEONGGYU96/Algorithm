import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] jewelries;
    private static ArrayList<ArrayList<Bridge>> bridges;
    private static boolean[][] isVisited;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        jewelries = new int[n + 1];
        Arrays.fill(jewelries, -1);

        int jewelryNumber = 0;
        for (int i = 0; i < k; i++) {
            jewelries[Integer.parseInt(br.readLine())] = jewelryNumber++;
        }

        bridges = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            bridges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            bridges.get(start).add(new Bridge(end, weight));
            bridges.get(end).add(new Bridge(start, weight));
        }
        bridges.get(1).add(new Bridge(1, 101));
        isVisited = new boolean[n + 1][1 << k];
        solution(1, 0, 0);
        System.out.println(answer);
    }

    private static void solution(int island, int jewelry, int count) {
        if (isVisited[island][jewelry]) {
            return;
        }

        if (island == 1 && answer < count) {
            answer = count;
        }
        isVisited[island][jewelry] = true;

        for (Bridge bridge : bridges.get(island)) {
            if (bridge.maxWeight < count) {
                continue;
            }
            solution(bridge.target, jewelry, count);
            if (jewelries[island] != -1) {
                if (bridge.maxWeight >= count + 1) {
                    solution(bridge.target, jewelry | (1 << jewelries[island]), count + 1);
                }
            }
        }
    }

    static class Bridge {
        int target;
        int maxWeight;

        public Bridge(int target, int maxWeight) {
            this.target = target;
            this.maxWeight = maxWeight;
        }
    }
}

