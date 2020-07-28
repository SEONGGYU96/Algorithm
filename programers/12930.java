class Solution {
        public String solution(String s) {
            char[] charArray = s.toCharArray();
            boolean isEven = true;

            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == ' ') {
                    if (!isEven) {
                        isEven = true;
                    }
                } else {
                    charArray[i] = switchCase(charArray[i], isEven);
                    isEven = !isEven;
                }
            }
            
            return new String(charArray);
        }

        private Boolean isUpperCase(char c) {
            return c >= 65 && c <= 90;
        }

        private char switchCase(char c, boolean isEven) {
            if (isEven) {
                if (!isUpperCase(c)) {
                    return (char) (c - 32);
                } else {
                    return c;
                }
            } else {
                if (isUpperCase(c)) {
                    return (char) (c + 32);
                } else {
                    return c;
                }
            }
        }
    }
