import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n,m,k;
    private static char[][] map;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static int[][] dp;
    private static boolean[][] visit;
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st= new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());

        map = new char[n][m];
        dp = new int[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                dp[i][j] = -1;
            }
        }

        dfs(n - 1, 0, 1);
        System.out.println(result);
    }

    private static void dfs(int r, int c, int v) {
        if (r == 0 && c == m - 1 && v == k) {
            result += 1;
            return;
        }

        if (v > k) return;

        visit[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            if (check(nx, ny) && !visit[nx][ny] && map[nx][ny] != 'T') {
                dfs(nx, ny, v + 1);
            }
        }
        visit[r][c] = false;
    }

    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }
    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}