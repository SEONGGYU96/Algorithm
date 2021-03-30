class Solution {

    private boolean[] isVisited;

    public int solution(String begin, String target, String[] words) {
        isVisited = new boolean[words.length];
        return dfs(begin, target, words, 0);
    }

    private int dfs(String begin, String target, String[] words, int depth) {
        if (begin.equals(target)) {
            return depth;
        }
        if (depth == words.length) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (!isVisited[i] && isChangeable(begin, words[i])) {
                isVisited[i] = true;
                min = Math.min(min, dfs(words[i], target, words, depth + 1));
                isVisited[i] = false;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private boolean isChangeable(String begin, String target) {
        int count = 0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != target.charAt(i)) {
                if (++count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }
}
