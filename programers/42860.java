class Solution {

    private static final int A = 65;
    private static final int Z = 90;

    public int solution(String name) {
        int answer = 0;
        int minMove = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            answer += getMinimumChange(name.charAt(i));
            int next = i + 1;
            while (next < name.length() && name.charAt(next) == 'A') {
                next++;
            }
            minMove = Math.min(minMove, i * 2 + name.length() - next);
        }

        return answer + minMove;
    }

    private int getMinimumChange(int to) {
        return Math.min(to - A, Z - to + 1);
    }
}
