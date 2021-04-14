import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.StringTokenizer;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        boolean hasAnswer = false;
        int playT = 0;

        String M = convert(m);


        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        for (String info : musicinfos) {
            StringTokenizer st = new StringTokenizer(info, ",");

            LocalTime start = LocalTime.parse(st.nextToken(), format);
            LocalTime end = LocalTime.parse(st.nextToken(), format);

            int playTime = (int) start.until(end, ChronoUnit.MINUTES);
            String name = st.nextToken();
            String melody = convert(st.nextToken());

            String playedMelody = melody.repeat(playTime / melody.length());
            playedMelody += melody.substring(0, playTime % melody.length());
            
            if (playedMelody.contains(M)) {
                if (!hasAnswer || playT < playTime) {
                    answer = name;
                    playT = playTime;
                    hasAnswer = true;
                }
            }
        }
        return answer;
    }

    private static final String[][] map = {{"C#", "c"}, {"D#", "d"}, {"F#", "f"}, {"G#", "g"}, {"A#", "a"}};

    private String convert(String m) {
        String result = m;
        for (String[] rule : map) {
            result = result.replaceAll(rule[0], rule[1]);
        }
        return result;
    }
}
