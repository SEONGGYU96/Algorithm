import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        int N = Integer.parseInt(br.readLine());

        int[] cubes = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            cubes[size] = count;
        }

        System.out.println(solution(length, width, height, cubes));
    }

    private static long solution(int length, int width, int height, int[] cubes) {
        long answer = 0;

        long before = 0;
        for (int i = cubes.length - 1; i >= 0; i--) {
            before <<= 3;

            long enableCube = (long) (length >> i) * (width >> i) * (height >> i) - before;
            long newCube = Math.min(cubes[i], enableCube);

            before += newCube;
            answer += newCube;
        }

        if (before == (long) length * width * height) {
            return answer;
        } else {
            return -1;
        }
    }
}
