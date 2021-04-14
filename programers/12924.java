class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] preSum = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + i;
        }
        
        int head = 0;
        int tail = 1;
        while (tail <= n) {
            int sum = preSum[tail] - preSum[head];
            if (sum == n) {
                head++;
                tail++;
                answer++;
            } else if (sum < n) {
                tail++;
            } else {
                head++;
            }
        }
        
        return answer;
    }
}
