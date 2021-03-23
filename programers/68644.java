import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer;
        ArrayList<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        add(list, numbers, set, 1, 0, 0);
        add(list, numbers, set, 1, 1, numbers[0]);

        Collections.sort(list);
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    private void add(ArrayList<Integer> list, int[] numbers, Set<Integer> set, int depth, int count, int sum) {
        if (count == 2) {
            if (!set.contains(sum)) {
                list.add(sum);
                set.add(sum);
            }
            return;
        }
        if (depth == numbers.length) {
            return;
        }
        add(list, numbers, set, depth + 1, count + 1, sum + numbers[depth]);
        add(list, numbers, set, depth + 1, count, sum);
    }
}
