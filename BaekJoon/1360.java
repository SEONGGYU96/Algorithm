class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<String> history = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            String arg = st.nextToken();
            int at = Integer.parseInt(st.nextToken());

            if (command.equals("type")) {
                if (history.isEmpty()) {
                    history.add(arg);
                } else {
                    history.add(history.get(history.size() - 1) + arg);
                }
            } else {
                int backTime = Integer.parseInt(arg);
                history.add(history.get(Math.max(0, at - backTime - 1)));
                
                for (int j = history.size() - 1; j >= 0; j--) {
                }
            }

        }
        System.out.println(history.get(history.size() - 1));
    }
}
