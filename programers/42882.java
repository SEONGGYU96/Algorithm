import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, Comparator.comparing(o -> o[1]));
        List<int[]> list = new ArrayList<>(Arrays.asList(routes));

        while (!list.isEmpty()) {
            int camera = list.get(0)[1];
            answer++;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[0] <= camera) {
                    list.remove(i);
                    i--;
                }
            }
        }
        return answer; 
    }
}
