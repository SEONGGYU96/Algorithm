import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] ground = new char[N][];
        for (int i = 0; i < N; i++) {
            ground[i] = br.readLine().toCharArray();
        }
        ArrayList<Integer> counts = solution(ground);
        System.out.println(counts.size());
        Collections.sort(counts);
        for (int count : counts) {
            System.out.println(count);
        }
    }

    private static ArrayList<Integer> solution(char[][] ground) {
        ArrayList<Integer> counts = new ArrayList<>();
        boolean[][] isVisited = new boolean[ground.length][ground[0].length];
        for (boolean[] horizon : isVisited) {
            Arrays.fill(horizon, false);
        }
        for (int y = 0; y < ground.length; y++) {
            for (int x = 0; x < ground[y].length; x++) {
                if (ground[y][x] == '1' && !isVisited[y][x]) {
                    isVisited[y][x] = true;
                    counts.add(dfs(ground, isVisited, x, y));
                }
            }
        }
        return counts;
    }

    private static int dfs(char[][] ground, boolean[][] isVisited, int x, int y) {
        int count = 1;
        if (y - 1 >= 0 && ground[y - 1][x] == '1'&& !isVisited[y - 1][x]) {
            isVisited[y - 1][x] = true;
            count += dfs(ground, isVisited, x, y - 1);
        }

        if (x + 1 < ground[0].length && ground[y][x + 1] == '1' && !isVisited[y][x + 1]) {
            isVisited[y][x + 1] = true;
            count += dfs(ground, isVisited, x + 1, y);
        }

        if (y + 1 < ground.length && ground[y + 1][x] == '1' && !isVisited[y + 1][x]) {
            isVisited[y + 1][x] = true;
            count += dfs(ground, isVisited, x, y + 1);
        }

        if (x - 1 >= 0 && ground[y][x - 1] == '1' && !isVisited[y][x - 1]) {
            isVisited[y][x - 1] = true;
            count += dfs(ground, isVisited, x - 1, y);
        }
        return count;
    }
}

