class Solution {
        public boolean solution(String s) {

            if (s.length() != 4) {
                return false;
            }

            char[] charArray = s.toCharArray();

            for (char c : charArray) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            
            return true;
        }
    }
