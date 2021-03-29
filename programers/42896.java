import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;

        Set<Integer>[] dp = new Set[9];
        int sequenceNumber = 0;

        for (int i = 1; i <= 8; i++) {
            Set<Integer> set = new HashSet<>();
            sequenceNumber += N * Math.pow(10, i - 1);
            set.add(sequenceNumber);
            dp[i] = set;

            for (int j = 1; j < i; j++) {
                for (int x : dp[j]) {
                    for (int y : dp[i - j]) {
                        set.add(x + y);
                        set.add(x - y);
                        set.add(x * y);
                        if (y != 0) {
                            set.add(x / y);
                        }
                    }
                }
            }
            if (set.contains(number)) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}
