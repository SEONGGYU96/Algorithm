import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] pokemons = new String[N];
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            pokemons[i] = br.readLine();
            map.put(pokemons[i], i);
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0 ; i < M; i++) {
            String current = br.readLine();
            try {
                answer.append(pokemons[Integer.parseInt(current) - 1]);
            } catch (Exception e) {
                answer.append((map.get(current) + 1));
            } finally {
                answer.append("\n");
            }
        }
        System.out.println(answer);
    }
}

