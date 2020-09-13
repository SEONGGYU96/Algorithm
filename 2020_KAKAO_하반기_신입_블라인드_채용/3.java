//시간초과
import java.util.ArrayList;
import java.util.List;

class Solution {
        public int[] solution(String[] info, String[] query) {
            int[] answer;

            List<Integer> answerList = new ArrayList<>();

            List<Participant> participants = new ArrayList<>();

            for (String participantInfo : info) {
                String[] parsed = participantInfo.split(" ");

                participants.add(new Participant(parsed[0], parsed[1], parsed[2], parsed[3], Integer.parseInt(parsed[4])));
            }

            for (String currentQuery : query) {
                int currentNumber = getNumberOfParticipant(participants, currentQuery);
                answerList.add(currentNumber);
            }

            answer = new int[answerList.size()];

            for (int i = 0; i < answerList.size(); i++) {
                answer[i] = answerList.get(i);
            }

            return answer;
        }

        private int getNumberOfParticipant(List<Participant> participants, String query) {
            int count = 0;
            for (Participant participant : participants) {
                if (participant.hasCondition(query)) {
                    count++;
                }
            }
            return count;
        }

        class Participant {
            private String language;
            private String part;
            private String career;
            private String food;
            private int score;

            public Participant(String language, String part, String career, String food, int score) {
                this.language = language;
                this.part = part;
                this.career = career;
                this.food = food;
                this.score = score;
            }

            public boolean hasCondition(String conditions) {
                String[] parsed = conditions.split(" and ");
                String[] foodAndScore = parsed[3].split(" ");
                boolean language;
                boolean part;
                boolean career;
                boolean food;
                boolean score;

                if (!parsed[0].equals("-")) {
                    language = this.language.equals(parsed[0]);
                } else {
                    language = true;
                }

                if (!parsed[1].equals("-")) {
                    part = this.part.equals(parsed[1]);
                } else {
                    part = true;
                }

                if (!parsed[2].equals("-")) {
                    career = this.career.equals(parsed[2]);
                } else {
                    career = true;
                }

                if (!foodAndScore[0].equals("-")) {
                    food = this.food.equals(foodAndScore[0]);
                } else {
                    food = true;
                }

                if (!foodAndScore[1].equals("-")) {
                    score = this.score >= Integer.parseInt(foodAndScore[1]);
                } else {
                    score = true;
                }

                return language && part && career && food && score;
            }
        }
    }

//시간초과 2
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
        public int[] solution(String[] info, String[] query) {
            int[] answer = {};

            List<Integer> list = new ArrayList<>();

            HashMap<String, Set<Integer>> cache = new HashMap<>();

            for (int i = 0; i < info.length; i++) {
                String[] parsed = info[i].split(" ");
                for (int j = 0; j < parsed.length - 1; j++) {
                    String current = parsed[j];
                    Set<Integer> temp;
                    if (cache.containsKey(current)) {
                        temp = cache.get(current);
                    } else {
                        temp = new HashSet<>();
                    }
                    temp.add(i);
                    cache.put(current, temp);
                }
            }

            for (String currentQuery : query) {
                String condition = currentQuery.replaceAll("[0-9]", "");
                condition = condition.substring(0, condition.length() - 1);
                String[] conditions = condition.split(" and ");
                int score = Integer.parseInt(currentQuery.replaceAll("[^0-9]", ""));

                Set<Integer> result = new HashSet<>();
                
                for (int i = 0; i < info.length; i++) {
                    result.add(i);
                }

                for (String currentCondition : conditions) {
                    if (cache.containsKey(currentCondition)) {
                        result.retainAll(cache.get(currentCondition));
                    } else if (!currentCondition.equals("-")) {
                        result.clear();
                        break;
                    }
                }

                if (result.size() == 0) {
                    list.add(0);
                    continue;
                }

                int removeCount = 0;
                for (int currentIdx : result) {
                    int currentScore = Integer.parseInt(info[currentIdx].replaceAll("[^0-9]", ""));
                    if (currentScore < score) {
                        removeCount++;
                    }
                }
                list.add(result.size() - removeCount);
            }

            answer = new int[list.size()];

            for (int i = 0; i < answer.length; i++) {
                answer[i] = list.get(i);
            }

            return answer;
        }
    }
