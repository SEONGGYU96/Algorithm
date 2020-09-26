import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solution(array, 0, N - 1));
    }

    private static int solution(int[] heights, int start, int end) {
        if (start == end) {
            return heights[start];
        }

        int middle = (start + end) / 2;
        int max = Math.max(solution(heights, start, middle), solution(heights, middle + 1, end));
        int head = middle;
        int tail = middle;
        int width = 1;
        int currentHeight = heights[head];

        while (width < end - start + 1) {
            int leftHeight = head > start ? Math.min(currentHeight, heights[head - 1]) : -1;
            int rightHeight = tail < end ? Math.min(currentHeight, heights[tail + 1]) : -1;

            if (leftHeight > rightHeight) {
                if (head > start) {
                    head--;
                }
                currentHeight = leftHeight;
            } else {
                if (tail < end) {
                    tail++;
                }
                currentHeight = rightHeight;
            }
            width = tail - head + 1;
            int currentSize = currentHeight * width;
            max = Math.max(max, currentSize);
        }
        return max;
    }
}
