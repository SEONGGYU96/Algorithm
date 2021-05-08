import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static final int GEARS = 4;
    private static final int RIGHT = 2;
    private static final int LEFT = 6;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] gears = new String[GEARS];
        for (int i = 0; i < GEARS; i++) {
            gears[i] = br.readLine();
        }
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken()) - 1;
            int count = Integer.parseInt(st.nextToken());
            rotate(gears, target, count, new boolean[GEARS]);
        }
        int answer = 0;
        for (int i = 0; i < GEARS; i++) {
            char top = gears[i].charAt(0);
            if (top == '1') {
                answer += (1 << i);
            }
        }
        System.out.println(answer);
    }

    private static void rotate(String[] gears, int target, int count, boolean[] isVisited) {
        boolean isRightConnected = false;
        boolean isLeftConnected = false;
        if (target + 1 < GEARS) {
            if (gears[target].charAt(RIGHT) != gears[target + 1].charAt(LEFT)) {
                isRightConnected = true;
            }
        }
        if (target - 1 >= 0) {
            if (gears[target].charAt(LEFT) != gears[target - 1].charAt(RIGHT)) {
                isLeftConnected = true;
            }
        }
        int realCount = Math.abs(count) % gears[target].length();
        if (count >= 0) {
            int ground = gears[target].length() - realCount;
            gears[target] = gears[target].substring(ground) + gears[target].substring(0, ground);
        } else {
            gears[target] = gears[target].substring(realCount) + gears[target].substring(0, realCount);
        }
        isVisited[target] = true;
        if (isRightConnected && !isVisited[target + 1]) {
            rotate(gears, target + 1, count * -1, isVisited);
        }
        if (isLeftConnected && !isVisited[target - 1]) {
            rotate(gears, target - 1, count * -1, isVisited);
        }
    }
}
