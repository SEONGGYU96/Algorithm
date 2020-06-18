class Solution {
        public String solution(int a, int b) {
            String answer = "";

            int count = 0;

            for (int i = 1; i < a; i++) {
                if (i == 2) {
                    count += 29;
                } else if (i < 8) {
                    if (i % 2 != 0) {
                        count += 31;
                    } else {
                        count += 30;
                    }
                } else {
                    if (i % 2 != 0) {
                        count += 30;
                    } else {
                        count += 31;
                    }
                }
            }

            count += b - 1;
            
            String[] date = {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};

            return date[count % 7];
        }
    }
