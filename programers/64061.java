class Solution {
        public int solution(int[][] board, int[] moves) {
            int answer = 0;
            int head_board = 0;
            int head_bucket = -1;
            int target = -1;
            int depth = board.length;

            int[] bucket = new int[moves.length];

            for (int move : moves) {
                while (head_board < depth) {

                    if (board[head_board][move - 1] != 0){
                        target = board[head_board][move - 1];
                        board[head_board][move - 1] = 0;

                        if (head_bucket >= 0 && bucket[head_bucket] == target) {
                            answer += 2;
                            head_bucket--;

                        } else {
                            bucket[++head_bucket] = target;
                        }
                        head_board = 0;
                        break;

                    } else {
                        head_board++;
                    }
                }
            }
            return answer;
        }
    }
