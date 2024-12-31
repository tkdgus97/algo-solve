import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean[] fix = new boolean[12];
    private static boolean[] visit = new boolean[13];
    private static int[] combi = new int[12];
    private static boolean find = false;
    private static int[] result = new int[12];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

//        st = new StringTokenizer(br.readLine());
        int idx = 0;
        char[][] star = new char[5][9];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < 9; j++) {
                char c = s.charAt(j);
                star[i][j] = c;
                if (c == 'x') {
                    idx++;
                } else if (c != '.') {
                    fix[idx] = true;
                    combi[idx] = c - 64;
                    visit[c - 64] = true;
                    idx++;
                }
            }
        }

        rec(0);
        idx = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (star[i][j] != '.') {
                    int v = result[idx++] + 64;
                    System.out.print((char) v+"");
                } else {
                    System.out.print(star[i][j]+"");
                }
            }
            System.out.println();
        }
    }

    private static void rec(int n) {
        if (find) return;
        if (n == 12) {
            if (check()) {
                find = true;
                for (int i = 0; i < 12; i++) {
                    result[i] = combi[i];
                }
            }
            return;
        }
        if (fix[n]) {
            rec(n + 1);
        } else {
            for (int i = 1; i <= 12; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    combi[n] = i;
                    rec(n + 1);
                    visit[i] = false;
                }
            }

        }

    }

    private static boolean check() {
        if ((combi[1] + combi[2] + combi[3] + combi[4]) != 26) return false;
        if ((combi[7] + combi[8] + combi[9] + combi[10]) != 26) return false;
        if ((combi[0] + combi[2] + combi[5] + combi[7]) != 26) return false;
        if ((combi[0] + combi[3] + combi[6] + combi[10]) != 26) return false;
        if ((combi[1] + combi[5] + combi[8] + combi[11]) != 26) return false;
        if ((combi[4] + combi[6] + combi[9] + combi[11]) != 26) return false;
        return true;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

