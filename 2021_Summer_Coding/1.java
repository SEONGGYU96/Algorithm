class Solution {
    public int[] solution(String code, String day, String[] data) {
        ArrayList<Data> answerList = new ArrayList<>();
        String regex = String.format("price=[0-9]+ code=%s time=%s[0-9]{2}", code, day);
        for (String _data : data) {
            if (_data.matches(regex)) {
                String[] parsed = _data.split(" ");
                int price = Integer.parseInt(parsed[0].replaceAll("[^0-9]", ""));
                int date = Integer.parseInt(parsed[2].replaceAll("[^0-9]", ""));
                answerList.add(new Data(price, date));
            }
        }
        answerList.sort(Comparator.comparingInt(o -> o.date));
        int[]answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i).price;
        }

        return answer;
    }

    private static class Data {
        private final int price;
        private final int date;

        public Data(int price, int date) {
            this.price = price;
            this.date = date;
        }
    }
}

