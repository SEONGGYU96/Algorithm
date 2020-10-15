import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String[] A = new String[N];
        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }
        int[] dp = new int[S.length()];
        Arrays.fill(dp, -1);
        boolean a = solution(S, A, dp, 0);
        System.out.println(a ? 1 : 0);
    }

    public static boolean solution(String S, String[] A, int[] dp, int startIndex) {
        if (startIndex == S.length()) {
            return true;
        }
        if (dp[startIndex] != -1) {
            return dp[startIndex] == 1;
        }
        boolean answer = false;
        for (String s : A) {
            if (S.substring(startIndex).startsWith(s) && startIndex + s.length() <= S.length()) {
                    answer = answer || solution(S, A, dp, startIndex + s.length());
            }
        }
        dp[startIndex] = answer ? 1 : 0;
        return answer;
    }
}

