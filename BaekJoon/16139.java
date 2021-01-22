import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        int q = Integer.parseInt(br.readLine());
        int[][] count = new int[26][S.length() + 1];

        for (int i = 0; i < S.length(); i++) {
            char curer = S.charAt(i);
            for (int j = 0; j < count.length; j++) {
                count[j][i + 1] = count[j][i];
                if (j == ((int) curer - 97)) {
                    count[j][i + 1]++;
                }
            }
        }

        StringTokenizer st;
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int target = st.nextToken().charAt(0) - 97;
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.write(count[target][end + 1] - count[target][start] + "\n");
        }
        
        bw.flush();
        bw.close();
    }
}

