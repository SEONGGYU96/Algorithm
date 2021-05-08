import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    private static int N;
    private static int M;
    private static ArrayList<Position> homes;
    private static ArrayList<Position> chickens;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        homes = new ArrayList<>();
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String current = st.nextToken();
                if (current.equals("1")) {
                    homes.add(new Position(i, j));
                } else if (current.equals("2")) {
                    chickens.add(new Position(i, j));
                }
            }
        }
        System.out.println(peek(0, 0, new boolean[chickens.size()]));
    }

    private static int peek(int start, int count, boolean[] isVisited) {
        if (count == M) {
            int sum = 0;
            for (Position home : homes) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < chickens.size(); i++) {
                    if (isVisited[i]) {
                        min = Math.min(min, home.getDistance(chickens.get(i)));
                    }
                }
                sum += min;
            }
            return sum;
        }

        int min = Integer.MAX_VALUE;

        for (int i = start; i < chickens.size(); i++) {
            if (isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            min = Math.min(min, peek(i + 1, count + 1, isVisited));
            isVisited[i] = false;
        }
        return min;
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getDistance(Position target) {
            return Math.abs(row - target.row) + Math.abs(col - target.col);
        }
    }
}
