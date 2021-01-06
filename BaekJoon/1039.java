import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(solution(N, K));
    }

    public static int solution(int N, int K) {
        int answer = 0;
        String strN = String.valueOf(N);

        Queue<String> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> offset = new ArrayList<>();
        getOffset(offset, new boolean[strN.length()], 0, strN.length(), 2);

        queue.add(strN);

        while (!queue.isEmpty()) {
            Set<String> isVisited = new HashSet<>();
            boolean isOperated = false;
            if (K == 0) {
                break;
            }
//            System.out.println("======= " + K + " =======");
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
//                System.out.println(current);

                for (ArrayList<Integer> switchCase : offset) {
                    char[] currentArray = current.toCharArray();
                    char temp = currentArray[switchCase.get(1)];
                    currentArray[switchCase.get(1)] = currentArray[switchCase.get(0)];
                    currentArray[switchCase.get(0)] = temp;
                    if (currentArray[0] == '0') {
                        continue;
                    }

                    StringBuilder builder = new StringBuilder();
                    for (char element : currentArray) {
                        builder.append(element);
                    }
                    String result = builder.toString();
                    if (!isVisited.contains(result)) {
                        isVisited.add(result);
                        queue.add(result);
                        isOperated = true;
                        if (K == 1) {
                            answer = Math.max(answer, Integer.parseInt(result));
                        }
                    }
                }
                if (!isOperated) {
                    return -1;
                }
            }
            K--;
        }
//        System.out.println();

        return answer;
    }

    private static void getOffset(ArrayList<ArrayList<Integer>> list, boolean[] isVisited, int depth, int n, int r) {
        if (r == 0) {
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < isVisited.length; i++) {
                if (isVisited[i]) {
                    result.add(i);
                }
            }
            list.add(result);
            return;
        }

        if (depth == n) {
            return;
        }

        isVisited[depth] = true;
        getOffset(list, isVisited, depth + 1, n, r - 1);

        isVisited[depth] = false;
        getOffset(list, isVisited, depth + 1, n, r);
    }
}

