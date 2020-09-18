import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        int[][] record = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(scanner.nextLine());
            for (int j = 0; j < 3; j++) {
                record[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(record));
    }

    private static int solution(int[][] records) {
        final int NUMBER = 0;
        final int STRIKE = 1;
        final int BALL = 2;

        List<Integer> result = new ArrayList<>();

        for (int i = 123; i <= 987; i++) {
            String number = String.valueOf(i);
            char[] value = number.toCharArray();
            int first = value[0] - '0';
            int second = value[1] - '0';
            int third = value[2] - '0';

            if (first == 0 || second == 0 || third == 0) {
                continue;
            }

            if (first == second || second == third || first == third) {
                continue;
            }

            boolean isMatched = true;

            for (int j = 0; j < records.length; j++) {
                char[] target = String.valueOf(records[j][NUMBER]).toCharArray();
                int tFirst = target[0] - '0';
                int tSecond = target[1] - '0';
                int tThird = target[2] - '0';
                int strike = 0;
                int ball = 0;

                if (first == tFirst) {
                    strike++;
                } else if (first == tSecond || first == tThird) {
                    ball++;
                }

                if (second == tSecond) {
                    strike++;
                } else if (second == tFirst || second == tThird) {
                    ball++;
                }

                if (third == tThird) {
                    strike++;
                } else if (third == tFirst || third == tSecond) {
                    ball++;
                }

                if (strike != records[j][STRIKE] || ball != records[j][BALL]) {
                    isMatched = false;
                    break;
                }
            }

            if (isMatched) {
                result.add(i);
            }
        }
        return result.size();
    }
}
