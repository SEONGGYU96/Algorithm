import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static final int[][] offsets = {{-1, 0, 0, 1}, {0, -1, 1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int fishes = 0;
        Shark shark = null;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0, 0);
                    map[i][j] = 0;
                } else if (map[i][j] != 0) {
                    fishes++;
                }
            }
        }
        int answer = 0;
        while (shark != null && shark.total < fishes) {
            Result result = eatNearbyFish(map, shark);
            shark = result.shark;
            answer += result.time;
        }
        System.out.println(answer);
    }

    private static Result eatNearbyFish(int[][] map, Shark shark) {
        int N = map.length;
        Queue<Shark> queue = new LinkedList<>();
        queue.offer(shark);
        boolean[][] isVisited = new boolean[N][N];
        isVisited[shark.row][shark.col] = true;

        Queue<Shark> candidates = new PriorityQueue<>();

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Shark current = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int nextRow = current.row + offsets[0][j];
                    int nextCol = current.col + offsets[1][j];
                    if (nextRow >= N || nextCol >= N || nextRow < 0 || nextCol < 0
                            || isVisited[nextRow][nextCol] || map[nextRow][nextCol] > current.size) {
                        continue;
                    }

                    if (map[nextRow][nextCol] != 0 && map[nextRow][nextCol] < current.size) {
                        Shark result = current.moveAndEat(nextRow, nextCol);
                        candidates.offer(result);
                    } else {
                        queue.offer(current.move(nextRow, nextCol));
                        isVisited[nextRow][nextCol] = true;
                    }
                }
            }
            time++;
            if (!candidates.isEmpty()) {
                Shark result = candidates.poll();
                map[result.row][result.col] = 0;
                return new Result(result, time);
            }
        }

        return new Result(null, 0);
    }

    private static class Result {
        private final Shark shark;
        private final int time;

        public Result(Shark shark, int time) {
            this.shark = shark;
            this.time = time;
        }
    }
    
    private static class Shark implements Comparable<Shark> {
        private final int row;
        private final int col;
        private int size;
        private int stack;
        private int total;

        public Shark(int row, int col, int size, int stack, int total) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.stack = stack;
            this.total = total;
        }

        public Shark move(int row, int col) {
            return new Shark(row, col, size, stack, total);
        }
        public Shark moveAndEat(int row, int col) {
            int newStack = stack + 1;
            int newTotal = total + 1;
            int newSize = size;
            if (newSize == newStack) {
                newSize++;
                newStack = 0;
            }
            Shark shark = move(row, col);
            shark.size = newSize;
            shark.stack = newStack;
            shark.total = newTotal;
            return shark;
        }

        @Override
        public int compareTo(Shark o) {
            if (row > o.row) {
                return 1;
            } else if (row < o.row) {
                return -1;
            } else {
                return Integer.compare(col, o.col);
            }
        }
    }
}
