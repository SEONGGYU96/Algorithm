class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        getThatWay(n, k - 1, 0, new boolean[n], answer);
        return answer;
    }

    private void getThatWay(int n, long k, int depth, boolean[] isVisited, int[] result) {
        if (depth == n) {
            return;
        }
        long numberOfCase = 1;
        for (int i = n - depth - 1; i > 0; i--) {
            numberOfCase *= i;
        }
        long index = k / numberOfCase;
        long remainder = k % numberOfCase;
        for (int i = 1, count = 0; i <= n; i++) {
            if (isVisited[i - 1]) {
                continue;
            }
            if (count == index) {
                result[depth] = i;
                isVisited[i - 1] = true;
                break;
            }
            count++;
        }
        getThatWay(n, remainder, depth + 1, isVisited, result);
    }
}
