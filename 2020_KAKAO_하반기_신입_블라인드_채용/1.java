import java.util.regex.Pattern;

class Solution {
        public String solution(String new_id) {
            String answer = "";

            if (checkId(new_id)) {
                answer = new_id;
            } else {
                answer = fixId(new_id);
            }

            return answer;
        }

        private boolean checkId(String id) {
            if (!Pattern.matches("^[a-z0-9_.-]{3,15}$", id)) {
                return false;
            }

            return !id.startsWith(".") && !id.endsWith(".");
        }

        private String fixId(String id) {
            id = id.toLowerCase();

            id = id.replaceAll("[^a-z0-9_.-]", "");

            while (id.contains("..")) {
                id = id.replace("..", ".");
            }

            if (id.startsWith(".")) {
                id = id.substring(1);
            }

            if (id.endsWith(".")) {
                id = id.substring(0, id.length() - 1);
            }
            
            if (id.isEmpty()) {
                id = "a";
            }


            if (id.length() > 15) {
                id = id.substring(0, 15);

                if (id.endsWith(".")) {
                    id = id.substring(0, id.length() - 1);
                }
            } else {
                while (id.length() < 3) {
                    id = id.concat(String.valueOf(id.charAt(id.length() - 1)));
                }
            }

            return id;
        }
    }
