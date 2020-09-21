import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int[][] parameters = new int[11][2];

        for (int i = 0; i < 11; i++) {
            parameters[i][0] = scanner.nextInt();
            parameters[i][1] = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.println(solution(parameters));
    }

    private static int solution(int[][] parameters) {
        int answer = 0;
        int time = 0;

        Arrays.sort(parameters, Comparator.comparingInt(o -> o[0]));

        for (int[] parameter : parameters) {
            time += parameter[0];
            answer += time + 20 * parameter[1];
        }

        return answer;
    }
}
