import java.util.Arrays;
import java.util.Comparator;

class Solution {
        public String[] solution(String[] strings, int n) {
            final int index = n;

            Arrays.sort(strings, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.charAt(index) > o2.charAt(index)) {
                        return 1;
                    } else if (o1.charAt(index) < o2.charAt(index)) {
                        return -1;
                    } else {
                        return o1.compareTo(o2);
                    }
                }
            });

            return strings;
        }
    }
