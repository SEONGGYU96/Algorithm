import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    final int OUT_OF_STOCK = -1;
    final int AMBIGUOUS = 0;
    final int IN_STOCK = 1;
    int[] stocks;
    
    public String solution(int n, int[][] delivery) {
        StringBuilder answer = new StringBuilder();
        ArrayList<int[]> reCheck = new ArrayList<>();
        stocks = new int[n];
        Arrays.fill(stocks, AMBIGUOUS);

        for (int[] currentOrder : delivery) {
            check(currentOrder, reCheck);
        }

        for (int[] currentOrder : reCheck) {
            check(currentOrder, null);
        }

        for (int stock : stocks) {
            switch (stock) {
                case OUT_OF_STOCK -> answer.append("X");
                case AMBIGUOUS -> answer.append("?");
                case IN_STOCK -> answer.append("O");
            }
        }

        return answer.toString();
    }
    
    private void check(int[] currentOrder, ArrayList<int[]> reCheck) {
        int productA = currentOrder[0] - 1;
        int productB = currentOrder[1] - 1;
        boolean isDelivered = currentOrder[2] == 1;

        if (isDelivered) {
            stocks[productA] = IN_STOCK;
            stocks[productB] = IN_STOCK;
        } else {
            if (stocks[productA] == IN_STOCK) {
                stocks[productB] = OUT_OF_STOCK;
            } else if (stocks[productB] == IN_STOCK) {
                stocks[productA] = OUT_OF_STOCK;
            } else {
                if (reCheck != null) {
                    reCheck.add(currentOrder);
                }
            }
        }
    }
}
