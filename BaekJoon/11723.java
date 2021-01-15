import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int S = 0;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
             st = new StringTokenizer(br.readLine());
             String operator = st.nextToken();

             int operand = 0;
             if (st.hasMoreTokens()) {
                 operand = Integer.parseInt(st.nextToken());
             }

             switch (operator) {
                 case "add" :
                     if (!contains(S, operand)) {
                         S |= (1 << operand);
                     }
                     break;

                 case "remove" :
                     if (contains(S, operand)) {
                         S &= ~(1 << operand);
                     }
                     break;

                 case "check" :
                     int result = contains(S, operand) ? 1 : 0;
                     bw.write(result + "\n");
                     break;

                 case "toggle" :
                     if (contains(S, operand)) {
                         S &= ~(1 << operand);
                     } else {
                         S |= (1 << operand);
                     }
                     break;

                 case "all" :
                     S = (1 << 21) - 1;
                     break;

                 case "empty" :
                     S = 0;
                     break;
             }
        }
        bw.flush();
        bw.close();
    }

    private static boolean contains(int S, int target) {
        return (S & (1 << target)) != 0;
    }
}
