import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();
        scanner.nextLine();
        String sequenceStr = scanner.nextLine();
        int[] sequence = Arrays.stream(sequenceStr.split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(solution(S, sequence));
    }

    private static int solution(int S, int[] sequence) {
        int count = 0;
        for (int i = 0; i < 1 << sequence.length; i++) {
            int sum = 0;
            boolean flag = false;
            for (int j = 0; j < sequence.length; j++) {
                if ((i & 1 << j) != 0) {
                    sum += sequence[j];
                    flag = true;
                }
            }
            if (sum == S && flag) {
                count++;
            }
        }
        return count;
    }
}
