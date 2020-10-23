import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int attack = Integer.parseInt(st.nextToken());
        int[][] rooms = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rooms[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(attack, rooms));
    }

    public static long solution(int attack, int[][] rooms) {
        long head = 1;
        long tail = ((long)2 << 62) - 1;

        while (head <= tail) {
            long middle = (head + tail) / 2;
            long currentHP = middle;
            long currentAttack = attack;
            boolean isSuccess = true;

            for (int[] room : rooms) {
                int roomType = room[0];
                if (roomType == 1) {
                    int attack_monster = room[1];
                    int hp_monster = room[2];

                    long count = (long) hp_monster / currentAttack;
                    if (hp_monster % currentAttack != 0) {
                        count++;
                    }
                    long damage = (long) attack_monster * (count - 1);
                    if (damage >= currentHP) {
                        isSuccess = false;
                        break;
                    } else {
                        currentHP -= damage;
                        continue;
                    }
                } else {
                    int additionalAttack = room[1];
                    int additionalHP = room[2];
                    currentAttack += additionalAttack;
                    if (currentHP + additionalHP >= middle) {
                        currentHP = middle;
                        continue;
                    } else {
                        currentHP += additionalHP;
                        continue;
                    }
                }
            }

            if (isSuccess) {
                tail = middle - 1;
            } else {
                head = middle + 1;
            }
        }
        return head;
    }
}
