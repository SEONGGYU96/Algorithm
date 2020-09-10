class Solution {
        public int[] solution(int n) {
            int[] answer;
            int size = 0;

            int[][] tower = new int[n][];

            for (int i = 1; i <= n; i++) {
                tower[i - 1] = new int[i];
                size += i;
            }

            answer = new int[size];

            int row = -1;
            int col = 0;
            int count = 1;

            for (int i = 1; i <= n; i++) {

                for (int j = 0; j <= n - i; j++) {

                    switch (i % 3) {
                        case 1:
                            row += 1;
                            break;

                        case 2:
                            col += 1;
                            break;

                        case 0:
                            row -= 1;
                            col -= 1;
                            break;
                    }

                    tower[row][col] = count++;
                }
            }

            int index = 0;

            for (int[] rows : tower) {
                for (int cols : rows) {
                    answer[index++] = cols;
                }
            }

            return answer;
        }
    }
