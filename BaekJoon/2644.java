import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int target1 = Integer.parseInt(st.nextToken());
        int target2 = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        System.out.println(solution(n, target1, target2, graph));
    }

    private static int solution(int n, int target1, int target2, ArrayList<ArrayList<Integer>> graph) {
        if (target1 == target2) {
            return 0;
        }
        boolean[] isVisited = new boolean[n + 1];
        Arrays.fill(isVisited, false);
        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;

        queue.offer(target1);
        isVisited[target1] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                if (queue.peek() == target2) {
                    return answer;
                }
                ArrayList<Integer> targets = graph.get(queue.poll());
                for (int target : targets) {
                    if (!isVisited[target]) {
                        isVisited[target] = true;
                        queue.offer(target);
                    }
                }
            }
            answer++;
        }
        return -1;
    }
}

