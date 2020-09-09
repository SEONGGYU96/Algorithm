import java.util.Stack;

class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            
            for (int i = 0; i < prices.length; i++) {
                for (int j = i; j < prices.length; j++) {
                    if (prices[i] > prices[j]) {
                        answer[i] = j - i;
                        break;
                    }
                    
                    if (j == prices.length - 1) {
                        answer[i] = j - i;
                    }
                }
            }
            
            return answer;
        }
    }


//실패했던 스택 코드
import java.util.Stack;

class Solution {
        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];

            Stack<Integer> priceStack = new Stack<>();
            priceStack.push(0);

            int count = 0;
            for (int i = 0; i < prices.length; i++) {
                answer[i] = prices.length - i - 1;
                while (prices[i] < priceStack.peek()) {
                    priceStack.pop();
                    answer[i - 1 - count] = ++count;
                }
                count = 0;
                priceStack.push(prices[i]);
            }

            return answer;
        }
    }
