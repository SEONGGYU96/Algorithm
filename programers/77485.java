import java.util.*;
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] map = new int[rows][columns];
        
        for (int i = 0, count = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = count++;
            }
        }
        
        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(map, queries[i]);
        }
        
        return answer;
    }
    
    private int rotate(int[][] map, int[] query) {
        LinkedList<Integer> borderLine = getBorderLine(map, query);
        borderLine.offerFirst(borderLine.removeLast());
        return put(map, query, borderLine);
    }
    
    private LinkedList<Integer> getBorderLine(int[][] map, int[] query) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int y = y1; y <= y2; y++) {
            queue.offer(map[x1][y]);
        }
        for (int x = x1 + 1; x <= x2; x++) {
            queue.offer(map[x][y2]);
        }
        for (int y = y2 - 1; y >= y1; y--) {
            queue.offer(map[x2][y]);
        }
        for (int x = x2 - 1; x > x1; x--) {
            queue.offer(map[x][y1]);
        }
        
        return queue;
    }
    
    private int put(int[][] map, int[] query, LinkedList<Integer> borderLine) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;
        int min = map.length * map[0].length;
        for (int y = y1; y <= y2; y++) {
            map[x1][y] = borderLine.poll();
            min = Math.min(min, map[x1][y]);
        }
        for (int x = x1 + 1; x <= x2; x++) {
            map[x][y2] = borderLine.poll();
            min = Math.min(min, map[x][y2]);
        }
        for (int y = y2 - 1; y >= y1; y--) {
            map[x2][y] = borderLine.poll();
            min = Math.min(min, map[x2][y]);
        }
        for (int x = x2 - 1; x > x1; x--) {
            map[x][y1] = borderLine.poll();
            min = Math.min(min, map[x][y1]);
        }
        return min;
    }
}
