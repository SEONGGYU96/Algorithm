import java.io.IOException;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
            String sizeStr = scanner.nextLine();
            String element1 = scanner.nextLine();
            String element2 = scanner.nextLine();

            StringTokenizer st = new StringTokenizer(sizeStr, " ");
            int count = st.countTokens();
            int[] size = new int[count];
            for (int i = 0; i < count; i++) {
                size[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(element1, " ");
            count = st.countTokens();
            int[] elements1 = new int[count];
            for (int i = 0; i < count; i++) {
                elements1[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(element2, " ");
            count = st.countTokens();
            int[] elements2 = new int[count];
            for (int i = 0; i < count; i++) {
                elements2[i] = Integer.parseInt(st.nextToken());
            }

        //output
        System.out.println(solution(size, elements1, elements2));
    }
    
    public static int solution(int[] size, int[] elements1, int[] elements2) {

            Set<Integer> elementA = new HashSet<>();
            Set<Integer> elementB = new HashSet<>();

            for (int element : elements1) {
                elementA.add(element);
            }

            for (int element : elements2) {
                elementB.add(element);
            }

            Set<Integer> temp = new HashSet<>(elementA);

            elementA.removeAll(elementB);
            elementB.removeAll(temp);

            return elementA.size() + elementB.size();
        }
}

