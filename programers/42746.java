import java.util.ArrayList;
import java.util.Collections;

 class Solution {
        public String solution(int[] numbers) {
            ArrayList<Number> numberArray = new ArrayList<>();
            boolean isSignificantFigures = false;

            for (int number : numbers) {
                numberArray.add(new Number(number));
            }

            Collections.sort(numberArray);

            StringBuilder answer = new StringBuilder();

            for (int i = numberArray.size() - 1; i >= 0; i--) {
                if (numberArray.get(i).getNumber() == 0) {
                    if (isSignificantFigures) {
                        answer.append(numberArray.get(i).numberString);
                    }
                } else {
                    answer.append(numberArray.get(i).numberString);
                    isSignificantFigures = true;
                }
            }
            
            if (answer.toString().isEmpty()) {
                answer.append("0");
            }

            return answer.toString();

        }

        class Number implements Comparable<Number> {
            int number;
            String numberString;

            public Number(int number) {
                this.number = number;
                this.numberString = Integer.toString(number);
            }

            public int getNumber() {
                return number;
            }

            public String getNumberToString() {
                return numberString;
            }

            @Override
            public int compareTo(Number num) {
                String temp1 = numberString + num.getNumberToString();
                String temp2 = num.getNumberToString() + numberString;

                if (Integer.parseInt(temp1) > Integer.parseInt(temp2)) {
                    return 1;
                } else if (Integer.parseInt(temp1) < Integer.parseInt(temp2)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
