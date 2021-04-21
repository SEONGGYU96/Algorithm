class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();

        int startNumber = 0;

        StringBuilder builder = new StringBuilder();

        while (builder.length() < m * t) {
            builder.append(Integer.toString(startNumber++, n));
        }

        String temp = builder.toString();

        for (int i = 0; i < t; i++) {
            answer.append(temp.charAt(i * m + p - 1));
        }

        return answer.toString().toUpperCase();
    }
}
