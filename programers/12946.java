import java.util.*;

class Solution {
    private ArrayList<int[]> history = new ArrayList<>();
    public int[][] solution(int n) {
        move(n, 0, 1, 2);
        int[][] answer = new int[history.size()][];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = history.get(i);
        }
        return answer;
    }
    
    private void move(int n, int start, int mid, int end) {
        if (n == 1) {
            history.add(new int[]{start + 1, end + 1});
            return;
        }
        move(n - 1, start, end, mid);
        move(1, start, mid, end);
        move(n - 1, mid, start, end);
    }
}
