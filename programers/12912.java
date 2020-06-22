class Solution {
        public long solution(int a, int b) {
            long answer = 0;

            long big = Math.max(a, b);
            long min = Math.min(a, b);

            long sum = a + b;

            if (a == b) {
                answer = a;
            } else if ((big - min) % 2 == 0) {
                answer = sum * ((big - min) / 2) + sum / 2;
            } else {
                answer = sum * ((big - min) / 2 + 1);
            }

            return answer;
        }
    }
