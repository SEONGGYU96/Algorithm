import java.util.HashMap;

class Solution {
        public String solution(int[] numbers, String hand) {
            StringBuilder answer = new StringBuilder();

            HashMap<Integer, Key> keypad = new HashMap<>();

            int rowStart = 0;
            int colStart = 0;

            int leftHand = 10;
            int rightHand = 12;


            for (int i = 1; i <= 12; i++) {
                if (i == 11) {
                    keypad.put(0, new Key(rowStart, colStart++));
                } else {
                    keypad.put(i, new Key(rowStart, colStart++));
                }

                if (colStart > 2) {
                    rowStart++;
                    colStart = 0;
                }
            }

            for (int targetNumber : numbers) {
                if (targetNumber == 1 || targetNumber == 4 || targetNumber == 7) {
                    answer.append("L");
                    leftHand = targetNumber;
                    continue;
                } else if (targetNumber == 3 || targetNumber == 6 || targetNumber == 9) {
                    answer.append("R");
                    rightHand = targetNumber;
                    continue;
                }

                Key leftKey = keypad.get(leftHand);
                Key rightKey = keypad.get(rightHand);
                Key targetKey = keypad.get(targetNumber);

                if (leftKey == null || rightKey == null || targetKey == null) {
                    break;
                }

                int leftDistance = leftKey.getDistance(targetKey);
                int rightDistance = rightKey.getDistance(targetKey);

                if (leftDistance > rightDistance) {
                    answer.append("R");
                    rightHand = targetNumber;
                } else if (leftDistance < rightDistance) {
                    answer.append("L");
                    leftHand = targetNumber;
                } else {
                    if (hand.equals("left")) {
                        answer.append("L");
                        leftHand = targetNumber;
                    } else {
                        answer.append("R");
                        rightHand = targetNumber;
                    }
                }
            }

            return answer.toString();
        }

        class Key {
            int row;
            int col;

            public Key(int row, int col) {
                this.row = row;
                this.col = col;
            }

            public int getRow() {
                return row;
            }

            public int getCol() {
                return col;
            }

            public int getDistance(Key targetKey) {
                return Math.abs(this.row - targetKey.row) + Math.abs(this.col - targetKey.col);
            }
        }
    }
