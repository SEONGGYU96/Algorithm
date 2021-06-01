import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static final int[][] offsets = {{0, 0, -1, 1}, {1, -1, 0, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Dice dice = new Dice();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int order = Integer.parseInt(st.nextToken()) - 1;
            int nextRow = row + offsets[0][order];
            int nextCol = col + offsets[1][order];
            if (nextRow >= N || nextCol >= M || nextRow < 0 || nextCol < 0) {
                continue;
            }

            int nextValue = map[nextRow][nextCol];
            dice.rotate(order);
            if (nextValue == 0) {
                map[nextRow][nextCol] = dice.getBottom();
            } else {
                dice.setBottom(nextValue);
                map[nextRow][nextCol] = 0;
            }
            System.out.println(dice.getTop());
            row = nextRow;
            col = nextCol;
        }
    }
    
    private static class Dice {
        private final int[][] values = new int[4][3];

        public void rotate(int direction) {
            int temp;
            switch (direction) {
                case 0:
                    temp = values[1][2];
                    System.arraycopy(values[1], 0, values[1], 1, 2);
                    values[1][0] = values[3][1];
                    values[3][1] = temp;
                    break;
                case 1:
                    temp = values[1][0];
                    System.arraycopy(values[1], 1, values[1], 0, 2);
                    values[1][2] = values[3][1];
                    values[3][1] = temp;
                    break;
                case 2:
                    temp = values[0][1];
                    for (int i = 1; i < 4; i++) {
                        values[i - 1][1] = values[i][1];
                    }
                    values[3][1] = temp;
                    break;
                case 3:
                    temp = values[3][1];
                    for (int i = 3; i > 0; i--) {
                        values[i][1] = values[i - 1][1];
                    }
                    values[0][1] = temp;
                    break;
            }
        }

        public int getTop() {
            return values[1][1];
        }

        public void setBottom(int value) {
            values[3][1] = value;
        }

        public int getBottom() {
            return values[3][1];
        }
    }
}

