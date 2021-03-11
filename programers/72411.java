import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answerList = new ArrayList<>();

        Map<String, Integer> newCourseMap = new HashMap<>();
        Map<Integer, Integer> maxCountForLengthOfCourse = new HashMap<>();
        Set<Integer> courseSet = new HashSet<>();
        for (int value : course) {
            courseSet.add(value);
        }

        for (String order : orders) {
            char[] foods = order.toCharArray();
            Arrays.sort(foods);
            makeNewCourse(newCourseMap, maxCountForLengthOfCourse, courseSet, foods, 0, "");
        }

        for (String newCourse : newCourseMap.keySet()) {
            int length = newCourse.length();
            int count = newCourseMap.get(newCourse);
            int maxCount = maxCountForLengthOfCourse.get(length);
            if (maxCount >= 2 && count >= maxCount) {
                answerList.add(newCourse);
            }
        }
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
    
    private void makeNewCourse(Map<String, Integer> newCourseMap, Map<Integer, Integer> maxCountForLengthOfCourse,
                                      Set<Integer> courseSet, char[] foods, int depth, String newCourse) {
        if (depth == foods.length) {
            int length = newCourse.length();
            if (length > 1 && courseSet.contains(length)) {
                int count = newCourseMap.getOrDefault(newCourse, 0) + 1;
                newCourseMap.put(newCourse, count);
                int previousCount = maxCountForLengthOfCourse.getOrDefault(length, 0);
                maxCountForLengthOfCourse.put(length, Math.max(count, previousCount));
            }
            return;
        }

        char current = foods[depth];
        makeNewCourse(newCourseMap, maxCountForLengthOfCourse, courseSet, foods, depth + 1, newCourse + current);
        makeNewCourse(newCourseMap, maxCountForLengthOfCourse, courseSet, foods, depth + 1, newCourse);
    }
}
