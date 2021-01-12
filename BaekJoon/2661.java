import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(backTracking("", Integer.parseInt(br.readLine())));
    }

    private static String backTracking(String answer, int n) {
        if (answer.length() == n) {
            return answer;
        }

        for (int i = 1; i <= 3; i++) {
            if (isPossible(answer, i)) {
                String result = backTracking(answer + i, n);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    private static boolean isPossible(String answer, int number) {
        String temp = answer + number;
        int size = temp.length();
        int gap = 1;

        while (gap * 2 <= size) {
            String a = temp.substring(size - gap, size);
            String b = temp.substring(size - gap * 2, size - gap);
            if (a.equals(b)) {
                return false;
            }
            gap++;
        }
        return true;
    }
}

