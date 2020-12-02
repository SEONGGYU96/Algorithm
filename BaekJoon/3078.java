import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        HashMap<Integer, Queue<Integer>> studentMap = new HashMap<>();

        long answer = 0;

        for (int i = 0; i < N; i++) {
            String current = br.readLine();
            if (studentMap.containsKey(current.length())) {
                Queue<Integer> queue = studentMap.get(current.length());
                while (!queue.isEmpty() && i - queue.peek() > K) {
                    queue.poll();
                }
                answer += queue.size();
                queue.offer(i);
            } else {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                studentMap.put(current.length(), list);
            }
        }
        System.out.println(answer);
    }
}

