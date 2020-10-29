import java.util.Scanner;

class Main {
    private static void solution(int numOfAllPlayers, int numOfQuickPlayers, char[] namesOfQuickPlayers, int numOfGames, int[] numOfMovesPerGame){
        CircleQueue queue = new CircleQueue();
        CircleNode currentTagger = new CircleNode('A');
        currentTagger.increaseTaggerCount();

        for (int i = 1; i < numOfAllPlayers; i++) {
            char name = (char) (65 + i);
            queue.add(name);
        }

        for (int moves : numOfMovesPerGame) {
            CircleNode towel = queue.find(moves);
            if (!isQuickPlayer(towel.getName(), namesOfQuickPlayers)) {
                currentTagger.change(towel);
            }
            currentTagger.increaseTaggerCount();
        }

        queue.print();
        System.out.println(currentTagger.name + " " + currentTagger.taggerCount);
    }

    private static boolean isQuickPlayer(char name, char[] namesOfQuickPlayers) {
        for (char player : namesOfQuickPlayers) {
            if (player == name) {
                return true;
            }
        }
        return false;
    }

    static class CircleQueue {
        private CircleNode head;
        private int size;
        private CircleNode customHead;

        public CircleQueue() {
            this.head = null;
            this.customHead = null;
            this.size = 0;
        }

        public void add(char name) {
            this.add(new CircleNode(name));
        }

        public void add(CircleNode node) {
            if (head == null) {
                node.setIndex(0);
                head = node;
                customHead = head;
                size = 1;
            } else if (size == 1) {
                node.setPrevious(head);
                node.setNext(head);
                head.setPrevious(node);
                head.setNext(node);

                node.setIndex(node.getPrevious().getIndex() + 1);
                size++;
            } else {
                CircleNode temp = head.getPrevious();
                head.setPrevious(node);
                node.setNext(head);

                node.setPrevious(temp);
                node.getPrevious().setNext(node);

                node.setIndex(node.getPrevious().getIndex() + 1);
                size++;
            }
        }

        public void print() {
            CircleNode start = head;
            for (int i = 0; i < size; i++) {
                System.out.println(start.getName() + " " + start.getTaggerCount());
                start = start.getNext();
            }
        }

        public CircleNode find(int count) {
            if (count < 0) {
                return find(count * -1, false);
            } else {
                return find(count, true);
            }
        }

        private CircleNode find(int count, boolean isPositive) {
            CircleNode findHead = customHead;

            while (count != 0) {
                findHead = isPositive ? findHead.getNext() : findHead.getPrevious();
                count--;
            }
            customHead = findHead;
            return findHead;
        }
    }

    static class CircleNode {
        private char name;
        private CircleNode previous;
        private CircleNode next;
        private int index;
        private int taggerCount;

        public CircleNode(char name) {
            this.name = name;
            this.taggerCount = 0;
        }

        public void change(CircleNode target) {
            char tempName = target.getName();
            int tempCount = target.getTaggerCount();
            target.setName(this.name);
            target.setTaggerCount(this.taggerCount);
            this.name = tempName;
            this.taggerCount = tempCount;
        }

        public void increaseTaggerCount() {
            this.taggerCount++;
        }

        public void setName(char name) {
            this.name = name;
        }

        public void setPrevious(CircleNode previous) {
            this.previous = previous;
        }

        public void setNext(CircleNode next) {
            this.next = next;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setTaggerCount(int taggerCount) {
            this.taggerCount = taggerCount;
        }

        public char getName() {
            return name;
        }

        public CircleNode getPrevious() {
            return previous;
        }

        public CircleNode getNext() {
            return next;
        }

        public int getIndex() {
            return index;
        }

        public int getTaggerCount() {
            return taggerCount;
        }
    }

    private static class InputData {
        int numOfAllPlayers;
        int numOfQuickPlayers;
        char[] namesOfQuickPlayers;
        int numOfGames;
        int[] numOfMovesPerGame;
    }

    private static InputData processStdin() {
        InputData inputData = new InputData();

        try (Scanner scanner = new Scanner(System.in)) {
            inputData.numOfAllPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));

            inputData.numOfQuickPlayers = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            inputData.namesOfQuickPlayers = new char[inputData.numOfQuickPlayers];
            System.arraycopy(scanner.nextLine().trim().replaceAll("\\s+", "").toCharArray(), 0, inputData.namesOfQuickPlayers, 0, inputData.numOfQuickPlayers);

            inputData.numOfGames = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
            inputData.numOfMovesPerGame = new int[inputData.numOfGames];
            String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
            for(int i = 0; i < inputData.numOfGames ; i++){
                inputData.numOfMovesPerGame[i] = Integer.parseInt(buf[i]);
            }
        } catch (Exception e) {
            throw e;
        }

        return inputData;
    }

    public static void main(String[] args) throws Exception {
        InputData inputData = processStdin();

        solution(inputData.numOfAllPlayers, inputData.numOfQuickPlayers, inputData.namesOfQuickPlayers, inputData.numOfGames, inputData.numOfMovesPerGame);
    }
}
