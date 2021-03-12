import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        Map<String, ArrayList<Integer>> map = new HashMap<>();

        for (String _info : info) {
            int score = Integer.parseInt(_info.replaceAll("[^0-9]", ""));
            String[] conditions = _info.replaceAll("[0-9]", "").split(" ");
            addCasesOfConditions(map, conditions, 0, score, "");
        }

        for (String condition : map.keySet()) {
            Collections.sort(map.get(condition));
        }

        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            int score = Integer.parseInt(q.replaceAll("[^0-9]", ""));
            String condition = q.replaceAll("\\band", "").replaceAll("[0-9 ]", "");
            if (!map.containsKey(condition)) {
                continue;
            }

            List<Integer> scores = map.get(condition);
            int head = 0;
            int tail = scores.size();
            while (head < tail) {
                int center = (head + tail) / 2;
                int value = scores.get(center);
                if (value >= score) {
                    tail = center;
                } else {
                    head = center + 1;
                }
            }
            answer[i] = scores.size() - head;
        }
        return answer;
    }
    
    private void addCasesOfConditions(Map<String, ArrayList<Integer>> map, String[] conditions, int depth, int score, String caseOfCondition) {
        if (depth == conditions.length) {
            if (!map.containsKey(caseOfCondition)) {
                map.put(caseOfCondition, new ArrayList<>());
            }
            map.get(caseOfCondition).add(score);
            return;
        }

        addCasesOfConditions(map, conditions, depth + 1, score, caseOfCondition + "-");
        addCasesOfConditions(map, conditions, depth + 1, score, caseOfCondition + conditions[depth]);
    }
}
