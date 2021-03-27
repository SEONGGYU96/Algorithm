class Solution {
    private static final int MAX = 1000000001;

    public int solution(int[] a) {
       int answer = 2;
       int[] leftMinDp = new int[a.length];
       int[] rightMinDp = new int[a.length];

       for (int i = 0, min = MAX; i < a.length; i++) {
           leftMinDp[i] = Math.min(min, a[i]);
           min = leftMinDp[i];
       }

       for (int i = a.length - 1, min = MAX; i >= 0; i--) {
           rightMinDp[i] = Math.min(min, a[i]);
           min = rightMinDp[i];
       }

       for (int i = 1; i < a.length - 1; i++) {
           int target = a[i];

           if (leftMinDp[i - 1] < target && target > rightMinDp[i + 1]) {
               continue;
           }
           answer++;
       }

       return answer;
    }
}
