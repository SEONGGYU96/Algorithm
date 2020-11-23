import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int[][] notes = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                notes[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(notes, P));
    }

    public static int solution(int[][] notes, int P) {
        int answer = 0;
        ArrayList<Stack<Integer>> fretOfLineStack = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            fretOfLineStack.add(new Stack<>());
        }

        for (int[] note : notes) {
            int targetLine = note[0];
            int targetFret = note[1];
            Stack<Integer> currentLineStack = fretOfLineStack.get(targetLine - 1);
            while (!currentLineStack.isEmpty() && currentLineStack.peek() > targetFret) {
                currentLineStack.pop();
                answer++;
            }
            if (currentLineStack.isEmpty() || currentLineStack.peek() != targetFret) {
                currentLineStack.push(targetFret);
                answer++;
            }
        }
        return answer;
    }
}
