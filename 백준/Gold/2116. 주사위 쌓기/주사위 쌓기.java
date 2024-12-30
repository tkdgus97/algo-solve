import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] dice;
    private static int[][] loc;
    private static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());

        dice = new int[n][6];
        loc = new int[n][7];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                int v = stoi(st.nextToken());
                dice[i][j] = v;
                loc[i][v] = j;
            }
        }

        for (int i = 0; i < 6; i++) {
            int m = 0;
            for (int j = 1; j <= 6; j++) {
                if (j == dice[0][i] || j == dice[0][pair(i)]) continue;
                m = Math.max(m, j);
            }
            rec(1, dice[0][pair(i)], m);
        }
        System.out.println(result);
    }

    private static void rec(int d, int b, int sum) {
        if (d == n) {
            result = Math.max(sum, result);
            return;
        }

        int l = loc[d][b];
        int p = pair(l);
        int m = 0;
        for (int i = 1; i <= 6; i++) {
            if (i == dice[d][l] || i == dice[d][p]) continue;
            m = Math.max(i, m);
        }

        sum += m;
        rec(d + 1, dice[d][p], sum);
    }

    private static int pair(int b) {
        if (b == 0) {
            return 5;
        } else if (b == 1) {
            return 3;
        } else if (b == 2) {
            return 4;
        } else if (b == 3) {
            return 1;
        } else if (b == 4) {
            return 2;
        } else if (b == 5) {
            return 0;
        }
        return 0;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

