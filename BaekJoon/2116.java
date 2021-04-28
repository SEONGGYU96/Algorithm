import java.io.*;
import java.util.*;

class Main {

    private static int[][] dices;
    private static int[][] addresses;
    private static final int[] pair = {0, 6, 4, 5, 2, 3, 1};
    private static int N;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        dices = new int[N + 1][7];
        addresses = new int[N + 1][7];
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 6; j++) {
                int value = Integer.parseInt(st.nextToken());
                dices[i][j] = value;
                addresses[i][value] = j;
            }
        }

        int answer = 0;

        for (int i = 1; i <= 6; i++) {
            // System.out.println("start from " + i);
            answer = Math.max(answer, getMaxSideValues(i, 1));
            // System.out.println();
        }

        System.out.println(answer);
    }

    private static int getMaxSideValues(int bottomValue, int start) {
        int topValue = dices[start][pair[addresses[start][bottomValue]]];
        int maxSideValue = 0;
        for (int i = 6; i >= 1; i--) {
            if (i != bottomValue && i != topValue) {
                maxSideValue = i;
                break;
            }
        }
        // System.out.println("Dice " + start + " - bottomValue : " + bottomValue + ", topValue :" + topValue + ", maxSideValue : " + maxSideValue);
        return maxSideValue + (start == N ? 0 : getMaxSideValues(topValue, start + 1));
    }
}



