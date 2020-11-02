import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] stages = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stages[i][0] = Integer.parseInt(st.nextToken());
            stages[i][1] = st.nextToken().charAt(0);
        }
        System.out.print(solution(N, stages));
    }

    public static String solution(int N, int[][] stages) {
        int[] wheel = new int[N];
        Arrays.fill(wheel, -1);
        boolean isWrong = false;
        Set<Integer> values = new HashSet<>();

        int head = 0;

        for (int[] stage : stages) {
            int time = stage[0];
            int value = stage[1];
            head = (head + time) % N;
            int target = wheel[head];
            
            if (target == -1) {
                wheel[head] = value;
                if (values.contains(value)) {
                    isWrong = true;
                    break;
                } else {
                    values.add(value);
                }
            } else if (target != value) {
                isWrong = true;
                break;
            }
        }
        if (isWrong) {
            return "!";
        }
        StringBuilder builder = new StringBuilder();
        for (int value : wheel) {
            if (value == -1) {
                builder.append("?");
            } else {
                builder.append((char) value);
            }
        }
        String answer = builder.toString();
        if (head != N - 1) {
            String start = (new StringBuffer(answer.substring(0, head + 1))).reverse().toString();
            String end = (new StringBuffer(answer.substring(head + 1))).reverse().toString();
            answer = start + end;
        } else {
            answer = (new StringBuilder(answer)).reverse().toString();
        }
        return answer;
    }
}
