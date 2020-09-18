import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(scanner.nextLine());
        }

        for (int number : numbers) {
            System.out.println(solution(number));
        }
    }

    private static int solution(int number) {
        List<Integer> T = new ArrayList<>();
        List<int[]> combinations = new ArrayList<>();

        for (int n = 1; n * n + n < 2 * number; n++) {
            T.add((n * (n + 1)) / 2);
        }

        combinationRepetition(T, new ArrayList<>(), combinations, T.size(), 3, 0);

        for (int[] combination: combinations) {
            if (Arrays.stream(combination).sum() == number) {
                return 1;
            }
        }

        return 0;
    }

    public static void combinationRepetition(List<Integer> T, List<Integer> tempList, List<int[]> result, int n, int r, int index) {
        if (r == 0) {
            result.add(new int[tempList.size()]);
            for (int i = 0; i < tempList.size(); i++) {
                result.get(result.size() - 1)[i] = tempList.get(i);
            }
            return;
        }
        if (index == n) {
            return;
        }
        tempList.add(T.get(index));
        combinationRepetition(T, tempList, result, n, r - 1, index);
        tempList.remove(tempList.size() - 1);
        combinationRepetition(T, tempList, result, n, r, index + 1);
    }
}
