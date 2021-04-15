class Solution {
    public int solution(int[] arr) {
        int lcm = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            lcm = lcm(lcm, arr[i]);
        }
        return lcm;
    }

    private int gcd(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        return big % small == 0 ? small : gcd(small, big % small);
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
