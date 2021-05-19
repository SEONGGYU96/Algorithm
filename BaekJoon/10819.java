import java.io.*;
import java.util.*;

class Main {
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        getPermutation(numbers, new int[N], new boolean[N], 0, result -> {
            int sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(result[i] - result[i + 1]);
            }
            answer = Math.max(answer, sum);
        });
        System.out.println(answer);
    }

    private static void getPermutation(int[] numbers, int[] result, boolean[] isUsed, int depth, Callback callback) {
        if (depth == numbers.length) {
            callback.onResultLoaded(result);
            return;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (isUsed[i]) {
                continue;
            }
            result[depth] = numbers[i];
            isUsed[i] = true;
            getPermutation(numbers, result, isUsed, depth + 1, callback);
            isUsed[i] = false;
         }
    }

    private interface Callback {
        void onResultLoaded(int[] result);
    }
}
