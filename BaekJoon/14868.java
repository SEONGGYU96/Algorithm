import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int K;

    private static int[] parent;

    private static int[][] map;
    private static Queue<Point> propagationQueue;
    private static Queue<Point> unionQueue;
    private static int[] xUnit = {0, 1, 0, -1};
    private static int[] yUnit = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[K + 1];
        map = new int[N][N];
        propagationQueue = new LinkedList<>();
        unionQueue = new LinkedList<>();

        Arrays.fill(parent, -1);

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = i;
            unionQueue.add(new Point(x, y));
        }

//        print();
        while (K > 1) {
            union();
            if (K == 1) {
//                print();
                break;
            }
            propagation();
            answer++;
//            print();
        }
        System.out.println(answer);
    }

    private static void print() {
        for (int j = N - 1; j >= 0; j--) {
            for (int i = 0; i < N; i++) {
                if (map[i][j] == 0) {
                    System.out.print(map[i][j] + " ");
                } else {
                    System.out.print(find(map[i][j]) + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int find(int n) {
        if (parent[n] < 0) {
            return n;
        }
        parent[n] = find(parent[n]);
        return parent[n];
    }

    private static boolean merge(int n, int m) {
        int parentOfN = find(n);
        int parentOfM = find(m);
        if (parentOfN == parentOfM) {
            return false;
        }
        int sizeOfN = Math.abs(parent[parentOfN]);
        int sizeOfM = Math.abs(parent[parentOfM]);
        if (sizeOfN >= sizeOfM) {
            parent[parentOfN] += parent[parentOfM];
            parent[parentOfM] = parentOfN;
        } else {
            parent[parentOfM] += parent[parentOfN];
            parent[parentOfN] = parentOfM;
        }
        return true;
    }

    private static void propagation() {
        while (!propagationQueue.isEmpty()) {
            Point head = propagationQueue.poll();
            int currentCivilization = map[head.x][head.y];

            for (int i = 0; i < 4; i++) {
                int nearX = head.x + xUnit[i];
                int nearY = head.y + yUnit[i];

                if (isDisablePlace(nearX, nearY)) {
                    continue;
                }

                int nearCivilization = map[nearX][nearY];

                if (nearCivilization == 0) {
                    map[nearX][nearY] = currentCivilization;
                    unionQueue.add(new Point(nearX, nearY));
                } else {
                    if (currentCivilization != nearCivilization) {
                        if (merge(currentCivilization, nearCivilization)) {
                            K--;
                        }
                    }
                }
            }
        }
    }

    private static void union() {
        while (!unionQueue.isEmpty()) {
            Point head = unionQueue.poll();
            propagationQueue.add(head);
            int currentCivilization = map[head.x][head.y];

            for (int i = 0; i < 4; i++) {
                int nearX = head.x + xUnit[i];
                int nearY = head.y + yUnit[i];

                if (isDisablePlace(nearX, nearY)) {
                    continue;
                }

                int nearCivilization = map[nearX][nearY];

                if (nearCivilization != 0 && nearCivilization != currentCivilization) {
                    if (merge(currentCivilization, nearCivilization)) {
                        K--;
                    }
                }
            }
        }
    }

    private static boolean isDisablePlace(int x, int y) {
        return x >= N || y >= N || x < 0 || y < 0;
    }
}

