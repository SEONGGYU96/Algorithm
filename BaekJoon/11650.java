import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] points = new int[N][];
		for (int i = 0; i < N; i++) {
			points[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		Arrays.sort(points, (o1, o2) -> {
			if (o1[0] > o2[0]) {
				return 1;
			} else if (o1[0] < o2[0]) {
				return -1;
			} else {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		for (int i = 0; i < N; i++) {
			System.out.println(points[i][0] + " " + points[i][1]);
		}
		
	}
}
