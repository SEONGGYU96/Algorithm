import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Solution {
    public int[] solution(int N, int[] stages) {
            int[] answer = new int[N];

            HashMap<Integer, Integer> userStage = new HashMap<>();
            List<FailRateOfStage> failRateOfStageList = new ArrayList<>();
            int numberOfUsers = stages.length;

            for (int stage : stages) {
                if (userStage.containsKey(stage)) {
                    userStage.replace(stage, userStage.get(stage) + 1);
                } else {
                    userStage.put(stage, 1);
                }
            }

            int previousUser = 0;
            for (int i = 1; i <= N; i ++) {
                if (!userStage.containsKey(i)) {
                    failRateOfStageList.add(new FailRateOfStage(i, 0));
                    continue;
                }

                int currentUser = userStage.get(i);
                failRateOfStageList.add(new FailRateOfStage(i, (double) currentUser / (numberOfUsers - previousUser)));
                previousUser += currentUser;
            }

            Collections.sort(failRateOfStageList);

            for (int i = 0; i < N; i++) {
                answer[i] = failRateOfStageList.get(i).stage;
            }

            return answer;
        }

        class FailRateOfStage implements Comparable<FailRateOfStage> {
            int stage;
            double failRate;

            public FailRateOfStage(int stage, double failRate) {
                this.stage = stage;
                this.failRate = failRate;
            }

            public int getStage() {
                return stage;
            }

            public double getFailRate() {
                return failRate;
            }

            @Override
            public int compareTo(FailRateOfStage failRateOfStage) {
                if (failRate > failRateOfStage.failRate) {
                    return -1;
                } else if (failRate < failRateOfStage.failRate) {
                    return 1;
                } else {
                    return Integer.compare(stage, failRateOfStage.stage);
                }
            }
        }
}
