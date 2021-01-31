import java.io.*;
import java.util.*;

public class Main {

    private static int[] population;
    private static boolean[] isWolf;
    private static List<Integer>[] children;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        population = new int[N];
        isWolf = new boolean[N];
        children = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            children[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int island = 1; island < N; island++) {
            st = new StringTokenizer(br.readLine());
            isWolf[island] = st.nextToken().equals("W");
            population[island] = Integer.parseInt(st.nextToken());
            children[Integer.parseInt(st.nextToken()) - 1].add(island);
        }

        System.out.println(dfs(0));
    }

    private static long dfs(int island) {
        if (children[island].isEmpty()) {
            return isWolf[island] ? 0 : population[island];
        }

        long populationOfChildren = 0;

        for (int child : children[island]) {
            populationOfChildren += dfs(child);
        }

        long result = isWolf[island] ? populationOfChildren - population[island] : populationOfChildren + population[island];
        return Math.max(0, result);
    }
}

