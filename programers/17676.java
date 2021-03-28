import java.util.Arrays;

class Solution {

    private static final int MILLI = 1000;
    private int n;
    private int[] requestTimes;
    private int[] responseTimes;

    public int solution(String[] lines) {
        n = lines.length;

        requestTimes = new int[n];
        responseTimes = new int[n];

        for (int i = 0; i < n; i++) {
            String[] times = lines[i].split(" ");

            responseTimes[i] = getMilliSecond(times[1]);
            requestTimes[i] = responseTimes[i] - getProcessingMilliSecond(times[2]) + 1;
        }

        return Math.max(getMax(requestTimes), getMax(responseTimes));
    }

    private int getMax(int[] target) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            int start = target[i];
            int end = start + MILLI;

            for (int j = 0; j < n; j++) {
                if (requestTimes[j] >= start && requestTimes[j] < end) {
                    count++;
                } else if (responseTimes[j] >= start && responseTimes[j] < end) {
                    count++;
                } else if (requestTimes[j] <= start && responseTimes[j] >= end) {
                    count++;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    private int getMilliSecond(String time) {
        String[] parsed = time.split(":");
        int[] seconds = Arrays.stream(parsed[2].split("\\.")).mapToInt(Integer::parseInt).toArray();
        return 1000 * (60 * (60 * Integer.parseInt(parsed[0]) + Integer.parseInt(parsed[1])) + seconds[0]) + seconds[1];
    }

    private int getProcessingMilliSecond(String time) {
        time = time.replaceAll("s", "");
        String[] seconds = time.split("\\.");
        int processingSecond = seconds[0].isEmpty() ? 0 : Integer.parseInt(seconds[0]);
        int processingMilliSecond = seconds.length > 1 ? Integer.parseInt(seconds[1]) : 0;
        return processingSecond * MILLI + processingMilliSecond;
    }
}
