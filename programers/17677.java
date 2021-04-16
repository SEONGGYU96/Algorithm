import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> parsed1 = parsing(str1);
        List<String> parsed2 = parsing(str2);

        HashSet<String> set1 = new HashSet<>(parsed1);
        HashSet<String> set2 = new HashSet<>(parsed2);

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        setCount(map1, parsed1);
        setCount(map2, parsed2);

        HashSet<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        HashSet<String> union = new HashSet<>(set1);
        union.addAll(set2);

        int intersectionCount = getCount(true, intersection, map1, map2);
        int unionCount = getCount(false, union, map1, map2);

        double result = unionCount == 0 ? 1 : (double) intersectionCount / (double) unionCount;

        return (int) (result * 65536);
    }

    private int getCount(boolean isMin, HashSet<String> set, HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
        int count = 0;

        for (String element : set) {
            int a = map1.getOrDefault(element, 0);
            int b = map2.getOrDefault(element, 0);
            count += isMin ? Math.min(a, b) : Math.max(a, b);
        }

        return count;
    }

    private void setCount(HashMap<String, Integer> map, List<String> parsed) {
        for (String s : parsed) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
    }

    private List<String> parsing(String str) {
        str = str.toLowerCase();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String element = str.substring(i, i + 2);
            if (element.replaceAll("[^a-z]", "").length() != element.length()) {
                continue;
            }
            result.add(element);
        }
        return result;
    }
}
