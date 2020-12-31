import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int answer = solution(F, S, G, U, D);
        System.out.println(answer == -1 ? "use the stairs" : answer);
    }

    private static int solution(int F, int S, int G, int U, int D) {
        int answer = -1;
        int[] offset = {U, -D};

        boolean[] isVisited = new boolean[F + 1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);

        while (!queue.isEmpty()) {
            answer++;
//            System.out.println("============== " + answer + " ============");
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
//                System.out.println(current);
                if (current == G) {
                    return answer;
                }
                for (int _offset : offset) {
                    int newPosition = current + _offset;
                    if (newPosition > F || newPosition <= 0 || isVisited[newPosition]) {
                        continue;
                    }
                    queue.add(newPosition);
                    isVisited[newPosition] = true;
                }
            }
        }
        return -1;
    }
}

