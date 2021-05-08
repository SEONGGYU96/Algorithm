import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private static final int offsets[][] = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    private static final int MAX_DISTANCE = 2;

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            answer[i] = isDistancing(places[i]);
        }

        return answer;
    }

    private int isDistancing(String[] place) {
        int n = place.length;
        int m = place[0].length();
        Queue<Position> queue = new LinkedList<>();
        int id = 0;
        for (int row = 0; row < place.length; row++) {
            for (int col = 0; col < place[row].length(); col++) {
                if (place[row].charAt(col) == 'P') {
                    queue.offer(new Position(row, col, id++,0));
                }
            }
        }
        boolean[][][] isVisited = new boolean[id][n][m];

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            isVisited[current.id][current.row][current.col] = true;
            if (current.distance >= MAX_DISTANCE) {
                continue;
            }
            for (int i = 0; i < offsets[0].length; i++) {
                int nextRow = current.row + offsets[0][i];
                int nextCol = current.col + offsets[1][i];
                if (nextRow >= n || nextCol >= m || nextRow < 0 || nextCol < 0) {
                    continue;
                }
                if (isVisited[current.id][nextRow][nextCol]) {
                    continue;
                }
                char nextPlace = place[nextRow].charAt(nextCol);
                if (nextPlace == 'X') {
                    continue;
                }
                if (nextPlace == 'P') {
                    return 0;
                }
                queue.offer(new Position(nextRow, nextCol, current.id, current.distance + 1));
                isVisited[current.id][nextRow][nextCol] = true;
            }
        }
        return 1;
    }

    private static class Position {
        private final int row;
        private final int col;
        private final int id;
        private final int distance;

        public Position(int row, int col, int id, int distance) {
            this.row = row;
            this.col = col;
            this.id = id;
            this.distance = distance;
        }
    }
}
