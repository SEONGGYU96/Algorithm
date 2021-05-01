import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        HashSet<Integer> answers = new HashSet<>();
        select(answers, user_id, banned_id, 0, 0);
        return answers.size();
    }

    private void select(Set<Integer> answers, String[] user_id, String[] banned_id, int depth, int bit) {
        if (depth == banned_id.length) {
            answers.add(bit);
            return;
        }

        String regex = banned_id[depth].replaceAll("\\*", "[a-z0-9]");
        for (int i = 0; i < user_id.length; i++) {
            if ((bit & (1 << i)) == 0 && user_id[i].matches(regex)) {
                select(answers, user_id, banned_id, depth + 1, bit | 1 << i);
            }
        }
    }
}
