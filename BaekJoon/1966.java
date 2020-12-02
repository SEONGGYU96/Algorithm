import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            LinkedList<Print> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                Print currentPrint = new Print(Integer.parseInt(st.nextToken()));
                if (j == target) {
                    currentPrint.setTarget(true);
                }
                queue.add(currentPrint);
            }
            bw.write(solution(queue) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int solution(LinkedList<Print> queue) {
        int count = 0;

        while (!queue.isEmpty()) {
            Print current = queue.poll();
            count++;

            int high = current.getPriority();
            int highIndex = -1;
            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).getPriority() > high) {
                    high = queue.get(i).getPriority();
                    highIndex = i;
                }
            }
            if (highIndex != -1) {
                queue.add(current);
                for (int j = 0; j < highIndex; j++) {
                    queue.add(queue.poll());
                }
                current = queue.poll();
            }
            if (current.isTarget()) {
                return count;
            }
        }
        return count;
    }

    private static class Print {
        private final int priority;
        private boolean isTarget;

        public Print(int priority) {
            this.priority = priority;
            this.isTarget = false;
        }

        public int getPriority() {
            return priority;
        }

        public boolean isTarget() {
            return isTarget;
        }

        public void setTarget(boolean target) {
            isTarget = target;
        }
    }
}
