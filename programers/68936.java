import java.util.*;

class Solution {

    private static final int[] rowOffset = {0, 0, 1, 1};
    private static final int[] colOffset = {0, 1, 0, 1};

    public int[] solution(int[][] arr) {
        int[] answer = new int[2];

        compress(arr, answer, 0, 0, arr.length);

        return answer;
    }

    private void compress(int[][] arr, int[] answer, int startRow, int startCol, int length) {
        int num = arr[startRow][startCol];
        if (length == 1) {
            answer[num]++;
            return;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int current = arr[startRow + i][startCol + j];
                if (num != current) {
                    for (int k = 0; k < 4; k++) {
                        int nextLength = length / 2;
                        int nextRow = startRow + rowOffset[k] * nextLength;
                        int nextCol = startCol + colOffset[k] * nextLength;
                        compress(arr, answer, nextRow, nextCol, nextLength);
                    }
                    return;
                }
            }
        }
        answer[num] ++;
    }
}
