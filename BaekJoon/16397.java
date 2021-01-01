import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int answer = solution(N, T, G);
        System.out.println(answer == -1 ? "ANG" : answer);
    }

    private static int solution(int N, int T, int G) {
        int answer = 0;

        boolean[] isVisited = new boolean[100000];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        isVisited[N] = true;

        while (!queue.isEmpty()) {
            if (answer > T) {
                return -1;
            }
//            System.out.println("============== " + answer + " ============");
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
//                System.out.println(current);
                if (current == G) {
                    return answer;
                }
                for (int j = 0; j < 2; j++) {
                    int newPosition = j == 0 ? current + 1 : pressB(current);

                    if (newPosition == -1 || newPosition > 99999 || isVisited[newPosition]) {
                        continue;
                    }
                    queue.add(newPosition);
                    isVisited[newPosition] = true;
                }
            }
            answer++;
        }
        return -1;
    }

    private static int pressB(int N) {
        if (N == 0) {
            return 0;
        }
        N *= 2;
        if (N > 99999) {
            return -1;
        }
        String n = String.valueOf(N);
        if (n.charAt(0) == '1') {
            return Integer.parseInt(n.substring(1));
        }
        int first = n.charAt(0) - '0' - 1;
        return Integer.parseInt(first + n.substring(1));
    }
}

