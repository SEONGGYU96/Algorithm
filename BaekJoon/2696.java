import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int M = Integer.parseInt(br.readLine());
            Queue<Integer> minimumHeap = new PriorityQueue<>();
            Queue<Integer> maximumHeap = new PriorityQueue<>(Comparator.reverseOrder());
            int middle = 0;

            StringBuilder tempAnswer = new StringBuilder();
            int count = 0;

            for (int j = 0; j <= M / 10; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= Math.min(10, M - j * 10); i++) {
                    int current = Integer.parseInt(st.nextToken());
                    if (i % 2 == 0) {
                        if (current > middle) {
                            minimumHeap.add(current);
                            maximumHeap.add(middle);
                        } else {
                            maximumHeap.add(current);
                            minimumHeap.add(middle);
                        }
                    } else {
                        if (minimumHeap.isEmpty() || minimumHeap.peek() < current) {
                            minimumHeap.add(current);
                            middle = minimumHeap.poll();
                        } else {
                            maximumHeap.add(current);
                            middle = maximumHeap.poll();
                        }
                        tempAnswer.append(middle).append(" ");
                        count++;
                        if (count % 10 == 0) {
                            tempAnswer.append("\n");
                        }
                    }
                }
            }
            answer.append(count).append("\n").append(tempAnswer.toString()).append("\n");
        }
        System.out.println(answer);
    }
}

