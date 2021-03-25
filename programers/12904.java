class Solution {
    public int solution(String s) {
        int answer = 1;
        int length = s.length();
        while (length >= 2) {
            for (int i = 0; i < s.length() - length + 1; i++) {
                boolean success = true;
                int head = i;
                int tail = i + length - 1;
                while (head < tail) {
                    int left = s.charAt(head);
                    int right = s.charAt(tail);
                    if (left != right) {
                        success = false;
                        break;
                    }
                    head++;
                    tail--;
                }
                if (success) {
                    return length;
                }
            }
            length--;
        }
        return answer;
    }
}

