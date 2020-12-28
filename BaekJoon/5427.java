import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int numberOfCase = Integer.parseInt(br.readLine());

        for (int _case = 0; _case < numberOfCase; _case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            char[][] building = new char[h][w];
            Position start = null;
            ArrayList<Position> firePosition = new ArrayList<>();

            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                if (start == null && str.contains("@")) {
                    start = new Position(i, str.indexOf('@'));
                }
                if (str.contains("*")) {
                    int lastIndex = -1;
                    while (lastIndex < h - 1) {
                        int currentIndex = str.indexOf('*', lastIndex + 1);
                        if (currentIndex == -1) {
                            break;
                        }
                        firePosition.add(new Position(i, currentIndex));
                        lastIndex = currentIndex;
                    }
                }
                building[i] = str.toCharArray();
            }
            int answer = solution(w, h, start, firePosition, building);
            bw.write(answer == -1 ? "IMPOSSIBLE" : String.valueOf(answer));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static int solution(int w, int h, Position start, ArrayList<Position> firePosition, char[][] building) {
        int answer = -1;
        final int CHARACTER = 0;
        final int FIRE = 1;
        boolean[][][] isVisited = new boolean[2][h][w];
        Queue<Position> characterMove = new LinkedList<>();

        characterMove.add(start);
        Queue<Position> fireMove = new LinkedList<>(firePosition);

        isVisited[CHARACTER][start.row][start.col] = true;

        for (Position fire : fireMove) {
            isVisited[FIRE][fire.row][fire.col] = true;
        }

        while (!characterMove.isEmpty()) {
            answer++;
            //System.out.println("============== " + answer + " ============");

            int size = fireMove.size();
            for (int i = 0; i < size; i++) {
                Position current = fireMove.poll();
                //System.out.println("Fire! : " + current);
                int row = current.row;
                int col = current.col;

                if (row + 1 < h && building[row + 1][col] != '#' && !isVisited[FIRE][row + 1][col]) {
                    fireMove.add(new Position(row + 1, col));
                    isVisited[FIRE][row + 1][col] = true;
                }
                if (col + 1 < w && building[row][col + 1] != '#' && !isVisited[FIRE][row][col + 1]) {
                    fireMove.add(new Position(row, col + 1));
                    isVisited[FIRE][row][col + 1] = true;
                }
                if (row - 1 >= 0 && building[row - 1][col] != '#' && !isVisited[FIRE][row - 1][col]) {
                    fireMove.add(new Position(row - 1, col));
                    isVisited[FIRE][row - 1][col] = true;
                }
                if (col - 1 >= 0 && building[row][col - 1] != '#' && !isVisited[FIRE][row][col - 1]) {
                    fireMove.add(new Position(row, col - 1));
                    isVisited[FIRE][row][col - 1] = true;
                }
            }

            size = characterMove.size();
            for (int i = 0; i < size; i++) {
                Position current = characterMove.poll();
                //System.out.println("Character : " + current);
                int row = current.row;
                int col = current.col;
                if (row == 0 || row == h - 1 || col == 0 || col == w - 1) {
                    return ++answer;
                }
                if (row + 1 < h && building[row + 1][col] != '#' && !isVisited[CHARACTER][row + 1][col] && !isVisited[FIRE][row + 1][col]) {
                    characterMove.add(new Position(row + 1, col));
                    isVisited[CHARACTER][row + 1][col] = true;
                }
                if (col + 1 < w && building[row][col + 1] != '#' && !isVisited[CHARACTER][row][col + 1] && !isVisited[FIRE][row][col + 1]) {
                    characterMove.add(new Position(row, col + 1));
                    isVisited[CHARACTER][row][col + 1] = true;
                }
                if (row - 1 >= 0 && building[row - 1][col] != '#' && !isVisited[CHARACTER][row - 1][col] && !isVisited[FIRE][row - 1][col]) {
                    characterMove.add(new Position(row - 1, col));
                    isVisited[CHARACTER][row - 1][col] = true;
                }
                if (col - 1 >= 0 && building[row][col - 1] != '#' && !isVisited[CHARACTER][row][col - 1] && !isVisited[FIRE][row][col - 1]) {
                    characterMove.add(new Position(row, col - 1));
                    isVisited[CHARACTER][row][col - 1] = true;
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

