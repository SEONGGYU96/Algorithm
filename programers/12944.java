import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public double solution(int[] arr) {
        return Arrays.stream(arr).average().getAsDouble();
    }
}
