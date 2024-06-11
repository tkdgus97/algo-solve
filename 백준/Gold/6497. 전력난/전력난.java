import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    private static int[] parent;
    private static class Edge {
        int s;
        int e;
        int v;

        public Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;

            ArrayList<Edge> pq = new ArrayList<>();

            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            int total = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                pq.add(new Edge(s,e,v));

                total += v;
            }

            Collections.sort(pq, (o1, o2) -> {
                return o1.v - o2.v;
            });

            int minCost = 0;

            for (Edge edge : pq) {
                if (find(edge.e) != find(edge.s)) {
                    union(edge.s, edge.e);
                    minCost += edge.v;
                }
            }

            sb.append(total - minCost).append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int n) {
        if (parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    private static void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);

        if (aP != bP) parent[aP] = bP;
    }

}
