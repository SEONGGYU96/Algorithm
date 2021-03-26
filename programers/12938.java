class Solution {

    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        if (n == 1) {
            answer[0] = s;
            return answer;
        }

        if (n > s) {
            return new int[]{-1};
        }

        int head = 0;
        int currentN = n;
        int currentS = s;
        while (currentN > 0) {
            answer[head] = currentS / currentN;
            currentN--;
            currentS -= answer[head++];
        }

        return answer;
    }
}
