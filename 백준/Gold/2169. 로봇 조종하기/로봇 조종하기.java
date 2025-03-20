import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static int[][] map;
    private static int n, m;
    private static int[][][] dp;
    private static boolean[][] visit;
    private static int[] dx = {0, 1, 0};
    private static int[] dy = {1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m][3];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = stoi(st.nextToken());
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        int result = Math.max(dfs(0, 0, 0), dfs(0, 0, 1));
        System.out.println(result);
    }


    private static int dfs(int r, int c, int dir) {
        if (r == n - 1 && c == m -1) {
            return map[n - 1][m - 1];
        }

        if (dp[r][c][dir] != -1) {
            return dp[r][c][dir];
        }

        visit[r][c] = true;
        int v = -100000000;
        for (int i = 0; i < 3; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (check(nx, ny) && !visit[nx][ny]) {
                v = Math.max(v, dfs(nx, ny, i) + map[r][c]);
            }
        }

        visit[r][c] = false;

        return dp[r][c][dir] = v;
    }

    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
