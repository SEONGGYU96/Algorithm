import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            bw.write(solution(A, B) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static String solution(int A, int B) {
        char[] offset = { 'D', 'S', 'L', 'R'};

        Queue<Element> queue = new LinkedList<>();
        queue.add(new Element(null, A, 'n'));
        boolean[] isVisited = new boolean[10000];
        isVisited[A] = true;

        while (!queue.isEmpty()) {
            Element current = queue.poll();
            int value = current.value;
            char operator = current.operator;
            if (value == B) {
                StringBuilder answer = new StringBuilder(operator + "");
                while (current.predecessor != null) {
                    current = current.predecessor;
                    if (current.operator != 'n') {
                        answer.insert(0, current.operator);
                    }
                }
                return answer.toString();
            }
            int[] convertedArray = {d(value), s(value), l(value), r(value)};
            for (int i = 0; i < 4; i++) {
                if (!isVisited[convertedArray[i]]) {
                    queue.add(new Element(current, convertedArray[i], offset[i]));
                    isVisited[convertedArray[i]] = true;
                }
            }
        }
        return null;
    }

    static class Element {
        Element predecessor;
        int value;
        char operator;

        public Element(Element predecessor, int value, char operator) {
            this.predecessor = predecessor;
            this.value = value;
            this.operator = operator;
        }
    }

    public static int d(int number) {
        number *= 2;
        return number > 9999 ? number % 10000 : number;
    }

    public static int s(int number) {
        number -= 1;
        return number == -1 ? 9999 : number;
    }

    public static int l(int number) {
        String num = String.valueOf(number);
        if (num.length() < 4) {
            num += "0";
        } else {
            num += num.charAt(0);
            num = num.substring(1);
        }
        return Integer.parseInt(num);
    }

    public static int r(int number) {
        String num = String.valueOf(number);
        if (num.length() == 4) {
            return Integer.parseInt(num.charAt(3) + num.substring(0, 3));
        } else {
            String temp = num.charAt(num.length() - 1) +
                    "0".repeat(Math.max(0, 4 - num.length())) +
                    num.substring(0, num.length() - 1);
            return Integer.parseInt(temp);
        }
    }
}

