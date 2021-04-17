import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        ArrayList<Character>[] blocks = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            blocks[i] = new ArrayList<>();
            for (int j = m - 1; j >= 0; j--) {
                blocks[i].add(board[j].charAt(i));
            }
        }

        Queue<Position> queue = new LinkedList<>();
        final int[][] dfsOffset = {{0, -1, -1}, {1, 1, 0}};

        int row = m - 1;
        int col = 0;
        int rowOffset = 0;
        while (row != 0 || col != n - 1) {
            boolean isRemoved;
            queue.add(new Position(row, col));

            HashMap<Integer, RemoveRange> removes = new HashMap<>();
            Queue<Position> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                Position current = queue.poll();
                tempQueue.offer(current);
                boolean isRemovable = true;
                for (int i = 0; i < dfsOffset[0].length; i++) {
                    int nextRow = current.row + dfsOffset[0][i];
                    int nextCol = current.col + dfsOffset[1][i];
                    if (nextCol < 0 || nextCol >= n || nextRow < 0 || nextRow >= blocks[nextCol].size()
                            || blocks[current.col].get(current.row) != blocks[nextCol].get(nextRow)) {
                        isRemovable = false;
                        break;
                    }
                    tempQueue.offer(new Position(nextRow, nextCol));
                }
                if (isRemovable) {
                    int size = tempQueue.size();
                    for (int i = 0; i < size; i++) {
                        Position temp = tempQueue.poll();
                        if (!removes.containsKey(temp.col)) {
                            RemoveRange range = new RemoveRange();
                            range.refresh(temp);
                            removes.put(temp.col, range);
                        } else {
                            removes.get(temp.col).refresh(temp);
                        }
                        if (!temp.equals(current)) {
                            queue.offer(temp);
                        }
                    }
                } else {
                    tempQueue.clear();
                }
            }

            isRemoved = !removes.isEmpty();

            for (int targetCol : removes.keySet()) {
                RemoveRange range = removes.get(targetCol);
                for (int i = 0; i < range.getCount(); i++) {
                    blocks[targetCol].remove(range.minRow);
                    answer++;
                }
            }

            if (isRemoved) {
                col = 0;
                rowOffset = 0;
            } else {
                if (col + 1 >= n) {
                    rowOffset++;
                    col = 0;
                } else {
                    col++;
                }
            }
            row = blocks[col].size() - 1 - rowOffset;
        }
        return answer;
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row &&
                    col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static class RemoveRange {
        private int maxRow = 0;
        private int minRow = 31;

        public void refresh(Position position) {
            maxRow = Math.max(maxRow, position.row);
            minRow = Math.min(minRow, position.row);
        }

        public int getCount() {
            return maxRow - minRow + 1;
        }
    }
}
