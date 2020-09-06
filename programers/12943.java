class Solution {
    int count = 0;
    
    public int solution(int num) {
        collatz(num);
        
        return count >= 500? -1 : count;
    }
    
    private void collatz(long num) {
        if (num == 1) {
            return;
        }
        count++;
        
        num = num % 2 == 0 ? num / 2 : num * 3 + 1;
        
        if (num == 1 || count >= 500) {
            return;
        } else {
            collatz(num);
        }
    }
}
