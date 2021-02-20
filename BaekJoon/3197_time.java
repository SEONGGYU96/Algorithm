import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final char WATER = '.';
    private static final char ICE = 'X';
    private static final char SWAN = 'L';
    private static final int MAP = 0;
    private static final int INDEX = 1;

    private static int R;
    private static int C;

    private static int[] parent;
    private static ArrayList<Integer> swans;

    private static char[][][] map;
    private static Queue<Point> propagationQueue;
    private static Queue<Point> unionQueue;
    private static final int[] xUnit = {-1, 0, 1, 0};
    private static final int[] yUnit = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        swans = new ArrayList<>();

        parent = new int[R * C + 1];
        map = new char[2][R][C];
        propagationQueue = new LinkedList<>();
        unionQueue = new LinkedList<>();

        Arrays.fill(parent, -1);

        int index = 1;
        for (int row = 0; row < R; row++) {
            String headRow = br.readLine();
            for (int col = 0; col < C; col++) {
                char head = headRow.charAt(col);
                if (head == SWAN) {
                    swans.add(index);
                }
//                map[MAP][row][col] = head;
                if (head == WATER || head == SWAN) {
                    map[INDEX][row][col] = (char) index++;
                    unionQueue.add(new Point(row, col));
                }
            }
        }

//        System.out.println("init: ");
//        print();
        while (!isSwanInSameWater()) {
//            System.out.println("union: ");
            union();
//            print();
            if (isSwanInSameWater()) {
                break;
            }
//            System.out.println("propagation: ");
            propagation();
            answer++;
//            print();
        }
        System.out.println(answer);
    }

    private static boolean isSwanInSameWater() {
        return find(swans.get(0)) == find(swans.get(1));
    }

    private static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                char current = map[MAP][i][j];
                if (current == WATER) {
                    System.out.printf("%3d ", find(map[INDEX][i][j]));
                } else {
                    System.out.printf("%3c ", current);
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
            int headIndex = map[INDEX][head.x][head.y];

            for (int i = 0; i < 4; i++) {
                int nearX = head.x + xUnit[i];
                int nearY = head.y + yUnit[i];

                if (isDisablePlace(nearX, nearY)) {
                    continue;
                }

                int nearIndex = map[INDEX][nearX][nearY];

                if (nearIndex == 0) {
                    map[INDEX][nearX][nearY] = (char) headIndex;
//                    map[MAP][nearX][nearY] = WATER;
                    unionQueue.add(new Point(nearX, nearY));
                } else {
                    if (headIndex != nearIndex) {
                        merge(headIndex, nearIndex);
                    }
                }
                if (isSwanInSameWater()) {
                    break;
                }
            }
            if (isSwanInSameWater()) {
                break;
            }
        }
    }

    private static void union() {
        while (!unionQueue.isEmpty()) {
            Point head = unionQueue.poll();
            int currentIndex = map[INDEX][head.x][head.y];

            for (int i = 0; i < 2; i++) {
                int nearX = head.x + xUnit[i];
                int nearY = head.y + yUnit[i];

                if (isDisablePlace(nearX, nearY)) {
                    continue;
                }

                int nearIndex = map[INDEX][nearX][nearY];

                if (nearIndex != 0) {
                    merge(currentIndex, nearIndex);
                } else {
                    propagationQueue.add(head);
                }
                if (isSwanInSameWater()) {
                    break;
                }
            }
            if (isSwanInSameWater()) {
                break;
            }
        }
    }

    private static boolean isDisablePlace(int row, int col) {
        return row >= R || col >= C || row < 0 || col < 0;
    }
}

