import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        boolean[] isRemoved = new boolean[n];
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.addLast(String.valueOf(i));
        }

        Stack<History> histories = new Stack<>();

        for (String c : cmd) {
            StringTokenizer st = new StringTokenizer(c);
            String operator = st.nextToken();
            switch (operator) {
                case "D" -> k += Integer.parseInt(st.nextToken());
                case "U" -> k -= Integer.parseInt(st.nextToken());
                case "C" -> {
                    String removed = list.remove(k);
                    isRemoved[Integer.parseInt(removed)] = true;
                    histories.push(new History(k, removed));
                    if (k >= list.size()) {
                        k = list.size() - 1;
                    }
                }
                case "Z" -> {
                    History history = histories.pop();
                    isRemoved[Integer.parseInt(history.value)] = false;
                    list.add(history.index, history.value);
                    if (history.index <= k) {
                        k++;
                    }
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            answer.append(isRemoved[i] ? "X" : "O");
        }

        return answer.toString();
    }

    private static class History {
        private final int index;
        private final String value;

        public History(int index, String value) {
            this.index = index;
            this.value = value;
        }
    }
}
