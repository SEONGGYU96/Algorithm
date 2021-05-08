import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parsed = br.readLine().split("-");
        int answer = getValue(parsed[0]);
        for (int i = 1; i < parsed.length; i++) {
            answer -= getValue(parsed[i]);
        }
        System.out.println(answer);
    }
    
    private static int getValue(String s) {
        String[] parsed = s.split("\\+");
        int value = 0;
        for (String element : parsed) {
            value += Integer.parseInt(element);
        }
        return value;
    }
}
