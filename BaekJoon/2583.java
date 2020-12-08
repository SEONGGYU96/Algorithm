import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[][] paper = new boolean[M][N];
        for (boolean[] row : paper) {
            Arrays.fill(row, true);
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            Point leftDown = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Point rightUp = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int x = leftDown.x; x < rightUp.x; x++) {
                for (int y = leftDown.y; y < rightUp.y; y++) {
                    paper[y][x] = false;
                }
            }
        }
        ArrayList<Integer> blanks = solution(paper);
        System.out.println(blanks.size());
        for (int blank : blanks) {
            System.out.print(blank + " ");
        }
    }

    private static ArrayList<Integer> solution(boolean[][] paper) {
        ArrayList<Integer> areas = new ArrayList<>();
        boolean[][] isVisited = new boolean[paper.length][paper[0].length];
        for (boolean[] horizon : isVisited) {
            Arrays.fill(horizon, false);
        }
        for (int y = 0; y < paper.length; y++) {
            for (int x = 0; x < paper[y].length; x++) {
                if (paper[y][x] && !isVisited[y][x]) {
                    isVisited[y][x] = true;
                    areas.add(dfs(paper, isVisited, x, y));
                }
            }
        }
        Collections.sort(areas);
        return areas;
    }

    private static int dfs(boolean[][] ground, boolean[][] isVisited, int x, int y) {
        int count = 1;
        if (y - 1 >= 0 && ground[y - 1][x]&& !isVisited[y - 1][x]) {
            isVisited[y - 1][x] = true;
            count += dfs(ground, isVisited, x, y - 1);
        }

        if (x + 1 < ground[0].length && ground[y][x + 1] && !isVisited[y][x + 1]) {
            isVisited[y][x + 1] = true;
            count += dfs(ground, isVisited, x + 1, y);
        }

        if (y + 1 < ground.length && ground[y + 1][x] && !isVisited[y + 1][x]) {
            isVisited[y + 1][x] = true;
            count += dfs(ground, isVisited, x, y + 1);
        }

        if (x - 1 >= 0 && ground[y][x - 1] && !isVisited[y][x - 1]) {
            isVisited[y][x - 1] = true;
            count += dfs(ground, isVisited, x - 1, y);
        }
        return count;
    }
}

