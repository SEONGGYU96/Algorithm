import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] targets = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(N, targets));
    }

    public static int solution(int N, int[] targets) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        for (int target : targets) {
            int current = list.get(0);
            if (current == target) {
                list.remove(0);
            } else if (leftFind(list, target) <= rightFind(list, target)) {
                while (current != target) {
                    current = leftShift(list);
                    answer++;
                }
                list.remove(0);
            } else {
                while (current != target) {
                    current = rightShift(list);
                    answer++;
                }
                list.remove(0);
            }
        }
        return answer;
    }

    private static int leftFind(ArrayList<Integer> list, int target) {
        int count = 0;
        for (Integer element : list) {
            if (element == target) {
                break;
            }
            count++;
        }
        return count;
    }

    private static int rightFind(ArrayList<Integer> list, int target) {
        int count = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == target) {
                break;
            }
            count++;
        }
        return count;
    }

    private static int leftShift(ArrayList<Integer> list) {
        list.add(list.get(0));
        list.remove(0);
        return list.get(0);
    }

    private static int rightShift(ArrayList<Integer> list) {
        list.add(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        return list.get(0);
    }
}

