import java.util.HashSet;
import java.util.Set;

class Solution {
        public int solution(String numbers) {
            Set<Integer> primeNumber = new HashSet<>();

            char[] parsed = numbers.toCharArray();

            for (int i = 1; i <= parsed.length; i++) {
                getPermutation(primeNumber, parsed, 0, i);
            }

            return primeNumber.size();
        }

        private void getPermutation(Set<Integer> primeNumber, char[] numbers, int depth, int count) {
            if (depth == count) {
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    temp.append(numbers[i]);
                }
                int result = Integer.parseInt(temp.toString());

                if (isPrimeNumber(result)) {
                    primeNumber.add(result);
                }
            } else {
                for (int i = depth; i < numbers.length; i++) {
                    char temp = numbers[depth];
                    numbers[depth] = numbers[i];
                    numbers[i] = temp;

                    getPermutation(primeNumber, numbers, depth + 1, count);

                    numbers[i] = numbers[depth];
                    numbers[depth] = temp;
                }
            }
        }

        private boolean isPrimeNumber(int n) {
            if (n == 1 || n == 0) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
