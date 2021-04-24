import java.io.*;
import java.util.*;

class Main {

    private static int[][] ways;
    private static int N;
    private static int D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ways = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                ways[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(ways, Comparator.comparingInt(o -> o[0]));
        
        System.out.println(getMinDistance(0));
    }

    private static int getMinDistance(int start) {
        if (start == D) {
            return 0;
        }

        int distance = D - start;

        for (int i = 0; i < N; i++) {
            if (ways[i][0] < start || ways[i][1] > D) {
                continue;
            }
            int beforeShortcut = (ways[i][0] - start);
            int shortcut = ways[i][2];
            int afterShortCut = getMinDistance(ways[i][1]);
            distance = Math.min(distance, beforeShortcut + shortcut + afterShortCut);
        }
        return distance;
    }
}
