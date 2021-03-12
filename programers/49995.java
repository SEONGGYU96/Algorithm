class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int size = cookie.length;

        int[] preSum = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            preSum[i] = preSum[i - 1] + cookie[i - 1];
        }


        for (int m = 0; m < size; m++) {
            int head = m;
            int tail = m + 1;
            while (head >= 0 && tail < size) {
                int leftSum = preSum[m + 1] - preSum[head];
                int rightSum = preSum[tail + 1] - preSum[m + 1];

                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                    head--;
                    tail++;
                } else if (leftSum > rightSum) {
                    tail++;
                } else {
                    head--;
                }
            }
        }
        return answer;
    }
}
