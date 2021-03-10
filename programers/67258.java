import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> frequency = new HashMap<>();
        Set<String> gemSet = new HashSet<>(Arrays.asList(gems));

        int head = 0;
        int tail = 0;
        int[] answer = new int[2];
        int range = Integer.MAX_VALUE;

        frequency.put(gems[0], 1);

        while (head <= tail) {
            int currentSize = frequency.keySet().size();

            if (currentSize == gemSet.size()) {
                int currentRange = tail - head;
                if (range > currentRange) {
                    range = currentRange;
                    answer[0] = head + 1;
                    answer[1] = tail + 1;
                }
            }
            if (currentSize >= gemSet.size()) {
                if (frequency.containsKey(gems[head])) {
                    int previous = frequency.get(gems[head]);
                    if (previous == 1) {
                        frequency.remove(gems[head]);
                    } else {
                        frequency.put(gems[head], previous - 1);
                    }
                }
                head++;
            } else {
                tail++;
                if (tail >= gems.length) {
                    break;
                }
                String gem = gems[tail];
                if (!frequency.containsKey(gem)) {
                    frequency.put(gem, 1);
                } else {
                    frequency.put(gem, frequency.get(gem) + 1);
                }
            }
        }
        return answer;
    }
}
