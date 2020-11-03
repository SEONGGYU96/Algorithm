import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] positionOfApple = new int[K][2];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                positionOfApple[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int L = Integer.parseInt(br.readLine());
        int[][] moveInfo = new int[L][2];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            moveInfo[i][0] = Integer.parseInt(st.nextToken());
            moveInfo[i][1] = st.nextToken().charAt(0);

        }
        System.out.print(solution(N, K, L, positionOfApple, moveInfo));
        bw.flush();
        bw.close();
    }

    public static int solution(int N, int K, int L, int[][] positionOfApple, int[][] moveInfo) {
        final int SNAKE = 0;
        final int APPLE = 1;
        final int EMPTY = -1;
        final int TOP = 10;
        final int RIGHT = 13;
        final int LEFT = 11;
        final int BOTTOM = 12;
        int headDirection = RIGHT;
        int tailDirection = RIGHT;
        int headR = 0;
        int headC = 0;
        int tailR = 0;
        int tailC = 0;
        int appleHead = 0;
        int moveHead = 0;

        int count = 0;

        int[][] board = new int[N][N];
        Arrays.sort(positionOfApple, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        }));


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    board[i][j] = SNAKE;
                    continue;
                }
                if (appleHead < K && i == positionOfApple[appleHead][0] - 1 && j == positionOfApple[appleHead][1] - 1) {
                    appleHead++;
                    board[i][j] = APPLE;
                    continue;
                }
                board[i][j] = EMPTY;
            }
        }

        while (true) {
            showBoard(count, board);
            count++;
            switch (headDirection) {
                case TOP :
                    headR--;
                    break;

                case RIGHT :
                    headC++;
                    break;

                case BOTTOM:
                    headR++;
                    break;

                case LEFT:
                    headC--;
                    break;
            }
            if (isWall(N, headR, headC)) {
                break;
            }
            int headState = board[headR][headC];
            if (headState == SNAKE) {
                break;
            }
            board[headR][headC] = SNAKE;

            if (moveHead < L && moveInfo[moveHead][0] == count) {
                if (moveInfo[moveHead][1] == 'L') {
                    headDirection += 1;
                    if (headDirection > RIGHT) {
                        headDirection = TOP;
                    }
                } else {
                    headDirection -= 1;
                    if (headDirection < TOP) {
                        headDirection = RIGHT;
                    }
                }
                board[headR][headC] = headDirection;
                moveHead++;
            }

            if (headState == APPLE) {
                continue;
            }
            
            board[tailR][tailC] = EMPTY;

            switch (tailDirection) {
                case TOP :
                    tailR--;
                    break;

                case RIGHT :
                    tailC++;
                    break;

                case BOTTOM:
                    tailR++;
                    break;

                case LEFT:
                    tailC--;
                    break;
            }

            if (board[tailR][tailC] != SNAKE) {
                tailDirection = board[tailR][tailC];
            }
        }
        showBoard(count, board);
        return count;
    }

    private static boolean isWall(int size, int row, int col) {
        return row < 0 || row >= size || col < 0 || col >= size;
    }

    private static void showBoard(int count, int[][] board) {
        System.out.println(count + " second after ---");
        for (int[] row : board) {
            for (int col : row) {
                String output;
                switch (col) {
                    case -1 :
                        output = "X";
                        break;
                    case 1 :
                        output = "A";
                        break;
                    default :
                        output = "B";
                        break;
                }
                System.out.print(output + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
