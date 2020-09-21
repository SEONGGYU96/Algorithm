import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] devices = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            devices[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(devices, N));
    }

    private static int solution(int[] devices, int N) {
        int answer = 0;
        Stack<Integer> multiTab = new Stack<>();

        for (int i = 0; i < devices.length; i++) {
            int currentDevice = devices[i];

            if (multiTab.contains(currentDevice)) {
                continue;
            }

            if (multiTab.size() < N) {
                multiTab.add(currentDevice);
                continue;
            }

            boolean[] isUsed = new boolean[N];
            Arrays.fill(isUsed, false);
            int count = 0;

            for (int j = i + 1; j < devices.length; j++) {
                Integer head = devices[j];
                if (multiTab.contains(head)) {
                    int index = multiTab.indexOf(head);
                    if (!isUsed[index]) {
                        isUsed[multiTab.indexOf(head)] = true;
                        if (++count == N) {
                            multiTab.remove(head);
                            break;
                        }
                    }
                }
            }

            if (multiTab.size() == N) {
                for (int j = 0; j < isUsed.length; j++) {
                    if (!isUsed[j]) {
                        multiTab.remove(j);
                        break;
                    }
                }
            }

            if (multiTab.size() == N) {
                multiTab.pop();
            }

            multiTab.add(currentDevice);
            answer++;
        }

        return answer;
    }
}
