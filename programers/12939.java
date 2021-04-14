import java.util.*;

class Solution {
    public String solution(String s) {
        int[] array = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array) {
            max = Math.max(max, element);
            min = Math.min(min, element);
        }
        return min + " " + max;
    }
}
