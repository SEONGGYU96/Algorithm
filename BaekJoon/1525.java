import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = 3;
        StringBuilder puzzle = new StringBuilder();
        int head = 0;
        int blank = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                String current = st.nextToken();
                puzzle.append(current);
                if (current.equals("0")) {
                    blank = puzzle.length() - 1;
                }
            }
        }
        System.out.println(solution(puzzle.toString(), blank));
    }

    public static int solution(String puzzle, int blank) {
        int[] offset = {-3, -1, 1, 3};
        int answer = 0;
        Set<String> isVisited = new HashSet<>();
        Queue<Snapshot> queue = new LinkedList<>();

        queue.add(new Snapshot(puzzle, blank));
        isVisited.add(puzzle);

        while (!queue.isEmpty()) {
            int size = queue.size();
//            System.out.println("==== " + answer + " ====");
            for (int i = 0; i < size; i++) {
                Snapshot current = queue.poll();
                String snapshot = current.snapshot;
                int currentBlank = current.blank;

//                print(snapshot);
                if (snapshot.equals("123456780")) {
                    return answer;
                }

                for (int _offset : offset) {
                    int originRow = currentBlank / 3;
                    int target = currentBlank + _offset;
                    int afterRow = target / 3;
                    if (target >= 0 && target < puzzle.length()) {
                        if ((_offset == -1 || _offset == 1) && originRow != afterRow) {
                            continue;
                        }
                        String cSnapshot = snapshot;
                        char temp = cSnapshot.charAt(target);
                        cSnapshot = cSnapshot
                                .replace(temp, 'a')
                                .replace('0', temp)
                                .replace('a', '0');
                        if (!isVisited.contains(cSnapshot)) {
                            isVisited.add(cSnapshot);
                            queue.add(new Snapshot(cSnapshot, target));
                        }
                    }
                }
            }
            answer++;
        }
        return -1;
    }

    private static void print(String snapshot) {
        for (int i = 0; i < snapshot.length(); i++) {
            System.out.print(snapshot.charAt(i));
            if (i % 3 == 2) {
                System.out.println();
            }
        }
        System.out.println();
     }

    static class Snapshot {
        String snapshot;
        int blank;

        public Snapshot(String snapshot, int blank) {
            this.snapshot = snapshot;
            this.blank = blank;
        }
    }
}

