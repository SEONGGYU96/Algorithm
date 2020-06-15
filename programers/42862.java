import java.util.Arrays;

class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            int answer = 0;

            for (int i = 0; i < reserve.length; i++) {
                for (int j = 0; j < lost.length; j++) {
                    if (reserve[i] == lost[j]) {
                        reserve[i] = -1;
                        lost[j] = -1;
                        break;
                    }
                }
            }

            for (int i = 0; i < lost.length; i++) {
                if (lost[i] == -1) {
                    continue;
                }

                for (int j = 0; j < reserve.length; j++) {
                    if (reserve[j] == lost[i] + 1) {
                        lost[i] = -1;
                        reserve[j] = -1;
                    } else if (reserve[j] == lost[i] - 1) {
                        lost[i] = -1;
                        reserve[j] = -1;
                    }
                }
            }

            boolean[] students = new boolean[n];

            Arrays.fill(students, true);

            for (int lostStudent : lost) {
                if (lostStudent != -1) {
                    students[lostStudent - 1] = false;
                }
            }

            for (boolean student : students) {
                if (student) {
                    answer++;
                }
            }

            return answer;
        }
    }
