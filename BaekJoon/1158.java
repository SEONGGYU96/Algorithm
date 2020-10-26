import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> answer = solution(N, K);
        System.out.print("<");
        for (int i = 0; i < answer.size(); i++) {
            if (i != 0 ) {
                System.out.print(", ");
            }
            System.out.print(answer.get(i));
        }
        System.out.print(">");
    }

    public static List<Integer> solution(int N, int K) {
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            array.add(i);
        }
        int head = -1;
        while (!array.isEmpty()) {
            int move = head + K;
            if (move >= array.size()) {
                move %= array.size();
            }
            answer.add(array.get(move));
            array.remove(move);
            head = move - 1;
        }
        return answer;
    }
}
