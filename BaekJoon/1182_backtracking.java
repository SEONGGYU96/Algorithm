import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
         System.out.println(solution(S, sequence));
    }

    private static int answer = 0;

    private static int currentSum = 0;

    public static int solution(int K, int[] sequence) {
        backtracking(sequence,0, sequence.length, K);
        return answer;
    }

    private static void backtracking(int[] sequence, int depth, int length,  int K) {
        if (depth == length) {
            return;
        }
        if (currentSum + sequence[depth] == K) {
            answer++;
        }

        backtracking(sequence, depth + 1, length,  K);

        currentSum += sequence[depth];

        backtracking(sequence, depth + 1, length, K);

        currentSum -= sequence[depth];
    }
}

