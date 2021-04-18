import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        Queue<String> queue = new LinkedList<>();

        for (String city : cities) {
            String current = city.toLowerCase();
            answer = queue.remove(current) ? answer + 1 : answer + 5;
            if (queue.size() >= cacheSize) {
                queue.poll();
            }
            queue.offer(current);
        }
        return answer;
    }
}
