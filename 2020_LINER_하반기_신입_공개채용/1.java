import java.util.Arrays;
import java.util.Stack;

class Solution {
        public int solution(int[][] boxes) {
            int answer;
            Stack<Integer> productStack = new Stack<>();

            int size = boxes.length * boxes[0].length;
            int[] temp = new int[size];

            int row = 0;
            int col = 0;

            for (int i = 0; i < temp.length; i++) {
                temp[i] = boxes[row][col++];
                if (col > 1) {
                    col = 0;
                    row++;
                }
            }

            Arrays.sort(temp);

            int boxCount = 0;

            for (int product : temp) {
                if (productStack.isEmpty()) {
                    productStack.push(product);
                } else {
                    if (productStack.peek() == product) {
                        productStack.pop();
                        boxCount++;
                    } else {
                        productStack.push(product);
                    }
                }
            }

            answer = Math.min(boxes.length - boxCount, productStack.size());

            return answer;
        }
    }
