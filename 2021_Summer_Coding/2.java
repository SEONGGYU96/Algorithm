class Solution {
    public int[] solution(int[] t, int[] r) {
        int[] answer = new int[t.length];
        Queue<Customer> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.priority > o2.priority) {
                return 1;
            } else if (o1.priority < o2.priority) {
                return -1;
            } else {
                if (o1.arriveTime > o2.arriveTime) {
                    return 1;
                } else if (o1.arriveTime < o2.arriveTime) {
                    return -1;
                } else {
                    return Integer.compare(o1.id, o2.id);
                }
            }
        });
        Queue<Customer> arriveQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.arriveTime));
        for (int i = 0; i < t.length; i++) {
            arriveQueue.offer(new Customer(i, t[i], r[i]));
        }

        int head = 0;
        int lift = 0;
        while (!arriveQueue.isEmpty() || !queue.isEmpty()) {
            while (!arriveQueue.isEmpty() && arriveQueue.peek().arriveTime <= lift) {
                queue.offer(arriveQueue.poll());
            }
            if (!queue.isEmpty()) {
                answer[head++] = queue.poll().id;
            }
            lift++;
        }

        return answer;
    }

    private static class Customer {
        private final int id;
        private final int arriveTime;
        private final int priority;

        public Customer(int id, int arriveTime, int priority) {
            this.id = id;
            this.arriveTime = arriveTime;
            this.priority = priority;
        }
    }
}
