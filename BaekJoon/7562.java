import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int _case = Integer.parseInt(br.readLine());
        while (_case > 0) {
            int I = Integer.parseInt(br.readLine());
            int[][] startAndEnd = new int[2][2];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 2; j++) {
                    startAndEnd[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            bw.write(solution(I, startAndEnd) + "\n");
            _case--;
        }
        bw.flush();
        bw.close();
    }

    private static int solution(int I, int[][] startAndEnd) {
        int answer = -1;
        int[] rowOffset = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] colOffset = {2, 1, -1, -2, -2, -1, 1, 2};

        boolean[][] isVisited = new boolean[I][I];

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startAndEnd[0][0], startAndEnd[0][1]));

        while (!queue.isEmpty()) {
            answer++;
//            System.out.println("============== " + answer + " ============");
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position current = queue.poll();
//                System.out.println(current);
                int row = current.row;
                int col = current.col;
                if (row == startAndEnd[1][0] && col == startAndEnd[1][1]) {
                    return answer;
                }
                for (int j = 0; j < rowOffset.length; j++) {
                    int newRow = row + rowOffset[j];
                    int newCol = col + colOffset[j];
                    if (newRow >= I || newRow < 0 || newCol >= I || newCol < 0 || isVisited[newRow][newCol]) {
                        continue;
                    }
                    queue.add(new Position(newRow, newCol));
                    isVisited[newRow][newCol] = true;
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
