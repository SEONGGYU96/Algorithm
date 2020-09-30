class Solution {
    public int[] solution(int[][] blocks) {
        int n = blocks.length;
        int[] answer = new int[n * (n + 1) / 2];

        int[][] pyramid = new int[blocks.length][];

        for (int i = 0; i < pyramid.length; i++) {
            pyramid[i] = new int[i + 1];
        }

        for (int i = 0; i < blocks.length; i++) {
            int position = blocks[i][0];
            int value = blocks[i][1];
            pyramid[i][position] = value;

            if (i == 0) {
                continue;
            }

            int right = position + 1;
            int tempRightValue = value;
            int left = position - 1;
            int tempLeftValue = value;

            while (right < pyramid[i].length) {
                tempRightValue = pyramid[i - 1][right - 1] - tempRightValue;
                pyramid[i][right] = tempRightValue;
                right++;
            }
            while (left >= 0) {
                tempLeftValue = pyramid[i - 1][left] - tempLeftValue;
                pyramid[i][left] = tempLeftValue;
                left--;
            }
        }

        int row = 0;
        int col = 0;
        for (int i = 0; i < answer.length; i++) {
            answer[i] = pyramid[row][col++];
            if (col >= pyramid[row].length) {
                col = 0;
                row++;
            }
        }
        return answer;
    }
}
