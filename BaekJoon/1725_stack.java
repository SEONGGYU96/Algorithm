import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> heights = new Stack<>();
        int[] histogram = new int[N + 1];
        histogram[N] = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            histogram[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= N; i++) {
            while (!heights.isEmpty() && histogram[heights.peek()] >= histogram[i]) {
                int currentHeight = histogram[heights.pop()];
                int width;
                if (heights.isEmpty()) {
                    width = i;
                } else {
                    width = i - heights.peek() - 1;
                }
                max = Math.max(max, currentHeight * width);
            }
            heights.push(i);
        }

        System.out.print(max);
    }
}import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> heights = new Stack<>();
        int[] histogram = new int[N + 1];
        histogram[N] = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            histogram[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= N; i++) {
            while (!heights.isEmpty() && histogram[heights.peek()] >= histogram[i]) {
                int currentHeight = histogram[heights.pop()];
                int width;
                if (heights.isEmpty()) {
                    width = i;
                } else {
                    width = i - heights.peek() - 1;
                }
                max = Math.max(max, currentHeight * width);
            }
            heights.push(i);
        }

        System.out.print(max);
    }
}
