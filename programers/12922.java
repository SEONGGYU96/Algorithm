class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                if (answer.charAt(i - 1) == '수') {
                    answer.append('박');
                } else {
                    answer.append('수');
                }
            } else {
                answer.append('수');
            }
        }
        
        return answer.toString();
    }
}
