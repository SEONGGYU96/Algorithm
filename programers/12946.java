import java.util.*;

class Solution {
    private ArrayList<int[]> history = new ArrayList<>();
    public int[][] solution(int n) {
        int[][] answer = {};
        Stack<Integer>[] towers = new Stack[3];
        
        for (int i = 0; i < 3; i++) {
            towers[i] = new Stack<>();
        }
        for (int i = n; i > 0; i--) {
            towers[0].push(i);
        }
        move(towers, n, 0, 1, 2);
        answer = new int[history.size()][];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = history.get(i);
        }
        return answer;
    }
    
    private void move(Stack<Integer>[] towers, int n, int start, int mid, int end) {
        if (n == 1) {
            history.add(new int[]{start + 1, end + 1});
            towers[end].push(towers[start].pop());
            return;
        }
        move(towers, n - 1, start, end, mid);
        move(towers, 1, start, mid, end);
        move(towers, n - 1, mid, start, end);
    }
}
