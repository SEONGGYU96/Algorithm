class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;
        int temp = n;
        while (temp > 1) {
            temp /= 2;
            answer++;
        }

        int head = 1;
        int tail = n + 1;
        
        while (head < tail) {
            int center = (head + tail) / 2;
            
            if (a < center && b < center) {
                tail = center;
                answer--;
            } else if (center <= a && center <= b) {
                head = center;
                answer--;
            } else {
                break;
            }
        }

        return answer;
    }
}
