import java.io.*;
import java.util.HashSet;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (isGroup(br.readLine())) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	private static boolean isGroup(String str) {
		HashSet<Character> set = new HashSet<>();
		char previous = str.charAt(0);
		set.add(previous);
		for (int i = 1; i < str.length(); i++) {
			char current = str.charAt(i);
			if (previous != current) {
				if (set.contains(current)) {
					return false;
				}
				previous = current;
				set.add(previous);
			}
		}
		return true;
	}
}
