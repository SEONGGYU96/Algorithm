import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int[] values;

    public static void main(String args[])  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        values = new int[N];

        StringTokenizer st =new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(develop(0, N));
    }

    private static int develop(int start, int end) {
        if (end - start <= 1) {
            return 0;
        }
        int center = (start + end) / 2;
        int leftMax = getMax(start, center);
        int rightMax = getMax(center, end);
        leftMax += develop(center, end);
        rightMax += develop(start, center);
        return Math.max(leftMax, rightMax);
    }

    private static int getMax(int start, int end) {
        int max = 0;
        for (int i = start; i < end; i++) {
            max = Math.max(max, values[i]);
        }
        return max;
    }
}
