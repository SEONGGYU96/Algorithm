class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long max = 0;
        for (int time : times) {
            max = Math.max(max, time);
        }
        max *= n;

        long head = 0;
        long tail = max;

        while (head <= tail) {
            long center = (head + tail) / 2;
            long sum = 0;
            for (int time : times) {
                sum += center / time;
            }

            if (sum < n) {
                head = center + 1;
            } else {
                answer = center;
                tail = center - 1;
            }
        }
        return answer;
    }
}
