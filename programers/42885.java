//첫 시도
class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;
            boolean[] remove = new boolean[people.length];

            Arrays.fill(remove, false);

            for (int i = 0; i < people.length; i++) {
                if (remove[i]) {
                    continue;
                }

                int sum = people[i];
                int max = 0;
                int maxIndex = 0;
                for (int j = i + 1; j < people.length; j++) {
                    if (sum + people[j] > limit || remove[j]) {
                        continue;
                    }
                    if (sum + people[j] > max) {
                        max = sum + people[j];
                        maxIndex = j;
                    }
                }
                remove[maxIndex] = true;
                answer++;
            }

            if (!remove[people.length - 1]) {
                answer++;
            }

            return answer;
        }
    }

//시간초과를 위한 두 번째 도전
class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;

            Stack<Integer> weights = new Stack<>();

            for (int weight : people) {
                weights.push(weight);
            }

            while (weights.size() > 0) {
                int first = weights.pop();
                int max = 0;
                int maxIndex = -1;
                for (int i = 0; i < weights.size(); i++) {
                    int second = weights.get(i);

                    if (first + second > limit) {
                        continue;
                    }

                    if (first + second > max) {
                        max = first + second;
                        maxIndex = i;
                    }
                }
                if (maxIndex != -1) {
                    weights.remove(maxIndex);
                }
                answer++;
            }

            return answer;
        }
    }

// 마지막 도전 (통과)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;

            Arrays.sort(people);
            List<Integer> weights = new ArrayList<>();

            for (int weight : people) {
                weights.add(weight);
            }

            while (!weights.isEmpty()) {
                if (weights.size() == 1) {
                    answer++;
                    break;
                }
                
                int first = weights.get(0);
                int second = weights.get(weights.size() - 1);
                
                if (first + second <= limit) {
                    weights.remove(0);
                }
                weights.remove(weights.size() - 1);
                
                answer++;
            }

            return answer;
        }
    }
