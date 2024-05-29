import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int n, m;

    private static int[] big, small;
    private static List<Integer>[] adj;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];

        big = new int[n + 1];
        small = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            adj[s].add(e);
        }

        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];
            big[i] = dfs(i) -1 ;
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (big[i] + small[i] == n - 1) result++;
        }

        System.out.println(result);
    }

    private static int dfs(int cur) {
        int cnt = 0;
        for (int next : adj[cur]) {
            if (visit[next]) continue;
            small[next]++;
            visit[next] = true;
            cnt += dfs(next);
        }
        return cnt + 1;
    }
}
