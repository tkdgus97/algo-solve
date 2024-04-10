import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new int[] {t,f,w});
        }
        int eCnt = 0;
        int result = 0;
        while (!pq.isEmpty()) {
            int[] e = pq.poll();

            if (find(e[0]) != find(e[1])) {
                union(e[0], e[1]);
                result += e[2];
                eCnt++;
            }

            if (eCnt == V - 1) break;
        }
        System.out.println(result);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
    }
}
