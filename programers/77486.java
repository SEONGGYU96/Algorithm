import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer;
        
        HashMap<String, Member> members = new HashMap<>();
        
        for (int i = 0; i < enroll.length; i++) {
            Member member = new Member();
            if (!referral[i].equals("-")) {
                member.parent = members.get(referral[i]);;
            }
            members.put(enroll[i], member);
        }
        
        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;
            members.get(seller[i]).earn(profit);
        }
        
        answer = new int[enroll.length];
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = members.get(enroll[i]).profit;
        }
        
        return answer;
    }
    
    private static class Member {
        private Member parent;
        private int profit;
        
        public Member() {
            this.parent = null;
            this.profit = 0;
        }
        
        public void earn(int profit) {
            int fee = (int) (profit * 0.1);
            this.profit += profit - fee;
            if (parent != null) {
                parent.earn(fee);
            }
        }
    }
}
