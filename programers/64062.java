class Solution {

    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        int height = (int) Math.ceil(Math.log(stones.length) / Math.log(2));
        int[] segmentMax = new int[1 << (height + 1)];
        init(segmentMax, stones, 0, 0, stones.length - 1);

        for (int i = 0; i <= stones.length - k; i++) {
            answer = Math.min(answer, getMax(segmentMax, 0, 0, stones.length - 1, i, i + k - 1));
        }
        return answer;
    }

    private int init(int[] segmentMax, int[] target, int index, int start, int end) {
        if (start == end) {
            segmentMax[index] = target[start];
        } else {
            int mid = (start + end) / 2;
            segmentMax[index] = Math.max(init(segmentMax, target, index * 2 + 1, start, mid),
                    init(segmentMax, target, index * 2 + 2, mid + 1, end));
        }
        return segmentMax[index];
    }

    int getMax(int[] segmentMax, int index, int start, int end, int left, int right) {
        if (start > right || end < left)
            return 0;
        else if (left <= start && end <= right)
            return segmentMax[index];
        else {
            int mid = (start + end) / 2;
            return Math.max(getMax(segmentMax, index * 2 + 1, start, mid, left, right),
                    getMax(segmentMax, index * 2 + 2, mid + 1, end, left, right));
        }
    }
}
