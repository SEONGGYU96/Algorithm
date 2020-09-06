class Solution {
    public boolean solution(int x) {
        return x % String.valueOf(x).chars().map(i -> (char) i - '0').sum() == 0;
    }
}
