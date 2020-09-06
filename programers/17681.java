class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] answer = new String[n];

            char[][] map1 = toBinaryArray(n, arr1);
            char[][] map2 = toBinaryArray(n, arr2);

            for (int i = 0; i < map1.length; i++) {
                StringBuilder pieceOfAnswer = new StringBuilder();
                for (int j = 0; j < map1[0].length; j++) {
                    if (map1[i][j] == '1' || map2[i][j] == '1') {
                        pieceOfAnswer.append("#");
                    } else {
                        pieceOfAnswer.append(" ");
                    }
                }
                answer[i] = pieceOfAnswer.toString();
            }

            return answer;
        }

        private char[][] toBinaryArray(int n, int[] arr) {
            char[][] map = new char[n][n];

            for (int i = 0; i < arr.length; i++) {
                char[] charArray = Integer.toBinaryString(arr[i]).toCharArray();
                int gap = n - charArray.length;
                int count = gap;
                for (int j = 0; j < arr.length; j++) {
                    if (count > 0) {
                        map[i][j] = '0';
                        count--;
                    } else {
                        map[i][j] = charArray[j - gap];
                    }
                }
            }

            return map;
        }
    }
