import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> answers = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        int previous = -1;
        while (!list.isEmpty()) {
            int size = list.size();
            int k = (K + previous) % size;
            answers.add(list.remove(k));
            previous = k - 1;
        }

        System.out.print("<");
        for (int i = 0; i < answers.size(); i++) {
            System.out.print(answers.get(i) + ((i == answers.size() - 1) ? "" : ", "));
        }
        System.out.println(">");
    }
}
