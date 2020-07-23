class Solution {
    public int solution(String s) {
        int answer = 0;
        boolean isPositive;
        
        if (s.charAt(0) == '-') {
            answer = Integer.parseInt(s.substring(1)) * -1;
        } else if (s.charAt(0) == '+') {
            answer = Integer.parseInt(s.substring(1));
        } else {
            answer = Integer.parseInt(s);
        }
        
        return answer;
    }
}
