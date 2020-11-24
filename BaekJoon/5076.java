import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        while (!line.equals("#")) {
            bw.write(solution(line) + '\n');
            line = br.readLine();
        }
        bw.flush();
        bw.close();
    }

    public static String solution(String line) {
        Stack<String> pairStack = new Stack<>();

        Pattern pattern = Pattern.compile("[<][/]?[a-z]*[ ]?[/]?[>]?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String current = matcher.group();

            if (Pattern.matches("<[a-z]*>", current)) {
                pairStack.push(current);
            } else if (Pattern.matches("<[a-z] ", current)) {
                pairStack.push(current.replace(' ', '>'));
            } else if (Pattern.matches("[<][/][a-z]*[>]", current)) {
                if (!pairStack.empty() && pairStack.peek().equals(current.replaceAll("/", ""))) {
                    pairStack.pop();
                } else {
                    return "illegal";
                }
            }
        }
        return pairStack.isEmpty() ? "legal" : "illegal";
    }
}
