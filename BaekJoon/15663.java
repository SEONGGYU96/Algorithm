import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    private static int N;
    private static int M;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
        getSequence(0, new boolean[N], new int[M]);
    }

    private static void getSequence(int depth, boolean[] isVisited, int[] result) {
        if (depth == M) {
            for (int element : result) {
                System.out.print(element + " ");
            }
            System.out.println();
            return;
        }
        int before = -1;
        for (int i = 0; i < N; i++) {
            if (!isVisited[i] && (i == 0 || before != numbers[i])) {
                before = numbers[i];
                result[depth] = numbers[i];
                isVisited[i] = true;
                getSequence(depth + 1, isVisited, result);
                isVisited[i] = false;
            }
        }
    }
}


