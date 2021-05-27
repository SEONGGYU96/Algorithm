import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    private static final int UNIT = 9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");
        System.out.println(add(numbers[0], numbers[1], 0));
    }

    private static String add(String num1, String num2, int up) {
        if (num1.length() < UNIT && num2.length() < UNIT) {
            return safeAdd(num1, num2, up);
        }
        int newUp = 0;
        String num1_back = concat_back(num1);
        String num2_back = concat_back(num2);
        String suffix = safeAdd(num1_back, num2_back, up);
        if (suffix.length() > Math.max(num1_back.length(), num2_back.length())) {
            newUp = Integer.parseInt(suffix.substring(0, 1));
            suffix = suffix.substring(1);
        }
        String prefix = add(concat(num1), concat(num2), newUp);
        return prefix + suffix;
    }

    private static String concat(String num) {
        return num.substring(0, Math.max(0, num.length() - UNIT));
    }

    private static String concat_back(String num) {
        return num.substring(Math.max(0, num.length() - UNIT));
    }

    private static String safeAdd(String num1, String num2, int up) {
        int intNum1 = num1.isEmpty() ? 0 : Integer.parseInt(num1);
        int intNum2 = num1.isEmpty() ? 0 : Integer.parseInt(num2);
        return String.valueOf(intNum1 + intNum2 + up);
    }
}
