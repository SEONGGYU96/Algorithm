import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;

        Map<String, Integer> playMap = new HashMap<>();
        Map<String, List<Music>> musicMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (!musicMap.containsKey(genres[i])) {
                List<Music> list = new ArrayList<>();
                list.add(new Music(i, plays[i]));
                musicMap.put(genres[i], list);
            } else {
                musicMap.get(genres[i]).add(new Music(i, plays[i]));
            }
            if (!playMap.containsKey(genres[i])) {
                playMap.put(genres[i], plays[i]);
            } else {
                playMap.put(genres[i], playMap.get(genres[i]) + plays[i]);
            }
        }

        Queue<Genre> queue = new PriorityQueue<>();

        for (String genre : playMap.keySet()) {
            queue.add(new Genre(genre, playMap.get(genre)));
            Collections.sort(musicMap.get(genre));
        }

        List<Integer> answerList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Genre current = queue.poll();
            List<Music> musics = musicMap.get(current.name);
            for (int i = 0; i < Math.min(2, musics.size()); i++) {
                answerList.add(musics.get(i).number);
            }
        }
        answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    private static class Genre implements Comparable<Genre> {
        private String name;
        private int plays;

        public Genre(String name, int plays) {
            this.name = name;
            this.plays = plays;
        }

        @Override
        public int compareTo(Genre o) {
            if (plays > o.plays) {
                return -1;
            } else if (plays < o.plays) {
                return 1;
            } else {
                return name.compareTo(o.name);
            }
        }
    }

    private static class Music implements Comparable<Music> {
        private int number;
        private int plays;

        public Music(int number, int plays) {
            this.number = number;
            this.plays = plays;
        }

        @Override
        public int compareTo(Music o) {
            if (plays > o.plays) {
                return -1;
            } else if (plays < o.plays) {
                return 1;
            } else {
                return Integer.compare(number, o.number);
            }
        }
    }
}
