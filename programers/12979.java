class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int head = 1;
        int unit = w * 2 + 1;
        for (int station : stations) {
            int left = station - w;
            int target = left - head;
            if (head < left) {
                answer += target / unit;
                if (target % unit != 0) {
                    answer++;
                }
            }
            head = station + w + 1;
        }
        if (head < n + 1) {
            answer += (n + 1 - head) / unit;
            if ((n + 1 - head) % unit != 0) {
                answer++;
            }
        }
        return answer;
    }
}
