import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] house = new int[N];
        int max = -1;
        int min = -1;
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
            if (i == 0) {
                max = house[i];
                min = house[i];
            } else {
                max = Math.max(max, house[i]);
                min = Math.min(min, house[i]);
            }
        }
        System.out.println(solution(C, house, max, min));
    }

    public static long solution(int C, int[] house, int max, int min) {
        int head = 1;
        int tail = max;
        Arrays.sort(house);
        int D = -1;

        while (head <= tail) {
            int middle = (head + tail) / 2;
            int previous = house[0];
            int count = 1;

            for (int i = 1; i < house.length; i++) {
                if (house[i] - previous >= middle) {
                    previous = house[i];
                    count++;
                }
            }

            if (count >= C) {
                D = middle;
                head = middle + 1;
            } else {
                tail = middle - 1;
            }
        }
        return D;
    }
}

