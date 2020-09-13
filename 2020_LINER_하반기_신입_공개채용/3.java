import java.util.Arrays;

class Solution {
        private int row = 0;
        private int col = 0;
        private boolean[] wall = {false, false, false, false};
        private final int TOP = 0;
        private final int RIGHT = 1;
        private final int BOTTOM = 2;
        private final int LEFT = 3;
        private int[][] maze;
        private int lastVisitedRow;
        private int lastVisitedCol;

        public int solution(int[][] maze) {
            int answer = 0;
            this.maze = maze;


            while (row != maze.length - 1 || col != maze[0].length - 1) {
                scan();

                for (int i = 0; i < wall.length; i++) {
                    if (!wall[i]) {
                        switch (i) {
                            case TOP:
                                if (!isVisited(row - 1, col) && !wall[TOP]) {
                                    saveVisited();
                                    row--;
                                    break;
                                }
                                if (isUniqueRoad()) {
                                    saveVisited();
                                    row--;
                                    break;
                                }
                            case RIGHT:
                                if (!isVisited(row, col + 1) && !wall[RIGHT]) {
                                    saveVisited();
                                    col++;
                                    break;
                                }
                                if (isUniqueRoad()) {
                                    saveVisited();
                                    col++;
                                    break;
                                }
                            case BOTTOM:
                                if (!isVisited(row + 1, col) && !wall[BOTTOM]) {
                                    saveVisited();
                                    row++;
                                    break;
                                }
                                if (isUniqueRoad()) {
                                    saveVisited();
                                    row++;
                                    break;
                                }
                            case LEFT:
                                saveVisited();
                                col--;
                                break;
                        }
                        break;
                    }
                }
                answer++;
            }
            return answer;
        }

        private boolean isUniqueRoad() {
            int count = 0;
            for (boolean b : wall) {
                if (b) {
                    count++;
                }
            }
            return count == 3;
        }

        private void saveVisited() {
            lastVisitedRow = row;
            lastVisitedCol = col;
        }

        private boolean isVisited(int row, int col) {
            return lastVisitedRow == row && lastVisitedCol == col;
        }

        private void scan() {
            Arrays.fill(wall, false);
            if (row - 1 < 0 || maze[row - 1][col] == 1) {
                wall[TOP] = true;
            }
            if (col == maze[0].length - 1 || maze[row][col + 1] == 1) {
                wall[RIGHT] = true;
            }
            if (row == maze.length - 1 || maze[row + 1][col] == 1) {
                wall[BOTTOM] = true;
            }
            if (col - 1 < 0 || maze[row][col - 1] == 1) {
                wall[LEFT] = true;
            }
        }
    }
