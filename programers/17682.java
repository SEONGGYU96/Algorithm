class Solution {
        public int solution(String dartResult) {
            int answer = 0;
            int count = -1;

            boolean inDigit = false;

            char[] parsing = dartResult.toCharArray();

            int[] parsedAnswer = new int[3];

            for (char item : parsing) {
                if (Character.isDigit(item)) {
                    if (inDigit) {
                        parsedAnswer[count] *= 10;
                        parsedAnswer[count] += item - '0';
                        inDigit = false;
                    } else {
                        count++;
                        answer += parsedAnswer[count];
                        parsedAnswer[count] = item - '0';
                        inDigit = true;
                    }
                    continue;
                }
                switch (item) {
                    case 'S':
                        break;
                    case 'D':
                        parsedAnswer[count] = (int) Math.pow(parsedAnswer[count], 2);
                        break;
                    case 'T':
                        parsedAnswer[count] = (int) Math.pow(parsedAnswer[count], 3);
                        break;

                    case '*':
                        parsedAnswer[count] *= 2;
                        if (count != 0) {
                            parsedAnswer[count - 1] *= 2;
                        }
                        break;
                    case '#':
                        parsedAnswer[count] *= -1;
                        break;
                }
                inDigit = false;
            }

            for(int parse : parsedAnswer) {
                answer += parse;
            }

            return answer;
        }
    }
