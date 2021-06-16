import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] pillars = new int[N][2];
        int max = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                pillars[i][j] = Integer.parseInt(st.nextToken());
            }
            max = Math.max(max, pillars[i][1]);
        }
        System.out.println(solution(pillars, max));
    }

    public static int solution(int[][] pillars, int max) {
        int answer = 0;
        int basePosition = pillars[0][0];
        int baseHeight = pillars[0][1];
        int leftTop = 0;
        int rightTop = 0;

        Arrays.sort(pillars, (Comparator.comparingInt(o -> o[0])));

        if (pillars.length == 1) {
            return max;
        }

        if (baseHeight != max) {
            for (int i = 1; i < pillars.length; i++) {
                int currentPosition = pillars[i][0];
                int currentHeight = pillars[i][1];

                if (baseHeight < currentHeight) {
                    answer += baseHeight * (currentPosition - basePosition);
                    baseHeight = currentHeight;
                    basePosition = currentPosition;
                }

                if (currentHeight == max) {
                    leftTop = currentPosition;
                    break;
                }
            }
        } else {
            leftTop = basePosition;
        }

        basePosition = pillars[pillars.length - 1][0];
        baseHeight = pillars[pillars.length - 1][1];

        if (baseHeight != max) {
            for (int i = pillars.length - 2; i >= 0; i--) {
                int currentPosition = pillars[i][0];
                int currentHeight = pillars[i][1];

                if (baseHeight < currentHeight) {
                    answer += baseHeight * (basePosition - currentPosition);
                    baseHeight = currentHeight;
                    basePosition = currentPosition;
                }

                if (currentHeight == max) {
                    rightTop = currentPosition;
                    break;
                }
            }
        } else {
            rightTop = basePosition;
        }

        if (leftTop != rightTop) {
            answer += max * (rightTop - leftTop + 1);
        } else {
            answer += max;
        }

        return answer;
    }
}
