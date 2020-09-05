import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {
        public long solution(long n) {
            List<Integer> intList = new ArrayList<>();

            while (n >= 1) {
                intList.add((int) (n % 10));
                n /= 10;
            }

            Collections.sort(intList, new Comparator<Integer>() {

                @Override
                public int compare(Integer integer, Integer t1) {
                    if (integer < t1) {
                        return 1;
                    } else if (integer > t1) {
                        return -1;
                    }
                    return 0;
                }
            });

            StringBuilder answerString = new StringBuilder();

            for (int i = 0; i < intList.size(); i++) {
                answerString.append(intList.get(i));
            }

            return Long.parseLong(answerString.toString());
        }
    }
