import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int to;
        int w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    private static int n, m;
    private static int start, end, result;
    private static List<Node>[] adj;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());
        int l = 0;
        int r = Integer.MIN_VALUE;

        adj = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            int v = stoi(st.nextToken());

            r = Math.max(v, r);

            adj[s].add(new Node(e, v));
            adj[e].add(new Node(s, v));
        }

        st = new StringTokenizer(br.readLine());
        start = stoi(st.nextToken());
        end = stoi(st.nextToken());
        while (l <= r) {
            int mid = (l + r) / 2;

            result = -1;
            visit = new boolean[n + 1];

            dfs(start, mid);

            if (result != -1) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(r);
    }

    private static void dfs(int now, int limit) {
        if (now == end) {
            result = limit;
            return;
        }

        visit[now] = true;
        for (Node nxt : adj[now]) {
            if (!visit[nxt.to] && nxt.w >= limit) {
                dfs(nxt.to, limit);
            }
        }
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}