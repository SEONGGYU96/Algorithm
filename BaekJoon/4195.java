import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static HashMap<Integer, Integer> parent;
    private static HashMap<String, Integer> index;
    private static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T > 0) {
            int F = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            index = new HashMap<>();

            StringTokenizer st;
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String p1 = st.nextToken();
                String p2 = st.nextToken();

                if (!index.containsKey(p1)) {
                    index.put(p1, idx++);
                }
                if (!index.containsKey(p2)) {
                    index.put(p2, idx++);
                }

                merge(p1, p2);
                answer.append(Math.abs(parent.get(find(getIndex(p1))))).append("\n");
            }
            T--;
        }
        System.out.println(answer);
    }
    private static int find(int n) {
        if (!parent.containsKey(n)) {
            parent.put(n, -1);
            return n;
        }
        if (parent.get(n) < 0) {
            return n;
        }
        parent.put(n, find(parent.get(n)));
        return parent.get(n);
    }

    private static void merge(String a, String b) {
        int indexOfA = getIndex(a);
        int indexOfB = getIndex(b);
        int indexOfParentOfA = find(indexOfA);
        int indexOfParentOfB = find(indexOfB);
        if (indexOfParentOfA == indexOfParentOfB) {
            return;
        }
        parent.put(indexOfParentOfA, parent.get(indexOfParentOfA) + parent.get(indexOfParentOfB));
        parent.put(indexOfParentOfB, indexOfParentOfA);
    }

    private static int getIndex(String n) {
        if (!index.containsKey(n)) {
            index.put(n, idx++);
        }
        return index.get(n);
    }
}

