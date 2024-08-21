import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int t;
        int v;

        public Node(int t, int v) {
            this.t = t;
            this.v = v;
        }
    }
    static List<Node>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[t].add(new Node(f, v));
            adj[f].add(new Node(t, v));
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int re1 = 0, re2 = 0;

        re1 += dijk(1,v,n);
        re1 += dijk(v,u,n);
        re1 += dijk(u,n,n);

        re2 += dijk(1,u,n);
        re2 += dijk(u,v,n);
        re2 += dijk(v,n,n);
        if (re1 >- 2000 * 1000 && re2 >= 2000 * 1000) System.out.print(-1);
        else System.out.println(Math.min(re1, re2));
    }

    static int dijk(int start, int end, int n) {
        int[] dis = new int[n + 1];
        Arrays.fill(dis, 2000 * 1000);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[] {start, 0});
        dis[start] = 0;
        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            for (Node node : adj[now[0]]) {
                if (dis[node.t] > now[1] + node.v) {
                    dis[node.t] =  now[1] + node.v;
                    pq.add(new int[] {node.t, now[1] + node.v});
                }
            }
        }

        return dis[end];
    }

}
