class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        Queue<int[]> jobHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] > o2[0]) {
                return 1;
            } else if (o1[0] < o2[0]) {
                return - 1;
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        Queue<int[]> waitingHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        jobHeap.addAll(Arrays.asList(jobs));

        int currentTime = 0;
        while (true) {
            if (waitingHeap.isEmpty()) {
                if (jobHeap.isEmpty()) {
                    break;
                }
                currentTime = jobHeap.peek()[0];
                waitingHeap.add(jobHeap.poll());
            }

            int[] current = waitingHeap.poll();
            currentTime += current[1];
            answer += currentTime - current[0];

            while (!jobHeap.isEmpty() && jobHeap.peek()[0] <= currentTime) {
                waitingHeap.add(jobHeap.poll());
            }
        }

        return answer / jobs.length;
    }
}
