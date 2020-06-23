import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
        public String solution(String s) {
            ArrayList<Character> c = new ArrayList<>();

            for (int i = 0; i < s.length(); i++) {
                c.add(s.charAt(i));
            }

            Collections.sort(c, new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    if (o1 < o2) {
                        return 1;
                    } else if (o1 > o2) {
                        return -1;
                    } else {
                        if (Character.isUpperCase(o1)) {
                            return 1;
                        } else {
                            return 0;
                        }
                    }
                }
            });
            
            StringBuilder answer = new StringBuilder();
            
            for (char cc : c) {
                answer.append(cc);
            }

            return answer.toString();
        }
    }
