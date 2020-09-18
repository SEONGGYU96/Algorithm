import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int[] people = new int[9];
        for (int i = 0; i < 9; i++) {
            people[i] = Integer.parseInt(scanner.nextLine());
        }

        int[] result = solution(people);

        for (int tall: result) {
            System.out.println(tall);
        }
    }

    private static int[] solution(int[] people) {
        int[] answer = new int[7];
        int sum = Arrays.stream(people).sum();

        for (int i = 0; i < people.length - 1; i++) {
            for (int j = i + 1; j < people.length; j++) {
                if (sum - people[i] - people[j] == 100) {
                    int spi1 = people[i];
                    int spi2 = people[j];
                    answer = Arrays.stream(people).filter(tall -> tall != spi1 && tall != spi2 ).sorted().toArray();
                    break;
                }
            }
        }
        return answer;
    }
}
