import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Edge {
        int to;
        int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge>[] nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[from].add(new Edge(to, w));
            nodes[to].add(new Edge(from, w));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.w - o2.w;
        });

        boolean[] visit = new boolean[N + 1];

        pq.add(new Edge(1,0));

        long result = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            
            if (visit[e.to]) continue;
            visit[e.to] = true;
            result += e.w;
            for (Edge edge : nodes[e.to]) {
                if (!visit[edge.to]) {
                    pq.add(edge);
                }
            }
        }

        System.out.println(result);
    }

}
