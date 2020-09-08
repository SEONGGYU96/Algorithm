//내가 최초로 작성한 코드

class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 1;
            PrintQueue queue = new PrintQueue();

            for (int i = 0; i < priorities.length; i++) {
                queue.push(new PrintItem(i, priorities[i]));
            }

            while (true) {
                PrintItem currentItem = queue.pop();
                int mostPriorityOfRest = queue.getMostPriority();

                if (currentItem.priority >= mostPriorityOfRest) {
                    if (currentItem.location == location) {
                        break;
                    } else {
                        answer++;
                    }
                } else {
                    queue.push(currentItem);
                }
            }

            return answer;
        }

        class PrintQueue {
            private PrintItem head;
            private PrintItem tail;
            private int size = 0;

            public PrintQueue() {
            }

            public int push(PrintItem item) {
                if (head == null) {
                    head = item;
                } else {
                    tail.setNextPrintItem(item);
                }
                tail = item;
                return ++size;
            }

            public PrintItem pop() {
                PrintItem item = head;
                if (head.hasNext()) {
                    head = head.getNextPrintItem();
                }
                size--;
                item.nextPrintItem = null;
                return item;
            }

            public int getMostPriority() {
                int max = 0;
                PrintItem searchHead = head;
                while (true) {
                    int priority = searchHead.getPriority();
                    if (priority > max) {
                        max = priority;
                    }
                    if (searchHead.hasNext()) {
                        searchHead = searchHead.getNextPrintItem();
                    } else {
                        break;
                    }
                }
                return max;
            }
        }

        class PrintItem {
            private int location;
            private int priority;
            private PrintItem nextPrintItem;

            public PrintItem(int location, int priority) {
                this.location = location;
                this.priority = priority;
            }

            public int getLocation() {
                return location;
            }

            public int getPriority() {
                return priority;
            }

            public void setNextPrintItem(PrintItem nextPrintItem) {
                this.nextPrintItem = nextPrintItem;
            }

            public PrintItem getNextPrintItem() {
                return nextPrintItem;
            }

            public boolean hasNext() {
                return nextPrintItem != null;
            }
        }
    }

//다른 분이 푼 코드
class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;
            Queue<Integer> priorityList = new LinkedList<>();


            for (int priority : priorities) {
                priorityList.add(priority);
            }

            Arrays.sort(priorities);

            while (true) {
                int currentPriority = priorityList.poll();

                if (currentPriority == priorities[priorities.length - 1 - answer]) {
                    answer++;
                    location--;

                    if (location < 0) {
                        break;
                    }
                } else {
                    priorityList.add(currentPriority);
                    location--;

                    if (location < 0) {
                        location = priorities.length - 1;
                    }
                }
            }

            return answer;
        }
    }
