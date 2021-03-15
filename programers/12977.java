import java.util.HashSet;
import java.util.Set;

class Solution {
    
    private int answer = 0;
    private Set<Integer> primeSet;
    
    public int solution(int[] nums) {
        primeSet = new HashSet<>();
        check(nums, 0, 0, 3, 0);
        return answer;
    }
    
    private void check(int[] nums, int depth, int count, int max, int current) {
        if (count == max) {
            if (primeSet.contains(current)) {
                answer++;
            } else {
                if (isPrimeNumber(current)) {
                    primeSet.add(current);
                    answer++;
                }
            }
            return;
        }

        if (depth == nums.length) {
            return;
        }

        check(nums, depth + 1, count + 1, max, current + nums[depth]);
        check(nums, depth + 1, count, max, current);
    }
    
    private boolean isPrimeNumber(int num) {
        boolean result = true;
        int end = (int) Math.sqrt(num);
        for (int i = 2; i <= end; i++) {
            if (num % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}
