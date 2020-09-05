class Solution {
        public long solution(long n) {
            long answer = 0;

            double sqrt = Math.sqrt(n);

            if ((long) sqrt == sqrt) {
                return (long) Math.pow(sqrt + 1, 2);
            } else  {
                return -1;
            }
        }
    }
