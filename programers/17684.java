import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer;
        ArrayList<Integer> answerList = new ArrayList<>();

        HashMap<String, Integer> dictionary = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            dictionary.put(String.valueOf((char) ('A' + i)), i + 1);
        }
        int lastIndex = 26;

        String target = msg;

        while (!target.isEmpty()) {
            for (int i = target.length(); i >= 0; i--) {
                String temp = target.substring(0, i);
                if (dictionary.containsKey(temp)) {
                    answerList.add(dictionary.get(temp));
                    target = target.substring(i);
                    if (!target.isEmpty()) {
                        dictionary.put(temp + target.charAt(0), ++lastIndex);
                    }
                    break;
                }
            }
        }
        answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
