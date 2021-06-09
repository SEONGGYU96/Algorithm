import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] susis = new int[N];
        for (int i = 0; i < N; i++) {
            susis[i] = Integer.parseInt(br.readLine());
        }

        int answer = 0;
        HashMap<Integer, Integer> susiMap = new HashMap<>();
        for (int i = 0; i < k; i++) {
            susiMap.put(susis[i], susiMap.getOrDefault(susis[i], 0) + 1);
        }
        susiMap.put(c, susiMap.getOrDefault(c, 0) + 1);
        if (N == k) {
            System.out.println(susiMap.size());
            return;
        }

        for (int head = 0; head < N; head++) {
            int headSusi = susis[head];
            int tailSusi = susis[(head + k) % N];
            if (headSusi != tailSusi) {
                int previous = susiMap.get(headSusi);
                if (previous == 1) {
                    susiMap.remove(headSusi);
                } else {
                    susiMap.put(headSusi, previous - 1);
                }
                susiMap.put(tailSusi, susiMap.getOrDefault(tailSusi, 0) + 1);
            }
            answer = Math.max(answer, susiMap.size());
        }

        System.out.println(answer);
    }
}
