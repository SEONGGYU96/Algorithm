import java.io.*;
import java.util.*;

public class Main {

    private static int P;
    private static int Q;
    private static HashMap<Long, Long> A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        A = new HashMap<>();
        A.put(0L, 1L);

        System.out.println(getValueFromA(N));
    }

    private static long getValueFromA(long index) {
        if (!A.containsKey(index)) {
            A.put(index, getValueFromA(index / (long) P) + getValueFromA(index / (long) Q));
        }
        return A.get(index);
    }
}
ggpackage com.gyugyu;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        //input
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        Map<Long, Long> sequence = new HashMap<>();
        sequence.put(0L, 1L);

        //output
        System.out.println(getSequence(sequence, n, p, q));
    }

    private static long getSequence(Map<Long, Long> sequence, long n, long p, long q) {
        if (sequence.containsKey(n)) {
            return sequence.get(n);
        } else {
            float a = (float) n / p;
            float b = (float) n / q;

            long A = (long) a;
            long B = (long) b;

            long result = getSequence(sequence, A, p, q) + getSequence(sequence, B, p, q);

            sequence.put(n, result);

            return result;
        }
    }
}

