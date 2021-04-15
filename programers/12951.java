class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        
        String[] parsed = s.toLowerCase().split("");
        boolean isFirstCase = true;
        
        for (String element: parsed) {
            answer.append(isFirstCase ? element.toUpperCase() : element);
            isFirstCase = element.equals(" ");
        }
        
        return answer.toString();
    }
}
