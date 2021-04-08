import java.util.*;

class Solution {

    private HashMap<Integer, List<Position>> pairMap;
    private boolean[] isRemoved = new boolean[8];
    private int n;
    private int m;
    private int answer = Integer.MAX_VALUE;

    public int solution(int[][] board, int r, int c) {
        n = board.length;
        m = board[0].length;

        pairMap = new HashMap<>();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] != 0) {
                    Position here = new Position(row, col);
                    if (!pairMap.containsKey(board[row][col])) {
                        ArrayList<Position> positions = new ArrayList<>();
                        positions.add(here);
                        pairMap.put(board[row][col], positions);
                    } else {
                        pairMap.get(board[row][col]).add(here);
                    }
                }
            }
        }

        dfs(board, new Position(r, c), -1, -1, 0, 0);

        return answer;
    }

    private void dfs(int[][] board, Position start, int currentCard, int nextIndex, int sum, int depth) {
        if (depth == pairMap.size()) {
            answer = Math.min(answer, sum);
            return;
        }

        int count = 0;

        if (currentCard != -1 && nextIndex != -1) {
            List<Position> cards = pairMap.get(currentCard);
            count += getMoveCount(board, start, cards.get(nextIndex));
            isRemoved[currentCard] = true; //enter
            count++;
            dfs(board, cards.get(nextIndex), -1, -1 , sum + count, depth + 1);
            isRemoved[currentCard] = false;
        } else {
            for (int nextCard: pairMap.keySet()) {
                if (isRemoved[nextCard]) {
                    continue;
                }
                List<Position> cards = pairMap.get(nextCard);
                int c = getMoveCount(board, start, cards.get(0));
                c++; //enter
                dfs(board, cards.get(0), nextCard, 1, sum + c, depth);

                int c1 = getMoveCount(board, start, cards.get(1));
                c1++;
                dfs(board, cards.get(1), nextCard, 0, sum + c1, depth);
            }
        }
    }
    
    private static final int[] rowOffset = {-1, 0, 1, 0};
    private static final int[] colOffset = {0, 1, 0, -1};

    private int getMoveCount(int[][] board, Position start, Position end) {
        int n = board.length;
        int m = board[0].length;

        boolean[][] isVisited = new boolean[n][m];
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(start.row, start.col));
        isVisited[start.row][start.col] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position current = queue.poll();
                if (current.row == end.row && current.col == end.col) {
                    return count;
                }

                for (int j = 0; j < 4; j++) {
                    int nextRow = current.row + rowOffset[j];
                    int nextCol = current.col + colOffset[j];
                    if (!isValidPosition(nextRow, nextCol)) {
                        continue;
                    }

                    if (!isVisited[nextRow][nextCol]) {
                        queue.offer(new Position(nextRow, nextCol));
                        isVisited[nextRow][nextCol] = true;
                    }

                    if (hasCard(board, nextRow, nextCol)) {
                        continue;
                    }

                    int tempRow = nextRow;
                    int tempCol = nextCol;

                    nextRow = tempRow + rowOffset[j];
                    nextCol = tempCol + colOffset[j];

                    while (isValidPosition(nextRow, nextCol)) {
                        tempRow = nextRow;
                        tempCol = nextCol;
                        nextRow += rowOffset[j];
                        nextCol += colOffset[j];
                        if (hasCard(board, tempRow, tempCol)) {
                            break;
                        }
                    }

                    if (isVisited[tempRow][tempCol]) {
                        continue;
                    }
                    queue.offer(new Position(tempRow, tempCol));
                    isVisited[tempRow][tempCol] = true;
                }
            }
            count++;
        }
        return count;
    }

    private boolean hasCard(int[][] board, int row, int col) {
        return board[row][col] != 0 && !isRemoved[board[row][col]];
    }

    private boolean isValidPosition(int row, int col) {
        return row < n && row >= 0 && col < m && col >= 0;
    }

    private static class Position {
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
