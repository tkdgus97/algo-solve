import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static boolean[] isFinish;
    private static boolean[] visit;
    private static int[] adj;
    private static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int t = stoi(st.nextToken());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken());
            result = 0;
            isFinish = new boolean[n + 1];
            adj = new int[n + 1];
            visit = new boolean[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                adj[j] = stoi(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    dfs(i);
                }
            }
            sb.append(n - result).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int v) {
        if (!visit[v]) {
            visit[v] = true;
            dfs(adj[v]);
        }

        if (!isFinish[adj[v]]) {
            int nxt = adj[v];
            result++;
            while (nxt != v) {
                nxt = adj[nxt];
                result++;
            }
        }

        isFinish[v] = true;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}