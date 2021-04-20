import java.util.*;

class Solution {

    public String[] solution(String[] record) {
        String[] answer;

        HashMap<String, String> nameMap = new HashMap<>();
        Queue<Log> logQueue = new LinkedList<>();

        for (String _record : record) {
            String[] parsed = _record.split(" ");
            String operator = parsed[0];
            Log.Type type;
            switch (operator) {
                case "Enter" -> type = Log.Type.Enter;
                case "Leave" -> type = Log.Type.Leave;
                default -> type = Log.Type.Change;
            }
            String userId = parsed[1];
            if (type != Log.Type.Leave) {
                String nickName = parsed[2];
                nameMap.put(userId, nickName);
            }
            if (type != Log.Type.Change) {
                logQueue.offer(new Log(type, userId));
            }
        }
        answer = new String[logQueue.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = logQueue.poll().getString(nameMap);
        }
        return answer;
    }

    private static class Log {
        private static final String enterFormat = "%s님이 들어왔습니다.";
        private static final String leaveFormat = "%s님이 나갔습니다.";

        private Type type;
        private String userId;

        public Log(Type type, String userId) {
            this.type = type;
            this.userId = userId;
        }

        public String getString(Map<String, String> nickNameMap) {
            return String.format(type == Type.Enter ? enterFormat : leaveFormat, nickNameMap.get(userId));
        }

        private enum Type {
            Enter, Leave, Change
        }
    }
}
