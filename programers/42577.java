import java.util.Arrays;

class Solution {
        public boolean solution(String[] phone_book) {

            Arrays.sort(phone_book, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
            
            for (int i = 0; i < phone_book.length; i++) {
                for (int j = i + 1; j < phone_book.length; j++) {
                    String currentPhone = phone_book[i];
                    String targetPhone = phone_book[j];
                    if (currentPhone.equals(targetPhone.substring(0, currentPhone.length()))) {
                        return false;
                    }
                }
            }
            return true;
        }
        
    }
