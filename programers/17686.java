import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    private static final String HEAD_REGEX = "^[A-z .-]+";
    private static final String NUMBER_REGEX = "^[0-9]{1,5}";
    private static final Pattern headPattern = Pattern.compile(HEAD_REGEX);
    private static final Pattern numbersPattern = Pattern.compile(NUMBER_REGEX);

    public String[] solution(String[] files) {

        Arrays.sort(files, (o1, o2) -> {
            int compare = getHead(o1).compareTo(getHead(o2));
            if (compare == 0) {
                return Integer.compare(getNumber(o1), getNumber(o2));
            } else {
                return compare;
            }
        });

        return files;
    }

    private String getHead(String fileName) {
        return getString(headPattern, fileName).toLowerCase();
    }

    private int getNumber(String fileName) {
        return Integer.parseInt(getString(numbersPattern, fileName.replaceAll(HEAD_REGEX, "")));
    }

    private String getString(Pattern pattern, String str) {
        Matcher headMatcher = pattern.matcher(str);
        headMatcher.find();
        return headMatcher.group();
    }
}
