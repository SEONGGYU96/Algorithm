import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {  
  public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] flat = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            flat[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < 3; i++) {
            max = Math.max(thred(flat, i, new boolean[N]), max);
        }

        System.out.println(max);
    }

    private static int thred(int[] flat, int position, boolean[] isVisited) {
        if (isVisited[position]) {
            return 1;
        }
        isVisited[position] = true;
        int value = flat[position];
        int next = position + value;
        int count = 1;
        count += thred(flat, next, isVisited);

        return count;
    }
}
