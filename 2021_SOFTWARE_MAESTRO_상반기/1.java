import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Main {  

    private static HashMap<Character, ArrayList<Character>> skils;

  public static void main(String args[]) throws IOException { 
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    skils = new HashMap<>();
    char start = '0';
    
    while (st.hasMoreTokens()) {
        char current = st.nextToken().charAt(0);
        skils.put(current, new ArrayList<>());
        if (start == '0') {
            start = current;
        }
    }
    
    int N = Integer.parseInt(br.readLine());
    
    for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        char before = st.nextToken().charAt(0);
        char after = st.nextToken().charAt(0);
        
        ArrayList<Character> sequence = skils.get(before);
        sequence.add(after);
    }
    dfs(start, "");
  }
  
  private static void dfs(char start, String print) {
    String currentPrint = print + start + " ";
    
    List<Character> sequence = skils.get(start);
    
    if (sequence.isEmpty()) {
        System.out.println(currentPrint);
    } else {
        for (char skil : sequence) {
            dfs(skil, currentPrint);
        }
    }
  }
}
