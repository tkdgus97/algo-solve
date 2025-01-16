import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m, r;
    private static List<int[]>[] adj;
    private static int[] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        r = stoi(st.nextToken());

        items = new int[n + 1];
        adj = new List[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            items[i] = stoi(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            int r = stoi(st.nextToken());

            adj[s].add(new int[]{e, r});
            adj[e].add(new int[]{s, r});
        }

        int maxV = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            boolean[] visit = new boolean[n + 1];
            visit[i] = true;
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
                return Integer.compare(o1[1], o2[1]);
            });
            int v = 0;
            int[] dis = new int[n + 1];
            Arrays.fill(dis, Integer.MAX_VALUE);
            dis[i] = 0;
            q.add(new int[]{i, 0});
            while (!q.isEmpty()) {
                int[] now = q.poll();

                for (int[] next : adj[now[0]]) {
                    if (dis[next[0]] > now[1] + next[1] && now[1] + next[1] <= m) {
                        dis[next[0]] = now[1] + next[1];
                        q.add(new int[]{next[0], now[1] + next[1]});
                    }
                }
            }

            for (int j = 1; j <= n; j++) {
                if (dis[j] != Integer.MAX_VALUE) v += items[j];
            }

            maxV = Math.max(v, maxV);
        }
        System.out.println(maxV);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}