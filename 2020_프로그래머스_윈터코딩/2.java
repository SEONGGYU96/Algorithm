class Solution {
    public String solution(String encrypted_text, String key, int rotation) {
        final int PREVIOUS_INT = 96;
        final int RANGE_LETTER = 26;
        StringBuilder answer = new StringBuilder();
        int length = encrypted_text.length();
        rotation %= length;

        if (rotation < 0) {
            rotation = length + rotation;
        }
        String start = encrypted_text.substring(0, rotation);
        String end = encrypted_text.substring(rotation);

        String AfterRotation = end + start;

        for (int i = 0; i < length; i++) {
            char currentLetter = AfterRotation.charAt(i);
            char currentKey = key.charAt(i);
            int diff = currentLetter - currentKey;
            char newLetter;

            if (diff < 0) {
                newLetter = (char) (PREVIOUS_INT + RANGE_LETTER + diff);
            } else {
                newLetter = (char) (PREVIOUS_INT + diff);
            }
            answer.append(newLetter);
        }
        return answer.toString();
    }
}

