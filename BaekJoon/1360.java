import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<History> histories = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            String arg = st.nextToken();
            int at = Integer.parseInt(st.nextToken());

            if (command.equals("type")) {
                if (histories.isEmpty()) {
                    histories.add(new History(at, arg));
                } else {
                    histories.add(new History(at, histories.get(histories.size() - 1).value + arg));
                }
            } else {
                int backTime = Integer.parseInt(arg);
                histories.add(new History(at, getHistory(histories, at - backTime)));
            }
        }
        System.out.println(histories.get(histories.size() - 1).value);
    }

    private static String getHistory(ArrayList<History> histories, int targetTime) {
        for (int i = histories.size() - 1; i >= 0; i--) {
            if (histories.get(i).time < targetTime) {
                return histories.get(i).value;
            }
        }
        return "";
    }

    private static class History {
        private final int time;
        private final String value;

        public History(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }
}

