import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];

        Map<String, Map<String, Integer>> ticketMap = new HashMap<>();

        for (String[] ticket : tickets) {
            String department = ticket[0];
            String destination = ticket[1];
            if (!ticketMap.containsKey(department)) {
                Map<String, Integer> destinationMap = new HashMap<>();
                destinationMap.put(destination, 1);
                ticketMap.put(department, destinationMap);
            } else {
                Map<String, Integer> destinationMap = ticketMap.get(department);
                destinationMap.put(destination, destinationMap.containsKey(destination) ?
                        destinationMap.get(destination) + 1 : 1);
            }
        }

        answer[0] = "ICN";
        dfs(ticketMap, answer, answer[0], 1);


        return answer;
    }

    private boolean dfs(Map<String, Map<String, Integer>> ticketMap, String[] answer, String department, int depth) {
        if (depth == answer.length) {
            return true;
        }
        if (!ticketMap.containsKey(department)) {
            return false;
        }
        Map<String, Integer> destinationMap = ticketMap.get(department);
        List<String> destinations = new ArrayList<>(destinationMap.keySet());
        Collections.sort(destinations);

        for (String destination : destinations) {
            if (destinationMap.get(destination) == 0) {
                continue;
            }
            destinationMap.put(destination, destinationMap.get(destination) - 1);
            answer[depth] = destination;
            if (dfs(ticketMap, answer, answer[depth], depth + 1)) {
                return true;
            }
            destinationMap.put(destination, destinationMap.get(destination) + 1);
        }
        return false;
    }
}
