import java.util.HashMap;

class Solution {
        public String solution(String[] participant, String[] completion) {
            HashMap<String, Integer> CompletionPlayer = new HashMap<>();
            String answer = "";
            
            for (String s : completion) {
                Integer time = CompletionPlayer.get(s);
                
                if (time != null) {
                    time += 1;
                }
                else {
                    time = 1;
                }
                CompletionPlayer.put(s, time);
            }

            for (String s : participant) {
                Integer time = CompletionPlayer.get(s);
                if (time == null || time == 0) {
                    answer = s;
                    break;
                    
                } else {
                    time -= 1;
                }
                CompletionPlayer.put(s, time);
            }

            return answer;
        }
    }
