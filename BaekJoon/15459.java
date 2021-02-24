import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static long[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());
        Queue<Haybale> heap = new PriorityQueue<>();
        Haybale[] haybales = new Haybale[N];
        parent = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int flavor = Integer.parseInt(st.nextToken());
            int spiciness = Integer.parseInt(st.nextToken());
            haybales[i] = new Haybale(i, flavor, spiciness);
            parent[i] = flavor * -1;
            heap.add(haybales[i]);
        }

        while (!heap.isEmpty()) {
            Haybale current = heap.poll();

            if (current.index > 0) {
                Haybale before = haybales[current.index - 1];
                compareAndMerge(current, before);
            }
            if (current.index < N - 1) {
                Haybale after = haybales[current.index + 1];
                compareAndMerge(current, after);
            }
            long sumOfFlavor = Math.abs(parent[find(current.index)]);
            if (sumOfFlavor >= M) {
                System.out.println(current.spiciness);
                return;
            }
        }
    }

    private static void compareAndMerge(Haybale base, Haybale target) {
        if (target.spiciness <= base.spiciness) {
            merge(base.index, target.index);
        }
    }

    private static int find(int n) {
        if (parent[n] < 0) {
            return n;
        }
        parent[n] = find((int) parent[n]);
        return (int) parent[n];
    }

    private static void merge(int n, int m) {
        int parentOfN = find(n);
        int parentOfM = find(m);
        if (parentOfN == parentOfM) {
            return;
        }
        long sumOfFlavorOfN = Math.abs(parent[parentOfN]);
        long sumOfFlavorOfM = Math.abs(parent[parentOfM]);
        if (sumOfFlavorOfN >= sumOfFlavorOfM) {
            parent[parentOfN] += parent[parentOfM];
            parent[parentOfM] = parentOfN;
        } else {
            parent[parentOfM] += parent[parentOfN];
            parent[parentOfN] = parentOfM;
        }
    }

    private static class Haybale implements Comparable<Haybale> {
        int index;
        int flavor;
        int spiciness;

        public Haybale(int index, int flavor, int spiciness) {
            this.index = index;
            this.flavor = flavor;
            this.spiciness = spiciness;
        }

        @Override
        public int compareTo(Haybale haybales) {
            return Integer.compare(this.spiciness, haybales.spiciness);
        }
    }
}

