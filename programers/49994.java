import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(String dirs) {
        int answer = 0;

        Point position = new Point(0, 0);

        Map<Point, Set<Point>> history = new HashMap<>();
        history.put(position, new HashSet<>());

        for (int i = 0; i < dirs.length(); i++) {
            char direction = dirs.charAt(i);
            Point nextPosition = new Point(position.x, position.y);

            switch (direction) {
                case 'U' -> nextPosition.y++;
                case 'R' -> nextPosition.x++;
                case 'D' -> nextPosition.y--;
                case 'L' -> nextPosition.x--;
            }

            if (nextPosition.x > 5 || nextPosition.x < -5 || nextPosition.y > 5 || nextPosition.y < -5) {
                continue;
            }

            if (!history.containsKey(nextPosition)) {
                history.put(nextPosition, new HashSet<>());
            }

            if (!history.get(position).contains(nextPosition) && !history.get(nextPosition).contains(position)) {
                answer++;
            }
            history.get(position).add(nextPosition);
            position = nextPosition;
        }
        return answer;
    }
}
