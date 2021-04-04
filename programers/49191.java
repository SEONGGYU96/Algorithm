import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        Player[] players = new Player[n + 1];

        for (int i = 1; i < n + 1; i++) {
            players[i] = new Player(i);
        }

        for (int[] result : results) {
            players[result[0]].winList.add(players[result[1]]);
            players[result[1]].loseList.add(players[result[0]]);
        }

        for (int i = 1; i < n + 1; i++) {
            Player current = players[i];
            if (current.getVictories().size() + current.getLoses().size() == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    private static class Player {
        int number;
        List<Player> winList = new ArrayList<>();
        List<Player> loseList = new ArrayList<>();
        Set<Player> victories = null;
        Set<Player> loses = null;

        public Player(int number) {
            this.number = number;
        }

        public Set<Player> getVictories() {
            if (victories == null) {
                victories = new HashSet<>(winList);
                for (Player player : winList) {
                    victories.addAll(player.getVictories());
                }
            }
            return victories;
        }

        public Set<Player> getLoses() {
            if (loses == null) {
                loses = new HashSet<>(loseList);
                for (Player player : loseList) {
                    loses.addAll(player.getLoses());
                }
            }
            return loses;
        }
    }
}
