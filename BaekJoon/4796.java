import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<int[]> parameter = new ArrayList<>();

        String str = scanner.nextLine();

        while (!str.equals("0 0 0")) {
            parameter.add(Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray());
            str = scanner.nextLine();
        }

        for (int i = 0; i < parameter.size(); i++) {
            int[] currentParams = parameter.get(i);
            int L = currentParams[0];
            int P = currentParams[1];
            int V = currentParams[2];
            System.out.println("Case " + (i + 1) + ": " + solution(L, P, V));
        }

    }

    private static int solution(int L, int P, int V) {
        int answer = (V / P) * L;
        answer += Math.min(V % P, L);

        return answer;
    }
}
