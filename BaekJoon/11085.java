import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][] ways = new int[w][3];
        parent = new int[p];
        Arrays.fill(parent, -1);
        Queue<Way> heap = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            heap.add(new Way(start, end, width));
        }

        int minimumWidth = 1001;

        while (!heap.isEmpty()) {
            Way current = heap.poll();
            merge(current.start, current.end);
            minimumWidth = Math.min(minimumWidth, current.width);
            if (find(v) == find(c)) {
                break;
            }
        }

        System.out.println(minimumWidth);
    }

    private static int find(int n) {
        if (parent[n] < 0) {
            return n;
        }
        parent[n] = find(parent[n]);
        return parent[n];
    }

    private static void merge(int n, int m) {
        int parentOfN = find(n);
        int parentOfM = find(m);

        if (parentOfN == parentOfM) {
            return;
        }
        parent[parentOfM] = parentOfN;
    }

    private static class Way implements Comparable<Way> {
        int start;
        int end;
        int width;

        public Way(int start, int end, int width) {
            this.start = start;
            this.end = end;
            this.width = width;
        }

        @Override
        public int compareTo(Way w) {
            return Integer.compare(w.width, width);
        }
    }
}

