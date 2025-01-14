import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] info;
    private static int[] order;
    private static boolean[] visit;
    private static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        info = new int[n][10];
        order = new int[9];
        visit = new boolean[10];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                info[i][j] = stoi(st.nextToken());
            }
        }

        visit[1] = true;
        order[3] = 1;
        rec(0);
        System.out.println(maxScore);
    }

    private static void rec(int d) {
        if (d == 9) {
            int re = simul();
            maxScore = Math.max(re, maxScore);
            return;
        }

        if (d == 3) {
            rec(d + 1);
        } else {
            for (int i = 2; i <= 9; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    order[d] = i;
                    rec(d + 1);
                    visit[i] = false;
                }
            }
        }
    }

    private static int simul() {
        int outCnt = 0;
        int idx = 0;
        int score = 0;
        int[] ru;
        for (int i = 0; i < n; i++) {
            ru = new int[4];
            outCnt = 0;
            while (outCnt <= 2) {
                ru[0] = 1;
                int t = info[i][order[idx]];
                if (t == 0) {
                    outCnt++;
                    idx = (idx + 1) % 9;
                    continue;
                }
                for (int j = 3; j >= 0; j--) {
                    if (ru[j] == 1) {
                        if (j + t >= 4) {
                            score += 1;
                        } else {
                            ru[j + t] = 1;
                        }
                        ru[j] = 0;
                    }
                }
                idx = (idx + 1) % 9;
            }
        }
        return score;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}