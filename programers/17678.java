import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Queue<LocalTime> localTimeHeap = new PriorityQueue<>();
        for (String time : timetable) {
            localTimeHeap.add(LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")));
        }

        LocalTime start = LocalTime.of(9, 0, 0, 0);

        LocalTime ans = null;
        LocalTime arrive = null;
        int remained = 0;
        for (int i = 0; i < n; i++) {
            arrive = start.plusMinutes(i * t);
            remained = m;
            while (!localTimeHeap.isEmpty() && !localTimeHeap.peek().isAfter(arrive) && remained > 0) {
                ans = localTimeHeap.poll();
                remained--;
            }
        }

        if (ans == null || remained > 0) {
            ans = LocalTime.of(arrive.getHour(), arrive.getMinute(), arrive.getSecond());
        } else {
            ans = ans.minusMinutes(1);
        }

        return String.format("%02d:%02d", ans.getHour(), ans.getMinute());
    }
}
