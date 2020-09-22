import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        int K = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        int[] sensorLocations = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensorLocations[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(sensorLocations, K));
    }

    private static int solution(int[] sensors, int K) {
        if (K >= sensors.length) {
            return 0;
        }
        Arrays.sort(sensors);
        List<Integer> distances = new ArrayList<>();
        int length = sensors[sensors.length - 1] - sensors[0];

        for (int i = 0; i < sensors.length - 1; i++) {
            distances.add(sensors[i + 1] - sensors[i]);
        }

        distances.sort(Collections.reverseOrder());

        for (int i = 0; i < K - 1; i++) {
            length -= distances.get(i);
        }

        return length;
    }
}
