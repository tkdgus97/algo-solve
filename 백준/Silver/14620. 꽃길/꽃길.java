import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static boolean[][] visit;
    private static int n;
    private static int result = Integer.MAX_VALUE;
    private static int[][] com;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        map = new int[n][n];
        visit = new boolean[n][n];
        com = new int[3][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        rec(0);

        System.out.println(result);

    }

    private static void rec(int l) {
        if (l == 3) {
            int sum = 0;
            for (int i = 0; i < 3; i++) {
                sum += map[com[i][0]][com[i][1]];
                for (int j = 0; j < 4; j++) {
                    sum += map[com[i][0] + dx[j]][com[i][1] + dy[j]];
                }
            }

            result = Math.min(result, sum);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (check(i, j) && !visit[i][j]) {
                    visit[i][j] = true;
                    com[l][0] = i;
                    com[l][1] = j;
                    visitCheck(i,j);

                    rec(l + 1);

                    visit[i][j] = false;
                    visitCheck(i,j);
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    private static void visitCheck(int r, int c) {
        for (int i = 0; i < 4; i++) {
            visit[r + dx[i]][c + dy[i]] = !visit[r + dx[i]][c + dy[i]];
        }
    }

    private static boolean check(int r, int c) {
        for (int i = 0; i < 4; i++) {
            if (visit[r + dx[i]][c + dy[i]]) {
                return false;
            }
        }
        return true;
    }
}
