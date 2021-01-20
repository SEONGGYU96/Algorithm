import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;
    private static int sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = N - 1; i >= 0; i--) {
            sequence |= ((Integer.parseInt(st.nextToken()) - 1) << i * 3);
        }

        System.out.println(solution());
    }

    private static int solution() {
        boolean[] isVisited = new boolean[1 << (N * 3 + 3)];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(sequence);
        isVisited[sequence] = true;
        int answer = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int state = queue.poll();

                if (isAscending(state)) {
                    return answer;
                }

                for (int j = 0; j <= N - K; j++) {
                    int nextState = swap(state, j);
                    if (!isVisited[nextState]) {
                        queue.add(nextState);
                        isVisited[nextState] = true;
                    }
                }
            }
            answer++;
        }

        return -1;
    }

    private static int swap(int state, int index) {
        int result = state;
        int head = index;
        int tail = index + K - 1;
        while (head < tail) {
            result = setElement(result, head, getElement(state, tail));
            result = setElement(result, tail, getElement(state, head));
            head++;
            tail--;
        }
        return result;
    }

    private static boolean isAscending(int state) {
        int last = 0;
        for (int i = 0; i < N; i++) {
            int element = getElement(state, i);
            if (last > element) {
                return false;
            }
            last = element;
        }
        return true;
    }

    private static int setElement(int sequence, int index, int value) {
        int realIndex = N - index - 1;
        sequence &= ~(7 << realIndex * 3);
        sequence |= ((value - 1) << realIndex * 3);
        return sequence;
    }

    private static int getElement(int sequence, int index) {
        int realIndex = N - index - 1;
        return ((sequence & (7 << realIndex * 3)) >> (realIndex * 3)) + 1;
    }

    private static void print(int sequence) {
        for (int i = 0; i < N; i++) {
            System.out.print(getElement(sequence, i) + " ");
        }
        System.out.println();
    }
}

