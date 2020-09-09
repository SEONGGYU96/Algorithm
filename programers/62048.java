class Solution {
    public long solution(long w, long h) {
            
        return w * h - w - h + gcd(w, h);
    }
    
    
    private long gcd(long a, long b) {
        if (b == 0) { 
            return a; 
        } 
        return gcd(b, a % b);
    }
}
