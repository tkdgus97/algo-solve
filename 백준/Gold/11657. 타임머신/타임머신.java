import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges.add(new int[] {s,e,v});
        }

        long[] dis = new long[n + 1];
        Arrays.fill(dis, Long.MAX_VALUE);
        dis[1] = 0;

        for (int i = 1; i <= n; i++) {
            for (int[] edge : edges) {
                if (dis[edge[0]] == Long.MAX_VALUE) continue;

                if (dis[edge[1]] > dis[edge[0]] + edge[2]) {
                    dis[edge[1]] = dis[edge[0]] + edge[2];
                    if (i == n) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2 ; i <= n ; i ++) {
            if(dis[i] == Long.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(dis[i]);
            }
        }
    }

}
