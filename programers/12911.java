class Solution {
    public int solution(int n) {
        int count = Integer.bitCount(n);
        for (int i = n + 1; i <= 1000000; i++) {
            if (Integer.bitCount(i) == count) {
                return i;
            }
        }
        return -1;
    }
}
