import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int atk = stoi(st.nextToken());

        int[][] rooms = new int[n][3];

        long l = 1;
        long r = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            rooms[i] = new int[]{stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken())};
            if (rooms[i][0] == 1) {
                r += ((long) rooms[i][1] * rooms[i][2]);
            }
        }

        while (l <= r) {
            long mid = (l + r) / 2;
            long tAtk = atk;
            long hp = mid;
            for (int i = 0; i < n; i++) {
                if (rooms[i][0] == 1) {
                    if (rooms[i][2] % tAtk == 0) {
                        hp -= (rooms[i][1] * ((rooms[i][2] / tAtk) - 1));
                    } else {
                        hp -= (rooms[i][1] * (rooms[i][2] / tAtk));
                    }
                    if (hp <= 0) break;
                } else if (rooms[i][0] == 2){
                    tAtk += rooms[i][1];
                    hp += rooms[i][2];
                    if (hp > mid) hp = mid;
                }
            }

            if (hp <= 0) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(l);

    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}