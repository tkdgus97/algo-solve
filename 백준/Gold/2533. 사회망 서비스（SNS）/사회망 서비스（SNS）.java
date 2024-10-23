import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<Integer>[] adj;
    private static int[][] dp;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        dp = new int[2][n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1);

        System.out.println(Math.min(dp[0][1], dp[1][1]));
    }

    private static void dfs(int cur) {
        dp[0][cur] = 0;
        dp[1][cur] = 1;
        visit[cur] = true;
        for (Integer next : adj[cur]) {
            if (visit[next]) continue;

            dfs(next);
            dp[0][cur] += dp[1][next];
            dp[1][cur] += Math.min(dp[0][next], dp[1][next]);
        }

    }

}
