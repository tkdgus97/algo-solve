
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] wheels;
    private static int[][] tmpWheels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        wheels = new int[4][8];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = s.charAt(j) - '0';
            }
        }
        st = new StringTokenizer(br.readLine());
        int k = stoi(st.nextToken());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num = stoi(st.nextToken()) - 1;
            int dir = stoi(st.nextToken());

            simul(num, dir);
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (wheels[i][0] == 1) {
                result += (int) Math.pow(2, i);
            }
        }

        System.out.println(result);
    }

    private static void simul(int num, int dir) {
        tmpWheels = new int[4][8];
        for (int i = 0; i < 8; i++) {
            int move = (8 + (i + dir)) % 8;
            tmpWheels[num][move] = wheels[num][i];
        }


        right(num + 1, -dir, true);
        left(num - 1, -dir, true);

        wheels = tmpWheels;
    }

    private static void right(int l, int dir, boolean turn) {
        if (l >= 4) return;

        if (!turn) {
            right(l + 1, dir, false);
            for (int i = 0; i < 8; i++) {
                tmpWheels[l][i] = wheels[l][i];
            }
        } else {
            if (wheels[l][6] != wheels[l - 1][2]) {
                right(l + 1, -dir, true);
                for (int i = 0; i < 8; i++) {
                    int move = (8 + (i + dir)) % 8;
                    tmpWheels[l][move] = wheels[l][i];
                }
            } else {
                right(l + 1, dir, false);
                for (int i = 0; i < 8; i++) {
                    tmpWheels[l][i] = wheels[l][i];
                }
            }
        }
    }

    private static void left(int l, int dir, boolean turn) {
        if (l < 0) return;

        if (!turn) {
            left(l - 1, dir, false);
            for (int i = 0; i < 8; i++) {
                tmpWheels[l][i] = wheels[l][i];
            }
        } else {
            if (wheels[l][2] != wheels[l + 1][6]) {
                left(l - 1, -dir, true);
                for (int i = 0; i < 8; i++) {
                    int move = (8 + (i + dir)) % 8;
                    tmpWheels[l][move] = wheels[l][i];
                }
            } else {
                left(l - 1, dir, false);
                for (int i = 0; i < 8; i++) {
                    tmpWheels[l][i] = wheels[l][i];
                }
            }
        }
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

