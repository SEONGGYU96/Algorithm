import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        int G = new Scanner(new InputStreamReader(System.in)).nextInt();
        ArrayList<Integer> answer = new ArrayList<>();

        int previous = 1;
        int current = 1;
        while (true) {
            double g = Math.pow(current, 2) - Math.pow(previous, 2);
            if (g < G) {
                current++;
            } else if (g > G) {
                if (current - previous == 1) {
                    break;
                }
                previous++;
            } else {
                answer.add(current);
                previous++;
            }
        }
        if (answer.isEmpty()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(answer);
        for (int element: answer) {
            System.out.println(element);
        }
    }
}

