import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        double[] probability = new double[4];

        for (int i = 0; i < probability.length; i++) {
            probability[i] = Integer.parseInt(st.nextToken()) * 0.01f;
        }

        Set<Point> isVisited = new HashSet<>();
        isVisited.add(new Point(0, 0));

        int[] xOffset = {0, 0, 1, -1};
        int[] yOffset = {1, -1, 0, 0};

        System.out.print(backTracking(probability, isVisited, xOffset, yOffset, 0, 0, 0, N));
    }

    private static double backTracking(double[] probability, Set<Point> isVisited, int[] xOffset, int[] yOffset, int x, int y, int depth, int N) {
        if (depth == N) {
            return 1;
        }

        double answer = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = x + xOffset[i];
            int nextY = y + yOffset[i];

            Point current = new Point(nextX, nextY);
            if (!isVisited.contains(current)) {
                isVisited.add(current);
                answer += probability[i] * backTracking(probability, isVisited, xOffset, yOffset, nextX, nextY, depth + 1, N);
                isVisited.remove(current);
            }
        }
        return answer;
    }
}

