import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        char[][] forest = new char[h][w];
        Position start = null;
        ArrayList<Position> waterPosition = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            if (start == null && str.contains("S")) {
                start = new Position(i, str.indexOf('S'));
                str = str.replace('S', '.');
            }
            if (str.contains("*")) {
                int lastIndex = -1;
                while (lastIndex < h - 1) {
                    int currentIndex = str.indexOf('*', lastIndex + 1);
                    if (currentIndex == -1) {
                        break;
                    }
                    waterPosition.add(new Position(i, currentIndex));
                    lastIndex = currentIndex;
                }
            }
            forest[i] = str.toCharArray();
        }
        int answer = solution(w, h, start, waterPosition, forest);
        System.out.println(answer == -1 ? "KAKTUS" : String.valueOf(answer));
    }

    private static int solution(int w, int h, Position start, ArrayList<Position> firePosition, char[][] forest) {
        int answer = -1;
        final int BEAVER = 0;
        final int WATER = 1;
        boolean[][][] isVisited = new boolean[2][h][w];
        Queue<Position> beaverMove = new LinkedList<>();
        beaverMove.add(start);

        Queue<Position> waterMove = new LinkedList<>(firePosition);

        isVisited[BEAVER][start.row][start.col] = true;

        for (Position fire : waterMove) {
            isVisited[WATER][fire.row][fire.col] = true;
        }

        while (!beaverMove.isEmpty()) {
            answer++;
//            System.out.println("============== " + answer + " ============");

            int size = waterMove.size();
            for (int i = 0; i < size; i++) {
                Position current = waterMove.poll();
//                System.out.println("Water! : " + current);
                int row = current.row;
                int col = current.col;

                if (row + 1 < h && forest[row + 1][col] == '.' && !isVisited[WATER][row + 1][col]) {
                    waterMove.add(new Position(row + 1, col));
                    isVisited[WATER][row + 1][col] = true;
                }
                if (col + 1 < w && forest[row][col + 1] == '.' && !isVisited[WATER][row][col + 1]) {
                    waterMove.add(new Position(row, col + 1));
                    isVisited[WATER][row][col + 1] = true;
                }
                if (row - 1 >= 0 && forest[row - 1][col] == '.' && !isVisited[WATER][row - 1][col]) {
                    waterMove.add(new Position(row - 1, col));
                    isVisited[WATER][row - 1][col] = true;
                }
                if (col - 1 >= 0 && forest[row][col - 1] == '.' && !isVisited[WATER][row][col - 1]) {
                    waterMove.add(new Position(row, col - 1));
                    isVisited[WATER][row][col - 1] = true;
                }
            }

            size = beaverMove.size();
            for (int i = 0; i < size; i++) {
                Position current = beaverMove.poll();
//                System.out.println("Beaver : " + current);
                int row = current.row;
                int col = current.col;
                if (forest[row][col] == 'D') {
                    return answer;
                }
                if (row + 1 < h && (forest[row + 1][col] == '.' || forest[row + 1][col] == 'D') && !isVisited[BEAVER][row + 1][col] && !isVisited[WATER][row + 1][col]) {
                    beaverMove.add(new Position(row + 1, col));
                    isVisited[BEAVER][row + 1][col] = true;
                }
                if (col + 1 < w && (forest[row][col + 1] == '.' || forest[row][col + 1] == 'D') && !isVisited[BEAVER][row][col + 1] && !isVisited[WATER][row][col + 1]) {
                    beaverMove.add(new Position(row, col + 1));
                    isVisited[BEAVER][row][col + 1] = true;
                }
                if (row - 1 >= 0 && (forest[row - 1][col] == '.' || forest[row - 1][col] == 'D') && !isVisited[BEAVER][row - 1][col] && !isVisited[WATER][row - 1][col]) {
                    beaverMove.add(new Position(row - 1, col));
                    isVisited[BEAVER][row - 1][col] = true;
                }
                if (col - 1 >= 0 && (forest[row][col - 1] == '.' || forest[row][col - 1] == 'D') && !isVisited[BEAVER][row][col - 1] && !isVisited[WATER][row][col - 1]) {
                    beaverMove.add(new Position(row, col - 1));
                    isVisited[BEAVER][row][col - 1] = true;
                }
            }
        }
        return -1;
    }

    static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return " row : " + row + " col : " + col;
        }
    }
}

