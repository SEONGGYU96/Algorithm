import java.util.Arrays;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int playTime = getSecond(play_time);
        long[] preSum = new long[playTime + 1];

        for (String log : logs) {
            String[] times = log.split("-");
            int start = getSecond(times[0]);
            int end = getSecond(times[1]);
            preSum[start] += 1;
            preSum[end] -= 1;
        }

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + preSum[i];
        }

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + preSum[i];
        }

        int advertisingTime = getSecond(adv_time);
        long max = preSum[advertisingTime];
        int at = 0;

        for (int i = advertisingTime + 1; i < playTime; i++) {
            long sum = preSum[i] - preSum[i - advertisingTime];
            if (sum > max) {
                max = sum;
                at = i - advertisingTime + 1;
            }
        }

        int second = at % 60;
        int minute = (at / 60) % 60;
        int hour = at / (60 * 60);
        answer = String.format("%02d:%02d:%02d", hour, minute, second);

        return answer;
    }
    
    private int getSecond(String time) {
        int[] times = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return times[2] + (times[1] + times[0] * 60) * 60;
    }
}
