import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int[][] map;
    private static int[][] dp;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        dp = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dfs(0,0);

        System.out.println(dp[0][0]);
    }

    private static int dfs(int x, int y) {
        if (x == h - 1 && y == w - 1) {
            return 1;
        }
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if ((nx >= 0 && nx < h) && (ny >= 0 && ny < w) && map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }

}
