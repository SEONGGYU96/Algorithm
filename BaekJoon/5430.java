import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine().replaceAll("[\\[\\] ]*", "");
            int[] array;
            if (str.isEmpty()) {
                array = null;
            } else {
                String[] arrayStr = str.split(",");
                array = Arrays.stream(arrayStr).mapToInt(Integer::parseInt).toArray();
            }
            bw.write(solution(p, array) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static String solution(String p, int[] array) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean isForward = true;

        if (array != null) {
            for (int element : array) {
                queue.offer(element);
            }
        }

        for (int i = 0; i < p.length(); i++) {
            switch (p.charAt(i)) {
                case 'R':
                    isForward = !isForward;
                    break;

                case 'D':
                    if (queue.isEmpty()) {
                        return "error";
                    } else {
                        if (isForward) {
                            queue.removeFirst();
                        } else {
                            queue.removeLast();
                        }
                    }
                    break;
            }
        }
        StringBuilder answer = new StringBuilder("[");
        if (!queue.isEmpty()) {
            while (true) {
                answer.append(isForward ? queue.removeFirst() : queue.removeLast());
                if (!queue.isEmpty()) {
                    answer.append(",");
                } else {
                    break;
                }
            }
        }
        answer.append("]");

        return answer.toString();
    }
}
