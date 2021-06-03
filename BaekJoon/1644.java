import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        int N = new Scanner(new InputStreamReader(System.in)).nextInt();
        if (N < 2) {
            System.out.println(0);
            return;
        }
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] isNotPrime = new boolean[N + 1];
        isNotPrime[0] = isNotPrime[1] = true;

        for (int i = 2; i * i <= N; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }

        int head = 0;
        int tail = 0;
        int answer = 0;
        int sum = 0;

        while (true) {
            if (sum >= N) {
                if (sum == N) {
                    answer++;
                }
                sum -= primes.get(head++);
            } else if (tail == primes.size()) {
                break;
            } else {
                sum += primes.get(tail++);
            }
        }

        System.out.println(answer);
    }
}

