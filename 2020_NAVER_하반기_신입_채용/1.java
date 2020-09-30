class Solution {
    public String solution(String m, String k) {
        StringBuilder answer = new StringBuilder();

        int head = 0;
        int prefix = 0;
        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) == k.charAt(head)) {
                answer.append(m.substring(prefix, i));
                prefix = i + 1;
                head++;

                if (head >= k.length()) {
                    answer.append(m.substring(prefix));
                    break;
                }
            }
        }
        return answer.toString();
    }
}
