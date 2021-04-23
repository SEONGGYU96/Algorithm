import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.*;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Pattern pattern = Pattern.compile("((100+1+)|(01))+");
        for (int i = 0; i < N; i++) {
            Matcher matcher = pattern.matcher(br.readLine());
            bw.write(matcher.matches() ? "YES\n" : "NO\n");
        }

        bw.flush();
        bw.close();
    }
}
