import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n;
    private static int[][] map;

    private static int[][] dp;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        map = new int[n][n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = stoi(st.nextToken());
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == -1) {
                    dfs(i,j);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println(result);
    }

    private static int dfs(int r, int c) {
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        dp[r][c] = 1;
        int v = 1;
        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (check(nx, ny) && map[r][c] < map[nx][ny]) {
                v = Math.max(v, dfs(nx, ny) + dp[r][c]);
            }
        }
        dp[r][c] = v;
        return dp[r][c];
    }

    private static boolean check(int nx, int ny) {
        return nx >= 0 & nx < n && ny >= 0 && ny < n;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}