class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for (int i = left; i <= right; i++) {
            answer += getDivisors(i) % 2 == 0 ? i : -1 * i;
        }
        return answer;
    }

    private int getDivisors(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;
                if (i * i < n) {
                    count++;
                }
            }
        }
        return count;
    }
}
