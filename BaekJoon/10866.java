import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int value = solution(br.readLine(), queue);
            if (value != -2) {
                bw.write(value + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static int solution(String order, LinkedList<Integer> queue) {

        switch (order) {
            case "pop_front" :
                return queue.isEmpty() ? -1 : queue.removeFirst();

            case "pop_back" :
                return queue.isEmpty() ? -1 : queue.removeLast();

            case "size" :
                return queue.size();

            case "empty" :
                return queue.isEmpty() ? 1 : 0;

            case "front" :
                return queue.isEmpty() ? -1 : queue.getFirst();

            case "back" :
                return queue.isEmpty() ? -1 : queue.getLast();

            default:
                StringTokenizer st = new StringTokenizer(order);
                String order2 = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                if (order2.equals("push_front")) {
                    queue.addFirst(value);
                } else {
                    queue.addLast(value);
                }
        }
        return -2;
    }
}
