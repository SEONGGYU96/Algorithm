import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};

        Pattern pattern = Pattern.compile("([0-9]+,)*[0-9]+");
        Matcher matcher = pattern.matcher(s);

        ArrayList<HashSet<Integer>> list = new ArrayList<>();

        while (matcher.find()) {
            String current = matcher.group();
            String[] elements = current.split(",");
            HashSet<Integer> set = new HashSet<>();
            for (String element : elements) {
                set.add(Integer.parseInt(element));
            }
            list.add(set);
        }

        list.sort(Comparator.comparingInt(HashSet::size));

        answer = new int[list.size()];
        answer[0] = (int) list.get(0).toArray()[0];

        for (int i = 0; i < list.size() - 1; i++) {
            HashSet<Integer> result = new HashSet<>(list.get(i + 1));
            result.removeAll(list.get(i));
            answer[i + 1] = (int) result.toArray()[0];
        }

        return answer;
    }
}
