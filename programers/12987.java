import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        int headOfA = 0;
        int headOfB = 0;
        while (headOfB < B.length) {
            int a = A[headOfA];
            int b = B[headOfB];
            if (a < b) {
                answer++;
                headOfA++;
            }
            headOfB++;
        }
        return answer;
    }
}
