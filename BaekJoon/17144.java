import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static final int[][] offsets = {{-1, 0, 1, 0}, {0, 1, 0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] map = new int[R][C];
        int machineRow = -1;

        int totalDust = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (machineRow == -1) {
                        machineRow = i;
                    }
                    map[i][j] = 0;
                }
                totalDust += map[i][j];
            }
        }

        for (int i = 0; i < T; i++) {
            map = diffusion(map, machineRow);
            totalDust -= purify(map, machineRow);
        }
        System.out.println(totalDust);
    }

    private static int purify(int[][] map, int machineRow) {
        int purified = map[machineRow][0] + map[machineRow][0];
        int topVertical = machineRow + 1;
        int bottomVertical = map.length - machineRow - 1;
        int horizontal = map[0].length;
        purified += shift(map, machineRow, new int[]{horizontal, topVertical, horizontal, topVertical},
                1, false);
        purified += shift(map, machineRow + 1, new int[]{horizontal, bottomVertical, horizontal, bottomVertical},
                1, true);
        return purified;
    }

    private static int shift(int[][] map, int startRow, int[] edges, int offset, boolean isClockDirection) {
        int previous = 0;
        int r = startRow;
        int c = 0;
        for (int edge: edges) {
            for (int i = 0; i < edge - 1; i++) {
               r += offsets[0][offset];
               c += offsets[1][offset];
               int temp = map[r][c];
               map[r][c] = previous;
               previous = temp;
            }
            offset += isClockDirection ? 1 : -1;
            if (offset < 0) {
                offset = 4 + offset;
            } else {
                offset %= 4;
            }
        }
        int left = map[startRow][0];
        map[startRow][0] = 0;
        return left;
    }

    private static int[][] diffusion(int[][] map, int machineRow) {
        int[][] temp = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                temp[i][j] += map[i][j];
                int effect = map[i][j] / 5;
                if (effect == 0) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nextRow = i + offsets[0][k];
                    int nextCol = j + offsets[1][k];
                    if (nextRow >= map.length || nextCol >= map[i].length || nextRow < 0 || nextCol < 0) {
                        continue;
                    }
                    if ((nextRow == machineRow || nextRow == machineRow + 1) && nextCol == 0) {
                        continue;
                    }
                    temp[nextRow][nextCol] += effect;
                    temp[i][j] -= effect;
                }
            }
        }
        return temp;
    }
}
