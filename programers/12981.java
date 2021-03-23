import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> wordSet = new HashSet<>();

        int i;
        char previous = words[0].charAt(words[0].length() - 1);
        wordSet.add(words[0]);
        boolean isFound = false;

        for (i = 1; i < words.length; i++) {
            String current = words[i];
            char start = current.charAt(0);
            if (wordSet.contains(current) || start != previous) {
                isFound = true;
                break;
            }
            wordSet.add(current);
            previous = current.charAt(current.length() - 1);
            ;
        }
        if (isFound) {
            answer[0] = i % n + 1;
            answer[1] = i / n + 1;
        }
        return answer;
    }
}
