import java.util.*;
class Solution {
    public long solution(String expression) {
        long answer = 0;

        String[] priority = {"*+-","*-+","+*-","+-*","-*+","-+*"};
        ArrayList<String> operands = new ArrayList<>();
        ArrayList<String> operators = new ArrayList<>();

        String temp = "";
        for (int i = 0; i < expression.length(); i++) {
            String current = String.valueOf(expression.charAt(i));
            if (current.matches("\\+|-|\\*")) {
                operators.add(current);
                operands.add(temp);
                temp = "";
            } else {
                temp += current;
            }
        }
        operands.add(temp);

        for (String currentPriority : priority) {
            ArrayList<String> tempOperands = new ArrayList<>(operands);
            ArrayList<String> tempOperators = new ArrayList<>(operators);
            for (int x = 0; x < priority[0].length(); x++) {
                int head = 0;
                while (head < tempOperators.size()) {
                    if (tempOperators.get(head).equals(String.valueOf(currentPriority.charAt(x)))) {
                        switch (tempOperators.get(head)) {
                            case "*" -> tempOperands.set(head, String.valueOf(Long.parseLong(tempOperands.get(head)) * Long.parseLong(tempOperands.get(head + 1))));
                            case "+" -> tempOperands.set(head, String.valueOf(Long.parseLong(tempOperands.get(head)) + Long.parseLong(tempOperands.get(head + 1))));
                            case "-" -> tempOperands.set(head, String.valueOf(Long.parseLong(tempOperands.get(head)) - Long.parseLong(tempOperands.get(head + 1))));
                        }
                        tempOperators.remove(head);
                        tempOperands.remove(head + 1);
                    } else {
                        head++;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(tempOperands.get(0))));
        }
        return answer;
    }
}
