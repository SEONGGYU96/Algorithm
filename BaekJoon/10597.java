import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 50;

        backtracking(br.readLine(), new ArrayList<>(), new boolean[n + 1], 0, n);

    }

    private static boolean backtracking(String str, ArrayList<Integer> sequence, boolean[] isVisited, int start, int n) {
        if (start >= str.length()) {
            for (int i = 1; i <= sequence.size(); i++) {
                if (!isVisited[i]) {
                    return false;
                }
            }
            print(sequence);
            return true;
        }

        if (sequence.size() > n) {
            return false;
        }

        for (int i = 1; i < 3 && start + i <= str.length(); i++) {
            int current = Integer.parseInt(str.substring(start, start + i));
            if (current <= n && current > 0) {
                if (!isVisited[current]) {
                    sequence.add(current);
                    isVisited[current] = true;
                    if (backtracking(str, sequence, isVisited, start + i, n)) {
                        return true;
                    }
                    sequence.remove(sequence.size() - 1);
                    isVisited[current] = false;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private static void print(ArrayList<Integer> sequence) {
        for (int element : sequence) {
            System.out.print(element + " ");
        }
    }
}




