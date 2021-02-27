import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String args[])  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] scores = new int[N * N];
        HashMap<Integer, ArrayList<Integer>> moles = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            scores[i] = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            for (int j = 0; j < t; j++) {
                int time = Integer.parseInt(st.nextToken());
                if (!moles.containsKey(time)) {
                    moles.put(time, new ArrayList<>());
                }
                moles.get(time).add(i);
            }
        }
        int answer = 0;
        for (int time : moles.keySet()) {
            List<Integer> moleList = moles.get(time);

            int max = 0;
            for (Integer mole : moleList) {
                max = Math.max(max, scores[mole]);
            }
            answer += max;
        }
        System.out.println(answer);
    }
}
