import java.util.Arrays;

class Solution {
    
    private int[] parents;
    
    public int solution(int n, int[][] computers) {
        int answer = n;
        parents = new int[n];
        Arrays.fill(parents, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    if (merge(i, j)) {
                        answer--;
                    }
                }
            }
        }
        return answer;
    }

    private int find(int n) {
        if (parents[n] < 0) {
            return n;
        }
        parents[n] = find(parents[n]);
        return parents[n];
    }

    private boolean merge(int n, int m) {
        int parentOfN = find(n);
        int parentOfM = find(m);
        if (parentOfN == parentOfM) {
            return false;
        }
        parents[parentOfM] = parentOfN;
        return true;
    }
}
