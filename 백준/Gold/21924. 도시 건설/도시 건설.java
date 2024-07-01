import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int n, m;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });

        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        long total = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            total += v;
            pq.add(new int[]{s, e, v});
        }

        int eCnt = 0;
        long save = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            if (find(now[0]) != find(now[1])) {
                union(now[0], now[1]);
                save += now[2];
                eCnt++;
            }
        }


        if (eCnt != n - 1) {
            System.out.println(-1);
        } else {
            System.out.println(total - save);
        }
    }

    private static int find(int n) {
        if (parents[n] == n) {
            return n;
        }
        return parents[n] = find(parents[n]);
    }

    private static void union(int a, int b) {
        int aP = find(a);
        int bP = find(b);

        if (aP < bP) {
            parents[bP] = aP;
        } else {
            parents[aP] = bP;
        }
    }

}
