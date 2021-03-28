import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            int current = a[i];
            if (!map.containsKey(current)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(current, list);
            } else {
                map.get(current).add(i);
            }
        }

        for (int key : map.keySet()) {
            int count = 0;
            List<Integer> indexes = map.get(key);
            if (answer > indexes.size()) {
                continue;
            }

            for (int i = 0; i < a.length - 1;) {
                if ((a[i] != key && a[i + 1] != key) || a[i] == a[i + 1]) {
                    i++;
                    continue;
                }
                count++;
                i += 2;
            }
            answer = Math.max(answer, count);
        }

        return answer * 2;
    }
}
