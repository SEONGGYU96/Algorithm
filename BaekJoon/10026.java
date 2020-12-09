import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] painting = new char[N][N];
        for (int i = 0; i < N; i++) {
            painting[i] = br.readLine().toCharArray();
        }
        int[] answer = solution(painting);
        for (int ans : answer) {
            System.out.print(ans + " ");
        }
    }

    private static int[] solution(char[][] painting) {
        int[] answer = new int[2];
        boolean[][][] isVisited = new boolean[2][painting.length][painting[0].length];

        for (boolean[][] z : isVisited) {
            for (boolean[] horizon : z) {
                Arrays.fill(horizon, false);
            }
        }

        for (int y = 0; y < painting.length; y++) {
            for (int x = 0; x < painting[y].length; x++) {
                if (!isVisited[1][y][x]) {
                    isVisited[1][y][x] = true;
                    ArrayList<Character> targetColors = new ArrayList<>();
                    if (painting[y][x] == 'B') {
                        targetColors.add('B');
                    } else {
                        targetColors.add('R');
                        targetColors.add('G');
                    }
                    dfs(painting, isVisited[1], x, y, targetColors);
                    answer[1]++;
                }
                if (!isVisited[0][y][x]) {
                    isVisited[0][y][x] = true;
                    ArrayList<Character> targetColors = new ArrayList<>();
                    targetColors.add(painting[y][x]);
                    dfs(painting, isVisited[0], x, y, targetColors);
                    answer[0]++;
                }
            }
        }
        return answer;
    }

    private static void dfs(char[][] painting, boolean[][] isVisited, int x, int y, ArrayList<Character> targetColors) {
        if (y - 1 >= 0 && isSameColor(painting[y - 1][x], targetColors) &&  !isVisited[y - 1][x]) {
            isVisited[y - 1][x] = true;
            dfs(painting, isVisited, x, y - 1, targetColors);
        }

        if (x + 1 < painting[0].length && isSameColor(painting[y][x + 1], targetColors) && !isVisited[y][x + 1]) {
            isVisited[y][x + 1] = true;
            dfs(painting, isVisited, x + 1, y, targetColors);
        }

        if (y + 1 < painting.length && isSameColor(painting[y + 1][x], targetColors) && !isVisited[y + 1][x]) {
            isVisited[y + 1][x] = true;
            dfs(painting, isVisited, x, y + 1, targetColors);
        }

        if (x - 1 >= 0 && isSameColor(painting[y][x - 1], targetColors) && !isVisited[y][x - 1]) {
            isVisited[y][x - 1] = true;
            dfs(painting, isVisited, x - 1, y, targetColors);
        }
    }

    private static boolean isSameColor(char color, ArrayList<Character> targetColors) {
        for (char targetColor : targetColors) {
            if (targetColor == color) {
                return true;
            }
        }
        return false;
    }
}
