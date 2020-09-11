import java.util.HashMap;

class Solution {
        public int solution(String[][] clothes) {
            int answer = 1;

            HashMap<String, Integer> clotheMap = new HashMap<>();

            for (String[] cloth : clothes) {
                if (clotheMap.containsKey(cloth[1])) {
                    clotheMap.put(cloth[1], clotheMap.get(cloth[1]) + 1);
                } else {
                    clotheMap.put(cloth[1], 1);
                }
            }

            for (String key : clotheMap.keySet()) {
                answer *= clotheMap.get(key) + 1;
            }

            return --answer;
        }
    }
