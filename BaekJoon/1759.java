import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[] characters = new char[C];
        char[] answer = new char[L];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            characters[i] = st.nextToken().charAt(0);
        }
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        Arrays.sort(characters);
        backtracking(characters,"", vowels, 0, 0, -1, 0, C, L);
    }

    private static void backtracking(char[] characters, String current, char[] vowels, int consonant, int vowel, int previous, int depth, int length, int r) {
        if (depth == r) {
            if (consonant >= 2 && vowel >= 1) {
                System.out.println(current);
            }
            return;
        }

        for (int i = previous + 1; i < length; i++) {
            boolean isVowel = false;

            for (char _vowel : vowels) {
                if (characters[i] == _vowel) {
                    isVowel = true;
                    break;
                }
            }
            backtracking(characters, current + characters[i], vowels, isVowel ? consonant : consonant + 1, isVowel ? vowel + 1 : vowel, i, depth + 1, length, r);
        }
    }
}
